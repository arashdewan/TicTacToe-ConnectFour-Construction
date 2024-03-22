public final class ConnectFour extends TwoPlayerBoardGame {
    private char RY = 'R';
    private int col;

    private static final int ROWS = 6;
    private static final int COLS = 7;

    public ConnectFour(Player p1, Player p2) {
        super(new char[ROWS][COLS], ROWS*COLS, p1, p2);
    }

    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append("\n");

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                display.append(board[i][j] == 0 ? " " : board[i][j]);
                if (j < COLS -1) {
                    display.append("|");
                }
            }
            display.append("\n");
            if (i < ROWS - 1) {
                display.append("-".repeat(COLS *2 -1)).append("\n");

            }
        }
        for (int j = 0; j < COLS; j++) {
            display.append(j + " ");
        }
        display.append("\n");
        return display.toString();
    }


    protected void askForMove() {
        System.out.println(current.getName() + ", it's your move and you're " + RY + "s.");
        System.out.println("Please choose your move by typing a column number: 0, 1, 2, 3, 4, 5, or 6.");
    }

    protected void receiveMove() {
       col = console.nextInt();
    }

    protected void generateMove() {
        col = random.nextInt(COLS);
    }

    protected boolean validMove() {
        return col >= 0 && col < COLS && board[0][col] == 0;
    }

    protected void applyMove() {
        int row = ROWS -1;
        while (row >= 0 && board[row][col] != 0) {
            row--;
        }
        if (row >= 0) {
            board[row][col] = RY;
        }
        RY = (RY == 'R') ? 'Y' : 'R';
    }

    protected boolean someoneWon() {
        //rows
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j] != 0 && board[i][j] == board[i][j + 1] && board[i][j] == board[i][j + 2] && board[i][j] == board[i][j + 3]) {
                    return true;
                }
            }
        }
        //columns
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] != 0 && board[i][j] == board[i + 1][j] && board[i][j] == board[i + 2][j] && board[i][j] == board[i + 3][j]) {
                    return true;
                }
            }
        }
        //diagonal
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j] != 0 && board[i][j] == board[i + 1][j + 1] && board[i][j] == board[i + 2][j + 2] && board[i][j] == board[i + 3][j + 3]) {
                    return true;
                }
            }
        }

        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = COLS - 1; j >= 3; j--) {
                if (board[i][j] != 0 && board[i][j] == board[i + 1][j - 1] && board[i][j] == board[i + 2][j - 2] && board[i][j] == board[i + 3][j - 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    protected void celebrateMove() {
        System.out.println("That was a winning move!");
        System.out.println(current.getName() + " (" + RY + ") wins!");
    }

    // protected void prepareForNextMove() {
        
    // }
}
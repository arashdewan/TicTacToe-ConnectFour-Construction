public final class TicTacToe extends TwoPlayerBoardGame {
    private char XO = 'X';
    private int row;
    private int col;

    public TicTacToe(Player p1, Player p2) {
        super(new char[][]{{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}}, 9, p1, p2);
    }

    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append("\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                display.append(board[i][j]).append("");
                if (j < 2) {
                    display.append("|");
                }
            }
            if (i < 2) {
                display.append("\n-----\n");
            }
        }
        display.append("\n");
        return display.toString();
    }


    protected void askForMove() {
        System.out.println(current.getName() + ", it's your move and you're " + XO + "s.");
        System.out.println("Please choose your move by typing row col where row is 0, 1, or 2 and col is 0, 1, or 2.");
    }

    protected void receiveMove() {
        row = console.nextInt();
        col = console.nextInt();
    }

    protected void generateMove() {
        row = Math.abs(random.nextInt()) % 3;
        col = Math.abs(random.nextInt()) % 3;
    }

    protected boolean validMove() {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    protected void applyMove() {
        board[row][col] = XO;
        XO = (XO == 'X') ? 'O' : 'X';
    }

    protected boolean someoneWon() {
        for (int i = 0; i < 3; i++) { //rows
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) { //columns
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        //diagonal left to right
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        //diagonal right to left
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        //no one has won
        return false;
    }

    protected void celebrateMove() {
        System.out.println("That was a winning move!");
        System.out.println(current.getName() + " (" + XO + ") wins!"); 
    }

    // protected void prepareForNextMove() {

    // }
}
public class Solver {

    private int[][] board;      // represents gameboard
    private boolean[] found;    // gets reset a lot, stores found values

    /*
     * 0  1  2  3  4  5  6  7  8
     * 9  10 11 12 13 14 15 16 17
     * 18 19 20 21 22 23 24 25 26
     * 27 28 29 30 31 32 33 34 35
     * 36 37 38 39 40 41 42 43 44
     * 45 46 47 48 49 50 51 52 53
     * 54 55 56 57 58 59 60 61 62
     * 63 64 65 66 67 68 69 70 71
     * 72 73 74 75 76 77 78 79 80
     */

    /*
     * X-Axis address: block number mod 9
     * Y-Axis address: block number divided by 9
     */
    
    public Solver(int[][] board) {
        this.board = board;
        // turns given numbers to negatives
        for (int y = 0; y < 9; y++)  {
            for (int x = 0; x < 9; x++) {
                this.board[y][x] = -this.board[y][x];
            }
        }
    }


    /*
     * Uses backtracking to recursively solve the sudoku board.
     */
    public Status solve(int blockNumber) {
        if (blockNumber == 81) { 
            assert 1 == 0;  // throws an error, signifying a completed puzzle
        }
        this.display();     // displays as it gets solved
        if (this.board[getRowNumber(blockNumber)][getColNumber(blockNumber)] < 0) {
            return solve(blockNumber + 1);  // return statement exits before it can be guessed
        }
        for (int guess = 1; guess <= 9; guess++) {
            this.board[getRowNumber(blockNumber)][getColNumber(blockNumber)] = guess;
            // if its a possible config, move to the next number
            if (isPossibleConfig(blockNumber)) {
                solve(blockNumber + 1);
            } else {
                continue;
            }
        }
        this.board[getRowNumber(blockNumber)][getColNumber(blockNumber)] = 0;   // resets to blank if none worked
        return Status.FAIL;
    }

    /*
     * Displays the Sudoku board.
     * Involves clearing the console window.
     */
    public void display() {
        String output = new String();
        for (int[] row : this.board) {
            for (int num : row) {
                output += Math.abs(num) + " ";
            }
            output += "\n";
        }
        
        // clears the console
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.print(output);

    }

    /*
     * Returns true if the board's current configuration is possible.
     */
    private boolean isPossibleConfig(int blockNumber) {
        // if any illegal case is met, return false, otherwise, return true
        return !(this.isIllegalBlock(blockNumber)
              || this.isIllegalRow(blockNumber)
              || this.isIllegalColumn(blockNumber));
    }

    /*
     * Check each of the 9 blocks and see if there are any duplicates.
     * If there are duplicates, return true, else return false. 
     */
    private boolean isIllegalBlock(int blockNumber) {

        // determine which blocks to go for based on progress in the algorithm
        int xBlock = (getColNumber(blockNumber) / 3) * 3;      // either 0, 3, 6
        int yBlock = (getRowNumber(blockNumber) / 3) * 3;      // either 0, 3, 6

        // check each block
        this.found = new boolean[9];
        for (int x = 0; x < 3; x++) {        // either 0, 1, 2
            for (int y = 0; y < 3; y++) {    // either 0, 1, 2
                if(this.illegalFound(this.board[xBlock + y][yBlock + x])) {
                    return true;
                }
            }
        }

        // returns false if nothing illegal
        return false;
    }

    /*
     * Check if any of the 9 columns contains duplicates.
     * Returns true if there are, otherwise false
     */
    private boolean isIllegalColumn(int blockNumber) {

        int colNumber = this.getColNumber(blockNumber);
        
        // check everything in the col
        this.found = new boolean[9];
        for (int y = 0; y < 9; y++) {
            if(this.illegalFound(this.board[y][colNumber])) {
                return true;
            }
        }

        // return false if nothing wrong
        return false;
    }

    /*
     * Check if any of the 9 rows contain duplicates
     * Returns true if there are, otherwise false
     */
    private boolean isIllegalRow(int blockNumber) {

        int rowNumber = getRowNumber(blockNumber);

        // check everything in the row
        this.found = new boolean[9];
        for (int x = 0; x < 9; x++) {
            if(this.illegalFound(this.board[rowNumber][x])) {
                return true;
            }
        }

        // return false if all good
        return false;
    }

    /*
     * Controls checking illegal values
     */
    private boolean illegalFound(int value) {
        if (value == 0) {                       // blank square
            return false;
        } else if (this.found[Math.abs(value)-1] == true) {    // taken square - illegal
            return true;
        } else {                                // otherwise mark as found and go next
            this.found[Math.abs(value)-1] = true;
            return false;
        }
    }

    private int getColNumber(int blockNumber) {
        return blockNumber % 9;
    }

    private int getRowNumber(int blockNumber) {
        return blockNumber / 9;
    }
}

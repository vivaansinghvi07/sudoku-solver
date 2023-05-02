public class Solver {

    private int[][] board;      // represents gameboard

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

    public enum Status {        // stores return for wins and losses
        WIN,
        LOSS
    }
    
    public Solver(int[][] board) {
        this.board = board;
    }

    private boolean isPossibleConfig(int blockNumber) {
        if (this.isIllegalBlock(blockNumber)) {
            return false;
        } else if (this.isIllegalRow(blockNumber)) {
            return false;
        } else if (this.isIllegalColumn(blockNumber)) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Check each of the 9 blocks and see if there are any duplicates.
     * If there are duplicates, return true, else return false. 
     */
    private boolean isIllegalBlock(int blockNumber) {

        // determine which blocks to go for based on progress in the algorithm
        int yAdderLimit = ((blockNumber / 27) + 1) * 3;  // either 3, 6, 9

        // go through each row
        for (int yAdder = 0; yAdder < yAdderLimit; yAdder += 3) {   // either 0, 3, 6

            // go through each column
            for (int xAdder = 0; xAdder < 9; xAdder ++) {           // either 0, 3, 6

                // check each block
                boolean[] found = new boolean[9];
                for (int blockX = 0; blockX < 3; blockX++) {        // either 0, 1, 2
                    for (int blockY = 0; blockY < 3; blockY++) {    // either 0, 1, 2

                        int tempValue = this.board[yAdder + blockY][xAdder + blockX];
                        if (tempValue == 0) {               // blank square
                            continue;
                        } else if (found[tempValue-1]) {    // take square - illegal
                            return true;
                        } else {                            // otherwise mark as found and go next
                            found[tempValue-1] = true;
                        }

                    }
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
        
        // go through every col
        for (int x = 0; x < 9; x++) {
            boolean[] found = new boolean[9];
            for (int y = 0; y < 9; y++) {
                int tempValue = this.board[y][x];
                if (tempValue == 0) {               // blank square
                    continue;
                } else if (found[tempValue-1]) {    // take square - illegal
                    return true;
                } else {                            // otherwise mark as found and go next
                    found[tempValue-1] = true;
                }
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

        // go through every row
        for (int[] row : this.board) {
            boolean[] found = new boolean[9];
            for (int x = 0; x < 9; x++) {
                int tempValue = row[x];
                if (tempValue == 0) {               // blank square
                    continue;
                } else if (found[tempValue-1]) {    // take square - illegal
                    return true;
                } else {                            // otherwise mark as found and go next
                    found[tempValue-1] = true;
                }
            }
        }

        // return false if all good
        return false;
    }
}
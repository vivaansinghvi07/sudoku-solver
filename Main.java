import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println("Enter the grid of numbers, where each row should have 9 numbers seperated by spaces. Represent blank cells with zeroes.\n");

        Solver solver = new Solver(Main.getBoard());
        Status status;
        try {
            status = solver.solve(0);
        } catch (Exception e) {
            status = Status.SUCCESS;
        }
        solver.display();
        if (status == Status.SUCCESS) {
            System.out.println("Here is the solved puzzle!\n");
        } else {
            System.out.println("The puzzle could not be solved.\n");
        }
    }

    public static int[][] getBoard() {
        Scanner scan = new Scanner(System.in);
        int[][] board = new int[9][9];          // should be 9x9
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = scan.nextInt();
                assert num >= 0 && num <= 9;    // make sure numbers entered are valid
                board[i][j] = num;
            }
        }
        scan.close();
        return board;
    }
}

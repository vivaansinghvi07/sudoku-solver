import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Solver solver = new Solver(Main.getBoard());
        try {
            solver.solve(0);
        } catch (Exception e) {
            solver.display();
        }
    }

    public static int[][] getBoard() {
        Scanner scan = new Scanner(System.in);
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = scan.nextInt();
                assert num >= 0 && num <= 9;
                board[i][j] = num;
            }
        }
        scan.close();
        return board;
    }
}

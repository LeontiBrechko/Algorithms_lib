package dp;

/**
 * Created by Leonti on 2016-06-01.
 */
public class MatrixChainMultiplication {
    private static Matrix MatrixMultiply(Matrix A, Matrix B) {
        if (A.columns != B.rows)
            throw new IllegalArgumentException("Invalid matrices` sizes");

        Matrix C = new Matrix(A.rows, B.columns);
        for (int i = 0; i < A.rows; i++) {
            for (int j = 0; j < B.columns; j++) {
                for (int k = 0; k < A.columns; k++) {
                    C.values[i][j] += A.values[i][k] * B.values[k][j];
                }
            }
        }
        return C;
    }

    public static void main(String[] args) {
        Matrix C = MatrixMultiply(new Matrix(new int[][] {{1, 2}, {2, 1}}),
                new Matrix(new int[][] {{1, 2}, {3, 4}}));
        for (int[] c : C.values) {
            for (int i : c) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}

class Matrix {
    int rows;
    int columns;
    int[][] values;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        values = new int[rows][columns];
    }

    public Matrix(int[][] values) {
        this.rows = values.length;
        this.columns = values[0].length;
        this.values = values;
    }
}

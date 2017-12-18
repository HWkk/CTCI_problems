package Chapter1;

import CTCILibrary.AssortedMethods;

/**
 * 若M*N的矩阵中某个元素为0，则将其所在的行与列清零
 */
public class SetZeros {

    /**
     * 第一次遍历，用两个boolean数组分别记录包含0的行和列。
     * 第二次遍历，将所在行和列清零。
     */
    public static void setZeros(int[][] matrix) {

        boolean[] row = new boolean[matrix.length];
        boolean[] col = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(row[i] || col[j])
                    matrix[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int nrows = 10;
        int ncols = 15;
        int[][] matrix = AssortedMethods.randomMatrix(nrows, ncols, 0, 100);
        AssortedMethods.printMatrix(matrix);
        System.out.println();
        setZeros(matrix);
        AssortedMethods.printMatrix(matrix);
    }
}

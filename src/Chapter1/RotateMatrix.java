package Chapter1;

import CTCILibrary.AssortedMethods;

/**
 * 将一个N*N的矩阵中的每个元素旋转90度
 */
public class RotateMatrix {

    /**
     * 从外层开始，按照左->上，下->左，右->下，上->右的顺序赋值
     */
    public static void rotate(int[][] matrix) {

        int length = matrix.length;
        for (int layer = 0; layer < length / 2; layer++) {
            int first = layer, last = length - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;

                int top = matrix[first][i];
                matrix[first][i] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = AssortedMethods.randomMatrix(10, 10, 0, 9);
        AssortedMethods.printMatrix(matrix);
        rotate(matrix);
        System.out.println();
        AssortedMethods.printMatrix(matrix);
    }

}

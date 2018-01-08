package Chapter4;

import CTCILibrary.AssortedMethods;
import CTCILibrary.TreeNode;

/**
 * 判断二叉树是否平衡，平衡定义为：任意一个节点，其两棵子树的高度差不超过1
 */
public class IsBalanced {

    public static boolean isBalanced(TreeNode root) {
        if(checkHeight(root) == -1)
            return false;
        return true;
    }

    public static int checkHeight(TreeNode root) {

        if(root == null) return 0;

        int leftHeight = checkHeight(root.left);
        if(leftHeight == -1)
            return -1;

        int rightHeight = checkHeight(root.right);
        if(rightHeight == -1)
            return -1;

        if(Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        // Create balanced tree
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode root = TreeNode.createMinimalBST(array);
        System.out.println("Root? " + root.data);
        System.out.println("Is balanced? " + isBalanced(root));

        // Could be balanced, actually, but it's very unlikely...
        TreeNode unbalanced = new TreeNode(10);
        for (int i = 0; i < 10; i++) {
            unbalanced.insertInOrder(AssortedMethods.randomIntInRange(0, 100));
        }
        System.out.println("Root? " + unbalanced.data);
        System.out.println("Is balanced? " + isBalanced(unbalanced));
    }
}

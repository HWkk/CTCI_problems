package Chapter4;

import CTCILibrary.TreeNode;

/**
 * 检查一棵二叉树是否是二叉查找树
 */
public class CheckBST {

    /**
     * 中序遍历树，记录前一次遍历到的值
     */
    static Integer preValue = null;
    public static boolean isBST(TreeNode root) {

        if(root == null) return true;

        if(!isBST(root.left))
            return false;

        if(preValue != null && root.data <= preValue) return false;
        preValue = root.data;

        if(!isBST(root.right)) return false;

        return true;
    }

    /**
     * 前序遍历树，通过min和max限制子树的范围来判断是否为二叉查找树
     */
    public static boolean dfs(TreeNode root, Integer min, Integer max) {

        if(root == null) return true;
        if(min != null && root.data <= min || max != null && root.data > max) return false;
        return dfs(root.left, min, root.data) && dfs(root.right, root.data, max);
    }

    public static boolean isBST1(TreeNode root) {
        return dfs(root, null, null);
    }


    public static void main(String[] args) {
        int[] array = {Integer.MIN_VALUE, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE};
        TreeNode node = TreeNode.createMinimalBST(array);
        //node.left.data = 5;
        //node.left.right.data = 3;
        System.out.println(isBST1(node));
    }

}

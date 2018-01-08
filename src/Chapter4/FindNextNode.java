package Chapter4;

import CTCILibrary.TreeNode;

/**
 * 找到二叉查找树中某个节点的下一个节点（即中序后继），假设每个节点可以访问其父结点
 */
public class FindNextNode {

    public static TreeNode findNextNode(TreeNode node) {

        if(node == null) return null;

        if(node.right != null) {
            TreeNode result = node.right;
            while (result.left != null)
                result = result.left;
            return result;
        }

        TreeNode current = node, parent = node.parent;
        while (parent != null && parent.left != current) {
            current = parent;
            parent = parent.parent;
        }
        return parent;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode root = TreeNode.createMinimalBST(array);
        for (int i = 0; i < array.length; i++) {
            TreeNode node = root.find(array[i]);
            TreeNode next = findNextNode(node);
            if (next != null) {
                System.out.println(node.data + "->" + next.data);
            } else {
                System.out.println(node.data + "->" + null);
            }
        }
    }

}

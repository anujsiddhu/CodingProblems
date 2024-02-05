package com.aks.code.dsa;

import java.util.ArrayList;
import java.util.List;

public class LCA3 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);

        TreeNode rl = new TreeNode(5);
        TreeNode rr = new TreeNode(1);
        root.left(rl);
        root.right(rr);
        rl.parent(root);
        rr.parent(root);

        TreeNode rll = new TreeNode(6);
        TreeNode rlr = new TreeNode(2);
        rl.left(rll);
        rll.right(rl);
        rlr.parent(rl);

        TreeNode rrl = new TreeNode(0);
        TreeNode rrr = new TreeNode(8);
        rr.left(rrl);
        rr.right(rrr);
        rrl.parent(rr);
        rrr.parent(rr);


        TreeNode rlrl = new TreeNode(7);
        TreeNode rlrr = new TreeNode(4);
        rlr.left(rlrl);
        rlr.right(rlrr);
        rlrl.parent(rlr);
        rlrr.parent(rlr);

//        inorder(root);
        TreeNode p = rrr;
        TreeNode q = rlrl;
        TreeNode lca = lca3(root, p, q);
        System.out.println(p.val() + " , " + q.val() + " = " + (lca != null ? lca.val() : null));
    }

    private static TreeNode lca3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        List<TreeNode> pl = new ArrayList<>();
        List<TreeNode> ql = new ArrayList<>();
        TreeNode ps = p;
        TreeNode qs = q;
        while (ps != null || qs != null) {
            if (ps != null) {
                pl.add(ps);
                ps = ps.parent();
            }
            if (qs != null) {
                ql.add(qs);
                qs = qs.parent();
            }
        }

        int size = Integer.min(pl.size(), ql.size());
        int i = pl.size() - 1;
        int j = ql.size() - 1;
        TreeNode last = null;
        while (size-- > 0) {
            TreeNode pe = pl.get(i);
            TreeNode qe = ql.get(j);
            if (pe == qe) {
                last =  pe;
            }
            i--;
            j--;
        }
        return last;


    }

    private static void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left());
            System.out.print(root.val() + " ");
            inorder(root.right());
        }
    }


}

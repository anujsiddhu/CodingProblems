package com.aks.code.dsa;

import java.util.Arrays;
import java.util.TreeSet;

// https://leetcode.com/problems/sliding-window-median/description/
public class SlidingWindowMedian {
    public static void main(String[] args) {
        Solution solution = new Solution();
        double[] res = solution.medianSlidingWindow(new int[]{1, 2, 3, 4, 2, 3, 1, 4, 2}, 1);
        System.out.println(Arrays.toString(res));
        res = solution.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(res));
        res = solution.medianSlidingWindow(new int[]{2147483647,2147483647}, 2);
        System.out.println(Arrays.toString(res));
    }

    static class Solution {
        public double[] medianSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            double[] res = new double[n - k + 1];
            TreeSet<Node> max = new TreeSet<>((n1, n2) -> {
                if (n1.val == n2.val) {
                    return n1.index - n2.index;
                }
                return Long.compare(n2.val , n1.val);
            });
            TreeSet<Node> min = new TreeSet<>((n1, n2) -> {
                if (n1.val == n2.val) {
                    return n1.index - n2.index;
                }
                return Long.compare(n1.val , n2.val);
            });

            for (int i = 0; i < k; i++) {
                Node nn = new Node(i, nums[i]);
                addNewNode(max, min, nn);
            }

            res[0] = max.size() == min.size() ? (max.first().val + min.first().val) / 2.0 : max.first().val * 1.0;

            int ri = 1;
            for (int i = 1; i <= n - k; i++) {
                int j = i + k - 1;
                Node pn = new Node(i - 1, nums[i - 1]);
                if (min.contains(pn)) {
                    min.remove(pn);
                    Node fx = max.first();
                    max.remove(fx);
                    min.add(fx);
                } else {
                    max.remove(pn);
                }
                Node nn = new Node(j, nums[j]);
                addNewNode(max, min, nn);
                res[ri++] = max.size() == min.size() ? (max.first().val + min.first().val) / 2.0 : max.first().val * 1.0;
            }

            return res;
        }

        private static void addNewNode(TreeSet<Node> max, TreeSet<Node> min, Node nn) {
            if (max.isEmpty()) {
                max.add(nn);
            } else if (min.isEmpty()) {
                if (max.first().val > nn.val) {
                    Node fx = max.first();
                    max.remove(fx);
                    min.add(fx);
                    max.add(nn);
                } else {
                    min.add(nn);
                }
            } else {
                if (nn.val <= max.first().val) {
                    if (max.size() > min.size()) {
                        Node fx = max.first();
                        max.remove(fx);
                        min.add(fx);
                    }
                    max.add(nn);
                } else if (nn.val < min.first().val) {
                    if (max.size() > min.size()) {
                        min.add(nn);
                    } else {
                        max.add(nn);
                    }
                } else {
                    if (min.size() >= max.size()) {
                        Node fn = min.first();
                        min.remove(fn);
                        max.add(fn);
                    }
                    min.add(nn);
                }
            }
        }

        static class Node {
            int index;
            long val;

            public Node(int i, long v) {
                val = v;
                index = i;
            }

            @Override
            public boolean equals(Object o) {
                Node n1 = (Node) o;
                return this.index == n1.index;
            }

            @Override
            public int hashCode() {
                return index;
            }

            @Override
            public String toString() {
                return " " + val;
            }
        }
    }
}

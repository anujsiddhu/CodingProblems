package com.aks.code.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class SkylineDrawing {
    /**
     * Represents either start or end of building
     */
    static class SKLNode implements Comparable<SKLNode> {
        int x;
        boolean isStart;
        int height;

        @Override
        public int compareTo(SKLNode o) {
            if (this.x != o.x) {
                return this.x - o.x;
            } else {
                // if both are start or end, sort desc based on height
                return (this.isStart ? -this.height : this.height) - (o.isStart ? -o.height : o.height);
            }
        }

        @Override
        public String toString() {
            return "SKLNode{" +
                    "x=" + x +
                    ", isStart=" + isStart +
                    ", height=" + height +
                    '}';
        }
    }

    private List<int[]> getSkyline(int[][] buildings) {

        SKLNode[] buildingPoints = new SKLNode[buildings.length * 2];
        int index = 0;
        for (int[] building : buildings) {
            buildingPoints[index] = new SKLNode();
            buildingPoints[index].x = building[0];
            buildingPoints[index].isStart = true;
            buildingPoints[index].height = building[2];

            buildingPoints[index + 1] = new SKLNode();
            buildingPoints[index + 1].x = building[1];
            buildingPoints[index + 1].isStart = false;
            buildingPoints[index + 1].height = building[2];
            index += 2;
        }
        Arrays.sort(buildingPoints);
//        System.out.println(Arrays.toString(buildingPoints));

        TreeMap<Integer, Integer> queue = new TreeMap<>();
        queue.put(0, 1);
        int prevMaxHeight = 0;
        List<int[]> result = new ArrayList<>();
        for (SKLNode buildingPoint : buildingPoints) {
            if (buildingPoint.isStart) {
                queue.compute(buildingPoint.height, (key, value) -> {
                    if (value != null) {
                        return value + 1;
                    }
                    return 1;
                });
            } else {
                queue.compute(buildingPoint.height, (key, value) -> {
                    if (value == 1) {
                        return null;
                    }
                    return value - 1;
                });
            }
            System.out.println(buildingPoint + " " + queue);
            int currentMaxHeight = queue.lastKey();
            if (prevMaxHeight != currentMaxHeight) {
                result.add(new int[] {buildingPoint.x, currentMaxHeight});
                prevMaxHeight = currentMaxHeight;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] buildings = {{0, 2, 3}, {2, 5, 3}};
        int[][] buildings2 = {{2, 9, 10},
                {3, 6, 15},
                {5, 12, 12},
                {13, 16, 10},
                {15, 17, 5}};

        SkylineDrawing sd = new SkylineDrawing();
        List<int[]> skl1 = sd.getSkyline(buildings2);
        skl1.forEach(cp -> System.out.println(cp[0] + " " + cp[1]));

//        List<int[]> skl2 = sd.getSkyline2(0, buildings2.length - 1, buildings2);
//        skl2.forEach(cp -> System.out.println(cp[0] + " " + cp[1]));
    }

    // https://javabypatel.blogspot.com/2016/08/skyline-problem-solution-in-java.html
    private List<int[]> getSkyline2(int low, int high, int[][] skyscraper) {
        int mid = low + (high - low) / 2;

        List<int[]> skyLineDiagonalPoints = new ArrayList<>();
        if (low > high) {
            return skyLineDiagonalPoints;

        } else if (low == high) {
            int[] point1 = new int[2];
            point1[0] = skyscraper[low][0];
            point1[1] = skyscraper[low][2];

            int[] point2 = new int[2];
            point2[0] = skyscraper[low][1];
            //point2[1] = 0;

            skyLineDiagonalPoints.add(point1);
            skyLineDiagonalPoints.add(point2);

            return skyLineDiagonalPoints;

        } else {
            List<int[]> skyline1 = getSkyline2(low, mid, skyscraper);
            List<int[]> skyline2 = getSkyline2(mid + 1, high, skyscraper);
            return mergeSkyLines(skyline1, skyline2);
        }
    }

    private List<int[]> mergeSkyLines(List<int[]> skyline1, List<int[]> skyline2) {

        List<int[]> mergedSkyLineDiagonalPoints = new ArrayList<>();
        int lastHeightSkyScraper1 = 0;
        int lastHeightSkyScraper2 = 0;

        while (!skyline1.isEmpty() && !skyline2.isEmpty()) {
            if (skyline1.get(0)[0] < skyline2.get(0)[0]) {

                int maxHeight = skyline1.get(0)[1];
                if (skyline1.get(0)[1] < lastHeightSkyScraper2) {
                    maxHeight = lastHeightSkyScraper2;
                }
                lastHeightSkyScraper1 = skyline1.get(0)[1];
                mergedSkyLineDiagonalPoints.add(new int[]{skyline1.get(0)[0], maxHeight});
                skyline1.remove(0);

            } else if (skyline1.get(0)[0] > skyline2.get(0)[0]) {

                int maxHeight = skyline2.get(0)[1];

                if (skyline2.get(0)[1] < lastHeightSkyScraper1) {
                    maxHeight = lastHeightSkyScraper1;
                }
                lastHeightSkyScraper2 = skyline2.get(0)[1];
                mergedSkyLineDiagonalPoints.add(new int[]{skyline2.get(0)[0], maxHeight});
                skyline2.remove(0);

            } else {
                int maxHeight = Math.max(skyline1.get(0)[1], skyline2.get(0)[1]);
                mergedSkyLineDiagonalPoints.add(new int[]{skyline1.get(0)[0], maxHeight});

                lastHeightSkyScraper1 = skyline1.get(0)[1];
                lastHeightSkyScraper2 = skyline2.get(0)[1];

                skyline1.remove(0);
                skyline2.remove(0);
            }
        }

        while (!skyline1.isEmpty()) {
            mergedSkyLineDiagonalPoints.add(new int[]{skyline1.get(0)[0], skyline1.get(0)[1]});
            skyline1.remove(0);
        }
        while (!skyline2.isEmpty()) {
            mergedSkyLineDiagonalPoints.add(new int[]{skyline2.get(0)[0], skyline2.get(0)[1]});
            skyline2.remove(0);
        }

        //Remove Points falling at same height
        for (int i = 0; i < mergedSkyLineDiagonalPoints.size(); i++) {
            int j = i + 1;
            while (j < mergedSkyLineDiagonalPoints.size() && mergedSkyLineDiagonalPoints.get(j)[1] == mergedSkyLineDiagonalPoints.get(i)[1]) {
                mergedSkyLineDiagonalPoints.remove(j);
                j++;
            }
        }

        return mergedSkyLineDiagonalPoints;
    }
}
package Algorithms.src.algorithms.stackqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Date 20/11/2018
 * @author tiwariabhishek
 *
 * Given skyline of a city merge the buildings
 *
 * Time complexity is O(nlogn)
 * Space complexity is O(n)
 *
 * References
 * https://leetcode.com/problems/the-skyline-problem/
 * https://leetcode.com/discuss/67091/once-for-all-explanation-with-clean-java-code-nlog-time-space
 */

class BuildingPoint implements Comparable<BuildingPoint> {
    int x,h;
    boolean isStart;

    public BuildingPoint(boolean isStart, int x, int h) {
        this.isStart = isStart;
        this.x = x;
        this.h = h;
    }

    public int compareTo(BuildingPoint b) {
        //first compare by x.
        //If they are same then use this logic
        //if two starts are compared then higher height building should be picked first
        //if two ends are compared then lower height building should be picked first
        //if one start and end is compared then start should appear before end
        if(this.x == b.x) {
            return (this.isStart? -this.h:this.h) - (b.isStart? -b.h:b.h);
        }
        return this.x - b.x;
    }
}

public class Skyline {

    private List<int[]> getSkyline(int[][] buildings) {
        BuildingPoint[] buildingPoints = new BuildingPoint[buildings.length<<1];
        int index = 0;
        for (int[] building: buildings) {
            buildingPoints[index] = new BuildingPoint(true, building[0], building[2]);
            buildingPoints[index+1] = new BuildingPoint(false, building[1], building[2]);
            index += 2;
        }
        Arrays.sort(buildingPoints);
        TreeMap<Integer, Integer> queue = new TreeMap<>();
        queue.put(0, 1);
        int prevMaxHeight = 0;
        List<int[]> results = new ArrayList<>();
        for(BuildingPoint buildingPoint: buildingPoints) {
            if (buildingPoint.isStart) {
                queue.compute(buildingPoint.h, (key, value) -> {
                    if(value != null) {
                        return value + 1;
                    }
                    return 1;
                });
            } else {
                queue.compute(buildingPoint.h, (key, value) -> {
                    if(value > 1) {
                        return value - 1;
                    }
                    return null;
                });
            }
            int currentMaxHeight = queue.lastKey();
            if(currentMaxHeight != prevMaxHeight) {
                results.add(new int[] {buildingPoint.x, currentMaxHeight});
                prevMaxHeight = currentMaxHeight;
            }
        }
        return results;
    }

    public static void main(String args[]) {
        Skyline skyline = new Skyline();
        int[][] buildings = {{1,3,4},{3,4,4},{2,6,2},{8,11,4}, {7,9,3},{10,11,2}};
        List<int[]> criticalPoints = skyline.getSkyline(buildings);
        criticalPoints.forEach(cp -> System.out.println(cp[0] + " " + cp[1]));
    }
}

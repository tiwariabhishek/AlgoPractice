package algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * Date 15/01/2018
 *
 * @author tiwariabhishek
 * <p>
 * Given different dimensions and unlimited supply of boxes for each dimension, stack boxes
 * on top of each other such that it has maximum height but with caveat that length and width
 * of box on top should be strictly less than length and width of box under it. You can
 * rotate boxes as you like.
 * <p>
 * 1) Create all rotations of boxes such that length is always greater or equal to width
 * 2) Sort boxes by base area in non increasing order (length * width). This is because box
 * with more area will never ever go on top of box with less area.
 * 3) Take T[] and result[] array of same size as total boxes after all rotations are done
 * 4) Apply longest increasing subsequence type of algorithm to get max height.
 * <p>
 * If n number of dimensions are given total boxes after rotation will be 3n.
 * So space complexity is O(n)
 * Time complexity - O(nlogn) to sort boxes. O(n^2) to apply DP on it So really O(n^2)
 * <p>
 * References
 * http://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem/
 * http://people.cs.clemson.edu/~bcdean/dp_practice/
 */

public class BoxStacking {

    private int maxHeight(Dimension input[]) {
        Dimension[] allRotations = new Dimension[input.length * 3];
        createAllDimensions(input, allRotations);
        Arrays.sort(allRotations);
        int[] table = new int[allRotations.length];
        int[] result = new int[allRotations.length];
        for (int i = 0; i < table.length; i++) {
            table[i] = allRotations[i].height;
            result[i] = i;
        }
        for (int i = 1; i < allRotations.length; i++) {
            for (int j = 0; j < i; j++) {
                if (allRotations[i].length < allRotations[j].length && allRotations[i].width < allRotations[j].width) {
                    if (table[j] + allRotations[i].height > table[i]) {
                        table[i] = table[j] + allRotations[i].height;
                        result[i] = j;
                    }
                }
            }
        }
        int maxIndex = getMaxIndex(table);
        printBoxStack(allRotations, result, maxIndex);
        return table[maxIndex];
    }

    private void printBoxStack(Dimension[] rotations, int[] result, int index) {
        int temp = index;
        do {
            System.out.println(rotations[temp].length + " " + rotations[temp].width + " " + rotations[temp].height);
            temp = result[temp];
        } while (temp != result[temp]);
        System.out.println(rotations[temp].length + " " + rotations[temp].width + " " + rotations[temp].height);
    }

    private int getMaxIndex(int[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) maxIndex = i;
        }
        return maxIndex;
    }

    //create all rotations of boxes, always keeping length greater or equal to width
    private void createAllDimensions(Dimension[] input, Dimension[] allRotations) {
        int iter = 0;
        for (int i = 0; i < input.length; i++) {
            allRotations[iter++] = Dimension.createDimension(input[i].length, input[i].width, input[i].height);
            allRotations[iter++] = Dimension.createDimension(input[i].width, input[i].length, input[i].height);
            allRotations[iter++] = Dimension.createDimension(input[i].height, input[i].width, input[i].length);
        }
    }

    public static void main(String[] args) {
        Dimension[] dimension = {new Dimension(3, 2, 5),
                new Dimension(1, 2, 4)};
        BoxStacking bs = new BoxStacking();
        System.out.println(bs.maxHeight(dimension));
    }
}

class Dimension implements Comparable<Dimension> {

    int length;
    int width;
    int height;

    public Dimension(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Dimension() {
    }

    static Dimension createDimension(int height, int side1, int side2) {
        Dimension d = new Dimension();
        d.height = height;
        if (side1 >= side2) {
            d.length = side1;
            d.width = side2;
        } else {
            d.length = side2;
            d.width = side1;
        }
        return d;
    }

    @Override
    public int compareTo(Dimension d) {
        return d.length * d.width - this.length * this.width;
    }
}

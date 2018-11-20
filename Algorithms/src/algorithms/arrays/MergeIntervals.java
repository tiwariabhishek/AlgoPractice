package Algorithms.src.algorithms.arrays;

import java.util.ArrayList;

/**
 * Date 20/11/2018
 * @author tiwariabhishek
 *
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Time complexity is O(n)
 * Space complexity is O(n)
 *
 * References
 * https://www.interviewbit.com/problems/merge-intervals/
 */


class Interval {
    int start;
    int end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
public class MergeIntervals {

    private boolean doesOverlap (Interval a, Interval b) {
        return !(Math.min(a.end, b.end) < Math.max(a.start, b.start));
    }
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<>();
        int sz = intervals.size();
        if (sz == 0) result.add(newInterval);
        else {
            if (intervals.get(0).start > newInterval.end || intervals.get(sz - 1).end < newInterval.start) {
                if (intervals.get(0).start > newInterval.end) {
                    result.add(newInterval);
                }
                for (Interval interval: intervals) {
                    result.add(interval);
                }
                if (intervals.get(sz - 1).end < newInterval.start) {
                    result.add(newInterval);
                }
            } else {
                int i = 0;
                boolean intervalAdded = false;
                for (Interval interval: intervals) {
                    if (doesOverlap(newInterval, interval)) break;
                    if (newInterval.end < interval.start && !intervalAdded) {
                        result.add(newInterval);
                        intervalAdded = true;
                    }
                    result.add(interval);
                    i++;
                }
                if (!intervalAdded) {
                    int start = i;
                    while (i < sz) {
                        i++;
                        if (i<sz && !doesOverlap(intervals.get(i), newInterval)) break;
                    }
                    i--;
                    result.add(new Interval(Math.min(intervals.get(start).start, newInterval.start),
                            Math.max(intervals.get(i).end, newInterval.end)));
                    while (++i < sz) {
                        result.add(intervals.get(i));
                    }
                }
            }
        }
        return result;
    }

    public static void main(String args[]) {
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(3,5));
        intervals.add(new Interval(6,7));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(12,16));
        MergeIntervals mergeIntervals = new MergeIntervals();
        mergeIntervals.insert(intervals, new Interval(4,9))
                .forEach(interval -> System.out.println(interval.start + " " + interval.end));

    }
}

package LeetCode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 18190
 * @Date: 2021/6/18  16:35
 * @VERSION 1.0
 */
public class EraseOverlapIntervals435 {

    public static void main(String[] args) {
        System.out.println(new EraseOverlapIntervals435().eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
    }
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length == 0)return 0;
//        Arrays.sort(intervals,(int[] o1,int [] o2)-> {return o1[1] - o2[1];});
        Arrays.sort(intervals, Comparator.comparingInt((int[] o) -> o[1]));
        int end = intervals[0][1];
        int ans = 0;
        for (int i = 1; i < intervals.length; i++){
            if(intervals[i][0] >= end){
                end = intervals[i][1];
            }else {
               ans++;
            }

        }
        return ans;
    }
}

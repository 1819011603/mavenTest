package LeetCode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 18190
 * @Date: 2021/6/18  17:00
 * @VERSION 1.0
 */
public class FindMinArrowShots452 {
    public static void main(String[] args) {
        System.out.println(new FindMinArrowShots452().findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}));
        System.out.println(new FindMinArrowShots452().findMinArrowShots1(new int[][]{{10,16},{2,8},{1,6},{7,12}}));
    }
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0)return 0;
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        int end = points[0][1];
        int count = 1;
        for (int i = 1; i < points.length; i++){
             if(points[i][0] > end){
                 count++;
                 end = points[i][1];
             }else {
                 end = Math.min(points[i][1],end);
             }
        }
        return count;
    }
    public int findMinArrowShots1(int[][] points) {
        if(points.length == 0)return 0;
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int end = points[0][1];
        int count = 1;
        for (int i = 1; i < points.length; i++){
            if(points[i][0] > end){
                count++;
                end = points[i][1];
            }
        }
        return count;
    }
}

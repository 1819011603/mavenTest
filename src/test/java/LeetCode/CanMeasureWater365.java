package LeetCode;

import javafx.scene.effect.SepiaTone;

import java.util.*;

/**
 * @author 18190
 * @Date: 2021/6/28  22:15
 * @VERSION 1.0
 */
public class CanMeasureWater365 {
    public static void main(String[] args) {
        System.out.println(new CanMeasureWater365().canMeasureWater(3,5,4));
    }

        public boolean canMeasureWater(int x, int y, int z) {
            Queue<int[]> stack = new LinkedList<>();
            stack.add(new int[]{0, 0});
            Set<Long> seen = new HashSet<>();
            while (!stack.isEmpty()) {
                if (seen.contains(hash(stack.peek()))) {
                    stack.poll();
                    continue;
                }
                seen.add(hash(stack.peek()));

                int[] state = stack.poll();
                int remain_x = state[0], remain_y = state[1];
                if (remain_x == z || remain_y == z || remain_x + remain_y == z) {
                    return true;
                }
                // 把 X 壶灌满。
                stack.add(new int[]{x, remain_y});
                // 把 Y 壶灌满。
                stack.add(new int[]{remain_x, y});
                // 把 X 壶倒空。
                stack.add(new int[]{0, remain_y});
                // 把 Y 壶倒空。
                stack.add(new int[]{remain_x, 0});
                // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
                stack.add(new int[]{remain_x - Math.min(remain_x, y - remain_y), remain_y + Math.min(remain_x, y - remain_y)});
                // 把 Y 壶的水灌进 X 壶，直至灌满或倒空。
                stack.add(new int[]{remain_x + Math.min(remain_y, x - remain_x), remain_y - Math.min(remain_y, x - remain_x)});
            }
            return false;
        }

        public long hash(int[] state) {
            return (long) state[0] * 1000001 + state[1];
        }



}

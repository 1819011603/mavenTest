package LeetCode;

import java.util.*;

/**
 * @author 18190
 * @Date: 2021/6/26  21:03
 * @VERSION 1.0
 */
public class SlidingPuzzle773 {
    public static void main(String[] args) {
        System.out.println(new SlidingPuzzle773().slidingPuzzle(new int[][]{{1,2,3},{5,4,0}}));
    }
    public int getTarget(int[][] board){
        int target = 0;
        for (int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                target = target*10+board[i][j];
            }
        }
        return target;
    }
    int pos;
    public int[][] getPuzzle(int now){
        int[][] ans = new int[2][3];
        int t = 5;
        pos = 0;
        while (now > 0){
            ans[t/3][t%3] = now%10;
            if(now% 10 == 0){
                pos = t;
            }
            t--;
            now/=10;
        }

//        System.out.println("pos:" + pos);
        return ans;

    }

    public int getNext(int[][] now,int i){
        if(i == 0){
            if(pos%3 == 0)return -1;
            int a = pos/3,b = pos%3;
            int temp = now[a][b];
            now[a][b] = now[a][b-1];
            now[a][b-1] = temp;

        }else if(i == 1){
            if(pos/3 == 1)return -1;
            int a = pos/3,b = pos%3;
            int temp = now[a][b];
            now[a][b] = now[a+1][b];
            now[a+1][b] = temp;
        }else if(i == 2){
            if(pos%3 == 2)return -1;
            int a = pos/3,b = pos%3;
            int temp = now[a][b];
            now[a][b] = now[a][b+1];
            now[a][b+1] = temp;
        }else {
            if(pos/3 == 0)return -1;
            int a = pos/3,b = pos%3;
            int temp = now[a][b];
            now[a][b] = now[a-1][b];
            now[a-1][b] = temp;
        }
        return getTarget(now);
    }
    public int slidingPuzzle(int[][] board){
        int target = getTarget(board);
        int ans = 123450;
        if(target == ans)return 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        boolean[] s1 = new boolean[543211];
        boolean[] s2 = new boolean[543211];
        s1[target] = true;
        q1.add(target);
        q2.add(ans);
        s2[ans] = true;
        Queue<Integer> qt = new LinkedList<>(),t1;
        boolean[] st = null;
        int now = 0,next;

        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()){
            if(q1.size() > q2.size()){
                t1 = q1;
                q1 = q2;
                q2 = t1;
                st = s1;
                s1 = s2;
                s2 = st;
            }

            while (!q1.isEmpty()){
                now = q1.poll();
                int[][] cur = getPuzzle(now);
                System.out.println("now: " + now);
                for(int i = 0; i < 4; i++){

                    next = getNext(cur,i);
                    if(next!=-1)
                    getNext(cur,i);
                    System.out.println("next: " + next);
                    if(next == -1|| s1[next])continue;
                    if(s2[next])return step+1;
                    if (s1[123540] || s1[124350] || s1[132450] || s1[213450])return -1;
                    qt.add(next);
                    s1[next] = true;
                }
            }
            t1 = q1;
            q1 = qt;
            qt = t1;
            step++;
        }
        return -1;

    }
}

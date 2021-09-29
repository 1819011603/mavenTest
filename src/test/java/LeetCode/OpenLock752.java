package LeetCode;

import java.util.*;

/**
 * @author 18190
 * @Date: 2021/6/25  16:25
 * @VERSION 1.0
 */
public class OpenLock752 {
    public static void main(String[] args) {

        System.out.println(new OpenLock752().openLock(new String[]{"0201","0101","0102","1212","2002"},"0202"));
        System.out.println(new OpenLock752().openLock1(new String[]{"0201","0101","0102","1212","2002"},"0202"));
    }

    // 双端bfs
    public int openLock1(String[] deadends, String target){
        Queue<Integer> q1 = new LinkedList<>();
        int[] v1 = new int[10000];
        int[] v2 = new int[10000];
        int di,end = Integer.parseInt(target);
        for (String s: deadends){
            di = Integer.parseInt(s);
            v1[di] = v2[di] = -1;
        }
        if(v1[0]==-1 || v1[end] == -1)return -1;
        if(end == 0)return 0;
        Queue<Integer> q2 = new LinkedList<>();

        q1.add(0);
        v1[0] = 1;
        q2.add(end);
        v2[end] = 1;
        Queue<Integer> qt = null;
        int[] vt;
        int step = 0;
        int []num = new int[]{1,10,100,1000};
        while (!q1.isEmpty() && !q2.isEmpty()){
            if(q1.size() > q2.size()){
//                qt = q1; 可省  因为qt == q1  qt==null时并不会进入此if语句内
                q1 = q2;
                q2 = qt;
                vt = v1;
                v1 = v2;
                v2 = vt;
            }
            qt = new LinkedList<>();
            while (!q1.isEmpty()){
                int now = q1.poll();
                for (int i = 0; i < 4; i++){
                    int point = (now/num[i])%10;
                    for (int j = 9;j<=11; j+=2){
                        int ans = now+ ((point+j)%10-point)*num[i];

                        if (v1[ans]!= 0)continue;
                        if(v2[ans] == 1)return step+1;
                        qt.add(ans);
                        v1[ans] = 1;
                    }
                }
            }
            q1 = qt;
            step++;


        }
        return -1;
    }

    // bfs
    public int openLock(String[] deadends, String target) {

        Set<String> dp = new HashSet<>(128);
        dp.addAll(Arrays.asList(deadends));
        if(dp.contains("0000") || dp.contains(target)){
            return -1;
        }

        if ("0000".equals(target))return 0;

        Queue<String> q = new LinkedList<>();
        q.add("0000");
        int step = 0;
        String status,num,temp;
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++){
                status = q.poll();
                if(target.equals(status)){
                    return step;
                }
                char[] array =status.toCharArray();
                for (int j = 0; j < 4; j++){
                    char c = array[j];
                    array[j] = c == '9'?'0':(char) (c+1);
                    if(!dp.contains(temp = new String(array))){
                        q.add(temp);
                        dp.add(temp);
                    }
                    array[j] = c == '0'?'9':(char) (c-1);
                    if(!dp.contains(temp = new String(array))){
                        q.add(temp);
                        dp.add(temp);
                    }
                    array[j] = c;


                }

            }
            step++;



        }


        return  -1;

    }



}

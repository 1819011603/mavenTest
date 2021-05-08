package Algorithm;

import java.util.Arrays;

// kmp 解决的是一个串中是否含有该子串的问题
// 暴力法 O(n*m)  kmp O(n)  求next数组只需要O(m)的时间复杂度
// 最长前后缀   ababababd  ababd  c!=d  i = 4 j = 4  此时不用将j = 0, i = 1(此时i回退了 这让前面的功夫成了无用功 其实回退的字母已经访问过了)
// 利用d的最长前后缀信息  d最长前后缀为ab == ab aba ！= bab 所以前后缀长度为2  直接 i = 4 与 j  = 2 进行比较 a!=d  i = 4 与 j = 0比较 直到出结果


// 假设 子串的 0-j(0到j位置均匹配) a   此时假设 主串有一段 i-(i+j) d 序列 此时 a!=d 假设a的最长前后缀为k(并且next[k] == 0) 则此时子串0-k-1内任意一个字母开始内不会有与主串匹配了 此时0-k-1 (j-k+1)-(j) 相等 此时直接子串从k位置与字母d进行匹配即可
// 为什么子串0-k-1 内不会有与主串匹配了呢？  可以假设0-(k-1)中某个字母（假设为u,u<k）开始进行比较会有在主串会有子串完全相同的序列
// 如果假设(k <= j/2) 则u-j 会与 0-(j-u)相等 最长前后缀就是（j-u) （k <= j/2 && u < k ==>  j-u > k）我们已经假设a的最长前后缀为k了 不可能存在一个比k更大的最长前后缀了  与题意相矛盾
// 如果假设 (k>j/2)   则u-j 会与 0-(j-u)相等  如果 u <（j-k+1）<k 同上 因为j-u+1>k 与题意相矛盾   如果 (j-k+1) <=u < k 此时 u-(k-1） 与 (j-k+1)- (j-u) 相等 并且(j-k+1)- (j-u) 与 0-(k-u-1)也相等  在0-(k-1)可得, next[k] = u-k >0 与题意相矛盾
// 所以我们假设有问题 在最长前后缀为k 且 next[k] == 0 的情况下 0-(k-1)的任何字母开始都不会有该子串的匹配了
public class KMP  {
    public static int[] getNext1(String s){
        if(s == null)return null;
        char[] chars = s.toCharArray();
        int[] next = new int[s.length()+1];
        next[1] = 0;
        int i = 1;
        int j = 0;
        while (i < s.length()){
            if(j == 0 || chars[i-1] == chars[j-1]){
                ++i;
                ++j;
                next[i] = j;
            }else {
                j = next[j];
            }


        }
        System.out.println(Arrays.toString(next));
        return next;

    }


    // next[0] == -1; next[2]==0; next[3] = chars[1] == chars[0]?1:0;  chars[i+1] =  char[i] == char[next[i]]? next[i]+1: j = next[next[i]];
    public static int[] getNext(String s){
        if(s == null)return null;
        char[] chars = s.toCharArray();
        int[] next = new int[s.length()];
        next[0] = -1;
        int i = 0; // i
        int j = -1;// j == next[i]
        // 根据i 和 next[i] 求next[i+1]  i+1 < s.length() ===> i < s.length()-1;
        while (i < s.length()-1){
            // j == -1 ---> next[i+1]=0  <===> next[i+1] = j+1;
            // next[i+1]= char[i] == char[next[i]]? next[i]+1  <=====> next[i+1] =  char[i] == char[j]? j+1;
            if(j == -1 || chars[i] == chars[j]){
                ++i;
                ++j;
                // 还可以减少不必要的判断 ababa  i = 4 a不匹配的话 前面那个a也没必要再比较了
                next[i] = j;
            }else {
                // 现在我们还是要去找chars[i+1]它的最长前后缀 此时已知 j 是 chars[i]的最长前后缀 （0-j-1） == ((i-j) - (i-1))
                // 如果 chars[j] == chars[i] 所以 next[i+1] = j+1;
                // 如果 chars[i] != chars[j] chars[j]的最长前后缀为next[j] 则 0-(next[j]-1) == （j-next[j]）-（j-1）
                // 因为（0-j-1） == ((i-j) - (i-1)) ==>  (i-j)-(i-j+next[j]-1) ==  (i-next[j])-(i-1) ===>   0-(next[j]-1) == (i-next[j])-(i-1)
                // 如果chars[i] == chars[next[j]] 则next[i+1] = next[j]+1; 否则 递归下去 chars[0] != chars[i], j= next[0] = -1 此时 next[i+1] = 0;

                j = next[j];
            }


        }
//        System.out.println(Arrays.toString(next));
        return next;

    }

    public static int[] getNextVal(String s){
        if(s == null)return null;
        char[] chars = s.toCharArray();
        int[] nextVal = new int[s.length()];
        nextVal[0] = -1;
        int i = 0;
        int j = -1;

        while (i < s.length()-1){
            if(j == -1 || chars[i] == chars[j]){
                ++i;
                ++j;
                if(chars[i] != chars[j])
                    nextVal[i] = j;
                else nextVal[i] = nextVal[j];
            }else {
                j = nextVal[j];
            }


        }
//        System.out.println(Arrays.toString(nextVal));
        return nextVal;

    }
    public static int kmp(String s, String sub){

        int[] next = getNext(sub);
        int i = 0;
        int j = 0;
        while (i < s.length() && j < sub.length()){
            if(j == -1 || s.charAt(i) == sub.charAt(j)){
                i++;
                j++;
            }else {
                j = next[j];
            }
        }
        return j < sub.length()?-1:i-j;
    }

    public static void main(String[] args) {
//        getNext("ababc");
        getNextVal("abababc");

//        System.out.println(kmp("ababadababc","ababc"));
    }
}

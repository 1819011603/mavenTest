package 左神;
//  求第k小/大的数  随机选点  T(N)=aT(N/b)+o(N^d); 最好情况T(N)=T(N/2)+o(N)  T(N)= O(n)
/*
我们随机在数组中选择一个数作为划分值（number），
然后进行快排的partation过程（将小于number的数放到数组左边，
等于number的数放到数组中间，大于number的数放到数组右边），
然后判断k与等于number区域的相对关系，
如果k正好在等于number区域，那么数组第k小的数就是number，如果k在等于number区域的左边，
那么我们递归对左边再进行上述过程，如果k在等于number区域的右边，那我们递归对右边再进行上述过程。
对于最好的情况：每次所选的number正好在数组的正中间，那么上式中a等于1，b等于2，对于partation过程，时间复杂度是O（n），
所以d等于1。所以T（N）= T（N/2）+ O（N），此时 log( 2 , 1 ) < 1，故时间复杂度为O（N）。
【第一次是O(N), 第二次是O(N/2), 第三次是 O(N/4) ... 第N次是O(N/N)，
总时间复杂度就是O(N + N/2 + N/4....+1) = O(2N-1)=O(N)】

对于最坏情况：每次所选的number正好在数组最边上，那么时间复杂度为O（N ^ 2）。
【第一次是O(N), 第二次是O(N-1), 第三次是 O(N-2) ... 第N次是O(N-N)，
总时间复杂度就是O(N + (N-1) + (N-2)....+(N-N))=O(N^2)】
* */
//  BFPRT
//  bfprt算法就是在这个number上做文章，
//  bfprt算法能够保证每次所选的number左右两边的数  在(3/10N, 7/10N之间)，那么时间复杂度就是O（N）。

/*
bfprt解法和常规解法唯一不同的就是在number的选取上，其他地方一模一样，所以我们只讲选取number这一过程。

第一步：我们将数组每5个相邻的数分成一组，后面的数如果不够5个数也分成一组。

第二步：对于每组数，我们找出这5个数的中位数，将所有组的中位数构成一个median数组（中位数数组）。

第三步：我们再求这个中位数数组中的中位数，此时所求出的中位数就是那个number。

第四步：通过这个number进行partation过程，下面和常规解法就一样了。
*/
// bfprt
/*
中位数数组中数字的个数就是 N / 5 （也可以是N/3  (N/3,2*N/3)  N/7   (2*N/7, 5*N/7)）
是中位数数组的中位数（divide）  那么由中位数的性质可知，中位数数组中有一半的数比这个divide大，所以总共有 N / 10个数比这个divide大。
 N / 10个数比这个divide大的组中 又有两个数比中位数要大  所以至少有N / 10 + ( 2N ) / 10 = ( 3N ) / 10 个数比divide大
 那么以divide为划分的partation过程能够使得divide在数组的靠近中间的位置，最坏情况也能够在数组的 ( 3N ) / 10 或者 ( 7N ) / 10 的位置。时间复杂度为O（N）。
*/
public class BFPRT {

    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i != res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static int select(int[] arr, int begin, int end, int i) {
        if (begin == end) {
            return arr[begin];
        }
        int pivot = medianOfMedians(arr, begin, end);
        int[] pivotRange = partition(arr, begin, end, pivot);
        if (i >= pivotRange[0] && i <= pivotRange[1]) {
            return arr[i];
        } else if (i < pivotRange[0]) {
            return select(arr, begin, pivotRange[0] - 1, i);
        } else {
            return select(arr, pivotRange[1] + 1, end, i);
        }
    }

    public static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        return select(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while (cur != big) {
            if (arr[cur] < pivotValue) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] > pivotValue) {
                swap(arr, cur, --big);
            } else {
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }
    public static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    public static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
    public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        int minKth = getMinKthByBFPRT(arr, k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] < minKth) {
                res[index++] = arr[i];
            }
        }
        for (; index != res.length; index++) {
            res[index] = minKth;
        }
        return res;
    }
    public static int getMinKthByBFPRT(int[] arr, int K) {
        int[] copyArr = copyArray(arr);
        return select(copyArr, 0, copyArr.length - 1, K - 1);
    }
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
        // sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }

        printArray(getMinKNumsByBFPRT(arr, 10));

    }

}

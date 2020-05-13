package day21.codingTest;

import java.util.Arrays;

/**
 * @program: java.shangguigu.learn
 * @description: 排序算法默写
 * @author: Hao Peng
 * @create: 2020-02-21 13:57
 **/
public class SortingTest {

    //冒泡排序
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    //选择排序
    public void selectSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[index]){
                    index = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    //插入排序
    public void insertSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i+1];
            int index = i;
            while (index>=0 && arr[index]>temp) {
                arr[index+1] = arr[index];
                index--;
            }
            arr[index+1] = temp;
        }
    }

    //希尔排序
    public void shellSort(int[] arr) {
        int len = arr.length;
        int gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int index = i - gap;
                while (index>=0 && arr[index] > temp) {
                    arr[index + gap] = arr[index];
                    index -= gap;
                }
                arr[index+gap] = temp;
            }
            gap /= 2;
        }
    }

    //归并排序
    public int[] mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) return arr;
        int[] left = Arrays.copyOfRange(arr,0,arr.length/2);
        int[] right = Arrays.copyOfRange(arr, arr.length/2,arr.length);
        return merge(mergeSort(left),mergeSort(right));

    }
    private int[] merge(int[] left, int[] right) {
        int[] bucket = new int[left.length + right.length];
        for (int m = 0, i = 0, j = 0; m < bucket.length; m++) {
            if (i>=left.length) {
                bucket[m] = right[j++];
            }else if (j >= right.length) {
                bucket[m] = left[i++];
            }else if (left[i] <= right[j]) {
                bucket[m] = left[i++];
            }else {
                bucket[m] = right[j++];
            }
        }
        return bucket;
    }

    //快速排序
    public void quickSort(int[] arr, int left,int right) {
        if (arr == null || left >= right || arr.length <2) return;
        int mid = partition(arr,left, right);
        quickSort(arr, 0, mid);
        quickSort(arr,mid + 1, right);
    }
    private int partition(int[] arr, int left, int right) {
        int cur = arr[left];
        while (left < right) {
            while (left < right && cur <= arr[right]) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
            }
            while (left < right && cur >= arr[left]){
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
            }
        }
        arr[left] = cur;
        return left;
    }

    //堆排序
    public void heapSort(int[] arr) {
        for (int i = arr.length/2 - 1; i >=0; i--) {
            adjustHeap(arr,i,arr.length);
        }
        for (int j = arr.length-1; j > 0 ; j--) {
            swap(arr,0,j);
            adjustHeap(arr,0, j);
        }

    }
    private void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int j = 2*i+1; j < length; j = 2*j+1) {
            if ((j+1)<length && arr[j] < arr[j+1]) {
                j++;
            }
            if (arr[j] > temp) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
    private void swap(int[] arr,int a, int b) {
        int tmp = arr[a];
        arr[a]=arr[b];
        arr[b]=tmp;
    }

    //计数排序
    public void countSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }
        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i] - min] += 1;
        }
        int index = 0, i = 0;
        while (i < arr.length) {
            if (bucket[index] != 0) {
                arr[i] = min + index;
                bucket[index]--;
                i++;
            } else {
                index++;
            }
        }

    }
    public static void main(String[] args) {
        int[] arr = {1,3,4,62,34,562,3,56,77,4,9,8};
        SortingTest ss = new SortingTest();
        //冒泡方法调用
        //ss.bubbleSort(arr);

        //选择排序调用
        //ss.selectSort(arr);

        //插入排序
        //ss.selectSort(arr);

        //shell排序
        ss.shellSort(arr);

        //归并排序
        //int[] res = ss.mergeSort(arr);
        //System.out.println(Arrays.toString(res));

        //快速排序
        //ss.quickSort(arr,0,arr.length-1);

        //堆排序
        //ss.heapSort(arr);

        //计数排序
        //ss.countSort(arr);

        System.out.println(Arrays.toString(arr));
    }
}

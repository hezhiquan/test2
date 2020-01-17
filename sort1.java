import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class sort1
{
    public static void main(String[] args)
    {
        int[] a = new int[100000];
        int[] a2 = {12, 123, 43, 56, 32, 321, 34, 233, 42, 54, 645, 43, 76, 8, 76657, 54, 654, 634, 2};
        int[] a3 = {1, 5, 6, 3, 5, 2, 7};
        int[] tmpArray = new int[a.length];
//        bubbleSort(a2);
//        insertSort(a2,0,a2.length-1);
//        for(int i=0;i<31;i++)
//        {

        for (int i1 = 0; i1 < a.length; i1++)
        {
            a[i1] = (int) (Math.random() * 100001);
        }
        long s = System.currentTimeMillis();
//            quickSort(a, 0, a.length - 1,2);
//            shellSort(a);
//        mergeSort(a,tmpArray,0,a.length-1);
//        heapSort(a);
        radixSort(a, 10);
        long e = System.currentTimeMillis();
        System.out.println(" 运行时间 ：" + (e - s) + " ms");
//        }
        for (int k : a)
        {
            System.out.print(k + " ");
        }

    }

    public static void insertSort(int[] a)
    {
        int j;
        for (int i = 0; i < a.length; i++)
        {
            int tmp = a[i];
            for (j = i; j > 0 && tmp < a[j - 1]; j--)
            {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    public static void bubbleSort(int[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {
            for (int j = arr.length - 1; j > i; j--)
            {
                if (arr[j] < arr[j - 1])
                {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int a, int b)
    {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void quickSort(int[] arr, int left, int right, int cutOff)
    {
        if (left + cutOff < right)
        {
            int pivot = median(arr, left, right);
            if (right - left <= 1)
            {
                return;
            }
            int i = left;
            int j = right - 1;
            for (; ; )
            {
                while (arr[++i] < pivot)
                {
                }//left
                while (arr[--j] > pivot)
                {
                }//right
                if (i < j)
                {
                    swap(arr, i, j);
                }
                else
                {
                    break;
                }
            }
            swap(arr, i, right - 1);//把pivot恢复到相应位置

            quickSort(arr, left, i - 1, cutOff);
            quickSort(arr, i + 1, right, cutOff);
        }
        else
        {
            insertSort(arr, left, right);
        }

    }

    public static void insertSort(int[] arr, int left, int right)
    {
        int j;
        for (int i = left + 1; i <= right; i++)
        {
            int tmp = arr[i];
            for (j = i; j > 0 && tmp < arr[j - 1]; j--)
            {
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }

    public static int median(int[] arr, int left, int right)
    {
        int center = (left + right) / 2;
        if (arr[left] > arr[center])
        {
            swap(arr, center, left);
        }
        if (arr[left] > arr[right])
        {
            swap(arr, left, right);
        }
        if (arr[center] > arr[right])
        {
            swap(arr, center, right);
        }

        swap(arr, center, right - 1);

        return arr[right - 1];
    }

    public static void shellSort(int[] arr)
    {
        int j;
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2)
        {
            for (int i = gap; i < arr.length; i++)
            {
                int tmp = arr[i];
                for (j = i; j >= gap && tmp < arr[j - gap]; j -= gap)
                {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
        }
    }

    public static void mergeSort(int[] arr, int[] tmpArray, int left, int right)
    {
        if (left < right)
        {
            int center = (left + right) / 2;
            mergeSort(arr, tmpArray, left, center);
            mergeSort(arr, tmpArray, center + 1, right);
            merge(arr, tmpArray, left, center + 1, right);
        }
    }

    public static void merge(int[] arr, int[] tmpArray, int leftPos, int rightPos, int rightEnd)
    {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElement = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd)
        {
            if (arr[leftPos] < arr[rightPos])
            {
                tmpArray[tmpPos++] = arr[leftPos++];
            }
            else
            {
                tmpArray[tmpPos++] = arr[rightPos++];
            }
        }

        while (leftPos <= leftEnd)
        {
            tmpArray[tmpPos++] = arr[leftPos++];
        }
        while (rightPos <= rightEnd) tmpArray[tmpPos++] = arr[rightPos++];

        for (int i = 0; i < numElement; i++, rightEnd--)
        {
            arr[rightEnd] = tmpArray[rightEnd];
        }
    }

    public static void heapSort(int[] array)
    {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(array.length, new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {//o2-o1降序排列    o1-o2升序排列
                return o2 - o1;
            }
        });
        for (int i = 0; i < array.length; i++)
        {
            maxHeap.add(array[i]);
        }
        for (int i = 0; i < array.length; i++)
        {
            array[i] = maxHeap.poll();
        }

    }

    public static void radixSort(int[] array, int radix)
    {
        int max = array[0];
        for (int i : array)
        {//找出最大值
            if (i > max)
            {
                max = i;
            }
        }
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < radix; i++)
        {
            buckets.add(new ArrayList<Integer>());
        }
        for (int i = 1; max / i > 0; i = i * radix)
        {//依次对个位、十位......排序
            for (int j = 0; j < array.length; j++)
            {//将数据放入桶中
                int index = (array[j] / i) % radix;
                buckets.get(index).add(array[j]);
            }
            int k = 0;
            for (int j = 0; j < buckets.size(); j++)
            {//把桶中数据放回数组中
                for (int m = 0; m < buckets.get(j).size(); m++)
                {
                    array[k++] = buckets.get(j).get(m);
                }
                buckets.get(j).clear();//清空桶
            }

        }
    }

}

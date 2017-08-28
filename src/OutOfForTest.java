import javax.swing.*;
import java.util.Arrays;

/**
 * Created by Alexa on 24.08.2017.
 */
public class OutOfForTest {

    //������� �����������
    public OutOfForTest() {
        System.out.println("For Test\n");
    }

    public static int a = 10;
    public static int b = 10;
    public static int[] arr = {0, 5, 6, 1, 7, 4, 15, 8, 9, 45};
    public static int[] arrSorted = new int[10];
    public static int[] arrMerge1 = {0, 2, 4, 7, 9};
    public static int[] arrMerge2 = {0, 3, 8, 0};

    public static void main(String[] args) {

        label1:
        for (int i = 0; i < 3; i++) {
            System.out.println(i + "\n");
            if (i == 1) {
                continue label1;
            }
        }

        System.out.println("----------");


        int a, i = 7;
        a = ++i;        //������� ����������� i = i + 1 = 8, ����� ����������� a = i = 8;
        System.out.println("a = " + a + "\ni = " + i);

        System.out.println("----------\n");
        int b, j = 7;
        b = j++;        //������� ����������� b = j = 7, ����� ����������� j = j + 1 = 8;
        System.out.println("b = " + b + "\nj = " + j);
        System.out.println("----------\n");

        for (int l = 0; l < arr.length; l++) {
            System.out.print(arr[l] + " ");
        }
        System.out.println("\nBubble sorting:");
        arrSorted = bubbleSort(arr, 0, arr.length);
        for (int k = 0; k < arr.length; k++) {
            System.out.print(arrSorted[k] + " ");
        }

        System.out.println("\nInsert sorting:");
        arrSorted = insertSort(arr, 1, arr.length);
        for (int k = 0; k < arr.length; k++) {
            System.out.print(arrSorted[k] + " ");
        }

        System.out.println("\nBinary search:");               // ���� +1 �� ��������� � ��� �������
        System.out.println(Arrays.binarySearch(arrSorted, 8)); // ���� -3 �� ������ �������� ��� � ��������� �: -(-3)-1 = 3-1 = 2.

        System.out.println("\nMerge sorting:");
        int[] mergeResult = new int[arrMerge1.length + arrMerge2.length];
        mergeResult = mergeSort(arrMerge1, arrMerge2, 1);
        for (int k = 0; k < mergeResult.length; k++) {
            System.out.print(mergeResult[k] + " ");
        }
    }

    // ���������� ���������
    // direction = 1 - �� �����������, 0 - �� ��������
    public static int[] bubbleSort(int[] arrayToSort, int direction, int ammount) {

        int[] sortedArray = new int[ammount];
        System.arraycopy(arrayToSort, 0, sortedArray, 0, ammount);

        //�� �����������
        if (direction == 1) {
            for (int i = 0; i < sortedArray.length - 1; i++) {
                for (int k = 0; k < sortedArray.length - 1; k++) {
                    if (sortedArray[k] > sortedArray[k + 1]) {
                        int temp = sortedArray[k];
                        sortedArray[k] = sortedArray[k + 1];
                        sortedArray[k + 1] = temp;
                    }
                }
            }
        } else //�� ��������
            if (direction == 0) {
                for (int i = 0; i < sortedArray.length - 1; i++) {
                    for (int k = 0; k < sortedArray.length - 1; k++) {
                        if (sortedArray[k] < sortedArray[k + 1]) {
                            int temp = sortedArray[k];
                            sortedArray[k] = sortedArray[k + 1];
                            sortedArray[k + 1] = temp;
                        }
                    }
                }
            }
        return sortedArray;
    }

    // ���������� ��������
    // direction = 1 - �� �����������, 0 - �� ��������
    public static int[] insertSort(int[] arrayToSort, int direction, int ammount) {

        int[] sortedArray = new int[ammount];
        System.arraycopy(arrayToSort, 0, sortedArray, 0, ammount);

        //�� �����������
        if (direction == 1) {
            for (int i = 0; i < sortedArray.length; i++) {
                int newElement = sortedArray[i]; // ����� �������
                int location = i - 1;            // ������� ������� ������� ��������
                while (location >= 0 && sortedArray[location] > newElement) {
                    sortedArray[location + 1] = sortedArray[location];
                    location--;
                }
                sortedArray[location + 1] = newElement;
            }
        } else if (direction == 0) { //�� ��������
            for (int i = 0; i < sortedArray.length; i++) {
                int newElement = sortedArray[i]; // ����� �������
                int location = i - 1;            // ������� ������� ������� ��������
                while (location >= 0 && sortedArray[location] < newElement) {
                    sortedArray[location + 1] = sortedArray[location];
                    location--;
                }
                sortedArray[location + 1] = newElement;
            }
        }
        return sortedArray;
    }

    // ���������� ��������
    // direction = 1 - �� �����������, 0 - �� ��������
    public static int[] mergeSort(int[] arrMerge1, int[] arrMerge2, int direction) {
        int[] result = new int[arrMerge1.length + arrMerge2.length];
        int aIndex = 0;
        int bIndex = 0;

        //�� �����������
        if(direction == 1){
            while(aIndex + bIndex != result.length){
                if(aIndex != arrMerge1.length && bIndex != arrMerge2.length) { // �������� �������� �� ������� �������� ����� ������
                    if (arrMerge1[aIndex] < arrMerge2[bIndex]) {
                        result[aIndex + bIndex] = arrMerge1[aIndex++]; // ������� �����������, ����� ��������������
                    } else {
                        result[aIndex + bIndex] = arrMerge2[bIndex++]; // ������� �����������, ����� ��������������
                    }
                } else // ���� �������� ������ ������� ������� � ������ ��� ����
                    if(aIndex == arrMerge1.length){
                        result[aIndex + bIndex] = arrMerge2[bIndex++]; // �������� ������� �� �������
                    }
                else // ���� �������� ������  �������  ������� � ������ ��� ����
                    if(bIndex == arrMerge2.length){
                        result[aIndex + bIndex] = arrMerge1[aIndex++]; // �������� ������� �� �������
                    }
            }
        }
        return result;
    }
}

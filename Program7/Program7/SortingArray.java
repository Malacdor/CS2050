// Diego Salas cs 2

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.File;

public class SortingArray
{
    public static void main(String[] args)
    {
        try {
            // Part 1:\
            // Create arrays and ArrayList for integers
            int[] bubbleSortArray = new int[20000];
            int[] selectionSortArray = new int[20000];
            ArrayList<Integer> arrayListIntegers = new ArrayList<>();

            // Read data from NumbersInFile.txt
            BufferedReader readerIntegers = new BufferedReader(new FileReader("NumbersInFile.txt"));
            String lineIntegers;
            int indexIntegers = 0;
            while ((lineIntegers = readerIntegers.readLine()) != null)
            {
                int num = Integer.parseInt(lineIntegers);
                bubbleSortArray[indexIntegers] = num;
                selectionSortArray[indexIntegers] = num;
                arrayListIntegers.add(num);
                indexIntegers++;
            }
            readerIntegers.close();

            // Measure time for Bubble Sort (both in nano)
            long bubbleSortStartTime = System.nanoTime();
            bubbleSort(bubbleSortArray);
            long bubbleSortEndTime = System.nanoTime();
            long bubbleSortTime = bubbleSortEndTime - bubbleSortStartTime;

            // Measure time for Selection Sort
            long selectionSortStartTime = System.nanoTime();
            selectionSort(selectionSortArray);
            long selectionSortEndTime = System.nanoTime();
            long selectionSortTime = selectionSortEndTime - selectionSortStartTime;

            // Part 2
            // Create arrays and ArrayList for strings
            String[] bubbleSortArrayStrings = new String[10000];
            String[] selectionSortArrayStrings = new String[10000];
            ArrayList<String> arrayListStrings = new ArrayList<>();

            // Read data from StringsInFile (which isnt a txt so this "should" work
            BufferedReader readerStrings = new BufferedReader(new FileReader("StringsInFile"));
            String lineStrings;
            int indexStrings = 0;
            while ((lineStrings = readerStrings.readLine()) != null)
            {
                bubbleSortArrayStrings[indexStrings] = lineStrings;
                selectionSortArrayStrings[indexStrings] = lineStrings;
                arrayListStrings.add(lineStrings);
                indexStrings++;
            }
            readerStrings.close();

            // Measure time for Bubble Sort (Strings)
            long bubbleSortStartTimeStrings = System.nanoTime();
            bubbleSort(bubbleSortArrayStrings);
            long bubbleSortEndTimeStrings = System.nanoTime();
            long bubbleSortTimeStrings = bubbleSortEndTimeStrings - bubbleSortStartTimeStrings;

            // Measure time for Selection Sort (Strings)
            long selectionSortStartTimeStrings = System.nanoTime();
            selectionSort(selectionSortArrayStrings);
            long selectionSortEndTimeStrings = System.nanoTime();
            long selectionSortTimeStrings = selectionSortEndTimeStrings - selectionSortStartTimeStrings;

            // Measure time for Collection.sort (ArrayList)
            long collectionSortStartTime = System.nanoTime();
            Collections.sort(arrayListStrings);
            long collectionSortEndTime = System.nanoTime();
            long collectionSortTime = collectionSortEndTime - collectionSortStartTime;

            // put results in the results.txt file
            File file = new File("results.txt");
            boolean fileExists = file.exists();
            FileWriter writer = new FileWriter(file, true); // Append to existing file or create a new one

            if (!fileExists)
            {
                writer.write("Number of Integers: 20000\n");
                writer.write("Bubble Sort Time (Numbers): " + bubbleSortTime + " nanoseconds\n");
                writer.write("Selection Sort Time (Numbers): " + selectionSortTime + " nanoseconds\n");
                writer.write("Number of Strings: 10000\n");
            }

            writer.write("Bubble Sort Time (Strings): " + bubbleSortTimeStrings + " nanoseconds\n");
            writer.write("Selection Sort Time (Strings): " + selectionSortTimeStrings + " nanoseconds\n");
            writer.write("Collection.sort Time (ArrayList): " + collectionSortTime + " nanoseconds\n");
            writer.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Bubble Sort implementation for integers and strings
    static void bubbleSort(int[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                if (arr[j] > arr[j + 1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void bubbleSort(String[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                if (arr[j].compareTo(arr[j + 1]) > 0)
                {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Selection Sort implementation for integers and strings
    static void selectionSort(int[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
            {
                if (arr[j] < arr[minIndex])
                {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    static void selectionSort(String[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0)
                {
                    minIndex = j;
                }
            }
            String temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}

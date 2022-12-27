/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program to play with various sorting algorithms. 
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 ******************************************************************************/
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class Sorting {


 /**
     * 
     * Sorts the numbers present in the file based on the algorithm provided.
     * 0 = Arrays.sort() (Java Default)
     * 1 = Bubble Sort
     * 2 = Selection Sort 
     * 3 = Insertion Sort 
     * 4 = Mergesort
     * 5 = Quicksort
     *
     * @param args the command-line arguments
     */
    
    public static int[] bubbleSort(int[] a){
      // StdOut.println("B4: "+ Arrays.toString(a));
      boolean swapOccurred = true;
      while(swapOccurred){
        swapOccurred = false;
        for(int i = 1; i < a.length; i++){
          if(a[i-1] > a[i]){
            // StdOut.println("Comparison made! " + i);
            if(!swapOccurred)
              swapOccurred = true;
            int temp = a[i];
            a[i] = a[i-1];
            a[i-1] = temp;
          }
        }
      }
      // StdOut.println("AF: " + Arrays.toString(a));
      return a;
    }

    public static int[] selectionSort(int[] a){
      // StdOut.println("B4: "+ Arrays.toString(a));
      for(int i = 1; i < a.length; i++){
        int j = i;
        while( j > 0 && a[j] < a[j-1]){
          int temp = a[j];
          a[j] = a[j-1];
          a[j-1] = temp;
          j--;
        }
      }
      // StdOut.println("AF: " + Arrays.toString(a));
      return a;
    }
    
    public static int[] insertionSort(int[] a){
      // StdOut.println("B4: "+ Arrays.toString(a));
      for(int i = 0; i < a.length; i++){
        int indSmall = i;
        for(int j = i+1; j < a.length; j++){
          if(a[j] < a[indSmall])
            indSmall = j;
        }
        int temp = a[i];
        a[i] = a[indSmall];
        a[indSmall] = temp;
      }
      // StdOut.println("AF: " + Arrays.toString(a));
      return a;
    }


    public static int quickSortParition(int[] a, int startInd, int endInd){
      int pivot = a[startInd + ((endInd - startInd) / 2)];
      boolean finished = false;
      while(!finished){
        while(a[startInd] < pivot)
          startInd++;
        while(pivot < a[endInd])
          endInd--;
        if(startInd >= endInd)
          finished = true;
        else {
          int temp = a[startInd];
          a[startInd] = a[endInd];
          a[endInd] = temp;

          startInd++;
          endInd--;
        }

      }
      return endInd;
    }

    public static int[] quickSort(int[] a, int startInd, int endInd){
      if(startInd >= endInd)
        return a;
      
      int lowEndIndex = quickSortParition(a, startInd, endInd);
      int[] aLow = quickSort(a, startInd, lowEndIndex);
      int[] aHigh = quickSort(a, lowEndIndex+1, endInd);
      return a;
    }
    
    




    public static void main(String[] args)  { 
        In in = new In(args[0]);
        
		  // Storing file input in an array
        int[] a = in.readAllInts();
        // TODO: Generate 3 other arrays, b, c, d where
        // b contains sorted integers from a (You can use Java Arrays.sort() method)
        // c contains all integers stored in reverse order 
        // (you can have your own O(n) solution to get c from b
        // d contains almost sorted array 
        //(You can copy b to a and then perform (0.1 * d.length)  many swaps to acheive this.   
        int[] b = a.clone();
        Arrays.sort(b);
        // simple sort method
        // StdOut.print("b done\n");

        int[] c = b.clone();
        
        for(int i = 0; i < c.length/2; i++){
            int adj = 0;
            if(i == 0)
              adj++;
            int temp = c[i];
            c[i] = c[c.length-i-adj];
            c[c.length-i-adj] = temp; 
        } // this will reverse the elements by using both ends and working towards the middle
        // StdOut.print("c done\n");

        int[] d = b.clone();
        int count = 0;
        Random rng = new Random();
        do{
          int i = rng.nextInt(d.length-1);
          int j = rng.nextInt(d.length-1);
          while(i==j)
            j = rng.nextInt(d.length-1);
          
          int temp = d[i];
          d[i] = d[j];
          d[j] = temp;
          count++;
        } while(count < (0.1 * d.length));
        // StdOut.print("d done\n");
        

        //TODO: 
        // Read the second argument and based on input select the sorting algorithm
        //  * 0 = Arrays.sort() (Java Default)
        //  * 1 = Bubble Sort
        //  * 2 = Selection Sort 
        //  * 3 = Insertion Sort 
        //  * 4 = Mergesort
        //  * 5 = Quicksort
        //  Perform sorting on a,b,c,d. Pring runtime for each case along with timestamp and record those. 
        // For runtime and priting, you can use the same code from Lab 4 as follows:
        int sortType = Integer.parseInt(args[1]);
        String algorithmUsed;

        double[] timeArr = new double[4];

        switch(sortType){
          case 0:
            //0 = Arrays.sort() (Java Default)
            algorithmUsed = "Arrays.sort()";
            Stopwatch timerAsort = new Stopwatch();
            Arrays.sort(a);
            timeArr[0] = timerAsort.elapsedTimeMillis();

            Stopwatch timerBsort = new Stopwatch();
            Arrays.sort(b);
            timeArr[1] = timerBsort.elapsedTimeMillis();

            Stopwatch timerCsort = new Stopwatch();
            Arrays.sort(c);
            timeArr[2] = timerCsort.elapsedTimeMillis();

            Stopwatch timerDsort = new Stopwatch();
            Arrays.sort(d);
            timeArr[3] = timerDsort.elapsedTimeMillis();
            break;
          case 1:
            //1 = Bubble Sort
            algorithmUsed = "Bubble Sort";

            Stopwatch timerAbub = new Stopwatch();
            a = bubbleSort(a);
            timeArr[0] = timerAbub.elapsedTimeMillis();

            Stopwatch timerBbub = new Stopwatch();
            b = bubbleSort(b);
            timeArr[1] = timerBbub.elapsedTimeMillis();

            Stopwatch timerCbub = new Stopwatch();
            c = bubbleSort(c);
            timeArr[2] = timerCbub.elapsedTimeMillis();

            Stopwatch timerDbub = new Stopwatch();
            d = bubbleSort(d);
            timeArr[3] = timerDbub.elapsedTimeMillis();
            break;
          case 2:
            //2 = Selection Sort 
            algorithmUsed = "Selection Sort";
            Stopwatch timerAsel = new Stopwatch();
            a = selectionSort(a);
            timeArr[0] = timerAsel.elapsedTimeMillis();

            Stopwatch timerBsel = new Stopwatch();
            b = selectionSort(b);
            timeArr[1] = timerBsel.elapsedTimeMillis();

            Stopwatch timerCsel = new Stopwatch();
            c = selectionSort(c);
            timeArr[2] = timerCsel.elapsedTimeMillis();

            Stopwatch timerDsel = new Stopwatch();
            d = selectionSort(d);
            timeArr[3] = timerDsel.elapsedTimeMillis();
            break;
          case 3:
            //3 = Insertion Sort 
            algorithmUsed = "Insertion Sort";
            
            Stopwatch timerAins = new Stopwatch();
            a = insertionSort(a);
            timeArr[0] = timerAins.elapsedTimeMillis();

            Stopwatch timerBins = new Stopwatch();
            b = insertionSort(b);
            timeArr[1] = timerBins.elapsedTimeMillis();

            Stopwatch timerCins = new Stopwatch();
            c = insertionSort(c);
            timeArr[2] = timerCins.elapsedTimeMillis();

            Stopwatch timerDins = new Stopwatch();
            d = insertionSort(d);
            timeArr[3] = timerDins.elapsedTimeMillis();

            break;
          case 5:
            //5 = Quicksort
            algorithmUsed = "Quicksort";

            Stopwatch timerAqik = new Stopwatch();
            // StdOut.println("B4: " + Arrays.toString(a));
            a = quickSort(a, 0, a.length-1);
            timeArr[0] = timerAqik.elapsedTimeMillis();
            // StdOut.println("AF: " + Arrays.toString(a));

            Stopwatch timerBqik = new Stopwatch();
            // StdOut.println("B4: " + Arrays.toString(b));
            b = quickSort(b, 0, b.length-1);
            timeArr[1] = timerBqik.elapsedTimeMillis();
            // StdOut.println("AF: " + Arrays.toString(b));

            Stopwatch timerCqik = new Stopwatch();
            // StdOut.println("B4: " + Arrays.toString(c));
            c = quickSort(c, 0, c.length-1);
            timeArr[2] = timerCqik.elapsedTimeMillis();
            // StdOut.println("AF: " + Arrays.toString(c));

            Stopwatch timerDqik = new Stopwatch();
            // StdOut.println("B4: " + Arrays.toString(d));
            d = quickSort(d, 0, d.length-1);
            timeArr[3] = timerDqik.elapsedTimeMillis();
            // StdOut.println("AF: " + Arrays.toString(d));


            break;
          default:
            algorithmUsed = "no sorting done.";
            break;
        }


        //System.out.println(Arrays.toString(a) + "\n" + Arrays.toString(b) + "\n" + Arrays.toString(c) + "\n" + Arrays.toString(d));
        
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());        String netID = "jschiavi";
        String arrayUsed = "a";
          StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, "a", timeArr[0], timeStamp, netID, args[0]);
          StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, "b", timeArr[1], timeStamp, netID, args[0]);
          StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, "c", timeArr[2], timeStamp, netID, args[0]);
          StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, "d", timeArr[3], timeStamp, netID, args[0]);

          // Write the resultant array to a file (Each time you run a program 4 output files should be generated. (one for each a,b,c, and d)
          String fileused = args[0].substring(2, args[0].length()-4);

          try{
            FileWriter fileA = new FileWriter("a.txt");
            String prntout = String.format("%s %s %8.1f   %s  %s  %s\n%s", algorithmUsed, "a", timeArr[0], timeStamp, netID, args[0], Arrays.toString(a));
            fileA.write(prntout);
            fileA.close();
            
            FileWriter fileB = new FileWriter("b.txt");
            prntout = String.format("%s %s %8.1f   %s  %s  %s\n%s", algorithmUsed, "b", timeArr[1], timeStamp, netID, args[0], Arrays.toString(b));
            fileB.write(prntout);
            fileB.close();

            FileWriter fileC = new FileWriter("c.txt");
            prntout = String.format("%s %s %8.1f   %s  %s  %s\n%s", algorithmUsed, "c", timeArr[2], timeStamp, netID, args[0], Arrays.toString(c));
            fileC.write(prntout);
            fileC.close();

            FileWriter fileD = new FileWriter("d.txt");
            prntout = String.format("%s %s %8.1f   %s  %s  %s\n%s", algorithmUsed, "d", timeArr[3], timeStamp, netID, args[0], Arrays.toString(d));
            fileD.write(prntout);
            fileD.close();
            StdOut.println(String.format("Successfully saved files."));
          }catch(IOException e){
            StdOut.println(String.format("Failed to write files."));
            e.printStackTrace();
          }
		
  }
} 



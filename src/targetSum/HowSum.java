package targetSum;

/*
 * QUESTION:
 * Write a function 'howSUm(targetSum, numbers)' that takes in a targetSum
 * and an array of numbers as arguements.
 *
 * The function should return an array containing any combination of elements that
 * add up to exactly the targetSum. If there is no combination that adds up to the
 * target sum then return null.
 *
 * NOTE
 * You may use an element of the array as many times as needed.
 * you may assume that all input numbers are nonnegative.
 */

import java.util.*;
import java.util.logging.Logger;

public class HowSum {

    static Logger log = Logger.getLogger(CanSum.class.getName());

    public static int[] howSum(int targetSum, int[] numbers){
        if(targetSum == 0) return new int[]{};
        if(targetSum < 0) return null;

        for (int i = 0; i < numbers.length; i++) {
            int remainder = targetSum-numbers[i];
            int[] result = howSum(remainder, numbers);

            if(result != null){
                int[] resultList = new int[result.length+1];

                for (int j = 0; j < result.length; j++) {
                    resultList[j] = result[j];
                }
                resultList[resultList.length-1] = numbers[i];
                return resultList;
            }
        }

        return null;
    }

    public static int[] howSumOptimized(int targetSum, int[] numbers, Map<Integer, int[]> memo){
        if(memo.containsKey(targetSum)) return memo.get(targetSum);
        if(targetSum == 0) return new int[]{};
        if(targetSum < 0) return null;

        for (int i = 0; i < numbers.length; i++) {
            int remainder = targetSum-numbers[i];
            int[] result = howSumOptimized(remainder, numbers, memo);

            if(result != null){
                int[] resultList = new int[result.length+1];

                for (int j = 0; j < result.length; j++) {
                    resultList[j] = result[j];
                }
                resultList[resultList.length-1] = numbers[i];
                memo.put(targetSum, resultList);

                return memo.get(targetSum);
            }
        }

        memo.put(targetSum, null);
        return memo.get(targetSum);
    }

    public static void main(String[] args){

        printArray(howSum(7, new int[]{2, 3}));
        printArray(howSum(7, new int[]{5, 3, 4, 7}));
        printArray(howSum(7, new int[]{2, 4}));
        printArray(howSum(8, new int[]{2, 3, 5}));
        printArray(howSumOptimized(300, new int[]{7, 14}, new HashMap<>()));
    }

    public static void printArray(int[] arr){
        if(arr != null) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
        }else System.out.print("No data!");
        System.out.println();
    }
}

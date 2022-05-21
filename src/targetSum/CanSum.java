package targetSum;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/*
 * /*
 * QUESTION:
 * Write a function 'canSUm(targetSum, numbers)' that takes in a targetSum
 * and an array of numbers as arguements.
 *
 * The function should return a boolean indicating whether or not it is possible
 * to generate the targetSum using numbers from the Array.
 *
 * NOTE
 * You may use an element of the array as many times as needed.
 * you may assume that all input numbers are nonnegative.
 */

public class CanSum {
    static Logger log = Logger.getLogger(CanSum.class.getName());
    public static boolean canSum(int targetSum, int[] numbers){

        if(targetSum == 0) return true;
        if(targetSum < 0) return false;

        for (int number : numbers) {
            int remainder = targetSum-number;
            if(canSum(remainder, numbers)) return true;
        }

        return false;
    }

    public static boolean canSumOptimized(int targetSum, int[] numbers, Map<Integer, Boolean> sumMap){

        if(sumMap.containsKey(targetSum)) return sumMap.get(targetSum);
        if(targetSum == 0) return true;
        if(targetSum < 0) return false;

        for (int number : numbers) {
            int remainder = targetSum-number;

            if(canSumOptimized(remainder, numbers, sumMap)) {
                sumMap.put(targetSum, true);
                return sumMap.get(targetSum);
            }
        }

        sumMap.put(targetSum, false);
        return sumMap.get(targetSum);
    }

    public static void main(String[] args){

        log.info("Exists : "+CanSum.canSum(7, new int[]{2, 3}));
        log.info("Exists : "+CanSum.canSum(7, new int[]{5, 3, 4, 7}));
        log.info("Exists : "+CanSum.canSum(7, new int[]{2, 4}));
        log.info("Exists : "+CanSum.canSum(8, new int[]{2, 3, 5}));
        log.info("Exists : "+CanSum.canSumOptimized(300, new int[]{7, 14}, new HashMap<>()));
    }
}

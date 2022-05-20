package targetSum;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

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
            sumMap.put(remainder, canSumOptimized(remainder, numbers, sumMap));
            if(sumMap.get(remainder)) return true;
        }

        return false;
    }

    public static void main(String[] args){

        log.info("Exists : "+CanSum.canSum(7, new int[]{2, 3}));
        log.info("Exists : "+CanSum.canSum(7, new int[]{5, 3, 4, 7}));
        log.info("Exists : "+CanSum.canSum(7, new int[]{2, 4}));
        log.info("Exists : "+CanSum.canSum(8, new int[]{2, 3, 5}));
        log.info("Exists : "+CanSum.canSumOptimized(300, new int[]{7, 14}, new HashMap<>()));
    }
}

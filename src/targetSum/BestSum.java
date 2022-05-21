package targetSum;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class BestSum {
    static Logger log = Logger.getLogger(CanSum.class.getName());

    public static int[] bestSum(int targetSum, int[] numbers){
        if(targetSum == 0) return new int[]{};
        if(targetSum < 0) return null;

        int[] shortestCombination = null;

        for (int i = 0; i < numbers.length; i++) {
            int remainder = targetSum-numbers[i];
            int[] result = bestSum(remainder, numbers);

            if(result != null){
                int[] resultList = new int[result.length+1];

                for (int j = 0; j < result.length; j++) {
                    resultList[j] = result[j];
                }
                resultList[resultList.length-1] = numbers[i];

                if(shortestCombination == null
                        || shortestCombination.length > resultList.length){
                    shortestCombination = resultList;
                }
            }
        }

        return shortestCombination;
    }

    public static int[] bestSumOptimized(int targetSum, int[] numbers, Map<Integer, int[]> memo){
        if(memo.containsKey(targetSum)) return memo.get(targetSum);
        if(targetSum == 0) return new int[]{};
        if(targetSum < 0) return null;

        int[] shortestCombination = null;

        for (int i = 0; i < numbers.length; i++) {
            int remainder = targetSum-numbers[i];
            int[] result = bestSumOptimized(remainder, numbers, memo);

            if(result != null){
                int[] resultList = new int[result.length+1];

                for (int j = 0; j < result.length; j++) {
                    resultList[j] = result[j];
                }
                resultList[resultList.length-1] = numbers[i];

                if(shortestCombination == null
                        || shortestCombination.length > resultList.length){
                    shortestCombination = resultList;
                }
            }
        }

        memo.put(targetSum, shortestCombination);
        return shortestCombination;
    }

    public static void main(String[] args){

        printArray(bestSum(7, new int[]{5, 3, 4, 7}));
        printArray(bestSum(8, new int[]{2, 3, 5}));
        printArray(bestSum(8, new int[]{1, 4, 5}));
        printArray(bestSumOptimized(100, new int[]{1, 2, 5, 25}, new HashMap<>()));
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

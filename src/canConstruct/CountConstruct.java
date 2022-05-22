package canConstruct;

import java.util.HashMap;
import java.util.Map;

/*
 * write a function `countConstruct(target, wordBank)` that accepts a target string
 * and an array of strings.
 *
 * The function should return the number of ways that the `target` can be constructed
 * by concatenating elements of the `wordBank` array.
 *
 * you may reuse elements of 'wordBank` as many times as needed.
 */
public class CountConstruct {

    public static int countConstruct(String target, String[] wordBank){
        if(target.equals("")) return 1;

        int countWays = 0;

        for (String s : wordBank) {

            if(target.indexOf(s) == 0){
                String suffix = target.substring(s.length());
                countWays += countConstruct(suffix, wordBank);
            }
        }
        return countWays;
    }

    public static int countConstructOptimizd(String target, String[] wordBank, Map<String, Integer> memo){
        if(target.equals("")) return 1;
        if(memo.containsKey(target)) return memo.get(target);

        int countWays = 0;

        for (String s : wordBank) {

            if(target.indexOf(s) == 0){
                String suffix = target.substring(s.length());
                countWays += countConstructOptimizd(suffix, wordBank, memo);
            }
        }
        memo.put(target, countWays);
        return memo.get(target);
    }

    public static void main(String[] args){
        System.out.println(countConstruct("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(countConstruct("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(countConstruct("enterapotentpot", new String[] {"a", "p", "ent", "enter", "ot", "o", "t"}));
        System.out.println(countConstructOptimizd("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[] {"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}, new HashMap<>()));
    }
}

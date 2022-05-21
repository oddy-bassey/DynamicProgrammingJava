package canConstruct;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/*
 * write a function `canConstruct(target, wordBank)` that accepts
 * a target string and an array of strings.
 *
 * the function should return a boolean indication whether the
 * `target` can be constructed by concatenating elements of
 * the `wordBank` array.
 */
public class CanConstruct {
    Logger log = Logger.getLogger(getClass().getName());

    public static boolean canConstruct(String target, String[] wordBank){
        if(target.equals("")) return true;

        for (String s : wordBank) {
            if(target.indexOf(s) == 0){
                String suffix = target.substring(s.length());
                if(canConstruct(suffix, wordBank)) return true;
            }
        }

        return false;
    }

    public static boolean canConstructOptimized(String target, String[] wordBank, Map<String, Boolean> memo){
        if(memo.containsKey(target)) return memo.get(target);
        if(target.equals("")) return true;

        for (String s : wordBank) {
            if(target.indexOf(s) == 0){
                String suffix = target.substring(s.length());
                if(canConstructOptimized(suffix, wordBank, memo)) {
                    memo.put(target, true);
                    return memo.get(target);
                }
            }
        }

        memo.put(target, false);
        return memo.get(target);
    }

    public static void main(String[] args){

        System.out.println(canConstruct("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(canConstruct("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(canConstruct("enterapotentpot", new String[] {"a", "p", "ent", "enter", "ot", "o", "t"}));
        System.out.println(canConstructOptimized("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[] {"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}, new HashMap<>()));
    }
}

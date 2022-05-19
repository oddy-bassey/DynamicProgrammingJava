package fibonacci;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Fibonacci {

    private static Logger log = Logger.getLogger(Fibonacci.class.getName());
    public static long findFibonacci(long n){

        if(n<=2) return 1;
        return findFibonacci(n-1) + findFibonacci(n-2);
    }

    public static long findFibonacciOptimized(long n, Map<Long, Long> mapData){

        if(mapData.containsKey(n)) return mapData.get(n);
        if(n<=2) return 1;

        mapData.put(n, findFibonacciOptimized(n-1, mapData) + findFibonacciOptimized(n-2, mapData));

        return mapData.get(n);
    }

    public static void main(String[] args){

        long startime = System.currentTimeMillis();

        long value = 50;
        log.info("Fibonacci of "+value+" is: "+findFibonacciOptimized(value, new HashMap<>()));

        long executionTime = (System.currentTimeMillis()-startime) /1000;
        log.info("executed in "+executionTime+" seconds");
    }
}

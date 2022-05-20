package gridTraveller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GridTraveller {
    static Logger log = Logger.getLogger(GridTraveller.class.getName());

    public static long gridTraveller(int m, int n){

        if(m==1 && n==1) return 1;
        if(m==0 || n==0) return 0;

        return gridTraveller(m-1, n)+gridTraveller(m,n-1);
    }

    public static long gridTravellerOptimized(int m, int n, Map<String, Long> gridMap){
        String key = m+","+n;

        if(gridMap.containsKey(key)) return gridMap.get(key);
        if(m==1 && n==1) return 1;
        if(m==0 || n==0) return 0;

        gridMap.put(key, gridTravellerOptimized(m-1, n, gridMap)+gridTravellerOptimized(m,n-1, gridMap));
        return gridMap.get(key);
    }

    public static void main(String[] args){

        log.info("no of paths "+gridTraveller(1,1));
        log.info("no of paths "+gridTraveller(2,3));
        log.info("no of paths "+gridTraveller(3,2));
        log.info("no of paths "+gridTravellerOptimized(3,3,new HashMap<>()));
        log.info("no of paths "+gridTravellerOptimized(18,18,new HashMap<>()));
    }
}

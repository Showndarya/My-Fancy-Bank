package Utilities;

import java.util.Random;

public class StockPriceUtils {
    /**
     * should be replaced by actual price later
     * @return
     */
    public static double getRandomPrice(){
        return Math.round(new Random().nextDouble()*100);
    }
}

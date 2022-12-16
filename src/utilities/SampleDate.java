package utilities;

import java.time.LocalDate;

public class SampleDate {
    private static LocalDate now;

    public LocalDate getInstance() {
        if (now == null) {
            now = LocalDate.now();

        }
        return now;
    }

    private SampleDate() {


    }

    public static void main(String[] args) {
        SampleDate sampleDate = new SampleDate();
        System.out.println(sampleDate.getCurrentDate());
        sampleDate.addOneDay();
        System.out.println(sampleDate.getCurrentDate());
    }

    public String getCurrentDate() {
        return now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth();
    }

    public void addOneDay() {
        now = now.plusDays(1);
        //rest things to do.
        //add money to saving account

        //add tranaction history for interest gain to transaction
    }


}
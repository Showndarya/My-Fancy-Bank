package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDate {
    private Date date;
    private String dateString;
    public SimpleDate() {
        this.date = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        dateString = simpleDateFormat.format(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}

package in.dharmin.calendar;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;

@SuppressWarnings("unused")
public class Main extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MonthView mv = new MonthView(this);
        setContentView(mv);
//        Calendar cal = Calendar.getInstance();
//        cal.set(2012, Calendar.DECEMBER,12);
//        mv.GoToDate(cal.getTime());
    }
}
package template.cheng.hollis.template;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.HashSet;

public class TestActivity extends AppCompatActivity {
    HashSet<CalendarDay> dates = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        MaterialCalendarView MCV_test = (MaterialCalendarView) findViewById(R.id.MCV_test);

        Calendar cal1 = Calendar.getInstance();
        cal1.set(2017, 10 - 1, 1);
        CalendarDay calDay1 = CalendarDay.from(cal1);
        dates.add(calDay1);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2017, 10 - 1, 2);
        CalendarDay calDay2 = CalendarDay.from(cal2);
        dates.add(calDay2);

        MCV_test.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return dates.contains(day);
            }

            @Override
            public void decorate(DayViewFacade view) {
//                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_calendar_next_right));
//                view.setSelectionDrawable(getResources().getDrawable(R.drawable.btn_calendar_previous_left));
                view.addSpan(new TwoDotSpan(6, getResources().getColor(R.color.red), getResources().getColor(R.color.green)));
//                view.addSpan(new DotSpan(6, getResources().getColor(R.color.green)));
            }
        });
    }
}

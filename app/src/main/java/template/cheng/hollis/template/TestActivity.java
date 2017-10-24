package template.cheng.hollis.template;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.HashSet;

import template.cheng.hollis.template.base.BaseActivity;

public class TestActivity extends BaseActivity {
    HashSet<CalendarDay> dates = new HashSet<>();
    boolean IsShowCV = true;
    TextView tv_show_hide_CV;
    MaterialCalendarView MCV_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        MCV_test = (MaterialCalendarView) findViewById(R.id.MCV_test);
        tv_show_hide_CV = (TextView) findViewById(R.id.tv_show_hide_CV);
        LL_NormalRow llnr_test = (LL_NormalRow) findViewById(R.id.llnr_test);

        llnr_test.tv_row_text.setText("ABCCBA");
        llnr_test.view_divider.setVisibility(View.GONE);

        //default is show when 1st time in
        tv_show_hide_CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTVText();
            }
        });

        Calendar cal1 = Calendar.getInstance();
        cal1.set(2017, 10 - 1, 1);
        CalendarDay calDay1 = CalendarDay.from(cal1);
        dates.add(calDay1);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2017, 10 - 1, 2);
        CalendarDay calDay2 = CalendarDay.from(cal2);
        dates.add(calDay2);
        MCV_test.setTopbarVisible(false);
        MCV_test.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return dates.contains(day);
            }

            @Override
            public void decorate(DayViewFacade view) {
//                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_calendar_next_right));
//                view.setSelectionDrawable(getResources().getDrawable(R.drawable.draw_selected_circle));
                view.addSpan(new TwoDotSpan(6, getResources().getColor(R.color.CadetBlue), getResources().getColor(R.color.LightBlue)));
//                view.addSpan(new DotSpan(6, getResources().getColor(R.color.green)));
            }
        });
    }

    private void setTVText() {
        if (IsShowCV) {
            tv_show_hide_CV.setText("Show");
            IsShowCV = false;
            System.out.println("11MCV_test.getHeight()=" + MCV_test.getHeight());
            MCV_test.animate()
                    .translationY(-MCV_test.getHeight())
                    .alpha(0.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            MCV_test.setVisibility(View.GONE);
                            System.out.println("22MCV_test.getHeight()=" + MCV_test.getHeight());
                        }
                    });


        } else {
            tv_show_hide_CV.setText("Hide");
            IsShowCV = true;

            System.out.println("33MCV_test.getHeight()=" + MCV_test.getHeight());
            // Prepare the View for the animation
            MCV_test.setVisibility(View.VISIBLE);
            System.out.println("44MCV_test.getHeight()=" + MCV_test.getHeight());
            MCV_test.setAlpha(0.0f);
            // Start the animation
            MCV_test.animate()
                    .translationY(0)
                    .alpha(1.0f)
                    .setListener(null);

        }
    }
}

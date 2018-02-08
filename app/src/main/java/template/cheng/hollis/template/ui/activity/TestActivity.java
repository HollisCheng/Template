package template.cheng.hollis.template.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import template.cheng.hollis.template.LL_NormalRow;
import template.cheng.hollis.template.R;
import template.cheng.hollis.template.TwoDotSpan;
import template.cheng.hollis.template.base.BaseActivity;
import template.cheng.hollis.template.dateTimeStringConvert.TimeRender;


public class TestActivity extends BaseActivity {
    @BindView(R.id.tv_show_hide_CV)
    TextView tvShowHideCV;
    @BindView(R.id.MCV_test)
    MaterialCalendarView MCVTest;
    @BindView(R.id.llnr_test)
    LL_NormalRow llnrTest;
    @BindView(R.id.wp_hours)
    WheelPicker wpHours;
    @BindView(R.id.wp_mins)
    WheelPicker wpMins;
    @BindView(R.id.wp_am_pm)
    WheelPicker wpAmPm;

    HashSet<CalendarDay> dates = new HashSet<>();
    boolean IsShowCV = true;
    boolean IsShowD = true;
    private ArrayList<String> HRS = new ArrayList<>();
    private ArrayList<String> MINS = new ArrayList<>();
    private ArrayList<String> AMPM = new ArrayList<>();
    String months[] = null;
    ArrayAdapter<String> monthAdapter = null;
    MultiAutoCompleteTextView multiAutoCompleteTvMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        Calendar cal1 = Calendar.getInstance();
        cal1.set(2017, 10 - 1, 1);
        CalendarDay calDay1 = CalendarDay.from(cal1);
        dates.add(calDay1);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2017, 10 - 1, 2);
        CalendarDay calDay2 = CalendarDay.from(cal2);
        dates.add(calDay2);
//        MCVTest.setTopbarVisible(false);
        MCVTest.addDecorator(new DayViewDecorator() {
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

        MCVTest.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                try {
                    System.out.println("date=" + date);
                    System.out.println("date string = " + TimeRender.getDate(date.getDate(), "dd MMM yyyy"));
                } catch (Exception e) {
                    System.out.println("error=" + e.toString());
                }


            }
        });

        setHrMinAMPMData();
        wpHours.setData(HRS);
        wpMins.setData(MINS);
        wpAmPm.setData(AMPM);

        wpHours.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {

            }
        });

        months = getResources().getStringArray(R.array.months);
        monthAdapter = new ArrayAdapter<String>(this, R.layout.hint_completion_layout, R.id.tvHintCompletion, months);
        // monthAdapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, months);
        multiAutoCompleteTvMonth = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTvMonth);
        multiAutoCompleteTvMonth.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        multiAutoCompleteTvMonth.setAdapter(monthAdapter);
    }

    @OnClick({R.id.tv_show_hide_CV, R.id.llnr_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_show_hide_CV:
                setTVText();
                break;
            case R.id.llnr_test:
                llnr_change();
                break;
        }
    }

    private void setTVText() {
        if (IsShowCV) {
            tvShowHideCV.setText("Show");
            IsShowCV = false;
            System.out.println("11MCVTest.getHeight()=" + MCVTest.getHeight());
            MCVTest.animate()
                    .translationY(-MCVTest.getHeight())
                    .alpha(0.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            MCVTest.setVisibility(View.GONE);
                            System.out.println("22MCVTest.getHeight()=" + MCVTest.getHeight());
                        }
                    });


        } else {
            tvShowHideCV.setText("Hide");
            IsShowCV = true;

            System.out.println("33MCVTest.getHeight()=" + MCVTest.getHeight());
            // Prepare the View for the animation
            MCVTest.setVisibility(View.VISIBLE);
            System.out.println("44MCVTest.getHeight()=" + MCVTest.getHeight());
            MCVTest.setAlpha(0.0f);
            // Start the animation
            MCVTest.animate()
                    .translationY(0)
                    .alpha(1.0f)
                    .setListener(null);

        }
    }

    private void llnr_change() {
        if (IsShowD) {
            llnrTest.tv_row_text("ABCCBA !IsShowD");
            llnrTest.view_divider(false);
            IsShowD = false;
        } else {
            llnrTest.tv_row_text("ABCCBA IsShowD");
            llnrTest.view_divider(true);
            IsShowD = true;
        }
    }

    private void setHrMinAMPMData() {
        for (int i = 0; i < 12; i++) {
            HRS.add(i + "");
        }

        for (int i = 0; i < 60; i += 5) {
            if (i < 10) {
                MINS.add("0" + i);
            } else {
                MINS.add(i + "");
            }
        }

        AMPM.add("AM");
        AMPM.add("PM");

    }

}

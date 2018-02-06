package template.cheng.hollis.template.calendar.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import template.cheng.hollis.template.calendar.constant.StyleableConst;
import template.cheng.hollis.template.R;

public class AttributeUtil implements StyleableConst {

    /**
     *
     * @param context
     * @param attrs
     * @return
     */
    public static int[] getAttributes(@NonNull Context context, AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCalendarView, 0, 0);

        try {
            final int calendarBackgroundColor = typedArray.getColor(
                    CALENDAR_BACKGROUND_COLOR,
                    ContextCompat.getColor(context, R.color.White)
            );

            final int calendarTitleBackgroundColor = typedArray.getColor(
                    TITLE_BACKGROUND_COLOR,
                    ContextCompat.getColor(context, R.color.White)
            );

            final int calendarTitleTextColor = typedArray.getColor(
                    TITLE_TEXT_COLOR,
                    ContextCompat.getColor(context, R.color.Black)
            );

            final int weekLayoutBackgroundColor = typedArray.getColor(
                    WEEK_BACKGROUND_COLOR,
                    ContextCompat.getColor(context, R.color.White)
            );

            final int dayOfWeekTextColor = typedArray.getColor(
                    DAY_TEXT_COLOR,
                    ContextCompat.getColor(context, R.color.Black)
            );

            final int disabledDayBackgroundColor = typedArray.getColor(
                    DISABLE_DAY_BACKGROUND_COLOR,
//                    ContextCompat.getColor(context, R.color.day_disabled_background_color)
                    ContextCompat.getColor(context, R.color.White)
            );

            final int disabledDayTextColor = typedArray.getColor(
                    DISABLE_DAY_TEXT_COLOR,
//                    ContextCompat.getColor(context, R.color.day_disabled_text_color)
                    ContextCompat.getColor(context, R.color.White)
            );

            final int selectedDayBackground = typedArray.getColor(
                    SELECTED_DAY_BACKGROUND_COLOR,
                    ContextCompat.getColor(context, R.color.selected_day_background)
            );

            final int selectedDayTextColor = typedArray.getColor(
                    SELECTED_DAY_TEXT_COLOR,
                    ContextCompat.getColor(context, R.color.White)
            );

            final int currentDayOfMonth = typedArray.getColor(
                    CURRENT_DAY_COLOR,
                    ContextCompat.getColor(context, R.color.current_day_of_month)
            );

            final int endOfWeek = typedArray.getColor(
                    END_OF_WEEK_COLOR,
                    ContextCompat.getColor(context, R.color.red)
            );

            return new int[] {
                    calendarBackgroundColor,
                    calendarTitleBackgroundColor,
                    calendarTitleTextColor,
                    weekLayoutBackgroundColor,
                    dayOfWeekTextColor,
                    disabledDayBackgroundColor,
                    disabledDayTextColor,
                    selectedDayBackground,
                    selectedDayTextColor,
                    currentDayOfMonth,
                    endOfWeek
            };

        } finally {
            typedArray.recycle();
        }
    }
}

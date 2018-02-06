package template.cheng.hollis.template.calendar.decor;

import android.support.annotation.NonNull;

import template.cheng.hollis.template.calendar.widget.DayView;

public interface DayDecorator {

    /**
     *
     * @param cell
     */
    void decorate(@NonNull DayView cell);
}
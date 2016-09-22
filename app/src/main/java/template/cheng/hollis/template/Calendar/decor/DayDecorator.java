package template.cheng.hollis.template.Calendar.decor;

import android.support.annotation.NonNull;

import template.cheng.hollis.template.Calendar.widget.DayView;

public interface DayDecorator {

    /**
     *
     * @param cell
     */
    void decorate(@NonNull DayView cell);
}
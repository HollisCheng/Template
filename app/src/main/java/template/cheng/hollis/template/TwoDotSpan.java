package template.cheng.hollis.template;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

/**
 * Created by hollischeng on 20/10/2017.
 */

public class TwoDotSpan implements LineBackgroundSpan {

    /**
     * Default radius used
     */
    public static final float DEFAULT_RADIUS = 3;

    private final float radius;
    private final int color;
    private final int color2;


    public TwoDotSpan() {
        this.radius = DEFAULT_RADIUS;
        this.color = 0;
        this.color2 = 0;
    }

    public TwoDotSpan(int color, int color2) {
        this.radius = DEFAULT_RADIUS;
        this.color = color;
        this.color2 = color2;
    }

    public TwoDotSpan(float radius, int color) {
        this.radius = radius;
        this.color = color;
        this.color2 = 0;
    }

    public TwoDotSpan(float radius, int color, int color2) {
        this.radius = radius;
        this.color = color;
        this.color2 = color2;
    }

    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence,
            int start, int end, int lineNum
    ) {
        int oldColor = paint.getColor();
        if (color != 0) {
            paint.setColor(color);
        }
        canvas.drawCircle(((left + right) / 2) - radius - 1, bottom + radius, radius, paint);

        if (color2 != 0) {
            paint.setColor(color2);
        }
        canvas.drawCircle((left + right) / 2 + radius + 1, bottom + radius, radius, paint);
        paint.setColor(oldColor);
    }
}


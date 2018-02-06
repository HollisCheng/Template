package template.cheng.hollis.template.textView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class RobotoBoldTextView extends TextView {

    public RobotoBoldTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public RobotoBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public RobotoBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }


    private void applyCustomFont(Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf");
        setTypeface(font);
    }

}

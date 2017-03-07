package template.cheng.hollis.template.TextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class SweetSansBoldTextView extends TextView {

    public SweetSansBoldTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public SweetSansBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public SweetSansBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }


    private void applyCustomFont(Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "SweetSans-Bold.otf");
        setTypeface(font);
    }

}

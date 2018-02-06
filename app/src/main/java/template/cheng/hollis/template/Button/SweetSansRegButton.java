package template.cheng.hollis.template.button;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;


public class SweetSansRegButton extends Button {

    public SweetSansRegButton(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public SweetSansRegButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public SweetSansRegButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }


    private void applyCustomFont(Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "SweetSans-Regular.otf");
        setTypeface(font);
    }

}

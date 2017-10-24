package template.cheng.hollis.template.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public abstract class BaseRelativeLayout extends RelativeLayout {

    public BaseRelativeLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public BaseRelativeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BaseRelativeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public abstract void init(AttributeSet attrs, int defStyleAttr);

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getContext();
    }
}

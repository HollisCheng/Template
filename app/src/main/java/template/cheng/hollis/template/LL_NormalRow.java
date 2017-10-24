package template.cheng.hollis.template;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import template.cheng.hollis.template.base.BaseLinearLayout;

/**
 * Created by hollischeng on 24/10/2017.
 */

public class LL_NormalRow extends BaseLinearLayout {

    @BindView(R.id.tv_row_text)
    TextView tv_row_text;
    @BindView(R.id.view_divider)
    View view_divider;

    public LL_NormalRow(Context context) {
        super(context);
    }

    public LL_NormalRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LL_NormalRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(AttributeSet attrs, int defStyleAttr) {
        inflate(getBaseActivity(), R.layout.view_ll_normalrow, this);
        ButterKnife.bind(this);
        if (attrs != null) {
            TypedArray mTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LL_NormalRow);
            String title = mTypedArray.getString(R.styleable.LL_NormalRow_ImageTextTitle);
//            int icon = mTypedArray.getResourceId(R.styleable.LL_NormalRow_ImageTextIcon, 0);
            boolean showDivider = mTypedArray.getBoolean(R.styleable.LL_NormalRow_ImageTextShowDivider, true);

            tv_row_text.setText(title);
//            iv_icon.setImageResource(icon);
            view_divider.setVisibility(showDivider ? View.VISIBLE : View.GONE);
            mTypedArray.recycle();
        }
    }
}

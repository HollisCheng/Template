package template.cheng.hollis.template.tabLayout;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class SweetSansRegTabLayout extends TabLayout {
    private Typeface mTypeface;

    public SweetSansRegTabLayout(Context context) {
        super(context);
        init();
    }

    public SweetSansRegTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SweetSansRegTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        Typeface font = Typeface.createFromAsset(context.getAssets(), "SweetSans-Regular.otf");
//        setTypeface(font);
        mTypeface = Typeface.createFromAsset(getContext().getAssets(), "SweetSans-Regular.otf");
    }
    @Override
    public void setupWithViewPager(ViewPager viewPager)
    {
        super.setupWithViewPager(viewPager);

        if (mTypeface != null)
        {
            this.removeAllTabs();

            ViewGroup slidingTabStrip = (ViewGroup) getChildAt(0);

            PagerAdapter adapter = viewPager.getAdapter();

            for (int i = 0, count = adapter.getCount(); i < count; i++)
            {
                Tab tab = this.newTab();
                this.addTab(tab.setText(adapter.getPageTitle(i)));
                AppCompatTextView view = (AppCompatTextView) ((ViewGroup) slidingTabStrip.getChildAt(i)).getChildAt(1);
                view.setTypeface(mTypeface, Typeface.NORMAL);
            }
        }
    }

//    @Override
//    public void addTab(Tab tab) {
//        super.addTab(tab);
//
//        ViewGroup mainView = (ViewGroup) getChildAt(0);
//        ViewGroup tabView = (ViewGroup) mainView.getChildAt(tab.getPosition());
//
//        int tabChildCount = tabView.getChildCount();
//        for (int i = 0; i < tabChildCount; i++) {
//            View tabViewChild = tabView.getChildAt(i);
//            if (tabViewChild instanceof TextView) {
//                ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
//            }
//        }
//    }


}

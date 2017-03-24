package template.cheng.hollis.template.TestBundlePage;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;
import template.cheng.hollis.template.R;
import template.cheng.hollis.template.Utility;

public class TestBundleActivity extends AppCompatActivity {

    private String BundleString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bundle);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            BundleString = params.getString("BundleString");
        }

        //region SetCustom Toolbar!
        Toolbar tb = (Toolbar) findViewById(R.id.TBToolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("Test Bundle");
        setSupportActionBar(tb);

        // Get the ActionBar here to configure the way it behaves.
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
        if (ab != null) {
            ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
            ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
        }
        //endregion

        Utility.SetTaskBarBackground(this, R.color.green);

        AutoScrollViewPager MeAutoViewPager = (AutoScrollViewPager) findViewById(R.id.MeAutoViewPager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicatorMeAuto);

        ArrayList<MsgInfo> NWCNMeAutoNoticeMsgAL = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MsgInfo msgInfo = new MsgInfo();
            msgInfo.setNoticeName("Notice" + i);
            msgInfo.setDesc("Desc" + i + ":" + BundleString);
            NWCNMeAutoNoticeMsgAL.add(msgInfo);
        }

        MeAutoViewPager.setAdapter(new MeNoticePagerAdapter(this, NWCNMeAutoNoticeMsgAL));

        MeAutoViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        indicator.setViewPager(MeAutoViewPager);
        MeAutoViewPager.setInterval(2000);
        MeAutoViewPager.startAutoScroll();
        MeAutoViewPager.setCurrentItem(0);
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            //Log.w("MF","MP:onPageSelected:position=" + position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
//        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
//            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

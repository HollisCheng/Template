package template.cheng.hollis.template.ui.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import template.cheng.hollis.template.FragmentAdapter;
import template.cheng.hollis.template.R;
import template.cheng.hollis.template.ui.fragment.TestFragment;
import template.cheng.hollis.template.Utility;
import template.cheng.hollis.template.textView.SweetSansRegTextView;
import template.cheng.hollis.template.viewPager.NoSwipeViewPager;

public class ViewPagerActivity extends AppCompatActivity {
    private ImageView tabMe, tabPrivileges, tabFacilities, tabEvents, tabProperties;
    private LinearLayout llTabBarMenu, LLTabMe, LLTabFacilities, LLTabPrivileges, LLTabEvents, LLTabProperties;
    private SweetSansRegTextView ssrvTagMe, ssrvTagFacilities, ssrvTagPrivileges, ssrvTagEvents, ssrvTagProperties;
    private NoSwipeViewPager mPager;
    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        //region SetCustom Toolbar!
        Toolbar tb = (Toolbar) findViewById(R.id.TBToolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);

        toolbar_title.setText(getString(R.string.FEEDB));
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

        resources = getResources();
        llTabBarMenu = (LinearLayout) findViewById(R.id.llTabBarMenu);

        tabMe = (ImageView) findViewById(R.id.tabMe);
        LLTabMe = (LinearLayout) findViewById(R.id.LLTabMe);
        ssrvTagMe = (SweetSansRegTextView) findViewById(R.id.ssrvTagMe);

        tabFacilities = (ImageView) findViewById(R.id.tabFacilities);
        LLTabFacilities = (LinearLayout) findViewById(R.id.LLTabFacilities);
        ssrvTagFacilities = (SweetSansRegTextView) findViewById(R.id.ssrvTagFacilities);

        tabPrivileges = (ImageView) findViewById(R.id.tabPrivileges);
        LLTabPrivileges = (LinearLayout) findViewById(R.id.LLTabPrivileges);
        ssrvTagPrivileges = (SweetSansRegTextView) findViewById(R.id.ssrvTagPrivileges);

//        tabEvents = (ImageView) findViewById(R.id.tabEvents);
//        LLTabEvents = (LinearLayout) findViewById(R.id.LLTabEvents);
//        ssrvTagEvents = (SweetSansRegTextView) findViewById(R.id.ssrvTagEvents);

        tabProperties = (ImageView) findViewById(R.id.tabProperties);
        LLTabProperties = (LinearLayout) findViewById(R.id.LLTabProperties);
        ssrvTagProperties = (SweetSansRegTextView) findViewById(R.id.ssrvTagProperties);

        LLTabMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(0);
            }
        });

        LLTabFacilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(1);
            }
        });

        LLTabPrivileges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPager.setCurrentItem(2);
            }
        });

//        LLTabEvents.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            //3
//                if (Utility.PrimaryEstateID != 0)
//                    mPager.setCurrentItem(DefaultTab);
//            }
//        });

        LLTabProperties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(3);
            }
        });

        //Tabs Me,Facilities,Privileges,Events,Properties
        mPager = (NoSwipeViewPager) findViewById(R.id.TabViewPager);
        if (mPager != null) {
            mPager.setOffscreenPageLimit(4);
            mPager.setPagingEnabled(false);
        }

        //inserting fragment into AL
        ArrayList<Fragment> fragmentsList = new ArrayList<>();
        fragmentsList.add(new TestFragment());
//        if (Utility.PrimaryEstateID != 0)
        fragmentsList.add(new TestFragment());
        fragmentsList.add(new TestFragment());
//        if (Utility.PrimaryEstateID != 0)
//            fragmentsList.add(new EventFragment());
        fragmentsList.add(new TestFragment());

        mPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentsList));
////       //Log.w("ALMP","Utility.currIndex=" + Utility.currIndex);
        mPager.setCurrentItem(Utility.currIndex);
        SW(Utility.currIndex);
        mPager.setOnPageChangeListener(new MyTabOnPageChangeListener());
    }

    public void SW(int arg0) {

        switch (arg0) {
            case 0:
                LLTabMe.setBackground(resources.getDrawable(R.drawable.img_menu_bar_active));
                tabMe.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_share));
                ssrvTagMe.setTextColor(resources.getColor(R.color.TabBarSelectColor));

                LLTabFacilities.setBackgroundColor(resources.getColor(R.color.transparent));
                tabFacilities.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_slideshow));
                ssrvTagFacilities.setTextColor(resources.getColor(R.color.White));

                LLTabPrivileges.setBackgroundColor(resources.getColor(R.color.transparent));
                tabPrivileges.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_gallery));
                ssrvTagPrivileges.setTextColor(resources.getColor(R.color.White));

//                    LLTabEvents.setBackgroundColor(resources.getColor(R.color.transparent));
//                    tabEvents.setImageDrawable(resources.getDrawable(R.drawable.icon_inactive_event));
//                    ssrvTagEvents.setTextColor(resources.getColor(R.color.White));

                LLTabProperties.setBackgroundColor(resources.getColor(R.color.transparent));
                tabProperties.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_manage));
                ssrvTagProperties.setTextColor(resources.getColor(R.color.White));
                mPager.setCurrentItem(arg0);
                break;
            case 1:
                LLTabMe.setBackgroundColor(resources.getColor(R.color.transparent));
                tabMe.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_share));
                ssrvTagMe.setTextColor(resources.getColor(R.color.White));

                LLTabFacilities.setBackground(resources.getDrawable(R.drawable.img_menu_bar_active));
                tabFacilities.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_slideshow));
                ssrvTagFacilities.setTextColor(resources.getColor(R.color.TabBarSelectColor));

                LLTabPrivileges.setBackgroundColor(resources.getColor(R.color.transparent));
                tabPrivileges.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_gallery));
                ssrvTagPrivileges.setTextColor(resources.getColor(R.color.White));

//                    LLTabEvents.setBackgroundColor(resources.getColor(R.color.transparent));
//                    tabEvents.setImageDrawable(resources.getDrawable(R.drawable.icon_inactive_event));
//                    ssrvTagEvents.setTextColor(resources.getColor(R.color.White));

                LLTabProperties.setBackgroundColor(resources.getColor(R.color.transparent));
                tabProperties.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_manage));
                ssrvTagProperties.setTextColor(resources.getColor(R.color.White));
                mPager.setCurrentItem(arg0);
                break;
            case 2:
                LLTabMe.setBackgroundColor(resources.getColor(R.color.transparent));
                tabMe.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_share));
                ssrvTagMe.setTextColor(resources.getColor(R.color.White));

                LLTabFacilities.setBackgroundColor(resources.getColor(R.color.transparent));
                tabFacilities.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_slideshow));
                ssrvTagFacilities.setTextColor(resources.getColor(R.color.White));

                LLTabPrivileges.setBackground(resources.getDrawable(R.drawable.img_menu_bar_active));
                tabPrivileges.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_gallery));
                ssrvTagPrivileges.setTextColor(resources.getColor(R.color.TabBarSelectColor));

//                    LLTabEvents.setBackgroundColor(resources.getColor(R.color.transparent));
//                    tabEvents.setImageDrawable(resources.getDrawable(R.drawable.icon_inactive_event));
//                    ssrvTagEvents.setTextColor(resources.getColor(R.color.White));

                LLTabProperties.setBackgroundColor(resources.getColor(R.color.transparent));
                tabProperties.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_manage));
                ssrvTagProperties.setTextColor(resources.getColor(R.color.White));
                mPager.setCurrentItem(arg0);
                break;
//                case 3:
//                    LLTabMe.setBackgroundColor(resources.getColor(R.color.transparent));
//                    tabMe.setImageDrawable(resources.getDrawable(R.drawable.icon_inactive_me));
//                    ssrvTagMe.setTextColor(resources.getColor(R.color.White));
//
//                    LLTabFacilities.setBackgroundColor(resources.getColor(R.color.transparent));
//                    tabFacilities.setImageDrawable(resources.getDrawable(R.drawable.icon_inactive_facilities));
//                    ssrvTagFacilities.setTextColor(resources.getColor(R.color.White));
//
//                    LLTabPrivileges.setBackgroundColor(resources.getColor(R.color.transparent));
//                    tabPrivileges.setImageDrawable(resources.getDrawable(R.drawable.icon_inactive_privilege));
//                    ssrvTagPrivileges.setTextColor(resources.getColor(R.color.White));
//
//                    LLTabEvents.setBackground(resources.getDrawable(R.drawable.img_menu_bar_active));
//                    tabEvents.setImageDrawable(resources.getDrawable(R.drawable.icon_active_event));
//                    ssrvTagEvents.setTextColor(resources.getColor(R.color.TabBarSelectColor));

//                LLTabProperties.setBackgroundColor(resources.getColor(R.color.transparent));
//                tabProperties.setImageDrawable(resources.getDrawable(R.drawable.icon_inactive_privilege));
//                ssrvTagProperties.setTextColor(resources.getColor(R.color.White));
//                    break;
            //if event show, should be 4
            case 3:
                LLTabMe.setBackgroundColor(resources.getColor(R.color.transparent));
                tabMe.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_share));
                ssrvTagMe.setTextColor(resources.getColor(R.color.White));

                LLTabFacilities.setBackgroundColor(resources.getColor(R.color.transparent));
                tabFacilities.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_slideshow));
                ssrvTagFacilities.setTextColor(resources.getColor(R.color.White));

                LLTabPrivileges.setBackgroundColor(resources.getColor(R.color.transparent));
                tabPrivileges.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_gallery));
                ssrvTagPrivileges.setTextColor(resources.getColor(R.color.White));

//                    LLTabEvents.setBackgroundColor(resources.getColor(R.color.transparent));
//                    tabEvents.setImageDrawable(resources.getDrawable(R.drawable.icon_inactive_event));
//                    ssrvTagEvents.setTextColor(resources.getColor(R.color.White));

                LLTabProperties.setBackground(resources.getDrawable(R.drawable.img_menu_bar_active));
                tabProperties.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_manage));
                ssrvTagProperties.setTextColor(resources.getColor(R.color.TabBarSelectColor));
                break;
        }

    }

    public class MyTabOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            SW(arg0);
//            currIndex = arg0;
            Utility.currIndex = arg0;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        //TODO same delay, maybe use boolean check if propic changed then use NavUtils,,,do after API
//        NavUtils.navigateUpFromSameTask(this);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
//            NavUtils.navigateUpFromSameTask(this);
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
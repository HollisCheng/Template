package template.cheng.hollis.template;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;
import template.cheng.hollis.template.YoutubeAPI.YouFragmentAdapter;

public class YoutubeAPIActivity extends AppCompatActivity {

    private String VideoPath = "https://www.youtube.com/watch?v=8-6Z4gGP1As";
    private String VideoPath2 = "https://www.youtube.com/watch?v=vAZGZ5s44g0";
    private ArrayList<String> VideoAL = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_api);

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

        VideoAL.add(VideoPath);
        VideoAL.add(VideoPath2);
        LinearLayout ownerArea_framelayout = (LinearLayout) findViewById(R.id.ownerArea_framelayout);
        ownerArea_framelayout.setVisibility(View.VISIBLE);
        initViewPager();
    }

    private void initViewPager() {
        ViewPager viewpager = (ViewPager) findViewById(R.id.ownerArea_viewpager);

        ArrayList<String> fragments = new ArrayList<>();
        for (int j = 0; j < VideoAL.size(); j++) {
            fragments.add(Utility.getYouTubeID(VideoAL.get(j)));
        }

        FragmentManager fm = getSupportFragmentManager();
        YouFragmentAdapter pagerAdapter = new YouFragmentAdapter(fm, fragments);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setOffscreenPageLimit(3);
        CircleIndicator circleIndicator = (CircleIndicator) findViewById(R.id.circleIndicator);
        circleIndicator.setViewPager(viewpager);
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

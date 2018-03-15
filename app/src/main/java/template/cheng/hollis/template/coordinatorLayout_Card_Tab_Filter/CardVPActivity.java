package template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;
import template.cheng.hollis.template.ui.adapter.FragmentAdapter;
import template.cheng.hollis.template.objectInfo.CardListInfo;
import template.cheng.hollis.template.R;
import template.cheng.hollis.template.util.Utility;

public class CardVPActivity extends AppCompatActivity {
    private ViewPager CWPager, mPager;
    private CircleIndicator CWCI;
    private FragmentAdapter fragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_vp);

        //region SetCustom Toolbar!
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
//        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);

//        toolbar_title.setText(getString(R.string.FEEDB));
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

        //region for HC design
        CWPager = (ViewPager) findViewById(R.id.CardVPager);
        mPager = (ViewPager) findViewById(R.id.vPager);
        CWCI = (CircleIndicator) findViewById(R.id.CWCI);

        ArrayList<Fragment> cardFragmentsList = new ArrayList<>();

        ArrayList<CardListInfo> OwnerCardListAL = new ArrayList<>();
        OwnerCardListAL.add(new CardListInfo("123132132","ph","Hollis","Cheng"));
        OwnerCardListAL.add(new CardListInfo("213214144","viva","Hollis","Cheng"));


        for (int i = 0; i < OwnerCardListAL.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString("cardsUserName",OwnerCardListAL.get(i).getUserName());
            bundle.putString("cardNo",OwnerCardListAL.get(i).getCardNo());
            bundle.putString("cardType", OwnerCardListAL.get(i).getCardType());

//        for (int i = 0; i < 2; i++) {
//            Bundle bundle = new Bundle();
//            if (i % 2 == 0) {
//                bundle.putString("cardsName", Utility.estateInfo.getEstateCode().toLowerCase());
////                bundle.putString("cardsPts", "180");
//                bundle.putInt("cardsID", i);
//            } else {
//                bundle.putString("cardsName", "nwc");
////                bundle.putString("cardsPts", "0");
//                bundle.putInt("cardsID", i);
//            }

            CardsFragment cardsFragment = new CardsFragment();
            cardsFragment.setArguments(bundle);
            cardFragmentsList.add(cardsFragment);

        }
        //region maybe phrase 1.2
        //last card must be addCardFragment
        //no change dp in addCardsFragment but display same
        //no add card now, all fixed, phrase 1.1 maybe add it back
//        final AddCardsFragment addCardsFragment = new AddCardsFragment();
//        CardFragmentsList.add(addCardsFragment);
        //endregion

//        fragmentAdapter = new FragmentAdapter(getFragmentManager(), cardFragmentsList);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), cardFragmentsList);
        CWPager.setAdapter(fragmentAdapter);
        //ZoomOut Animation
        CWPager.setPageTransformer(true, new ZoomOutSlide());
        CWPager.setCurrentItem(0);
        CWPager.setClipChildren(false);
        CWPager.setClipToPadding(false);
        int PageMargin = Utility.GetMobilePageMargin(this);
        CWPager.setPageMargin(-PageMargin);

        CWCI.setViewPager(CWPager);

        CWPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Log.w("PF", "position=" + position);

//                Utility.cardIDValue = position;
//
//                if (EventListener.getCardSelectedListener() != null) {
//                    EventListener.getCardSelectedListener().eventOccurred(position);
//                }
                if (PrivilegesCardListener.getViewPagerCardListener() != null) {
                    PrivilegesCardListener.getViewPagerCardListener().eventOccurred(position);
                }
//
//                //Latest
//                if (EventListener.getViewPagerSelectedListener() != null) {
//                    EventListener.getViewPagerSelectedListener().eventOccurred(position);
//                }
//
//                //Nearby
//                if (PrivilegesNearbyListener.getViewPagerSelectedListener() != null) {
//                    PrivilegesNearbyListener.getViewPagerSelectedListener().eventOccurred(position);
//                }
//
//                //Bookmarked
//                if (PrivilegesBookmarkedListener.getViewPagerSelectedListener() != null) {
//                    PrivilegesBookmarkedListener.getViewPagerSelectedListener().eventOccurred(position);
//                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //endregion

        ArrayList<Fragment> fragmentsList = new ArrayList<>();
        CardWalletOffersFragment cardWalletOffersFragment = new CardWalletOffersFragment();
        fragmentsList.add(cardWalletOffersFragment);
        mPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentsList));
        mPager.setCurrentItem(0);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}

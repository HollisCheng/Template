package template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import template.cheng.hollis.template.R;
import template.cheng.hollis.template.button.SweetSansRegButton;
import template.cheng.hollis.template.tabLayout.SweetSansRegTabLayout;
import template.cheng.hollis.template.tabLayout.TabsAdapter;
import template.cheng.hollis.template.ui.fragment.TestFragment;
import template.cheng.hollis.template.Utility;


public class CardWalletOffersFragment extends Fragment {

    //    private UpdateEvent updateEvent;
    private LinearLayout llCardWalletFragment;
    //    private RelativeLayout rlAddCardView;
    private UpdateEvent updateEvent;
    private TabsAdapter adapter;
    private ViewPager OffersTabsViewPager;
    private SweetSansRegTabLayout OffersTabs;
    private TextView tvPrivilegesT, tvSearchFilter;
    private ImageView ivFilter;
    private ListAdapter LAAdapter;
    private boolean Is1st = true;
    private Dialog dialog;
    private String SearchWords, SearchCategory, SearchNearby, SearchPeriod;
    private ArrayList<String> FilterAL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cardwallet_fragment, container, false);

//        EventBus.getDefault().register(this);

        tvPrivilegesT = (TextView) view.findViewById(R.id.tvPrivilegesT);
        //PListKeyWordsSearch
//        tvSearchFilter = (TextView) view.findViewById(R.id.tvSearchFilter);
        ivFilter = (ImageView) view.findViewById(R.id.ivFilter);
        //make when scroll to add card display no offers at below,,maybe add back later
//        updateEvent = new UpdateEvent();
//        EventListener.setCardSelectedListener(updateEvent);
        updateEvent = new UpdateEvent();
        PrivilegesCardListener.setViewPagerCardListener(updateEvent);
        OffersTabsViewPager = (ViewPager) view.findViewById(R.id.OffersTabsViewPager);
        adapter = new TabsAdapter(getFragmentManager());
        llCardWalletFragment = (LinearLayout) view.findViewById(R.id.llCardWalletFragment);
//        rlAddCardView = (RelativeLayout) view.findViewById(R.id.rlAddCardView);
        adapter.addFragment(new TestFragment(), getString(R.string.LATEST));
        //phrase 1.1
//        adapter.addFragment(new MyOffersNearbyFragment(), getString(R.string.NEARBY));
        adapter.addFragment(new TestFragment(), getString(R.string.BOOKMARKED));
        OffersTabsViewPager.setAdapter(adapter);
        OffersTabsViewPager.setOffscreenPageLimit(3);
        OffersTabs = (SweetSansRegTabLayout) view.findViewById(R.id.OffersTabs);
        OffersTabs.setupWithViewPager(OffersTabsViewPager);

        FilterAL = new ArrayList<>();
        FilterAL.add(getString(R.string.All_privileges));
//        for (int i = 0; i < Utility.PrivilegeFilterInfoAL.size(); i++) {
//            FilterAL.add(Utility.PrivilegeFilterInfoAL.get(i).getName());
//        }
        for (int i = 0; i < 5; i++) {
            FilterAL.add("Filter" + i);
        }

        OffersTabsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                //Log.w("CWOF", "IsBookmarked" + Utility.IsBookmarked);
//                if (Utility.IsBookmarked) {
                //Log.w("CWOF", "onTabSelected:update,position=" + position);
//                    update();
//                    Utility.IsBookmarked = false;
                OffersTabs.getTabAt(position).select();
//                }

//                if (position == 0) {
//                    Log.w("PF", "OnPageChangeListener");
//                    Tracker t = ((AnalyticsApplication) getActivity().getApplication()).getTracker();
//                    t.setScreenName("Privileges-Recommend");
//                    t.send(new HitBuilders.ScreenViewBuilder().build());
//                } else if (position == 1) {
//
//                    Tracker t = ((AnalyticsApplication) getActivity().getApplication()).getTracker();
//                    t.setScreenName("Privileges-Bookmark");
//                    t.send(new HitBuilders.ScreenViewBuilder().build());
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //region Search Filter

//        tvSearchFilter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                KeySearchFilterDialog();
//            }
//        });

        //endregion

        //region Filter
        ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryFilterDialog();
            }
        });

        //endregion

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (updateEvent != null) {
            PrivilegesCardListener.setViewPagerCardListener(null);
            updateEvent = null;
            //Log.w("CWOF", "setViewPagerCardListener = null!");
        }
    }

    class UpdateEvent implements PrivilegesCardListener.ViewPagerCardListener {
        @Override
        public void eventOccurred(int value) {
//            Log.w("CWOF", "CWOF:Listener pass the card value = " + value);
//            Log.w("CWOF", "Utility.cardID=" + Utility.cardIDValue);
            //default the filter
//            Utility.PLCategoryCode = "";
//            Utility.PLIsFilter = false;
            Is1st = true;
            update();
//            adapter = new MyOffersAdapter(getContext(), mData);
//            mRecyclerView.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
        }
    }

//    @Subscribe
//    public void onMessageEvent(ChangeLanguageBus event) {
//        //Log.w("CWOF", "onMessageEvent");
//        UpdateText();
//    }

//    private void UpdateText() {
//        tvPrivilegesT.setText(R.string.PO);
//
//        FilterAL = new ArrayList<>();
//        FilterAL.add(getString(R.string.All_privileges));
//        for (int i = 0; i < Utility.PrivilegeFilterInfoAL.size(); i++) {
//            FilterAL.add(Utility.PrivilegeFilterInfoAL.get(i).getName());
//        }
//        Utility.PLCategoryCode = "";
//        Utility.PLIsFilter = false;
//        Is1st = true;
//
////        Log.w("CWOF", "FilterAL=" + FilterAL);
//        update();
//    }

    private void update() {
        adapter = new TabsAdapter(getFragmentManager());
        adapter.addFragment(new TestFragment(), getString(R.string.LATEST));
        adapter.addFragment(new TestFragment(), getString(R.string.BOOKMARKED));
        OffersTabsViewPager.setAdapter(adapter);
        OffersTabsViewPager.setOffscreenPageLimit(3);
        OffersTabs.setupWithViewPager(OffersTabsViewPager);
    }

    private void clickFilter() {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
//                                builderSingle.setIcon(R.drawable.icon_main_hotspots_contact);
//        builderSingle.setTitle(R.string.PFilter);
//                                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(OffersActivity.this, android.R.layout.select_dialog_singlechoice);

        if (Is1st) {
            LAAdapter = new CallAdapter(getContext(), FilterAL, 1);
            Is1st = false;
        }

        builderSingle.setNegativeButton(R.string.bookeddetail_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(LAAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                                    String strName = arrayAdapter.getItem(which);
                Log.w("CWOF", "which=" + which);
                if (which == 0) {
//                    Utility.PLCategoryCode = "";
//                    Utility.PLIsFilter = false;
                    Is1st = true;
                    LAAdapter = new CallAdapter(getContext(), FilterAL, 1);
                    update();
                } else {
                    try {
                        //disable other filter
//                        Utility.PLSearchFilter = false;
//                        Log.w("CWOF", "Utility.PrivilegeFilterInfoAL.get(which).getCode()=" + Utility.PrivilegeFilterInfoAL.get(which - 1).getCode());
//                        Utility.PLCategoryCode = Utility.PrivilegeFilterInfoAL.get(which - 1).getCode();
//                        Utility.PLIsFilter = true;
                        LAAdapter = new CallAdapter(getContext(), FilterAL, which + 1);
                        update();
                    } catch (SecurityException e) {
                        //Log.w("OA", e.toString());
                    }
//                                AlertDialog.Builder builderInner = new AlertDialog.Builder(OffersActivity.this);
//                                builderInner.setMessage(strName);
//                                builderInner.setTitle("Your Selected Item is");
//                                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                dialog.dismiss();
////
//                                            }
//                                        });
//                                builderInner.show();
                }
            }
        });
        builderSingle.show();
    }

    private void CategoryFilterDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.search_category_dialog_layout, null);
        TextView tvCancelFilter = (TextView) layout.findViewById(R.id.tvCancelFilter);
        final ListView lvCategorySearch = (ListView) layout.findViewById(R.id.lvCategorySearch);
        if (Is1st) {
            LAAdapter = new CallAdapter(getContext(), FilterAL, 1);
            Is1st = false;
        }
        lvCategorySearch.setAdapter(LAAdapter);
        lvCategorySearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                        Log.w("CWOF", "position=" + position);
                                                        if (position == 0) {
//                                                            Utility.PLCategoryCode = "";
//                                                            Utility.PLIsFilter = false;
                                                            Is1st = true;
                                                            LAAdapter = new CallAdapter(getContext(), FilterAL, 1);
                                                            lvCategorySearch.setAdapter(LAAdapter);
                                                            update();
                                                            dialog.dismiss();
                                                        } else {
                                                            try {
                                                                //disable other filter
//                                                                Utility.PLSearchFilter = false;
//                                                                Log.w("CWOF", "Utility.PrivilegeFilterInfoAL.get(position).getCode()=" + Utility.PrivilegeFilterInfoAL.get(position - 1).getCode());
//                                                                Utility.PLCategoryCode = Utility.PrivilegeFilterInfoAL.get(position - 1).getCode();
//                                                                Utility.PLIsFilter = true;
                                                                LAAdapter = new CallAdapter(getContext(), FilterAL, position + 1);
                                                                lvCategorySearch.setAdapter(LAAdapter);
                                                                update();
                                                                dialog.dismiss();
                                                            } catch (SecurityException e) {
                                                                Log.w("CWOF", e.toString());
                                                            }
                                                        }
                                                    }
                                                }
        );

        tvCancelFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog = ShowDialog(getContext(), layout);
    }

    private void KeySearchFilterDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.search_dialog_layout, null);
        final EditText etSearch = (EditText) layout.findViewById(R.id.etSearch);
        TextView tvResetFilter = (TextView) layout.findViewById(R.id.tvResetFilter);
        TextView tvCancelFilter = (TextView) layout.findViewById(R.id.tvCancelFilter);
        Spinner SpinnerCategory = (Spinner) layout.findViewById(R.id.SpinnerCategory);
        Spinner SpinnerNearby = (Spinner) layout.findViewById(R.id.SpinnerNearby);
        Spinner SpinnerPeriod = (Spinner) layout.findViewById(R.id.SpinnerPeriod);
        SweetSansRegButton ssrbSearch = (SweetSansRegButton) layout.findViewById(R.id.ssrbSearch);

        //SpinnerCategory
//        final List<String> Category = new ArrayList<>();
//        for (int i = 0; i < Utility.PrivilegeFilterInfoAL.size(); i++) {
//            Category.add(Utility.PrivilegeFilterInfoAL.get(i).getName());
//        }

//        HintSpinner<String> hintCategorySpinner = new HintSpinner<>(
//                SpinnerCategory,
//                new HintAdapter<>(getContext(), R.string.Category, Category),
//                new HintSpinner.Callback<String>() {
//                    @Override
//                    public void onItemSelected(int position, String itemAtPosition) {
//                        for (int i = 0; i < Utility.PrivilegeFilterInfoAL.size(); i++) {
//                            if (Category.get(position).equals(Utility.PrivilegeFilterInfoAL.get(i).getName())) {
//                                SearchCategory = Utility.PrivilegeFilterInfoAL.get(i).getCode();
//                            }
//                        }
//                    }
//                });
//        hintCategorySpinner.init();
//
//        //SpinnerNearby
//        final List<String> Nearby = new ArrayList<>();
//        for (int i = 0; i < Utility.PrivilegeFilterNearByInfoAL.size(); i++) {
//            Nearby.add(Utility.PrivilegeFilterNearByInfoAL.get(i).getName());
//        }
//
//        HintSpinner<String> hintNearbySpinner = new HintSpinner<>(
//                SpinnerNearby,
//                new HintAdapter<>(getContext(), R.string.Nearby, Nearby),
//                new HintSpinner.Callback<String>() {
//                    @Override
//                    public void onItemSelected(int position, String itemAtPosition) {
//                        for (int i = 0; i < Utility.PrivilegeFilterNearByInfoAL.size(); i++) {
//                            if (Nearby.get(position).equals(Utility.PrivilegeFilterNearByInfoAL.get(i).getName())) {
//                                SearchNearby = Utility.PrivilegeFilterNearByInfoAL.get(i).getCode();
//                            }
//                        }
//                    }
//                });
//        hintNearbySpinner.init();
//
//        //SpinnerPeriod
//        final List<String> Period = new ArrayList<>();
//        for (int i = 0; i < Utility.PrivilegeFilterPeriodInfoAL.size(); i++) {
//            Period.add(Utility.PrivilegeFilterPeriodInfoAL.get(i).getName());
//        }
//
//        HintSpinner<String> hintPeriodSpinner = new HintSpinner<>(
//                SpinnerPeriod,
//                new HintAdapter<>(getContext(), R.string.Period, Period),
//                new HintSpinner.Callback<String>() {
//                    @Override
//                    public void onItemSelected(int position, String itemAtPosition) {
//                        for (int i = 0; i < Utility.PrivilegeFilterPeriodInfoAL.size(); i++) {
//                            if (Period.get(position).equals(Utility.PrivilegeFilterPeriodInfoAL.get(i).getName())) {
//                                SearchPeriod = Utility.PrivilegeFilterPeriodInfoAL.get(i).getCode();
//                            }
//                        }
//                    }
//                });
//        hintPeriodSpinner.init();

        ssrbSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if all empty , do nothing
                SearchWords = etSearch.getText().toString();
                Log.w("CWOF", "SearchWords=" + SearchWords + ",SearchCategory=" + SearchCategory + ",SearchNearby=" + SearchNearby + ",SearchPeriod=" + SearchPeriod);
                if (SearchWords.isEmpty() && SearchCategory.isEmpty() && SearchNearby.isEmpty() && SearchPeriod.isEmpty()) {

                } else {
                    //reset other filter
//                    Utility.PLCategoryCode = "";
//                    Utility.PLIsFilter = false;
                    //set search filter info
//                    Utility.PLSearchFilter = true;
//                    Utility.FilerKeyWords = SearchWords;
//                    Utility.FilterCaCode = SearchCategory;
//                    Utility.FilterNCode = SearchNearby;
//                    Utility.FilterPCode = SearchPeriod;
                    //TODO refresh myoffersbookmarkedfragment and myofferslatestfragment
//                if (UpdatePListFilterListener.getViewPagerCardListener() != null) {
//                    UpdatePListFilterListener.getViewPagerCardListener().eventOccurred(SearchWords, SearchCategory, SearchNearby, SearchPeriod);
//                }
                    update();
                }

                dialog.dismiss();
            }
        });

        tvResetFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Utility.PLSearchFilter = false;
//                Utility.FilerKeyWords = "";
//                Utility.FilterCaCode = "";
//                Utility.FilterNCode = "";
//                Utility.FilterPCode = "";
                dialog.dismiss();
                update();
            }
        });

        tvCancelFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

//        if (Utility.PLSearchFilter) {
//            etSearch.setText(Utility.FilerKeyWords);
//
//            for (int i = 0; i < Utility.PrivilegeFilterInfoAL.size(); i++) {
//                if (Utility.FilterCaCode.equals(Utility.PrivilegeFilterInfoAL.get(i).getCode())) {
//                    SpinnerCategory.setSelection(i);
//                }
//            }
//            for (int i = 0; i < Utility.PrivilegeFilterNearByInfoAL.size(); i++) {
//                if (Utility.FilterNCode.equals(Utility.PrivilegeFilterNearByInfoAL.get(i).getCode())) {
//                    SpinnerNearby.setSelection(i);
//                }
//            }
//            for (int i = 0; i < Utility.PrivilegeFilterPeriodInfoAL.size(); i++) {
//                if (Utility.FilterPCode.equals(Utility.PrivilegeFilterPeriodInfoAL.get(i).getCode())) {
//                    SpinnerPeriod.setSelection(i);
//                }
//            }
//        }

//                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(display.getWidth() * 100 / 100, display.getHeight() * 100 / 100);
//                image.setLayoutParams(layoutParams);
//                image.setPadding(0, 0, 0, 0);
//        Log.w("ALMP", "image.getWidth():" + image.getWidth());
//        Log.w("ALMP", "image.getHeight():" + image.getHeight());

        dialog = ShowDialog(getContext(), layout);

    }

    private Dialog ShowDialog(Context context, View rootView) {
        rootView.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT));
        //LayoutInflater inflater = getLayoutInflater();
        //View layout = inflater.inflate(R.layout.go_pro_dialog_layout, null);
        //ImageView image = (ImageView) layout.findViewById(R.id.goProDialogImage);
        Dialog dialog = new Dialog(context, R.style.popupDialogStyle);

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        //params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        //params.height = WindowManager.LayoutParams.WRAP_CONTENT;


        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.Transparent));
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(rootView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        dialog.getWindow().setGravity(Gravity.BOTTOM);

        Display display = Utility.GetScreenSize(context);
        //image.setMinimumWidth(display.getWidth() * 90 / 100);
//        params.y = display.getHeight() * 9 / 100;
        params.width = display.getWidth() * 100 / 100;
//        params.height = display.getHeight() * 100 / 100;
        //params.height = 200;
        //params.alpha = 0.7f;
        dialog.getWindow().setAttributes(params);
        dialog.show();

//        Utility.IsShowed = true;

        return dialog;
    }
}


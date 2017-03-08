package template.cheng.hollis.template.CoordinatorLayout_Card_Tab_Filter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import template.cheng.hollis.template.ImageView.CircleImageView;
import template.cheng.hollis.template.MapInsideScrollFragment;
import template.cheng.hollis.template.R;
import template.cheng.hollis.template.Utility;
import template.cheng.hollis.template.WebConnect.WebViewClientPage;

public class CoordinatorActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;
    private OffersInfo offersInfo;
    private int PrivilegeID;
    private boolean isReady = false;
    private ArrayList<ShopList> MapShopListAL;
    private LinearLayout LLLoopAddress, LLBottomOffer;
    private ImageView IVBackground, ivSoldOut, IVBookmarked;
    private CircleImageView view;
    private NestedScrollView scroll;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 2;
    private ProgressBar offerBookmarkPB;
//    private CallbackManager callbackManager;
//    private ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

//        EventBus.getDefault().register(this);

//        Bundle params = getIntent().getExtras();
//        if (params != null) {
//            PrivilegeID = params.getInt("PrivilegeID");
//        }
//        Log.w("OA","PrivilegeID=" + PrivilegeID);
//        Utility.showLoadingDialog(this, this.getString(R.string.Loading));

        ArrayList<IconList> ILAL = new ArrayList<>();
        ILAL.add(new IconList("2215005", "PH", "柏𣾷", "Christy"));
        ArrayList<ShopList> SLAL = new ArrayList<>();
        SLAL.add(new ShopList("APM   2舖", "中環分行", "26963918", 56, 22.2691996, 114.2449821));
        SLAL.add(new ShopList("APM   3舖", "APM", "31481082", 57, 22.3157254, 114.1352665));
        offersInfo = new OffersInfo(1, "category", "categoryGA", "Desc", "distance", "endDate", new ArrayList<IconList>(), false, false, false, false,
                false, 1, "Merchant_Name", "Merchant_PhotoPath", "Merchant_Status", "Name", "OfferPIN", "OfferRefNo", "OfferStatus",
                "PhotoPath", "PhotoPathIn", "PostDate", 1, "ReferralCode", 1, 1, new ArrayList<ShopList>(), "StartDate", "Status",
                "TCLink", 1, "WebSite", 1 + 0.1, 1 + 0.1, "NameGA", 1, "", "");

        MapShopListAL = offersInfo.getShopList();
        PrivilegeID = offersInfo.getPrivilegeID();

        isReady = true;

//        GetPrivilegeDetail();

//        Tracker t = ((AnalyticsApplication) this.getApplication()).getTracker();
//        t.setScreenName("Privileges-Detail-" + offersInfo.getEngTitle());
//        t.send(new HitBuilders.ScreenViewBuilder().build());

        //region Setting

        scroll = (NestedScrollView) findViewById(R.id.scroll);

        //for Google Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        ((MapInsideScrollFragment) getSupportFragmentManager().findFragmentById(R.id.map)).setListener(new MapInsideScrollFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                scroll.requestDisallowInterceptTouchEvent(true);
            }
        });
        mapFragment.getMapAsync(this);

        LinearLayout llMap = (LinearLayout) findViewById(R.id.llMap);
        //setInfo
        TextView TVOfferSide = (TextView) findViewById(R.id.TVOfferSide);
        IVBackground = (ImageView) findViewById(R.id.IVBackground);
        IVBookmarked = (ImageView) findViewById(R.id.IVBookmarked);
        ivSoldOut = (ImageView) findViewById(R.id.ivSoldOut);
        view = (CircleImageView) findViewById(R.id.view);
        TextView tvOfferName = (TextView) findViewById(R.id.tvOfferName);
//        TextView tvOfferPartner = (TextView) findViewById(R.id.tvOfferPartner);
        TextView tvOfferDateFrom = (TextView) findViewById(R.id.tvOfferDateFrom);
        TextView tvOfferDateTo = (TextView) findViewById(R.id.tvOfferDateTo);
        LinearLayout llWebSite = (LinearLayout) findViewById(R.id.llWebSite);
        TextView tvOfferLink = (TextView) findViewById(R.id.tvOfferLink);
        TextView tvOfferDesc = (TextView) findViewById(R.id.tvOfferDesc);
        TextView tvTandC = (TextView) findViewById(R.id.tvTandC);
        TextView tvViewCount = (TextView) findViewById(R.id.tvViewCount);
        offerBookmarkPB = (ProgressBar) findViewById(R.id.offerBookmarkPB);
        LLBottomOffer = (LinearLayout) findViewById(R.id.LLBottomOffer);

        if (TVOfferSide != null) {
            try {
                TVOfferSide.setText(offersInfo.getIconList().get(0).getName());
            } catch (Exception e) {
                TVOfferSide.setText("");
            }
        }
        if (offersInfo.isBookmarked()) {
            if (IVBookmarked != null) {
                IVBookmarked.setImageResource(R.drawable.icon_active_bookmark);
            }
        } else {
            if (IVBookmarked != null) {
                IVBookmarked.setImageResource(R.drawable.icon_inactive_bookmark);
            }
        }

        if (offersInfo.getRemainingQuota() == 0 && !offersInfo.getOfferPIN().isEmpty()) {
            ivSoldOut.setVisibility(View.VISIBLE);
        }

        Thread thread = new Thread() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Glide.with(CoordinatorActivity.this).load(offersInfo.getPhotoPath())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .centerCrop()
                                .error(R.drawable.img_card_demo)
                                .into(IVBackground);

                        Glide.with(CoordinatorActivity.this).load(offersInfo.getMerchant_PhotoPath())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .centerCrop()
                                .error(R.drawable.img_card_demo_logo)
                                .into(view);
                    }
                });
            }
        };
        thread.start();


        if (tvOfferName != null) {
            tvOfferName.setText(offersInfo.getName());
        }
//        tvOfferPartner.setText(offersInfo.getMerchant_Name());
        if (tvOfferDesc != null) {
            tvOfferDesc.setText(offersInfo.getDescription());
        }
        if (tvOfferDateFrom != null) {
            tvOfferDateFrom.setText(offersInfo.getStartDate());
        }
        if (tvOfferDateTo != null) {
            tvOfferDateTo.setText(offersInfo.getEndDate());
        }
        if (tvOfferLink != null) {
            if (offersInfo.getWebSite().isEmpty()) {
                llWebSite.setVisibility(View.GONE);
            } else {
                String htmlString = "<u>" + offersInfo.getWebSite() + "</u>";
                tvOfferLink.setText(Html.fromHtml(htmlString));
                tvOfferLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CoordinatorActivity.this, WebViewClientPage.class);
                        intent.putExtra("LINK", offersInfo.getWebSite());
                        intent.putExtra("Title", offersInfo.getMerchant_Name());
                        startActivity(intent);
                    }
                });
            }
        }

        if (!checkXYIsShow()) {
            llMap.setVisibility(View.GONE);
        }


        if (tvViewCount != null) {
            tvViewCount.setText(offersInfo.getViewCount() + "");
        }

        if (tvTandC != null) {
            tvTandC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CoordinatorActivity.this, WebViewClientPage.class);
                    intent.putExtra("LINK", offersInfo.getTCLink());
                    intent.putExtra("Title", getString(R.string.TNC));
                    startActivity(intent);
                }
            });
        }

//        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), "OfferActivity:image");
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitle(offersInfo.getName());
            collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
            collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.Black));
            collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.transparent));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onBackPressed();
//                }
//            });
//        }
        setSupportActionBar(toolbar);

        // Get the ActionBar here to configure the way it behaves.
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
        if (ab != null) {
            ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
            ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
        }

//        Utility.dismissLoadingDialog();
        //endregion

        //isResident got from internet
        boolean isResident;
        isResident = true;

        //region LLBookmark
        LinearLayout llOfferBookmark = (LinearLayout) findViewById(R.id.llOfferBookmark);
        View viewOfferBookmarkLine = findViewById(R.id.viewOfferBookmarkLine);
        if (isResident) {
            if (llOfferBookmark != null) {
                llOfferBookmark.setVisibility(View.VISIBLE);
            }
            if (llOfferBookmark != null) {
                llOfferBookmark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (offersInfo.isBookmarked()) {

                            Utility.mTracker.send(new HitBuilders.EventBuilder()
                                    .setCategory("Privileges")
                                    .setAction("UnBookmark")
                                    .setLabel(offersInfo.getNameGA() + "")
                                    .setValue(100)
                                    .build());
                            IVBookmarked.setVisibility(View.INVISIBLE);
                            offerBookmarkPB.setVisibility(View.VISIBLE);
                            //POST update
//                            try {
//                                JSONObject jsonObj = new JSONObject();
//                                jsonObj.put("MemberID", Utility.userData.getMemberID());
//                                jsonObj.put("PrivilegeID", offersInfo.getPrivilegeID());
//                                jsonObj.put("IsBookmarked", false);
//                                jsonObj.put("CreatedBy", Utility.userData.getName());
//                                jsonObj.put("IsClub", offersInfo.isClub());
//                                JSONObject privilege = new JSONObject();
//                                privilege.put("privilege", jsonObj);
//
////                                Log.w("OA", "HTTP REQ JSON=" + Utility.ipURL + "UpdatePrivilegeBookmark" + ",JSON=" + privilege.toString());
//                                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, Utility.ipURL + "UpdatePrivilegeBookmark", privilege, new Response.Listener<JSONObject>() {
//                                    @Override
//                                    public void onResponse(JSONObject response) {
//                                        try {
//                                            offersInfo.setBookmarked(false);
//                                            offerBookmarkPB.setVisibility(View.INVISIBLE);
//                                            IVBookmarked.setVisibility(View.VISIBLE);
//                                            IVBookmarked.setImageResource(R.drawable.icon_inactive_bookmark);
//                                            Log.w("MsgA", "CreateNoticeRead:onResponse=" + response.toString());
//                                        } catch (Exception e) {
//                                            Log.w("MOA", "UpdatePrivilegeBookmark" + e.toString());
//                                        }
//                                    }
//                                }, new Response.ErrorListener() {
//
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//                                        Log.w("MOA", "UpdatePrivilegeBookmark:onError:" + error.toString());
//                                    }
//                                }) {
//                                    @Override
//                                    public Map<String, String> getHeaders() throws AuthFailureError {
//                                        Map<String, String> params = new HashMap<>();
//                                        params.put("Content-Type", "application/json; charset=utf-8");
//                                        params.put("Accept-Type", "application/json; charset=utf-8");
//                                        return params;
//                                    }
//                                };
//                                Utility.mQueue.add(Utility.SetVolleyTimeoutAndNoCache(jsonRequest));
//                            } catch (Exception e) {
//                                Log.w("MOA", "UpdatePrivilegeBookmark:Catch" + e.toString());
//                            }

                        } else {
//                            Utility.mTracker.send(new HitBuilders.EventBuilder()
//                                    .setCategory("Privileges")
//                                    .setAction("Bookmark")
//                                    .setLabel(offersInfo.getEngTitle() + "")
//                                    .setValue(100)
//                                    .build());

                            IVBookmarked.setVisibility(View.INVISIBLE);
                            offerBookmarkPB.setVisibility(View.VISIBLE);
                            //POST update
//                            try {
//                                JSONObject jsonObj = new JSONObject();
//                                jsonObj.put("MemberID", Utility.userData.getMemberID());
//                                jsonObj.put("PrivilegeID", offersInfo.getPrivilegeID());
//                                jsonObj.put("IsBookmarked", true);
//                                jsonObj.put("CreatedBy", Utility.userData.getName());
//                                jsonObj.put("IsClub", offersInfo.isClub());
//                                JSONObject privilege = new JSONObject();
//                                privilege.put("privilege", jsonObj);
//
////                                Log.w("OA", "HTTP REQ JSON=" + Utility.ipURL + "UpdatePrivilegeBookmark" + ",JSON=" + privilege.toString());
//                                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, Utility.ipURL + "UpdatePrivilegeBookmark", privilege, new Response.Listener<JSONObject>() {
//                                    @Override
//                                    public void onResponse(JSONObject response) {
//                                        try {
//                                            offersInfo.setBookmarked(true);
//                                            offerBookmarkPB.setVisibility(View.INVISIBLE);
//                                            IVBookmarked.setVisibility(View.VISIBLE);
//                                            IVBookmarked.setImageResource(R.drawable.icon_active_bookmark);
////                        Log.w("MsgA","CreateNoticeRead:onResponse=" + response.toString());
//                                        } catch (Exception e) {
////                                            Log.w("MOA", "UpdatePrivilegeBookmark" + e.toString());
//                                        }
//                                    }
//                                }, new Response.ErrorListener() {
//
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
////                                        Log.w("MOA", "UpdatePrivilegeBookmark:onError:" + error.toString());
//                                    }
//                                }) {
//                                    @Override
//                                    public Map<String, String> getHeaders() throws AuthFailureError {
//                                        Map<String, String> params = new HashMap<>();
//                                        params.put("Content-Type", "application/json; charset=utf-8");
//                                        params.put("Accept-Type", "application/json; charset=utf-8");
//                                        return params;
//                                    }
//                                };
//                                Utility.mQueue.add(Utility.SetVolleyTimeoutAndNoCache(jsonRequest));
//                            } catch (Exception e) {
////                                Log.w("MOA", "UpdatePrivilegeBookmark:Catch" + e.toString());
//                            }
                        }
//                        Utility.IsBookmarked = true;
                    }
                });
            }

            if (viewOfferBookmarkLine != null) {
                viewOfferBookmarkLine.setVisibility(View.VISIBLE);
            }
        } else {
            if (llOfferBookmark != null) {
                llOfferBookmark.setVisibility(View.GONE);
            }
            if (viewOfferBookmarkLine != null) {
                viewOfferBookmarkLine.setVisibility(View.GONE);
            }
        }
        //endregion

        //region Call button
        LinearLayout llOfferCall = (LinearLayout) findViewById(R.id.llOfferCall);

        if (llOfferCall != null) {
            llOfferCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (MapShopListAL.size() > 1) {
                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(CoordinatorActivity.this);
//                                builderSingle.setIcon(R.drawable.icon_main_hotspots_contact);
                        builderSingle.setTitle(R.string.PSTOCN);
//                                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(CoordinatorActivity.this, android.R.layout.select_dialog_singlechoice);
                        ArrayList<String> callPhoneAL = new ArrayList<>();
                        for (int i = 0; i < MapShopListAL.size(); i++) {
                            callPhoneAL.add(MapShopListAL.get(i).getName() + MapShopListAL.get(i).getPhone());
                        }
                        ListAdapter adapter = new CallAdapter(CoordinatorActivity.this, callPhoneAL);


                        builderSingle.setNegativeButton(R.string.bookeddetail_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builderSingle.setAdapter(adapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                    String strName = arrayAdapter.getItem(which);
                                try {
//                                Log.w("OA", "which=" + which + "MapShopListAL.get(which).getPhone()=" + MapShopListAL.get(which).getPhone());
//                                        Intent intent = new Intent(Intent.ACTION_CALL);
//                                        intent.setData(Uri.parse("tel:" + strName));
//                                        startActivity(intent);
                                    Intent dial = new Intent();
                                    dial.setAction("android.intent.action.DIAL");
                                    dial.setData(Uri.parse("tel:" + MapShopListAL.get(which).getPhone()));
                                    startActivity(dial);
                                } catch (SecurityException e) {
//                                    Log.w("OA", e.toString());
                                }
//                                AlertDialog.Builder builderInner = new AlertDialog.Builder(CoordinatorActivity.this);
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
                        });
                        builderSingle.show();
                    } else {
                        try {
//                                Log.w("OA", "which=" +  "MapShopListAL.get(which).getPhone()=" + MapShopListAL.get(which).getPhone());
//                                Intent intent = new Intent(Intent.ACTION_CALL);
//                                intent.setData(Uri.parse("tel:" + MapShopListAL.get(0).getPhone()));
//                                startActivity(intent);
                            Intent dial = new Intent();
                            dial.setAction("android.intent.action.DIAL");
                            dial.setData(Uri.parse("tel:" + MapShopListAL.get(0).getPhone()));
                            startActivity(dial);
                        } catch (SecurityException e) {
//                            Log.w("OA", e.toString());
                        }
                    }

                }
            });
        }
        //endregion

        //region share Button
//        LinearLayout llOfferShare = (LinearLayout) findViewById(R.id.llOfferShare);
//        if (llOfferShare != null) {
//            llOfferShare.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent sharingIntent = new Intent();
//                    List<Intent> targetShareIntents = new ArrayList<>();
//                    Intent shareIntent = new Intent();
//                    shareIntent.setAction(Intent.ACTION_SEND);
//                    shareIntent.setType("text/plain");
//                    List<ResolveInfo> resInfos = getPackageManager().queryIntentActivities(shareIntent, 0);
//                    if (!resInfos.isEmpty()) {
//                        //Log.w("OA", "Have package");
//                        Intent intent1 = new Intent();
//                        Intent intent2 = new Intent();
//                        int i = 1;
//                        for (ResolveInfo resInfo : resInfos) {
//                            String packageName = resInfo.activityInfo.packageName;
//                            Log.i("Package Name", packageName);
//                            if (packageName.contains("com.android.mms")
////                                    || packageName.contains("com.android.email")
////                                    || packageName.contains("com.tencent.mm")
////                                    || packageName.contains("com.whatsapp")
////                                    || packageName.contains("com.facebook.katana")
//                                    ) {
//                                intent1.setComponent(new ComponentName(packageName, resInfo.activityInfo.name));
//                                intent1.setAction(Intent.ACTION_SEND);
//                                intent1.setType("text/plain");
//                                intent1.putExtra(Intent.EXTRA_TEXT, "Text");
//                                intent1.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//                                intent1.setPackage(packageName);
//                            }
//                            if (packageName.contains("com.facebook.katana") && i == 1
////                                    || packageName.contains("com.android.email")
////                                    || packageName.contains("com.tencent.mm")
////                                    || packageName.contains("com.whatsapp")
//                                    ) {
//                                intent2.setComponent(new ComponentName(packageName, resInfo.activityInfo.name));
//                                intent2.setAction(Intent.ACTION_SEND);
//                                intent2.setType("text/plain");
//                                intent2.putExtra(Intent.EXTRA_TEXT, "Text");
//                                intent2.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//                                intent2.setPackage(packageName);
//                                i++;
//                            }
//                        }
//                        i = 1;
//                        final PackageManager pm = getPackageManager();
//                        List<ResolveInfo> activityList = pm.queryIntentActivityOptions(getComponentName(), new Intent[]{intent1, intent2}, sharingIntent, 0);
//                        final ShareIntentListAdapter objShareIntentListAdapter;
//                        objShareIntentListAdapter = new ShareIntentListAdapter(CoordinatorActivity.this, activityList.toArray());
//
//                        // Create alert dialog box
//                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                        builder.setTitle(getString(R.string.SV));
//                        builder.setAdapter(objShareIntentListAdapter, new DialogInterface.OnClickListener() {
//
//                            public void onClick(DialogInterface dialog, int item) {
//
//                                ResolveInfo info = (ResolveInfo) objShareIntentListAdapter.getItem(item);
//
//                                // if email shared by user
//                                Log.w("Emsaildaw", "info" + info);
//                                Log.w("Emsaildaw", "info.activityInfo.packageName" + info.activityInfo.packageName);
//                                if (info.activityInfo.packageName.contains("mms")
//                                        || info.activityInfo.packageName.contains("Gmail")
//                                        || info.activityInfo.packageName.contains("Y! Mail")) {
//                                    Intent intent = new Intent(Intent.ACTION_SEND);
//                                    intent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
//                                    intent.setType("text/plain");
//                                    intent.putExtra(Intent.EXTRA_SUBJECT, "afs");
//                                    intent.putExtra(Intent.EXTRA_TEXT, "faw");
//                                    intent.putExtra(Intent.EXTRA_TITLE, "afw");
//                                    startActivity(intent);
//
//                                }
//                                if (info.activityInfo.packageName.contains("katana")) {
//                                    FacebookSdk.sdkInitialize(getApplicationContext());
//                                    callbackManager = CallbackManager.Factory.create();
//                                    shareDialog = new ShareDialog(CoordinatorActivity.this);
//                                    // this part is optional
//                                    shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
//                                        @Override
//                                        public void onSuccess(Sharer.Result result) {
//                                        }
//
//                                        @Override
//                                        public void onError(FacebookException error) {
//
//                                        }
//
//                                        @Override
//                                        public void onCancel() {
//
//                                        }
//                                    });
//                                    if (ShareDialog.canShow(ShareLinkContent.class)) {
//                                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                                                .setContentTitle("Hello Facebook")
//                                                .setContentDescription(
//                                                        "The 'Hello Facebook' sample  showcases simple Facebook integration")
//                                                .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
//                                                .setQuote("HIHIHIIHIHIHI")
//                                                .build();
//
//                                        shareDialog.show(linkContent);
//                                    }
//                                    Log.w("Emsaildaw", "Emsaildaw");
//                                }
//
//
//                            }// end onClick
//
//                        });
//
//                        AlertDialog alert = builder.create();
//                        alert.show();
//
//                    }
//                }
//            });
//        }
        //endregion

        if (MapShopListAL == null || MapShopListAL.size() == 0) {
            viewOfferBookmarkLine.setVisibility(View.GONE);
            llOfferCall.setVisibility(View.GONE);
            llMap.setVisibility(View.GONE);

//            if (llOfferCall != null) {
//                //Log.w("OF", "MapShopListAL==null");
//                llOfferCall.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Utility.NoticeDialogOK(CoordinatorActivity.this, "There is no contact number!");
//                    }
//                });
//            }

        }

        try {
            LLLoopAddress = (LinearLayout) findViewById(R.id.LLLoopAddress);
            for (int i = 0; i < MapShopListAL.size(); i++) {

                addP(MapShopListAL.get(i).getName(), MapShopListAL.get(i).getAddress(), MapShopListAL.get(i).getPhone());
            }
        } catch (Exception ex) {
            Log.w("OA", "Error in OnCreate: " + ex.getMessage());
        }

        //btnGetOfferNow.setVisibility(View.VISIBLE);
        // GetPrivilegeList (return extra new field 'IsOfferUsed' and 'OfferPIN')
        // if no PIN or IsOfferUsed=1, then no need to display get offer button
        //SweetSansRegButton btnGetOfferNow = (SweetSansRegButton) findViewById(R.id.btnGetOfferNow);

        //sunny,cloudy,rainy,thunderstorm,foggy,windy,snowy

    }

    public void addP(String AddressName, String Address, final String Phone) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //region offer Address
        final View v1 = inflater.inflate(R.layout.nwd_offer_address, null);
        ImageView ivMapPin = (ImageView) v1.findViewById(R.id.ivMapPin);
        TextView tvOfferAddressName = (TextView) v1.findViewById(R.id.tvOfferAddressName);
        TextView tvOfferAddress = (TextView) v1.findViewById(R.id.tvOfferAddress);
        TextView tvContactNumber = (TextView) v1.findViewById(R.id.tvContactNumber);
        LinearLayout llPhoneSection = (LinearLayout) v1.findViewById(R.id.llPhoneSection);

//        ivMapPin.setImageDrawable(getResources().getDrawable(R.drawable.icon_pin));
        if (AddressName.equals("")) {
            tvOfferAddressName.setVisibility(View.GONE);
        } else {
            tvOfferAddressName.setText(AddressName);
        }

        if (Address.equals("")) {
            tvOfferAddress.setVisibility(View.GONE);
        } else {
            tvOfferAddress.setText(Address);
        }

        if (Address.isEmpty() && AddressName.isEmpty())
            ivMapPin.setVisibility(View.GONE);

        if (Phone.equals("")) {
            llPhoneSection.setVisibility(View.GONE);
        } else {
            String htmlString = "<u>" + Phone + "</u>";
            tvContactNumber.setText(Html.fromHtml(htmlString));
            //setting
            tvContactNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent dial = new Intent();
                    dial.setAction("android.intent.action.DIAL");
                    dial.setData(Uri.parse("tel:" + Phone));
                    startActivity(dial);
                }
            });
        }

        LLLoopAddress.addView(v1);

        //endregion

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        System.gc();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

//    @Subscribe
//    public void onMessageEvent(UpdatePromotionBus event) {
////        Log.w("CoordinatorActivity", "UpdatePromotionBus");
//    }


//
//    private void GetPrivilegeDetail() {
//        try {
//            JSONObject jsonObj = new JSONObject();
//            jsonObj.put("EstateID", Utility.PrimaryEstateID);
//            jsonObj.put("MemberID", Utility.userData.getMemberID());
//            jsonObj.put("Lang", Utility.APILang());
//            jsonObj.put("PrivilegeID", offersInfo.getPrivilegeID());
//            jsonObj.put("IsClub", Utility.IsClub);
//
////            Log.w("PWA", "HTTP REQ JSON=" + Utility.ipURL + "GetPrivilegeDetail?json=" + jsonObj.toString());
//
//            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, Utility.ipURL + "GetPrivilegeDetail?json=" + jsonObj.toString(), null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    try {
//
//                        JSONObject JOget = response.getJSONObject("Output");
//
//                        if (JOget.getBoolean("Success")) {
//                            //IconList
//                            ArrayList<IconList> IconListAL = new ArrayList<>();
//                            JSONArray IconList = JOget.getJSONArray("IconList");
//                            for (int q = 0; q < IconList.length(); q++) {
//                                JSONObject IconListObj = IconList.getJSONObject(q);
//                                IconListAL.add(new IconList(IconListObj.getString("CardNo"), IconListObj.getString("Code"), IconListObj.getString("Name"), IconListObj.getString("NameOnCard")));
//                            }
//                            //ShopList
//                            ArrayList<ShopList> ShopListAL = new ArrayList<>();
//                            JSONArray ShopList = JOget.getJSONArray("ShopList");
//                            for (int a = 0; a < ShopList.length(); a++) {
//                                JSONObject ShopListObj = ShopList.getJSONObject(a);
//                                ShopListAL.add(new ShopList(ShopListObj.getString("Address"), ShopListObj.getString("Name"), ShopListObj.getString("Phone"),
//                                        ShopListObj.getInt("ShopID"), ShopListObj.getDouble("XCoordinate"), ShopListObj.getDouble("YCoordinate")));
//                            }
//
//                            offersInfo = new OffersInfo(1, JOget.getInt("PrivilegeID"), JOget.getInt("MerchantID"), JOget.getInt("ViewCount"),
//                                    JOget.getBoolean("IsBookmarked"), IconListAL, ShopListAL, JOget.getString("Name"), JOget.getString("Merchant_PhotoPath"),
//                                    JOget.getString("PhotoPath"), JOget.getString("PhotoPathIn"), Utility.DateStringCutTime(JOget.getString("StartDate")),
//                                    Utility.DateStringCutTime(JOget.getString("EndDate")), JOget.getString("OfferRefNo"), JOget.getString("Merchant_Name"), JOget.getString("Merchant_Status"),
//                                    JOget.getString("Status"), JOget.getString("TCLink"), JOget.getBoolean("IsClub"), JOget.getString("Description"),
//                                    JOget.getString("WebSite"), JOget.getString("Category"), JOget.getBoolean("IsOfferUsed"), JOget.getString("OfferPIN"), JOget.getString("CategoryGA"),
//                                    JOget.getString("OfferStatus"), JOget.getInt("RemainingQuota"), JOget.getString("ReferralCode"));
//                            updateGetPrivilegeButton();
//                        } else {
////                            Log.w("PUA", "GetPrivilegeDetail:onResponse,Success=false");
//                        }
//                        //}
//
//                    } catch (Exception e) {
////                        Log.w("PUA", "GetPrivilegeDetail:onResponse:Catch:" + e.toString());
//                    }
//                    Utility.dismissLoadingDialog();
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
////                    Log.w("GetPrivilegeDetail", "GetPrivilegeDetail:onErrorResponse:" + error.toString());
//
//                }
//            });
//            Utility.mQueue.add(Utility.SetVolleyTimeoutAndNoCache(jsonRequest));
//
//        } catch (Exception e) {
////            Log.w("GetPrivilegeDetail", "GetPrivilegeDetail:Catch:" + e.toString());
//        }
//
//    }

    //    //region GoogleMap
    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (isReady) {
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            for (int i = 0; i < MapShopListAL.size(); i++) {
                //if xy=0 , = no pin
                if (MapShopListAL.get(i).getXCoordinate() != 0 && MapShopListAL.get(i).getYCoordinate() != 0) {
                    if (i == 0) {
                        LatLng ParkSignature = new LatLng(MapShopListAL.get(i).getXCoordinate(), MapShopListAL.get(i).getYCoordinate());
                        moveMap(ParkSignature);
                        addMarker(ParkSignature, MapShopListAL.get(i).getName(), MapShopListAL.get(i).getAddress());
                    } else {
                        LatLng ParkSignature = new LatLng(MapShopListAL.get(i).getXCoordinate(), MapShopListAL.get(i).getYCoordinate());
//                    moveMap(ParkSignature);
                        addMarker(ParkSignature, MapShopListAL.get(i).getName(), MapShopListAL.get(i).getAddress());
                    }
                }

            }
//            LatLng ParkSignature = new LatLng(22.436864, 114.023319);
//            moveMap(ParkSignature);
//            addMarker(ParkSignature, "Park Signature 溱柏", "元朗公庵路68號");
        }

    }

    private void moveMap(LatLng place) {
        // 建立地圖攝影機的位置物件
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(10)
                        .build();

        // 使用動畫的效果移動地圖
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void addMarker(LatLng place, String title, String snippet) {
        BitmapDescriptor icon =
                BitmapDescriptorFactory.fromResource(R.drawable.icon_location_pink);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(place)
                .title(title)
                .snippet(snippet)
                .icon(icon);

        mMap.addMarker(markerOptions);
    }

//    //endregion

    private boolean checkXYIsShow() {
        for (int i = 0; i < MapShopListAL.size(); i++) {
            //if once x and y != 0 , = show
            if (MapShopListAL.get(i).getXCoordinate() != 0 && MapShopListAL.get(i).getYCoordinate() != 0) {
                return true;
            }
        }

        return false;
    }
//
//    private void CreatePrivilegeOffer() {
//        try {
//            JSONObject jsonObj = new JSONObject();
//            jsonObj.put("PrivilegeID", offersInfo.getPrivilegeID());
//            jsonObj.put("MemberID", Utility.userData.getMemberID());
//
//            JSONObject offer = new JSONObject();
//            offer.put("offer", jsonObj);
//
////            Log.w("OA", "HTTP REQ JSON=" + Utility.ipURL + "CreatePrivilegeOffer" + ",JSON=" + offer.toString());
//            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, Utility.ipURL + "CreatePrivilegeOffer", offer, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    try {
////                        Log.w("OA", "CreatePrivilegeOffer:onResponse=" + response.toString());
//                        JSONObject Output = response.getJSONObject("Output");
//                        if (Output.getBoolean("Success")) {
//                            Log.w("MPRF", "CreatePrivilegeOffer:Catch:Success");
////                            EventBus.getDefault().post(new UpdatePromotionBus("updatePromotionTabList"));
//                            Utility.IsBookmarked = true;
//                            Utility.dismissLoadingDialog();
//                            Intent intent = new Intent(CoordinatorActivity.this, OffersQRActivity.class);
//                            //intent.putExtra("Title", offersInfo.getName());
////                                            Log.w("PIN", "offersInfo.getOfferPIN()" + offersInfo.getOfferPIN());
//                            startActivity(intent);
//                        } else {
//                            Utility.dismissLoadingDialog();
//                            Utility.NoticeDialogOK(CoordinatorActivity.this, getString(R.string.CEPTA));
//                        }
//                    } catch (Exception e) {
//                        Utility.dismissLoadingDialog();
//                        Log.w("MPRF", "CreatePrivilegeOffer:Catch:" + e.toString());
//                    }
//                    //GetAllNoticeCount();
//                }
//            }, new Response.ErrorListener() {
//
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Utility.dismissLoadingDialog();
//                    Log.w("CreatePrivilegeOffer:", "onErrorResponse" + error.toString());
//                }
//            }) {
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("Content-Type", "application/json; charset=utf-8");
//                    params.put("Accept-Type", "application/json; charset=utf-8");
//                    return params;
//                }
//            };
//            Utility.mQueue.add(Utility.SetVolleyTimeoutAndNoCache(jsonRequest));
//        } catch (Exception e) {
//            Utility.dismissLoadingDialog();
//            Log.w("CreateNoticeRead", "GetLoginInfo:Catch" + e.toString());
//        }
//    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        if (requestCode == REQUEST_CODE_MY_PICK) {
//            String appName = data.getComponent().flattenToShortString();
//            Log.i("appName","dhajd"+ appName);
//            // Now you know the app being picked.
//            // data is a copy of your launchIntent with this important extra info added.
//
//            // Don't forget to start it!
//            startActivity(data);
//        }
//    }

//    @Override
//    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//    }

//    private void updateGetPrivilegeButton() {
//        try {
//            Thread thread2 = new Thread() {
//                @Override
//                public void run() {
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//
//
//                            Log.w("OA", "offersInfo.getOfferStatus()=" + offersInfo.getOfferStatus() + ",offersInfo.getRemainingQuota()=" +
//                                    offersInfo.getRemainingQuota() + ",offersInfo.getOfferPIN()=" + offersInfo.getOfferPIN());
//                            if (offersInfo.getOfferStatus().equalsIgnoreCase("Got") && !offersInfo.getOfferPIN().isEmpty()) {
//                                LLBottomOffer.setVisibility(View.VISIBLE);
//                                SweetSansRegButton btnGetOfferNow = (SweetSansRegButton) findViewById(R.id.btnGetOfferNow);
//                                btnGetOfferNow.setText(R.string.GPN);
//                                if (btnGetOfferNow != null) {
//                                    btnGetOfferNow.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            Intent intent = new Intent(CoordinatorActivity.this, OffersQRActivity.class);
//                                            startActivity(intent);
//                                        }
//                                        //btnGetOfferNow.setVisibility(View.GONE);
//                                        //        else
//                                        //        if (btnGetOfferNow != null) {
//                                        // btnGetOfferNow.setOnClickListener(new View.OnClickListener() {
//                                        // @Override
//                                        // public void onClick(View v) {
//                                        // Intent intent = new Intent(CoordinatorActivity.this, OffersQRActivity.class);
//                                        // intent.putExtra("Title", offersInfo.getName());
//                                        // intent.putExtra("QRCode", "ABC");
//                                        // startActivity(intent);
//                                        // }
//                                    });
//                                }
//                            } else if (offersInfo.getOfferStatus().isEmpty() && !offersInfo.getOfferPIN().isEmpty() && offersInfo.getRemainingQuota() > 0) {
////                                Log.w("getOfferStatus()", "offersInfo.getOfferStatus():" + offersInfo.getOfferStatus());
////                            if (!offersInfo.getOfferStatus().equalsIgnoreCase("Redeemed")&& !offersInfo.getOfferPIN().isEmpty()) {
//                                LLBottomOffer.setVisibility(View.VISIBLE);
//                                SweetSansRegButton btnGetOfferNow = (SweetSansRegButton) findViewById(R.id.btnGetOfferNow);
//                                btnGetOfferNow.setText(R.string.GON);
//                                if (btnGetOfferNow != null) {
//                                    btnGetOfferNow.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            Utility.showLoadingDialog(CoordinatorActivity.this, getString(R.string.Loading));
//                                            CreatePrivilegeOffer();
//
//                                        }
//                                        //btnGetOfferNow.setVisibility(View.GONE);
//                                        //        else
//                                        //        if (btnGetOfferNow != null) {
//                                        // btnGetOfferNow.setOnClickListener(new View.OnClickListener() {
//                                        // @Override
//                                        // public void onClick(View v) {
//                                        // Intent intent = new Intent(CoordinatorActivity.this, OffersQRActivity.class);
//                                        // intent.putExtra("Title", offersInfo.getName());
//                                        // intent.putExtra("QRCode", "ABC");
//                                        // startActivity(intent);
//                                        // }
//                                    });
//                                }
//                            } else {
//                                LLBottomOffer.setVisibility(View.GONE);
//                            }
//                        }
//                    });
//                }
//            };
//            thread2.start();
//        } catch (Exception e) {
//            Log.w("Weather", "Error:" + e.toString());
//
//        }
//    }
}

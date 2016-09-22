package template.cheng.hollis.template;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.facebook.FacebookSdk;

import java.util.ArrayList;
import java.util.Locale;

import template.cheng.hollis.template.Button.SweetSansRegButton;
import template.cheng.hollis.template.CoordinatorLayout_Card_Tab_Filter.CardVPActivity;
import template.cheng.hollis.template.SQLiteDB.Language;
import template.cheng.hollis.template.SQLiteDB.LanguageDAO;
import template.cheng.hollis.template.TestBundlePage.TestBundleActivity;
import template.cheng.hollis.template.TextView.SweetSansRegTextView;

public class MainActivity extends AppCompatActivity {
    private TextView etCount;
    private DrawerLayout drawer;
    private SweetSansRegButton SSRBTestBundle;
    private AutoCompleteTextView etProperty_Name;
    private Button LangENG, LangTC, btnFbTestPage, btnFbOtherClickPage;
    private String language;
    private SweetSansRegTextView ssrtvSlideRZ, ssrtvSlideFB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        AppEventsLogger.activateApp(this);

        //for whole App use
        Utility.mQueue = Volley.newRequestQueue(getApplicationContext());
        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH);
//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        Utility.mTracker = application.getDefaultTracker();
        LanguageDAO languageDAO = new LanguageDAO(getApplicationContext());

        //region load language DB
        try {
            if (languageDAO.getCount() == 0) {
                //Log.w("MA", "Create sample for languageDAO");
                languageDAO.sample();

                //after create set the language in Utility
                Language language;
                language = languageDAO.get(1);

                Utility.AppLang = language.getLanguage();
                //Log.w("MA", language.toString());
            } else {
                //check Language
                Language language;
                language = languageDAO.get(1);

                Utility.AppLang = language.getLanguage();
                //Log.w("MA", language.toString());
            }
        } catch (Exception e) {
            //Log.w("MA", "language DB error:" + e.toString());
        }

        //set Load DB if exist , if not=>Set Default Language == English , also set the language, the lang to update app
        Locale myLocale = new Locale(Utility.AppLang);
        Resources res = getBaseContext().getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;

        res.updateConfiguration(conf, dm);
        getBaseContext().getResources().updateConfiguration(getBaseContext().getResources().getConfiguration(),
                getBaseContext().getResources().getDisplayMetrics());
        //endregion

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        etCount = (TextView) findViewById(R.id.counter);
        ImageView IVCounter = (ImageView) findViewById(R.id.IVCounter);

        if (IVCounter != null) {
            IVCounter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(ALMainPage.this, MessageActivity.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                }
            });
        }

        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

        ImageView IVTToggle = (ImageView) findViewById(R.id.IVTToggle);
        if (IVTToggle != null) {
            IVTToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.openDrawer(Gravity.LEFT);
                    //                if (drawer.isDrawerVisible(GravityCompat.START))
                    //                    drawer.closeDrawer(GravityCompat.START);
                    //                else
                    //                    drawer.openDrawer(GravityCompat.END);
                }
            });
        }

        //setting
        etProperty_Name = (AutoCompleteTextView) findViewById(R.id.etProperty_Name);
        SSRBTestBundle = (SweetSansRegButton) findViewById(R.id.SSRBTestBundle);
        btnFbTestPage = (Button) findViewById(R.id.btnFbTestPage);
        btnFbOtherClickPage = (Button) findViewById(R.id.btnFbOtherClickPage);

        btnFbTestPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FBActivity.class);
                startActivity(intent);
            }
        });

        btnFbOtherClickPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FBotherClickActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<String> keyWords = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            keyWords.add("ABC" + i);
        }
        keyWords.add("HollisCheng");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, keyWords);
        etProperty_Name.setAdapter(adapter);
        etProperty_Name.clearFocus();
        etProperty_Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    etProperty_Name.showDropDown();
                }
            }
        });

        SSRBTestBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestBundleActivity.class);
                intent.putExtra("BundleString", etProperty_Name.getText().toString());
                startActivity(intent);
            }
        });

        //region Slide Bar
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        View slideMenu = navigationView.inflateHeaderView(R.layout.nav_header_almain_page);
        ssrtvSlideRZ = (SweetSansRegTextView) slideMenu.findViewById(R.id.ssrtvSlideRZ);
        ssrtvSlideFB = (SweetSansRegTextView) slideMenu.findViewById(R.id.ssrtvSlideFB);

        //region AnimationActivity
        LinearLayout llSlideResidentZone = (LinearLayout) slideMenu.findViewById(R.id.llSlideResidentZone);
        llSlideResidentZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });
        //endregion

        //region ViewPagerActivity
        LinearLayout llSlideReferral = (LinearLayout) slideMenu.findViewById(R.id.llSlideReferral);
        llSlideReferral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });

        //endregion

        //region Language Activity
        LinearLayout llSlideFeedback = (LinearLayout) slideMenu.findViewById(R.id.llSlideFeedback);
        llSlideFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LanguageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });
        //endregion

        //region CardWallet
        LinearLayout llSlideSetting = (LinearLayout) slideMenu.findViewById(R.id.llSlideSetting);
        llSlideSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, CardVPActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });

        //endregion

        //region CalendarActivity
        LinearLayout llSlideTnC = (LinearLayout) slideMenu.findViewById(R.id.llSlideTnC);
        llSlideTnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);

//                Intent intent = new Intent(ALMainPage.this, WebViewClientPage.class);
//                intent.putExtra("LINK", Utility.estateInfo.getTCLink());
//                intent.putExtra("Title", getString(R.string.TNC));
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });

        //endregion

        //region Language

        LangENG = (Button) slideMenu.findViewById(R.id.btnLangENG);
        LangTC = (Button) slideMenu.findViewById(R.id.btnLangTC);
//        LangSC = (Button) slideMenu.findViewById(R.id.btnLangSC);

        //region check DB language

        switch (Utility.AppLang) {
            case "en":
                SWLang(0);
                break;
            case "zh":
                SWLang(1);
                break;
            case "tw":
                SWLang(2);
                break;
        }

        //endregion

        LangENG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SWLang(0);
                Locale myLocale = new Locale("en");
                Resources res = getBaseContext().getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                Configuration conf = res.getConfiguration();
                conf.locale = myLocale;
                res.updateConfiguration(conf, dm);
                getBaseContext().getResources().updateConfiguration(getBaseContext().getResources().getConfiguration(), getBaseContext().getResources().getDisplayMetrics());
                updateText();
                InsertDB("en");
            }
        });

        LangTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SWLang(1);
                Locale myLocale = new Locale("zh");
                Resources res = getBaseContext().getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                Configuration conf = res.getConfiguration();
                conf.locale = myLocale;
                res.updateConfiguration(conf, dm);
                getBaseContext().getResources().updateConfiguration(getBaseContext().getResources().getConfiguration(), getBaseContext().getResources().getDisplayMetrics());
                updateText();
                InsertDB("zh");
            }
        });

//        LangSC.setVisibility(View.GONE);

//        LangSC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SWLang(2);
//                Locale locale = new Locale("tw");
//                Configuration config = ALMainPage.this.getResources().getConfiguration();
//                if (config == null) {
//                    config = new Configuration();
//                }
//                Locale.setDefault(locale);
//                config.locale = locale;
//                ALMainPage.this.getResources().updateConfiguration(config, ALMainPage.this.getResources().getDisplayMetrics());
//                ALMainPage.this.getApplication().onConfigurationChanged(config);
//                InsertDB("tw");
//            }
//        });

        //endregion

        //endregion

        RadioGroup RGUserPropertyType = (RadioGroup) findViewById(R.id.RGUserPropertyType);

        RGUserPropertyType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.RBResident) {
//                    LLAddImagesForProperty.setVisibility(View.GONE);
//                    info.setPropertyType(finalResidentCode);
                } else if (i == R.id.RBOwner) {
//                    LLAddImagesForProperty.setVisibility(View.VISIBLE);
//                    info.setPropertyType(finalOwnerCode);
                } else {
                    //R.id.RBTenant
//                    LLAddImagesForProperty.setVisibility(View.VISIBLE);
//                    info.setPropertyType(finalTenantCode);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            confirmLeaveDialog();
//            super.onBackPressed();
        }
    }

    //        @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    private void confirmLeaveDialog() {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Leave?");
        alertDlg.setCancelable(true);

        alertDlg.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDlg.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//do wt u want after click No
            }
        });

        alertDlg.create().show();

    }

    private void SWLang(int num) {
        switch (num) {
            case 0:
//                LangENG.setBackgroundResource(R.drawable.lang_button_bg_round);//for below API 16 replace setBackground
                LangENG.setBackground(getResources().getDrawable(R.drawable.lang_button_bg_round));
                LangENG.setTextColor(getResources().getColor(R.color.White));
                LangTC.setBackground(getResources().getDrawable(R.drawable.selector_round));
                LangTC.setTextColor(getResources().getColor(R.color.Gray));
//                LangSC.setBackground(getResources().getDrawable(R.drawable.selector_round));
//                LangSC.setTextColor(getResources().getColor(R.color.Gray));
                break;
            case 1:
                LangENG.setBackground(getResources().getDrawable(R.drawable.selector_round));
                LangENG.setTextColor(getResources().getColor(R.color.Gray));
                LangTC.setBackground(getResources().getDrawable(R.drawable.lang_button_bg_round));
                LangTC.setTextColor(getResources().getColor(R.color.White));
//                LangSC.setBackground(getResources().getDrawable(R.drawable.selector_round));
//                LangSC.setTextColor(getResources().getColor(R.color.Gray));
                break;
            //TODO SChi option maybe need to change above
//            case 2:
//                LangENG.setBackground(getResources().getDrawable(R.drawable.selector_round));
//                LangENG.setTextColor(getResources().getColor(R.color.Gray));
//                LangTC.setBackground(getResources().getDrawable(R.drawable.selector_round));
//                LangTC.setTextColor(getResources().getColor(R.color.Gray));
//                LangSC.setBackground(getResources().getDrawable(R.drawable.lang_button_bg_round));
//                LangSC.setTextColor(getResources().getColor(R.color.White));
//                break;
        }
    }

    private void InsertDB(String lang) {
//        database = new Database(ALMainPage.this);
//        database.deleteTable("Language");
//        database.CreateLanguageTable();
//        String insertData = "{\"Language\":[{" +
//                "\"Language\":\"" + language +
//                "\"}]}";
////       //Log.w("ALMP","insertData =" + insertData);
//        Utility.AppLang = language;
//        ImportData importData = new ImportData();
//        importData.Import(getApplicationContext(), insertData);
        Utility.showLoadingDialog(MainActivity.this, MainActivity.this.getString(R.string.Loading));
        Utility.AppLang = lang;

        LanguageDAO languageDAO = new LanguageDAO(getApplicationContext());
        if (languageDAO.update(new Language(1, lang))) {
            ////Log.w("ALMP", "languageDAO.update=true");
        } else {
            ////Log.w("ALMP", "languageDAO.update=fail");
        }

        language = lang;

//        PreLoadAPI preLoadAPI = new PreLoadAPI(new MyInterface() {
//            @Override
//            public void myMethod(boolean result) {
//                if (result) {
//                    //Log.w("ALMP", "preLoadAPI.loadAllAPI()=true");
//                    checkDBForAccAndGO();
//                } else {
//                    //Log.w("ALMP", "preLoadAPI.loadAllAPI()=fail");
//                }
//            }
//        });

        Utility.dismissLoadingDialog();
    }

    private void updateText() {
//change the text inside this mainActivity Page
        ssrtvSlideRZ.setText(R.string.RESIDENTS_AREA);
        ssrtvSlideFB.setText(R.string.FEEDB);
    }
}

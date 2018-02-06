package template.cheng.hollis.template;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.expandablelayout.library.ExpandableLayout;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.facebook.FacebookSdk;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import se.simbio.encryption.Encryption;
import template.cheng.hollis.template.button.SweetSansRegButton;
import template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter.CardVPActivity;
import template.cheng.hollis.template.objectInfo.KeyWordsInfo;
import template.cheng.hollis.template.panorama.MD360PlayerActivity;
import template.cheng.hollis.template.SQLiteDB.Language;
import template.cheng.hollis.template.SQLiteDB.LanguageDAO;
import template.cheng.hollis.template.testBundlePage.TestBundleActivity;
import template.cheng.hollis.template.textView.SweetSansRegTextView;
import template.cheng.hollis.template.webConnect.WebViewClientPage;
import template.cheng.hollis.template.youtubeAPI.MyInterface;
import template.cheng.hollis.template.youtubeAPI.PropertiesNamePageActivity;
import template.cheng.hollis.template.youtubeAPI.PropertiesNamePageCallBack;

import static template.cheng.hollis.template.Utility.KeyWordsInfoAL;


public class MainActivity extends AppCompatActivity {
    private TextView etCount;
    private DrawerLayout drawer;
    private SweetSansRegButton SSRBTestBundle;
    private AutoCompleteTextView etProperty_Name;
    private Button LangENG, LangTC, btnFbTestPage, btnFbOtherClickPage;
    private String language;
    private SweetSansRegTextView ssrtvSlideRZ, ssrtvSlideFB;

    private ArrayList<String> keyWords;
    public static PropertiesNamePageCallBack propertiesNamePageCallBack;

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

        //Test if error show and crash! return to MainActivity.class
//        if (BuildConfig.FLAVOR.toLowerCase().equals("pro"))
//            Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this, MainActivity.class));
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this));
//        else
//            Thread.setDefaultUncaughtExceptionHandler(new LogExceptionHandler(this, MainActivity.class));
        //Set a exceptionHandler if error crush app, log the error in txt and return to MainActivity.class

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        etCount = findViewById(R.id.counter);
        ImageView IVCounter = findViewById(R.id.IVCounter);

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

        drawer = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

        ImageView IVTToggle = findViewById(R.id.IVTToggle);
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
        Button BTN_Panorama = findViewById(R.id.BTN_Panorama);
        etProperty_Name = findViewById(R.id.etProperty_Name);
        SSRBTestBundle = findViewById(R.id.SSRBTestBundle);
        btnFbTestPage = findViewById(R.id.btnFbTestPage);
        btnFbOtherClickPage = findViewById(R.id.btnFbOtherClickPage);

        BTN_Panorama.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // for panorama testing
                try {
                    String url = "https://upload.wikimedia.org/wikipedia/commons/8/86/360-degree_Panorama_of_the_Southern_Sky_edit.jpg";
//                    String url = "http://4.bp.blogspot.com/_BZhaBQfPrpw/TNhsAZC0oII/AAAAAAAAATg/CwTEH968hPs/s1600/_D0M7193Dom+Bower+Photography+Panorama.jpg";
                    if (!TextUtils.isEmpty(url)) {
                        MD360PlayerActivity.startBitmap(MainActivity.this, Uri.parse(url));
                    } else {
                        Toast.makeText(MainActivity.this, "empty url!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utility.PrintLog(getClass().getName(), "panorama error=" + e.toString());
                }
            }
        });

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

        ArrayList<String> keyWordsA = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            keyWordsA.add("ABC" + i);
        }
        keyWordsA.add("HollisCheng");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, keyWordsA);
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
        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        View slideMenu = navigationView.inflateHeaderView(R.layout.nav_header_almain_page);
        ssrtvSlideRZ = slideMenu.findViewById(R.id.ssrtvSlideRZ);
        ssrtvSlideFB = slideMenu.findViewById(R.id.ssrtvSlideFB);

        //region AnimationActivity
        LinearLayout llSlideResidentZone = slideMenu.findViewById(R.id.llSlideResidentZone);
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
        LinearLayout llSlideReferral = slideMenu.findViewById(R.id.llSlideReferral);
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
        LinearLayout llSlideFeedback = slideMenu.findViewById(R.id.llSlideFeedback);
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
        LinearLayout llSlideSetting = slideMenu.findViewById(R.id.llSlideSetting);
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
        LinearLayout llSlideTnC = slideMenu.findViewById(R.id.llSlideTnC);
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

        //region YoutubeAPIActivity
        LinearLayout llYoutubeAPI = slideMenu.findViewById(R.id.llYoutubeAPI);
        llYoutubeAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YoutubeAPIActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });
        //endregion

        //region LLWebView
        LinearLayout LLWebView = slideMenu.findViewById(R.id.LLWebView);
        LLWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewClientPage.class);
//                intent.putExtra("LINK", "http://???");
                intent.putExtra("LINK", "https://www.google.com.hk");
//                intent.putExtra("LINK", "https://im.nwd.com.hk/NWD_SmartOfficeApp/Account/Login.aspx?msg=f%2fxsZW1IMq1H1HEVqPgXI8vN2JLFZwCGtuXiwmlaAOcYbSCOYsL98A%3d%3d&ReturnUrl=Admin%2fHappen.aspx%3fId%3d0");
                intent.putExtra("Title", "SSO");
                //test AES256 add below
                intent.putExtra("K11SSO", true);
                intent.putExtra("IsShowWebButton", true);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });

        //endregion

        //region Language

        LangENG = slideMenu.findViewById(R.id.btnLangENG);
        LangTC = slideMenu.findViewById(R.id.btnLangTC);
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

        RadioGroup RGUserPropertyType = findViewById(R.id.RGUserPropertyType);

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

        //region ExpandableLayout / filter search page and call back function / scrollView not focus editTextView

        final ScrollView SV_preventFocusET = findViewById(R.id.SV_preventFocusET);
        final EditText tvAboveName = findViewById(R.id.tvAboveName);
        //scrollView not focus editTextView
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        SV_preventFocusET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                IBinder windowToken = null;
                if (tvAboveName.hasFocus()) {
                    tvAboveName.clearFocus();
                    windowToken = tvAboveName.getWindowToken();
                }

                if (windowToken != null) {
                    imm.hideSoftInputFromWindow(windowToken, 0);
                }
                SV_preventFocusET.requestFocusFromTouch();

                return false;
            }
        });

        final AutoCompleteTextView No_etProperty_NameNotSame = findViewById(R.id.No_etProperty_NameNotSame);
        final ExpandableLayout YesEL = findViewById(R.id.YesEL);
        final ImageView YesELOpenOffIcon = findViewById(R.id.YesELOpenOffIcon);
        YesEL.getHeaderLayout().setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Utility.PrintLog(getClass().getName(), "isOpened=" + YesEL.isOpened());
                if (YesEL.isOpened()) {
//                    YesELOpenOffIcon.setImageDrawable(getResources().getDrawable(R.drawable.icon_deduct));
                    YesELOpenOffIcon.animate().rotation(0).start();
                    YesEL.hide();
//                    ViewPushFullScreen.setVisibility(VISIBLE);
                } else {
//                    YesELOpenOffIcon.setImageDrawable(getResources().getDrawable(R.drawable.app_icon));
                    YesELOpenOffIcon.animate().rotation(180).start();
                    YesEL.show();
//                    ViewPushFullScreen.setVisibility(GONE);
                }
            }
        });

        keyWords = new ArrayList<>();
        ArrayList<KeyWordsInfo> WordsInfoAL = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            ArrayList<String> WordsAL = new ArrayList<>();
            WordsAL.add("abc" + i);
            WordsInfoAL.add(new KeyWordsInfo("abc" + i, i, "abc" + i, "abc" + i, "abc" + i, WordsAL));
        }
        KeyWordsInfoAL = WordsInfoAL;

        Spinner No_SpinnerUnitNotSame = findViewById(R.id.No_SpinnerUnitNotSame);
        ArrayAdapter<String> adapterSpinnerBlock = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item, keyWordsA);
        adapterSpinnerBlock.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        No_SpinnerUnitNotSame.setAdapter(adapterSpinnerBlock);

        for (int k = 0; k < KeyWordsInfoAL.size(); k++) {
//            keyWords.add(Utility.KeyWordsInfoAL.get(k).getNameEng().toUpperCase());
//            keyWords.add(Utility.KeyWordsInfoAL.get(k).getNameSChi());
//            keyWords.add(Utility.KeyWordsInfoAL.get(k).getNameTChi());
            if (KeyWordsInfoAL.get(k).getNameEng().toUpperCase().equals(KeyWordsInfoAL.get(k).getNameTChi().toUpperCase())) {
                //if ENG SAME WITH TCHI
                keyWords.add(KeyWordsInfoAL.get(k).getNameEng().toUpperCase());
            } else {
                keyWords.add(KeyWordsInfoAL.get(k).getNameEng().toUpperCase());
//            keyWords.add(Utility.KeyWordsInfoAL.get(k).getNameSChi());
                keyWords.add(KeyWordsInfoAL.get(k).getNameTChi());
            }
        }
        ArrayAdapter<String> NoAsSame_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, keyWords);
        No_etProperty_NameNotSame.setAdapter(NoAsSame_adapter);

        No_etProperty_NameNotSame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.IsRegisterPropertyName = true;
                propertiesNamePageCallBack = new PropertiesNamePageCallBack(MainActivity.this, new MyInterface() {
                    @Override
                    public void myMethod(boolean result) {
                        if (result) {
                            Utility.PrintLog(getClass().getName(), "PropertiesNamePageCallBack=true");
                            No_etProperty_NameNotSame.setText(propertiesNamePageCallBack.getSelectText());
                            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                            propertiesNamePageCallBack = null;
                        } else {
                            Utility.PrintLog(getClass().getName(), "PropertiesNamePageCallBack=fail");
                        }
                    }
                });

                Intent intent = new Intent(MainActivity.this, PropertiesNamePageActivity.class);
                startActivity(intent);
            }
        });

        //endregion

        //TODO make the spinner enable=false also display black color text
        //default is android.R.layout.simple_spinner_item now using R.layout.spinnerenablecolortextitem
//        ArrayAdapter<String> adapterSpinnerBlock = new ArrayAdapter<>(AddPropertyActivity.this, R.layout.spinnerenablecolortextitem, Utility.BlockNameOutput(AddPropertyActivity.this, EstateID[0]));
//        adapterSpinnerBlock.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        SpinnerBlock.setAdapter(adapterSpinnerBlock);

        //test encrypt/decrypt
        EncryptOrDecrypt();

        //test POST
//        TestPOST();

        Utility.PrintLog(getClass().getName(), "BuildConfig.HOST=" + BuildConfig.HOST);
        Utility.PrintLog(getClass().getName(), "BuildConfig.APPLICATION_ID=" + BuildConfig.APPLICATION_ID);
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
//                LangTC.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_round));
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

    private void EncryptOrDecrypt() {
        String key = "Artisanal";
        String key2 = "Living";
        StringBuilder sb4Key = new StringBuilder();
        for (int i = 0; i < key2.length(); i++) {
            sb4Key.append(key2.charAt(i));
            sb4Key.append(key.charAt(i));
            sb4Key.append(key.charAt(i));
        }
        String combinedKey = sb4Key.toString();
        String salt = "ArtisanalLiving";
        String salt2 = "Living";
        StringBuilder sb4Salt = new StringBuilder();
        for (int i = 0; i < salt2.length(); i++) {
            sb4Salt.append(salt.charAt(i));
            sb4Salt.append(salt2.charAt(i));
        }
        String combinedSalt = sb4Salt.toString();
        Log.w("A", "combinedKey:" + combinedKey);
        Log.w("A", "combinedSalt:" + combinedSalt);
        Log.w("A", "+++++++++++++++++++++++++++++++++++++");
        String text = "password";
        byte[] decryptedData = null;
        try {
            Encryption encryption = Encryption.getDefault(combinedKey, combinedSalt, new byte[16]);
            String encrypted = encryption.encryptOrNull("Hello$$EventFormSubmitInfo**GoodBye");
            Log.w("A", "encrypted:" + encrypted);
            String decrypted = encryption.decryptOrNull("kKt4gpbMK4uZHrZbLF00CjK1atUZ8lBmnMGExntxaDIR3FFNtRmCp/KQo2tiDFFQ");
            String text2 = null;
            Log.w("A", "decryptedData:" + decrypted);
            //https://apps.nwd.com.hk/NWDService/Service.svc/
        } catch (Exception e) {
            Utility.PrintLog(getClass().getName(), "Exception e=" + e.toString());
        }
    }

    private void TestPOST() {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("CurrentUser", "nwd-edp-nt\\eliwong");
            jsonObj.put("Token", "1acXt70Fry92YgWcSxmt0HrGLrh2Wqn7ZE1UgEofQKU=");
            jsonObj.put("WorkflowType", "");
            jsonObj.put("Keyword", "");
            jsonObj.put("DateFrom", "");
            jsonObj.put("DateTo", "");
            jsonObj.put("SortBy", "");
            jsonObj.put("IndexFrom", 1);
            jsonObj.put("IndexTo", 10);

            JSONObject member = new JSONObject();
            member.put("fields", jsonObj);

            Utility.PrintLog(getClass().getName(), "HTTP REQ JSON=" + "https://eapproval.nwd.com.hk/NWD_Eapproval/Services/MobileServices.svc/GetWorkList" + ",JSON=" + member.toString());
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, "https://eapproval.nwd.com.hk/NWD_Eapproval/Services/MobileServices.svc/GetWorkList", member, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Utility.PrintLog(getClass().getName(), "UpdateStaffResignEmail:onResponse=" + response.toString());
                        JSONObject Output = response.getJSONObject("Output");

                    } catch (Exception e) {
                        Utility.dismissLoadingDialog();
                        Utility.PrintLog(getClass().getName(), "UpdateStaffResignEmail:Volley decode JSON:" + e.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Utility.PrintLog(getClass().getName(), "onErrorResponse:" + error.toString());
                    Utility.dismissLoadingDialog();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json; charset=utf-8");
                    params.put("Accept-Type", "application/json; charset=utf-8");
                    return params;
                }
            };
            Utility.mQueue.add(Utility.SetVolleyTimeoutAndNoCache(jsonRequest));
        } catch (Exception e) {
            Utility.dismissLoadingDialog();
            Utility.PrintLog(getClass().getName(), "catch:" + e.toString());
        }

    }
}

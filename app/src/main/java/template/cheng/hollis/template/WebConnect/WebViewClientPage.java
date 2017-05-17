package template.cheng.hollis.template.WebConnect;

import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scottyab.aescrypt.AESCrypt;

import org.apache.http.util.EncodingUtils;

import javax.crypto.spec.SecretKeySpec;

import template.cheng.hollis.template.R;
import template.cheng.hollis.template.Utility;


public class WebViewClientPage extends AppCompatActivity
//        implements SwipeRefreshLayout.OnRefreshListener
{
    private WebView webview;
    private String url;
    private String Title;
    private TextView toolbar_title;
    private ImageView IVBackButton;
    private ImageView IVNextButton;
    private ImageView IVReloadButton;
    private ProgressBar webViewPB;
    private LinearLayout LLIsShowWebButton;
    private boolean K11SSO = false;
    private boolean IsShowWebButton = false;
//    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_client_page);
//        Utility.showLoadingDialog(this, this.getString(R.string.Loading));

        Bundle params = getIntent().getExtras();
        if (params != null) {
            url = params.getString("LINK");
            Title = params.getString("Title");
            K11SSO = params.getBoolean("K11SSO");
            IsShowWebButton = params.getBoolean("IsShowWebButton");
        } else {
            url = "";
            Title = "";
        }

        //region SetCustom Toolbar!
        Toolbar tb = (Toolbar) findViewById(R.id.TBToolbar);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        if (Title.equals("")) {
            toolbar_title.setText("");
        } else {
            toolbar_title.setText(Title);
        }
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

        //region Initial setting
        webview = (WebView) findViewById(R.id.webView);
        webViewPB = (ProgressBar) findViewById(R.id.webViewPB);
        IVBackButton = (ImageView) findViewById(R.id.IVBackButton);
        IVNextButton = (ImageView) findViewById(R.id.IVNextButton);
        IVReloadButton = (ImageView) findViewById(R.id.IVReloadButton);
        LLIsShowWebButton = (LinearLayout) findViewById(R.id.LLIsShowWebButton);

//        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
//        swipeLayout.setOnRefreshListener(this);
//        swipeLayout.setColorSchemeResources(R.color.pink, R.color.green,
//                R.color.blue, R.color.yellow);


//        this.setTitle(R.string.app_name);
//        System.out.println("WebViewClientPage:url=" + url);
//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        webViewPB.getProgressDrawable().setColorFilter(getResources().getColor(R.color.SelectedColor), PorterDuff.Mode.SRC_IN);

        webViewPB.setMax(100);

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
//                    Utility.dismissLoadingDialog();
                if (Title.equals("")) {
                    if (!title.isEmpty()) {
                        toolbar_title.setText(view.getTitle());
                    }
                }
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                Utility.PrintLog(getClass().getName(), "newProgress=" + newProgress);
                webViewPB.setProgress(newProgress);

                if (newProgress == 100)
                    webViewPB.setVisibility(View.GONE);
                else
                    webViewPB.setVisibility(View.VISIBLE);

                super.onProgressChanged(view, newProgress);
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlS) {
                if (Utility.checkExtension(urlS).toLowerCase().equals("pdf")) {
                    webview.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + urlS);

                }
                return true;
//                    return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        // 设置可以支持缩放
        webview.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        webview.getSettings().setBuiltInZoomControls(true);
//设置可在大视野范围内上下左右拖动，并且可以任意比例缩放
        webview.getSettings().setUseWideViewPort(true);
//设置默认加载的可视范围是大视野范围
        webview.getSettings().setLoadWithOverviewMode(true);
//自适应屏幕
//        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webview.clearCache(true);

        if (url.contains("http")) {
            if (K11SSO) {
                webViewPB.setProgress(0);
                webViewPB.setVisibility(View.VISIBLE);

                //region AES256 decry/encrypt
                String data = "";
//                try {
//                    List<NameValuePair> nameValuePairs = new ArrayList<>();
//                    nameValuePairs.add(new BasicNameValuePair("email", "12345"));
//                    nameValuePairs.add(new BasicNameValuePair("phone", "12345"));
//                    nameValuePairs.add(new BasicNameValuePair("age_group", "23456"));
//                    nameValuePairs.add(new BasicNameValuePair("firstname", "23456"));
//                    nameValuePairs.add(new BasicNameValuePair("lastname", "23456"));
//                    nameValuePairs.add(new BasicNameValuePair("title", "23456"));
//
//                    HttpClient httpclient = new DefaultHttpClient();
//                    HttpPost httppost = new HttpPost("URL_STRING");
//                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//                    HttpResponse response = httpclient.execute(httppost);
//                    data = new BasicResponseHandler().handleResponse(response);
//                } catch (Exception e) {
//                    //handle error
//                }

                String POSTData = "email=" + "&phone=" + "&age_group=" +
                        "&firstname=" + "&lastname=" + "&title=";

                long unixTime = System.currentTimeMillis() / 1000L;
//                String password = "abcdefghijklmnopqrstuvwxyz123456";
                String password = "k11@Artisanal_Living@2017app1234";
//                String message = "email=leo@cleargo.com&phone=99998888";
                String message = "{\"Mobile_Email\": \"abc@nwd.com.hk\",\"Password\": \"11111111a\",\"Timestamp\": 1486364749}";
                String encryptedMsg = "";
                String Test = "";
//                String ivString = "abcdefghijklmnop";
//                String ivString = "5625482646533243";
                String ivString = "BTYQXTNTVVAFEKDQ";

//                byte[] iv = new byte[16];
//                SecureRandom random = new SecureRandom();
//                random.nextBytes(iv);

                try {

                    byte[] iv = ivString.getBytes();
//                    final MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] bytes = password.getBytes();
//                    digest.update(bytes, 0, bytes.length);
//                    byte[] key = digest.digest();

                    //TODO joyce no need SHA-256 this part
                    SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");

                    encryptedMsg = Base64.encodeToString(AESCrypt.encrypt(secretKeySpec, iv, message.getBytes()), Base64.NO_WRAP);

                    Log.e("AES256", "encryptedMsg=" + encryptedMsg);

                    encryptedMsg= "WN3IiUh66AuBZRBq6HWPc0b/KO8jz5yd39/hpGbcnbq3aQiWHJI9Kit1OOAtID1BPQ/vAqjUpmA7Hd2hvnLYA1MYFgvZDDSeFKdSsE5pLZw=";

                    byte[] decodedCipherText = Base64.decode(encryptedMsg, Base64.NO_WRAP);

                    String DecryptedMsg = new String(AESCrypt.decrypt(secretKeySpec, iv, decodedCipherText), "UTF-8");

                    Log.e("AES256", "DecryptedMsg=" + DecryptedMsg);

                    //TODO using password as key, message as data, iv String as iv , AES256/CBC/CS7Padding is correct!
//                    Test = Base64.encodeToString(AESCrypt.encrypt(secretKeySpec, iv, message.getBytes("UTF-8")), Base64.NO_WRAP);
//                    encryptedMsg = AESCrypt.encrypt(password, message);


                } catch (Exception e) {
                    //handle error
                }

                webview.postUrl(url, EncodingUtils.getBytes(encryptedMsg, "BASE64"));
                //endregion
            } else {
                webViewPB.setProgress(0);
                webViewPB.setVisibility(View.VISIBLE);

                if (Utility.checkExtension(url).toLowerCase().equals("pdf")) {
                    Utility.PrintLog(getClass().getName(), "last extension=pdf");
                    webview.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
                } else {
                    Utility.PrintLog(getClass().getName(), "last extension!=pdf");
                    webview.loadUrl(url);
                }
            }
        } else {
            webViewPB.setProgress(0);
            webViewPB.setVisibility(View.VISIBLE);
//            webview.loadData(url, "text/html", "UTF-8");
            webview.getSettings().setSupportZoom(false);
            webview.getSettings().setBuiltInZoomControls(false);
            webview.getSettings().setUseWideViewPort(false);
            webview.getSettings().setLoadWithOverviewMode(false);
            webview.loadDataWithBaseURL(null, url, "text/html", "UTF-8", null);
//            Log.w("WVCP","!contains http");
        }

        if (IsShowWebButton) {
            LLIsShowWebButton.setVisibility(View.VISIBLE);
            buttonSetting();
        } else {
            LLIsShowWebButton.setVisibility(View.GONE);
        }
        //endregion
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

    private void buttonSetting() {
        IVBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoBack()) {
                    webview.goBack();
                }
            }
        });

        IVNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoForward()) {
                    webview.goForward();
                }
            }
        });

        IVReloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.reload();
            }
        });
    }
}

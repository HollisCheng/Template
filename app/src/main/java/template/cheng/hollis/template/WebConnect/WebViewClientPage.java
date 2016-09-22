package template.cheng.hollis.template.WebConnect;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import template.cheng.hollis.template.R;
import template.cheng.hollis.template.Utility;


public class WebViewClientPage extends AppCompatActivity
//        implements SwipeRefreshLayout.OnRefreshListener
{
    private WebView webview;
    private String url;
    private String Title;
    private TextView toolbar_title;
//    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_client_page);
        Utility.showLoadingDialog(this, this.getString(R.string.Loading));

        Bundle params = getIntent().getExtras();
        if (params != null) {
            url = params.getString("LINK");
            Title = params.getString("Title");
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

        webview = (WebView) findViewById(R.id.webView);
//        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
//        swipeLayout.setOnRefreshListener(this);
//        swipeLayout.setColorSchemeResources(R.color.pink, R.color.green,
//                R.color.blue, R.color.yellow);


//        this.setTitle(R.string.app_name);
//        System.out.println("WebViewClientPage:url=" + url);
//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        webview.setWebViewClient(new WebViewClient());
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

        webview.clearCache(true);

        if (url.contains("http")) {
            webview.loadUrl(url);
//            Log.w("WVCP","contains http");
        } else {
//            webview.loadData(url, "text/html", "UTF-8");
            webview.getSettings().setSupportZoom(false);
            webview.getSettings().setBuiltInZoomControls(false);
            webview.getSettings().setUseWideViewPort(false);
            webview.getSettings().setLoadWithOverviewMode(false);
            webview.loadDataWithBaseURL(null, url, "text/html", "UTF-8", null);
//            Log.w("WVCP","!contains http");
        }

        if (Title.equals("")) {
            webview.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView view, String url) {
                    Utility.dismissLoadingDialog();
                    toolbar_title.setText(view.getTitle());
                }
            });
        } else {
            webview.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView view, String url) {
                    Utility.dismissLoadingDialog();
//                    toolbar_title.setText(view.getTitle());
                }
            });
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

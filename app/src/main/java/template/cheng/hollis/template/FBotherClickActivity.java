package template.cheng.hollis.template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Collections;

public class FBotherClickActivity extends AppCompatActivity {
    private SwitchCompat SwitchFBIsLogin;
    private CallbackManager callbackManager;
    private AccessToken accessToken;
    private String LoginID = "";
    private String LoginEmail = "";
    private String LoginName = "";
    private String LoginToken = "";
    private boolean isLinkedFB = false;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbother_click);

        //region SetCustom Toolbar!
        Toolbar tb = (Toolbar) findViewById(R.id.TBToolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getString(R.string.LAS));
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

        SwitchFBIsLogin = (SwitchCompat) findViewById(R.id.SwitchFBIsLogin);
        tvResult = (TextView) findViewById(R.id.tvResult);

        //region Facebook
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                //accessToken之後或許還會用到 先存起來
                accessToken = loginResult.getAccessToken();
                //Log.w("LSMA","FACEBOOK Login Success!");
                Log.d("FB", "access token got.");
                //send request and call graph api

                GraphRequest request = GraphRequest.newMeRequest(
                        accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {

                            //當RESPONSE回來的時候

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                //讀出姓名 ID FB個人頁面連結
                                //Log.w("LSMA","FB JSON=" + object.toString());
                                Log.d("FB", object.optString("name"));
                                Log.d("FB", object.optString("id"));
                                Log.d("FB", object.optString("email"));
//                                Log.d("FB", object.optString("birthday"));
//                                Log.d("FB", object.optString("gender"));
                                Log.d("FB", accessToken.getToken());

                                LoginID = object.optString("id");
                                LoginEmail = object.optString("email");
                                LoginName = object.optString("name");
                                LoginToken = accessToken.getToken();
                                isLinkedFB = true;
                                //TODO after user logined and linked with facebook , he can use this FBLogin bypass insert login info
                                //check the FB_ID and Email if mathc with ExternalLogin go through
                                tvResult.setText("LoginID =" + LoginID +
                                        "\nLoginEmail =" + LoginEmail +
                                        "\nLoginName =" + LoginName +
                                        "\nLoginToken =" + LoginToken
                                );
                            }
                        });

                //包入你想要得到的資料 送出request
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,birthday,gender,picture");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.d("FB", "CANCEL");
                SwitchFBIsLogin.setChecked(false);
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FB", error.toString());
                SwitchFBIsLogin.setChecked(false);
            }
        });
//        callbackManager = CallbackManager.Factory.create();
//        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
//        //permission!
//        loginButton.setReadPermissions("email");
//        //這邊為了方便 直接寫成inner class
//
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            //登入成功
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//                //accessToken之後或許還會用到 先存起來
//                accessToken = loginResult.getAccessToken();
//                //Log.w("LSMA","FACEBOOK Login Success!");
//                Log.d("FB", "access token got.");
//                //send request and call graph api
//
//                GraphRequest request = GraphRequest.newMeRequest(
//                        accessToken,
//                        new GraphRequest.GraphJSONObjectCallback() {
//
//                            //當RESPONSE回來的時候
//
//                            @Override
//                            public void onCompleted(JSONObject object, GraphResponse response) {
//
//                                //讀出姓名 ID FB個人頁面連結
//                                //Log.w("LSMA","FB JSON=" + object.toString());
//                                Log.d("FB", object.optString("name"));
//                                Log.d("FB", object.optString("id"));
//                                Log.d("FB", object.optString("email"));
////                                Log.d("FB", object.optString("birthday"));
////                                Log.d("FB", object.optString("gender"));
//                                Log.d("FB", accessToken.getToken());
//
//                                //TODO after user logined and linked with facebook , he can use this FBLogin bypass insert login info
//                                //check the FB_ID and Email if mathc with ExternalLogin go through
//                            }
//                        });
//
//                //包入你想要得到的資料 送出request
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,email,birthday,gender,picture");
//                request.setParameters(parameters);
//                request.executeAsync();
//            }
//
//            //登入取消
//            @Override
//            public void onCancel() {
//                // App code
//
//                Log.d("FB", "CANCEL");
//            }
//
//            //登入失敗
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//
//                Log.d("FB", exception.toString());
//            }
//        });

        //endregion

        SwitchFBIsLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    LoginManager.getInstance().logInWithReadPermissions(LinkedSocialMediaActivity.this, Arrays.asList("public_profile", "user_friends"));
                    LoginManager.getInstance().logInWithReadPermissions(FBotherClickActivity.this, Collections.singletonList("email"));
                } else {
                    if (isLinkedFB)
                        LoginManager.getInstance().logOut();
                    tvResult.setText("");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginManager.getInstance().logOut();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

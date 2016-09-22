package template.cheng.hollis.template;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class FBActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private boolean btnFBClicked = false;
    private boolean isLoginFb = false;
    private AccessToken accessToken;
    private String FBLoginID;
    private String FBLoginEmailMobile;
    private String FBLoginName;
    private String FBLoginToken, first_name, last_name, gender, birthday, picture;
    private TextView tvResult;
    private SwitchCompat SwitchFBIsLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb);

        //region SetCustom Toolbar!
        Toolbar tb = (Toolbar) findViewById(R.id.TBToolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("Test FB");
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

        tvResult = (TextView) findViewById(R.id.tvResult);

        //region Facebook
        // change the login button to normal and use it to login(Ref:linkedFacebookPage)   method:GetMemberExternalLogin
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        //permission!
        loginButton.setReadPermissions("email");
        //這邊為了方便 直接寫成inner class
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFBClicked = true;
                //Log.w("FB", "btnFBClicked=true");
            }
        });
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            //登入成功
            @Override
            public void onSuccess(LoginResult loginResult) {
//                Utility.showLoadingDialog(PersonalizationActivity.this, PersonalizationActivity.this.getString(R.string.Loading));
                //accessToken之後或許還會用到 先存起來
                accessToken = loginResult.getAccessToken();
                //Log.w("PersonalA", "FACEBOOK Login Success!");
//                Log.d("FB", "access token got.");
                //send request and call graph api

                GraphRequest request = GraphRequest.newMeRequest(
                        accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {

                            //當RESPONSE回來的時候

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                //讀出姓名 ID FB個人頁面連結
                                ////Log.w("PersonalA","FB JSON=" + object.toString());
                                ////Log.w("FB", object.optString("name"));
                                ////Log.w("FB", object.optString("id"));
                                ////Log.w("FB", object.optString("email"));
                                ////Log.w("FB", accessToken.getToken());

                                FBLoginID = object.optString("id");
                                FBLoginEmailMobile = object.optString("email");
                                FBLoginName = object.optString("name");
                                first_name = object.optString("first_name");
                                last_name = object.optString("last_name");
                                gender = object.optString("gender");
                                birthday = object.optString("birthday");
                                FBLoginToken = accessToken.getToken();
                                isLoginFb = true;
                                btnFBClicked = false;
                                tvResult.setText("FBLoginID =" + FBLoginID +
                                        "\nFBLoginEmailMobile =" + FBLoginEmailMobile +
                                        "\nFBLoginName =" + FBLoginName +
                                        "\nfirst_name =" + first_name +
                                        "\nlast_name =" + last_name +
                                        "\ngender =" + gender +
                                        "\nbirthday =" + birthday +
                                        "\npicture =" + picture
                                );
                            }
                        });

                //包入你想要得到的資料 送出request
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,first_name,last_name,gender,birthday,picture");
                request.setParameters(parameters);
                request.executeAsync();

                btnFBClicked = false;
            }

            //登入取消
            @Override
            public void onCancel() {
                // App code

                Log.d("FB", "onCancel");
                btnFBClicked = false;
                isLoginFb = false;
            }

            //登入失敗
            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("FB", "onError");
                Log.d("FB", exception.toString());
                btnFBClicked = false;
                isLoginFb = false;
            }
        });

        //endregion


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
//        mCameraIntentHelper.onActivityResult(requestCode, resultCode, intent);

        if (btnFBClicked)
            callbackManager.onActivityResult(requestCode, resultCode, intent);

//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            //get the image uri
//            Uri selectedImageUri = intent.getData();
//            String[] projection = {MediaStore.MediaColumns.DATA};
//            Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
//                    null);
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//            cursor.moveToFirst();
//            String selectedImagePath = cursor.getString(column_index);
//            //from uri to bitmap
//            Bitmap bm;
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
//            //prevent load picture error,,,,do not know why
//            BitmapFactory.decodeFile(selectedImagePath, options);
//            final int REQUIRED_SIZE = 200;
//            int scale = 1;
//            while (options.outWidth / scale / 2 >= REQUIRED_SIZE
//                    && options.outHeight / scale / 2 >= REQUIRED_SIZE)
//                scale *= 2;
//            options.inSampleSize = scale;
//            options.inJustDecodeBounds = false;
//            bm = BitmapFactory.decodeFile(selectedImagePath, options);
//            bitmap = bm;
//            //TODO select from gallery no need to rotate *DONE*
////            int degree = Utility.readPictureDegree(selectedImagePath);
////            bitmap = Utility.rotateBitmap(bitmap, degree);
////            set the imageView bitmap
//            IVProPic.setImageBitmap(bitmap);
////            //Log.w("SSA","the path from chose library =" + selectedImagePath);
////            //Log.w("SSA", "Pick photo degree=" + degree);
//            //bitmap to string
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
//            byte[] b = baos.toByteArray();
//            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
//            photoType = "jpg";
//            photoString = encodedImage;
//        }
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

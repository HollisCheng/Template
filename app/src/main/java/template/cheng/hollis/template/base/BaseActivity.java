package template.cheng.hollis.template.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import template.cheng.hollis.template.R;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private LinearLayout mProgressBarContainer;
    private AlertDialog mAlertDialog;

    public BaseApplication getBaseApplication() {
        return (BaseApplication) getApplication();
    }

    //Register fragment lifecycle callbacks
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                    @Override
                    public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
                        super.onFragmentAttached(fm, f, context);
                        getBaseApplication().addToFragmentArrayList(f);
                    }

                    @Override
                    public void onFragmentDetached(FragmentManager fm, Fragment f) {
                        super.onFragmentDetached(fm, f);
                        getBaseApplication().removeFromFragmentArrayList(f);
                    }

                }, true);
    }

    @Override
    public BaseActivity getBaseActivity() {
        return this;
    }

    @Override
    public void showLoadingIndicator() {
        if (mProgressBarContainer == null) {
            mProgressBarContainer = new LinearLayout(this);
            mProgressBarContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));

            ProgressBar mProgressBar = new ProgressBar(this, null, android.R.attr.progressBarStyle);
            mProgressBar.getIndeterminateDrawable().setColorFilter(
                    getResources().getColor(R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);

            mProgressBar.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            mProgressBarContainer.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            mProgressBarContainer.addView(mProgressBar);
            ViewGroup mViewGroup = (ViewGroup) findViewById(android.R.id.content);
            mViewGroup.addView(mProgressBarContainer);
        }

        mProgressBarContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        if (mProgressBarContainer != null
//                && APIManager.getInstance().getApiRequestCount() <= 0
                ) {
            mProgressBarContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void showDialog(String message) {
        if(!this.isFinishing()) { //here activity means your activity class

            if (mAlertDialog != null && mAlertDialog.isShowing()) {
                mAlertDialog.dismiss();
            }

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(BaseActivity.this);
            mBuilder.setMessage(message);
            mBuilder.setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mAlertDialog.dismiss();
                }
            });

            mAlertDialog = mBuilder.create();
            mAlertDialog.show();
        }
    }

    //Change Activity
    public Intent getChangeActivityIntent(Class targetActivity, boolean allowBack) {
        Intent mIntent = new Intent(this, targetActivity);
        if (!allowBack) {
            finishAffinity();
        }
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        return mIntent;
    }

    public Gson getGson(){
        return getBaseApplication().getGson();
    }

    //invaild api token , expired api token handling
    public void handleInvalidAPIToken(String message){
//        log("handleInvalidAPIToken called");
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(BaseActivity.this);
        mBuilder.setMessage(message);
        mBuilder.setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                deleteAPIToken();
//                deleteRongIMToken();
//                RongIM.getInstance().logout();
//                startActivity(getChangeActivityIntent(SignInActivity.class , false));
                dialogInterface.dismiss();
            }
        });
        mBuilder.setCancelable(false);
        mBuilder.show();
    }

    // TODO: 17/9/2017  refreshUserInfoCache , refreshGroupInfoCache when user info changed
}

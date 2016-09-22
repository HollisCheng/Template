package template.cheng.hollis.template;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class AnimationActivity extends AppCompatActivity {
    private ImageView ivBG, ivShow;
    private Handler handler = new Handler();
    private Handler handler2 = new Handler();
    private boolean isLoaded1stImage4sec = false;
    private int index = 0;
    private Animation slide_left_in, slide_left_out;
    private Button btnShow, btnHide;
    private FloatingActionMenu famChangeFrag;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FragmentManager fm;
    private ImageView IVMultipleSelect;
    private ArrayList<String> multiImageString;
    private int REQUEST_IMAGE = 5;
    private Bitmap bitmap = null;
    private String photoString, photoType;
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        //region SetCustom Toolbar!
        Toolbar tb = (Toolbar) findViewById(R.id.TBToolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);

        toolbar_title.setText(getString(R.string.FEEDB));
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

        ivBG = (ImageView) findViewById(R.id.ivBG);
        handler.postDelayed(run, 0);

        ivShow = (ImageView) findViewById(R.id.ivShow);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnHide = (Button) findViewById(R.id.btnHide);
        slide_left_in = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
        slide_left_out = AnimationUtils.loadAnimation(this, R.anim.slide_left_out);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivShow.setVisibility(View.VISIBLE);
                ivShow.startAnimation(slide_left_in);
                btnShow.setVisibility(View.GONE);
                btnHide.setVisibility(View.VISIBLE);
//                new CountDownTimer(300, 100) {
//                    public void onTick(long ms) {
//                    }
//                    public void onFinish() {
//
//                    }
//                }.start();
            }
        });

        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivShow.startAnimation(slide_left_out);
                btnShow.setVisibility(View.VISIBLE);
                btnHide.setVisibility(View.GONE);
                new CountDownTimer(300, 100) {
                    public void onTick(long ms) {
                    }

                    public void onFinish() {
                        ivShow.setVisibility(View.GONE);
                    }
                }.start();
            }
        });

        fm = getSupportFragmentManager();
        // add
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.your_placeholder, new TestFragment());
// alternatively add it with a tag
// trx.add(R.id.your_placehodler, new YourFragment(), "detail");
        ft.commit();

        famChangeFrag = (FloatingActionMenu) findViewById(R.id.famChangeFrag);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        famChangeFrag.setClosedOnTouchOutside(true);
        famChangeFrag.hideMenuButton(false);
        famChangeFrag.showMenuButton(true);

        fab1.setLabelColors(getResources().getColor(R.color.transparent), getResources().getColor(R.color.transparent), getResources().getColor(R.color.transparent));
        fab2.setLabelColors(getResources().getColor(R.color.transparent), getResources().getColor(R.color.transparent), getResources().getColor(R.color.transparent));
        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);

        IVMultipleSelect = (ImageView) findViewById(R.id.IVMultipleSelect);
        IVMultipleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ContextCompat.checkSelfPermission(AnimationActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

//            } else {

                    // No explanation needed, we can request the permission.
                    Log.w("SSA", "checkSelfPermission else");
                    ActivityCompat.requestPermissions(AnimationActivity.this,
                            new String[]{android.Manifest.permission.CAMERA
//                            , Manifest.permission.FLASHLIGHT
                                    , android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                                    , android.Manifest.permission.READ_EXTERNAL_STORAGE
                                    , android.Manifest.permission.WAKE_LOCK
                            },
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
//            }
                } else {
                    // Multi image selector form an Activity
//                MultiImageSelector.create(AnimationActivity.this)
//                        .start(AnimationActivity.this, REQUEST_IMAGE);

                    MultiImageSelector.create(AnimationActivity.this)
                            .showCamera(true) // show camera or not. true by default
                            .count(2) // max select image size, 9 by default. used width #.multi()
//                            .single() // single mode
                            .multi() // multi mode, default mode;
//                            .origin(multiImageString) // original select data set, used width #.multi()
                            .start(AnimationActivity.this, REQUEST_IMAGE);

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {

                // Get the result list of select image paths
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // do your logic ....
                Log.w("AA", "List<String>=" + path.size() + "path.toString()=" + path.toString());

                //loop the path of image and store string of each image
                multiImageString = new ArrayList<>();
                for (int i = 0; i < path.size(); i++) {
                    String selectedImagePath = path.get(i);
                    //from uri to bitmap
                    Bitmap bm;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    //prevent load picture error,,,,do not know why
                    BitmapFactory.decodeFile(selectedImagePath, options);
                    Log.w("LA", i + "selectedImagePath=" + selectedImagePath);
                    final int REQUIRED_SIZE = 200;
                    int scale = 1;
                    while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                            && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                        scale *= 2;
                    options.inSampleSize = scale;
                    options.inJustDecodeBounds = false;
                    bm = BitmapFactory.decodeFile(selectedImagePath, options);
                    bitmap = bm;
                    //TODO select from gallery no need to rotate *DONE*
//            int degree = Utility.readPictureDegree(selectedImagePath);
//            bitmap = Utility.rotateBitmap(bitmap, degree);
//            set the imageView bitmap
//                    IVCamera.setImageBitmap(bitmap);
//            //Log.w("SSA","the path from chose library =" + selectedImagePath);
//            //Log.w("SSA", "Pick photo degree=" + degree);
                    //bitmap to string
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                    photoType = "jpg";
                    photoString = encodedImage;

                    // POST the image
//            UpdateUserProfile();
                    Log.w("LA", "onActivityResult:photoType=" + photoType + ",photoString=" + photoString);

                    multiImageString.add(photoString);
                }
                Log.w("AA", "multiImageString=" + multiImageString.toString());

                bitmap = null;
            }
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab1:
////                    OffersTabsViewPager.setCurrentItem(0);
//                    if (Utility.IsBookmarked) {
//                        //Log.w("CWOF", "onTabSelected:update,position=" + position);
//                        update();
//                        Utility.IsBookmarked = false;
////                        OffersTabs.getTabAt(position).select();
//                    }
//
//                    t.setScreenName("Privileges-Recommend");
//                    t.send(new HitBuilders.ScreenViewBuilder().build());

                    // replace
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.your_placeholder, new TestFragment());
                    ft.commit();
//                    tvFragmentName.setText(R.string.LATEST);
                    famChangeFrag.close(true);
                    break;
                case R.id.fab2:
//                    OffersTabsViewPager.setCurrentItem(1);
//                    if (Utility.IsBookmarked) {
//                        //Log.w("CWOF", "onTabSelected:update,position=" + position);
//                        update();
//                        Utility.IsBookmarked = false;
////                        OffersTabs.getTabAt(position).select();
//                    }


//                    t.setScreenName("Privileges-Bookmark");
//                    t.send(new HitBuilders.ScreenViewBuilder().build());
                    // replace
                    FragmentTransaction ft2 = fm.beginTransaction();
                    ft2.replace(R.id.your_placeholder, new Test2Fragment());
                    ft2.commit();

//                    tvFragmentName.setText(R.string.BOOKMARKED);
                    famChangeFrag.close(true);
                    break;
//                case R.id.fab3:
//                    fab2.setVisibility(View.VISIBLE);
//                    break;
            }
        }
    };
    Runnable run = new Runnable() {
        @Override
        public void run() {
            int images[] = {R.drawable.icon_feedback, R.drawable.icon_residentzone, R.drawable.common_google_signin_btn_icon_dark};

            if (index > 3) {
                handler.removeCallbacks(run);
            } else {
                if (index >= 3)
                    index = 0;
                //fade to original
                AlphaAnimation animation1 = new AlphaAnimation(0.7f, 1.0f);
                animation1.willChangeBounds();
                animation1.willChangeTransformationMatrix();
                animation1.getBackgroundColor();
                animation1.setDuration(1000);
                animation1.setBackgroundColor(100);
                animation1.setStartOffset(1000); //time for that color effect
                animation1.setFillAfter(true);
                ivBG.startAnimation(animation1);
                ivBG.setBackgroundResource(images[index++]);
                handler.postDelayed(run, 1000);
            }
        }
    };

//    after loading go to other page -> handler2.postDelayed(goIntent, 2000);
//    Runnable goIntent = new Runnable() {
//        @Override
//        public void run() {
//            if (isLoaded1stImage4sec) {
//                if (isLoggedIn) {
//                    Intent intent1 = new Intent(MainActivity.this, ALMainPage.class);
//
//                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent1);
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                } else if (isFirstTimeInApp) {
//                    Intent intent = new Intent(getApplicationContext(), FirstTimeActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
////            MainActivity.this.finish();
//                } else {
//                    Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//
//
//                }
//            } else {
//                handler2.postDelayed(goIntent, 2000);
//            }
//        }
//    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(run);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        //TODO same delay, maybe use boolean check if propic changed then use NavUtils,,,do after API
//        NavUtils.navigateUpFromSameTask(this);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
//            NavUtils.navigateUpFromSameTask(this);
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Log.w("SSA", "permission success");
                    MultiImageSelector.create(AnimationActivity.this)
                            .showCamera(true) // show camera or not. true by default
                            .count(12) // max select image size, 9 by default. used width #.multi()
                            .single() // single mode
                            .multi() // multi mode, default mode;
                            .origin(multiImageString) // original select data set, used width #.multi()
                            .start(AnimationActivity.this, REQUEST_IMAGE);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    boolean should = ActivityCompat.shouldShowRequestPermissionRationale(AnimationActivity.this, android.Manifest.permission.CAMERA);
                    boolean should2 = ActivityCompat.shouldShowRequestPermissionRationale(AnimationActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (!should || !should2) {
                        Utility.promptSettings(AnimationActivity.this);
                    }
                    Log.w("SSA", "permission fail");
                }
                return;
            }
//            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//                    Log.w("SSA", "permission success");
//                    MultiImageSelector.create(AnimationActivity.this)
//                            .showCamera(true) // show camera or not. true by default
//                            .count(12) // max select image size, 9 by default. used width #.multi()
//                            .single() // single mode
//                            .multi() // multi mode, default mode;
//                            .origin(multiImageString) // original select data set, used width #.multi()
//                            .start(AnimationActivity.this, REQUEST_IMAGE);
//                } else {
//                    boolean should = ActivityCompat.shouldShowRequestPermissionRationale(AnimationActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
//                    if (!should) {
//                        Utility.promptSettings(AnimationActivity.this);
//                    }
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                    Log.w("SSA", "permission fail");
//                }
//            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}

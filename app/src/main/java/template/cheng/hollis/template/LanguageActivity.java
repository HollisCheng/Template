package template.cheng.hollis.template;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.fingerprint.FingerprintManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import me.philio.pinentry.PinEntryView;
import template.cheng.hollis.template.Button.SweetSansRegButton;
import template.cheng.hollis.template.Camera.BitmapHelper;
import template.cheng.hollis.template.Camera.CameraIntentHelper;
import template.cheng.hollis.template.Camera.CameraIntentHelperCallback;
import template.cheng.hollis.template.CoordinatorLayout_Card_Tab_Filter.CoordinatorActivity;
import template.cheng.hollis.template.SQLiteDB.Language;
import template.cheng.hollis.template.SQLiteDB.LanguageDAO;

import static com.facebook.internal.FacebookRequestErrorClassification.KEY_NAME;

public class LanguageActivity extends AppCompatActivity {
    private Button LangENG, LangTC, LangSC;
    private String language;
    private ImageView IVonTouch, IVCamera, IVFPA;
    private PinEntryView pinEntryView;
    private SweetSansRegButton SSRBShow;

    private Dialog dialog;
    private static final int RESULT_OK = -1;
    private Bitmap bitmap = null;
    private String photoString, photoType;
    private CameraIntentHelper mCameraIntentHelper;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 2;
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 3;

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;
    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        bus.register(this);

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

        //region Language

        LangENG = (Button) findViewById(R.id.btnLangENG);
        LangTC = (Button) findViewById(R.id.btnLangTC);
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
                InsertDB("en");
                Intent intent1 = new Intent(LanguageActivity.this, MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
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
                InsertDB("zh");
                Intent intent1 = new Intent(LanguageActivity.this, MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
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

        IVonTouch = (ImageView) findViewById(R.id.IVonTouch);

        TextView tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setText("https://im.nwd.com.hk/NWD_SmartOfficeApp/Account/Login.aspx?msg=f%2fxsZW1IMq1H1HEVqPgXI8vN2JLFZwCG%2bS%2bwZDSGDABISvAN6MzwHQ%3d%3d&ReturnUrl=Admin%2fHappen.aspx%3fId%3d0");
        tv2.setTextIsSelectable(true);

        IVonTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        IVonTouch.setImageAlpha(200);
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        IVonTouch.setImageAlpha(255);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        IVonTouch.setImageAlpha(255);

                        Intent intent = new Intent(LanguageActivity.this, CoordinatorActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
                return true;
            }
        });

        pinEntryView = (PinEntryView) findViewById(R.id.pin_entry_simple);
        SSRBShow = (SweetSansRegButton) findViewById(R.id.SSRBShow);

        SSRBShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pinEntryView.getText().length() == 4) {
                    SSRBShow.setText(pinEntryView.getText().toString());
                } else {
                    Utility.NoticeDialogOK(LanguageActivity.this, "Please input PIN");
                }
            }
        });

        //region Take photo and select image from gallery
        IVCamera = (ImageView) findViewById(R.id.IVCamera);
        setupCameraIntentHelper();

        IVCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) LanguageActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.nwd_question_layout, null);
                TextView txtTakePhoto = (TextView) layout.findViewById(R.id.txtTakePhoto);
                TextView txtChooseGallery = (TextView) layout.findViewById(R.id.txtChooseGallery);

                //	Take a Photo
                txtTakePhoto.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        SelectedCamera();
                        dialog.dismiss();
                    }
                });

                //	Choose from Gallery
                txtChooseGallery.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        if (ContextCompat.checkSelfPermission(LanguageActivity.this,
                                android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                            // Should we show an explanation?
//                            if (ActivityCompat.shouldShowRequestPermissionRationale(SlideSettingActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                            // Show an explanation to the user *asynchronously* -- don't block
                            // this thread waiting for the user's response! After the user
                            // sees the explanation, try again to request the permission.

                            ActivityCompat.requestPermissions(LanguageActivity.this,
                                    new String[]{
//                                                Manifest.permission.CAMERA
//                                                , Manifest.permission.FLASHLIGHT,
                                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                                            , android.Manifest.permission.READ_EXTERNAL_STORAGE
                                            , android.Manifest.permission.WAKE_LOCK
                                    },
                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
//                            } else {
                            // No explanation needed, we can request the permission.
                            Log.w("SSA", "checkSelfPermission else");


                            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
//                            }
                        } else {
                            Intent intent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(Intent.createChooser(intent, "Select File"), 1);
                            dialog.dismiss();
                            Log.w("SSA", "checkSelfPermission");
                        }


                    }
                });

                dialog = ShowDialog(LanguageActivity.this, layout);
            }
        });
        //endregion

        //region Finger print auth
        IVFPA = (ImageView) findViewById(R.id.IVFPA);
        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        IVFPA.setOnClickListener(new OnSingleClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onSingleClick(View v) {
                //put below action to OnCreate can always listen

                if (!keyguardManager.isKeyguardSecure()) {

                    Toast.makeText(LanguageActivity.this,
                            "Lock screen security not enabled in Settings",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if (ActivityCompat.checkSelfPermission(LanguageActivity.this,
                        Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(LanguageActivity.this,
                            "Fingerprint authentication permission not enabled",
                            Toast.LENGTH_LONG).show();

                    return;
                }

                if (!fingerprintManager.hasEnrolledFingerprints()) {
                    // This happens when no fingerprints are registered.
                    Toast.makeText(LanguageActivity.this,
                            "Register at least one fingerprint in Settings",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                generateKey();

                if (cipherInit()) {
                    cryptoObject = new FingerprintManager.CryptoObject(cipher);
                    FingerprintHandler helper = new FingerprintHandler(LanguageActivity.this);
                    helper.startAuth(fingerprintManager, cryptoObject);

                }

                //animation to let you know fingerprint auth is listening now
                ScaleAnimation a = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                a.setDuration(500);
                a.setRepeatCount(Animation.INFINITE);
                a.setRepeatMode(Animation.REVERSE);
                IVFPA.startAnimation(a);
                //after get success fingerprint auth, should go other page
            }
        });
        //endregion

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
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        mCameraIntentHelper.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCameraIntentHelper.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        mCameraIntentHelper.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            //get the image uri
            Uri selectedImageUri = intent.getData();
            String[] projection = {MediaStore.MediaColumns.DATA};
            Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                    null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            String selectedImagePath = cursor.getString(column_index);
            //from uri to bitmap
            Bitmap bm;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            //prevent load picture error,,,,do not know why
            BitmapFactory.decodeFile(selectedImagePath, options);
            Log.w("LA", "selectedImagePath=" + selectedImagePath);
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
            IVCamera.setImageBitmap(bitmap);
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
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Log.w("SSA", "permission success");
                    if (mCameraIntentHelper != null) {
                        mCameraIntentHelper.startCameraIntent();
                    }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    boolean should = ActivityCompat.shouldShowRequestPermissionRationale(LanguageActivity.this, Manifest.permission.CAMERA);
                    boolean should2 = ActivityCompat.shouldShowRequestPermissionRationale(LanguageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (!should || !should2) {
                        Utility.promptSettings(LanguageActivity.this);
                    }
                    Log.w("SSA", "permission fail");
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Log.w("SSA", "permission success");
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select File"), 1);
                    dialog.dismiss();
                } else {
                    boolean should = ActivityCompat.shouldShowRequestPermissionRationale(LanguageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (!should) {
                        Utility.promptSettings(LanguageActivity.this);
                    }
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.w("SSA", "permission fail");
                }

            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES,
                    "AndroidKeyStore");
        } catch (NoSuchAlgorithmException |
                NoSuchProviderException e) {
            throw new RuntimeException(
                    "Failed to get KeyGenerator instance", e);
        }

        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    @Subscribe
    public void onMessageEvent(FingerPrintBus event) {
        Utility.PrintLog(getClass().getName(), "FingerPrintBus,message=" + event.message + ",IsCorrectFingerPrint=" + event.IsCorrectFingerPrint);

        if (event.IsCorrectFingerPrint) {
            IVFPA.clearAnimation();
        }
    }

    public void SWLang(int num) {
        switch (num) {
            case 0:
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
        Utility.showLoadingDialog(this, this.getString(R.string.Loading));
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

    }

    private void SelectedCamera() {
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        fileUri = MediaCamStore.getOutputMediaFileUri(MediaCamStore.MEDIA_TYPE_IMAGE);  // create a file to save the image
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//        startActivityForResult(cameraIntent, CAMERA_RESULT);
        // Here, thisActivity is the current activity

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

//            } else {

            // No explanation needed, we can request the permission.
            Log.w("SSA", "checkSelfPermission else");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA
//                            , Manifest.permission.FLASHLIGHT
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE
                            , Manifest.permission.READ_EXTERNAL_STORAGE
                            , Manifest.permission.WAKE_LOCK
                    },
                    MY_PERMISSIONS_REQUEST_CAMERA);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
//            }
        } else {
            if (mCameraIntentHelper != null) {
                mCameraIntentHelper.startCameraIntent();
            }
            Log.w("SSA", "checkSelfPermission");
        }


    }

    private Dialog ShowDialog(Context context, View rootView) {
        rootView.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        Dialog dialog = new Dialog(context);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;


        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.Transparent));
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(rootView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);


        dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        Display display = Utility.GetScreenSize(context);

        params.width = display.getWidth() * 70 / 100;
        //params.height = 200;
        //params.alpha = 0.7f;
        dialog.getWindow().setAttributes(params);

        dialog.show();

        return dialog;
    }

    private void setupCameraIntentHelper() {
        mCameraIntentHelper = new CameraIntentHelper(this, new CameraIntentHelperCallback() {
            @Override
            public void onPhotoUriFound(Date dateCameraIntentStarted, Uri photoUri, int rotateXDegrees) {

                Bitmap photo = BitmapHelper.readBitmap(LanguageActivity.this, photoUri);
                if (photo != null) {
                    photo = BitmapHelper.shrinkBitmap(photo, 500, rotateXDegrees);
                    IVCamera.setImageBitmap(photo);

                    //get the type and extension
                    photo = BitmapHelper.shrinkBitmap(photo, 500, 0);
                    String sMimeType;
                    String extension;
                    sMimeType = Utility.getMimeType(photoUri.getPath());
                    extension = Utility.getFileExtension(sMimeType);

                    String encodedImage = Utility.convertBitmapToBase64(photo, extension);

                    photoType = "jpg";
                    photoString = encodedImage;

                    // POST the image
//                    UpdateUserProfile();
                    Log.w("LA", "setupCameraIntentHelper:photoType=" + photoType + ",photoString=" + photoString);
                }
            }

            @Override
            public void deletePhotoWithUri(Uri photoUri) {
                BitmapHelper.deleteImageWithUriIfExists(photoUri, LanguageActivity.this);
            }

            @Override
            public void onSdCardNotMounted() {
//                Toast.makeText(getApplicationContext(), getString(R.string.error_sd_card_not_mounted), Toast.LENGTH_LONG).show();
                Log.w("SSA", "External storage is required. Your SD card is most likely not mounted.");
            }

            @Override
            public void onCanceled() {
//                Toast.makeText(getApplicationContext(), getString(R.string.warning_camera_intent_canceled), Toast.LENGTH_LONG).show();
                Log.w("SSA", "Camera Intent was canceled by user.");
            }

            @Override
            public void onCouldNotTakePhoto() {
//                Toast.makeText(getApplicationContext(), getString(R.string.error_could_not_take_photo), Toast.LENGTH_LONG).show();
                Log.w("SSA", "There was an issue with your camera. We are very sorry for the inconvenience!");
            }

            @Override
            public void onPhotoUriNotFound() {
//                messageView.setText(getString(R.string.activity_camera_intent_photo_uri_not_found));
                Log.w("SSA", "Photo uri not found!");
            }

            @Override
            public void logException(Exception e) {
//                Toast.makeText(getApplicationContext(), getString(R.string.error_sth_went_wrong), Toast.LENGTH_LONG).show();
                Log.w("SSA", "Something went wrong. Please try again.");
                Log.d(getClass().getName(), e.getMessage());
            }
        });
    }

}

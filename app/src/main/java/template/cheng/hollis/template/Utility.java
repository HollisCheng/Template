package template.cheng.hollis.template;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.analytics.Tracker;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import template.cheng.hollis.template.objectInfo.KeyWordsInfo;

public class Utility {
    public static Tracker mTracker;
    public static RequestQueue mQueue;
    public static String AppLang = "";
    public static int currIndex;
    public static final String YOUTUBE_API_KEY = "AIzaSyCfNk5sbGNk4qcvPL0XpRA6p4KI8C416pc";

    public static ArrayList<KeyWordsInfo> KeyWordsInfoAL = new ArrayList<>();
    //for propertiesNamePageActivity identify which page using
    public static boolean IsRegisterPropertyName = false;
    public static boolean IsAddPPropertyName = false;
//    public static final String PREFS_NAME = "NWD";
//    public static void delOwnProperties(Context context) {
//        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        settings.edit().clear().apply();
//    }

//    public static void saveOwnProperties(Context context, Object favorites) {
//        SharedPreferences settings;
//        SharedPreferences.Editor editor;
//
//        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        editor = settings.edit();
//
//
//        Gson gson = new Gson();
//        String jsonFavourites = gson.toJson(favorites);
//
//        editor.putString(FAVORITES, jsonFavourites);
//        editor.apply();
//    }

//    public static GetMemberInfo getOwnProperties(Context context) {
//        SharedPreferences settings;
//        GetMemberInfo obj;
//        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        if (settings.contains(FAVORITES)) {
//            String jsonFavorites = settings.getString(FAVORITES, "");
//            Gson gson = new Gson();
//            obj = gson.fromJson(jsonFavorites, GetMemberInfo.class);
//        } else
//            return null;
//        return obj;
//    }

    public static boolean pwCharCheck(String pw, String repw) {
        if (pw.length() >= 8 && repw.length() >= 8 &&
                pw.length() <= 18 && repw.length() <= 18 &&
                Pattern.compile("[0-9]").matcher(pw).find() && Pattern.compile("[0-9]").matcher(repw).find() &&
                Pattern.compile("[a-z]").matcher(pw).find() && Pattern.compile("[a-z]").matcher(repw).find()) {
            return true;
        } else {
            return false;
        }


    }

    public static boolean isOnline(Context con) {
        ConnectivityManager cm = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static int GetMobilePageMargin(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        double sw = dm.widthPixels;
        double sh = dm.heightPixels;
//        //Log.w("PagerMargin", "screenWidth=" + sw + ",screenHeight=" + sh);
        double pageMargin = 300.0;
        double dif = sw / 720.0;
//        //Log.w("PagerMargin", "dif=" + dif);
        DecimalFormat df = new DecimalFormat("###.##");
        dif = Double.parseDouble(df.format(dif));
//        //Log.w("PagerMargin", "dif=" + dif);
        double DPageMargin = pageMargin * dif;
//        //Log.w("PagerMargin", "DPageMargin=" + DPageMargin);
        return (int) DPageMargin;
    }

    public static String GetImageSizeAPIRequest(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        double sw = dm.widthPixels;
        double sh = dm.heightPixels;
        //Log.w("U", "sw=" + sw + "," + "sh=" + sh);
//       S 640 x 960
//       M 640 x 1136
//       L 750 x 1334
//       X 1242 x 2208
        if (sh <= 1048)
            return "S";
        if (sh <= 1235)
            return "M";
        if (sh <= 1771)
            return "L";
        if (sh <= 2208)
            return "X";

        return "X";
    }

    public static int dayLeft(String date) {
        int display = 0;
//        String display = "";
//            String inputDateString = "01/22/2013";
//        try {
//            Calendar calCurr = Calendar.getInstance();
//            Calendar day = Calendar.getInstance();
//            //Log.w("U_dayLeft", "date=" + date);
//            day.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(date));
//            if (day.after(calCurr)) {
//                display = (day.get(Calendar.DAY_OF_YEAR) - (calCurr.get(Calendar.DAY_OF_YEAR)) + " days left");
//                //Log.w("U_dayLeft", display);
//                //Log.w("U_dayLeft", "day.get(Calendar.DAY_OF_YEAR)=" + day.get(Calendar.DAY_OF_YEAR));
//                //Log.w("U_dayLeft", "calCurr.get(Calendar.DAY_OF_YEAR)=" + calCurr.get(Calendar.DAY_OF_YEAR));
//            }
//
//        } catch (Exception e) {
//            //Log.w("U_dayLeft", e.toString());
//        }

//        String inputDateString = "29/09/2016";
        try {
            Calendar calCurr = Calendar.getInstance();
            Calendar calNext = Calendar.getInstance();
//            calNext.setTime(new Date(date));
//            calCurr.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(inputDateString));
            calNext.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(date));
//            calNext.set(Calendar.MONTH, calNext.get(Calendar.MONTH) + 1);
//            //Log.w("U_dayLeft", "NextDate=" + date);
//            //Log.w("U_dayLeft", "CurrDate=" + calCurr.toString());
//            //Log.w("U_dayLeft", "calNext=" + calNext.toString());
            if (calNext.after(calCurr)) {
                long timeDiff = calNext.getTimeInMillis() - calCurr.getTimeInMillis();
                int daysLeft = (int) (timeDiff / DateUtils.DAY_IN_MILLIS);
                display = (daysLeft);
//                //Log.w("U_dayLeft", display);
//                //Log.w("U_dayLeft", "calNext.getTimeInMillis()=" + calNext.getTimeInMillis());
//                //Log.w("U_dayLeft", "calCurr.getTimeInMillis()=" + calCurr.getTimeInMillis());
//                //Log.w("U_dayLeft", "timeDiff=" + timeDiff);
//                //Log.w("U_dayLeft", "DateUtils.DAY_IN_MILLIS=" + DateUtils.DAY_IN_MILLIS);

            } else {
                //Log.w("U_dayLeft", "curr date>target date");
            }

        } catch (Exception e) {
            ////Log.w("U_dayLeft", e.toString());
        }
//        else {
//            long timeDiff = calCurr.getTimeInMillis() - calNext.getTimeInMillis();
//            timeDiff = DateUtils.YEAR_IN_MILLIS - timeDiff;
//            int daysLeft = (int) (timeDiff / DateUtils.DAY_IN_MILLIS);
//        }

        return display;
    }

    public static String DateStringCutTime(String date) {
        //Date sample 11/01/2000 00:00:00
        return date.substring(0, 10);
    }

    public static boolean isEmpty(EditText myeditText) {
        boolean isEmpty = false;
        if (myeditText.getText().toString().trim().length() == 0)
            isEmpty = true;

        return isEmpty;
    }

    public static String getYouTubeID(String ytUrl) {
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|watch\\?v%3D|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(ytUrl);

        if (matcher.find()) {
            return matcher.group();
        }
        return "empty";
    }

    public static String APILang() {
        if (Utility.AppLang.equals("zh")) {
            return "TChi";
        } else if (Utility.AppLang.equals("en")) {
            return "Eng";
        } else {
            return "SChi";
        }
    }

    public static boolean BooleanIsTrueOrFalse(String str) {
        return str.toLowerCase().equals("true");
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isHKMobileValid(String mobile) {
        boolean isValid = false;

        String expression = "^[0-9]{8}$";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(mobile);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static String DateStringChange(String date) {
        //example "01/03/2016" -> "01-03-2016"

        return date.replace('/', '-');
    }

    public static String displayWithOutHrMinSec(String dateTime) {
        //example "01/03/2016 00:00:00" -> "01/03/2016"

        return dateTime.substring(0, 10);
    }

    public static long TransferDateTimeToLong(String selectedDate, String StartTime) {
        //Date Sample
//        String selectedDate = "01-05-2016";
        //Time Sample
//        String StartTime = "10:35";
        long DateTimeLong;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d1 = sdf.parse(selectedDate);
            long yyyymmdd = d1.getTime();


            String StringHour = StartTime.substring(0, 2);
            String StringMinutes = StartTime.substring(3, 5);

            int Hour;
            if (!StringHour.equals("00")) {
                Hour = Integer.parseInt(StringHour);
            } else {
                Hour = 0;
            }

            int Minutes;
            if (!StringMinutes.equals("00")) {
                Minutes = Integer.parseInt(StringMinutes);
            } else {
                Minutes = 0;
            }

//            //Log.w("Utility","TransferDateTimeToLong:StringHour=" + StringHour + ",StringMinutes=" + StringMinutes + ",Hour=" + Hour + ",Minutes=" + Minutes + ",selectedDate=" + selectedDate);
            long hours = (Hour * 3600000) + (Minutes * 60000);
            //the booking start dateTime Long
            DateTimeLong = yyyymmdd + hours;

//            facilityBookedInfo.setBookingStartDateTimeLong(yyyymmdd + hours);
        } catch (Exception e) {
            ////Log.w("Utility", "TransferDateTimeToLong:Error!" + e.toString());
            return 0;
        }

        return DateTimeLong;
    }

    public static void NoticeDialogOK(final Context context, String Notice) {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(context);
        alertDlg.setMessage(Notice);
        alertDlg.setCancelable(true);
//        alertDlg.setPositiveButton(context.getString(R.string.OK),
        alertDlg.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alertDlg.create().show();
    }

    public static void showLoadingDialog(Context context, String message) {
        showLoadingDialog(context, "", message);
    }

    //internal private
    private static ProgressDialog progress = null;

    public static void showLoadingDialog(final Context context, final String title, final String message) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (progress == null) {
                        progress = new ProgressDialog(context) {
                            @Override
                            protected void onCreate(Bundle savedInstanceState) {
                                super.onCreate(savedInstanceState);
                                ProgressBar bar = (ProgressBar) findViewById(android.R.id.progress);
                                bar.getIndeterminateDrawable()
                                        .setColorFilter(context.getResources().getColor(R.color.SelectedColor), android.graphics.PorterDuff.Mode.SRC_IN);
                            }
                        };
//                        progress.setIndeterminate(true);
                        progress.setProgressDrawable(context.getResources().getDrawable(R.color.SelectedColor));
//                        progress.setIndeterminateDrawable(context.getResources().getDrawable(R.color.SelectedColor));
                    }

                    if (!title.equals(""))
                        progress.setTitle(title);
                    progress.setMessage(message);
                    progress.setCancelable(false);
                    progress.setCanceledOnTouchOutside(false);
                    progress.show();
                } catch (Exception ex) {
                    //Log.w("Utility", "Error in loading showLoadingDialog: " + ex.getMessage());
                }
            }
        }, 500);

    }

    public static void dismissLoadingDialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (progress != null && progress.isShowing()) {
                        progress.dismiss();
                        progress = null;
                    }
                } catch (Exception ex) {
                    ////Log.w("Utility", "Error in loading dismissLoadingDialog: " + ex.getMessage());
                }
            }
        }, 500);
    }

    public static String convertBitmapToBase64(Bitmap bitmap, String extension) {

        Bitmap.CompressFormat cFormat = Bitmap.CompressFormat.JPEG;
        if (extension.toLowerCase().equals("png"))
            cFormat = Bitmap.CompressFormat.PNG;

        ByteArrayOutputStream bos = null;
        byte[] data = null;

        try {
            bos = new ByteArrayOutputStream();
            bitmap.compress(cFormat, 70, bos);
            data = bos.toByteArray();
        } catch (Exception ex) {
            ////Log.w("Utility", "Error in compress ByteArrayOutputStream: " + ex.getMessage());
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                    bos = null;
                }
            } catch (IOException e) {
                ////Log.w("Utility", "Error in close ByteArrayOutputStream: " + e.getMessage());
            }
        }

        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
                System.gc(); // system garbage recycle
            }
        }


        if (data != null) {

            String result = Base64.encodeToString(data, Base64.DEFAULT);
            data = null;

            return result;
        } else
            return "";
    }

    //region for Volley use
    public static JsonObjectRequest VolleyPOST(String url, JSONObject json) {
        JsonObjectRequest jsonRequest = null;
        try {
            ////Log.w("Utility", "HTTP REQ JSON=" + url + ",JSON=" + json.toString());
            jsonRequest = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        ////Log.w("Utility", "CommonVolleyPOST:onResponse=" + response.toString());
                    } catch (Exception e) {
                        ////Log.w("Utility", "Volley decode JSON:" + e.toString());
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
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
        } catch (Exception e) {
            ////Log.w("VolleyJson", e.toString());
        }

        SetVolleyTimeoutAndNoCache(jsonRequest);
        return jsonRequest;
    }

    public static JsonObjectRequest VolleyGET(String url, JSONObject json) {
        JsonObjectRequest jsonRequest = null;
        try {
            ////Log.w("Utility", "HTTP REQ JSON=" + url + ",JSON=" + json.toString());
            jsonRequest = new JsonObjectRequest(Request.Method.GET, url + json.toString(), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        ////Log.w("Utility", "CommonVolleyGET:onResponse=" + response.toString());
                    } catch (Exception e) {
                        ////Log.w("Utility", "Volley decode JSON:" + e.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
        } catch (Exception e) {
            ////Log.w("VolleyJson", e.toString());
        }

        SetVolleyTimeoutAndNoCache(jsonRequest);
        return jsonRequest;
    }

    public static JsonObjectRequest SetVolleyTimeoutAndNoCache(JsonObjectRequest jsonRequest) {
//        int socketTimeout = 30000;//30 seconds - change to what you want
        int socketTimeout = 10000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonRequest.setRetryPolicy(policy);
        jsonRequest.setShouldCache(false);

        return jsonRequest;
    }

//endregion

    public static Bitmap readBitmap(String img_file) {
        Bitmap bmp;
        BitmapFactory.Options opt = new BitmapFactory.Options();

        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(img_file, opt);

        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inSampleSize = computeSampleSize(opt, -1, 1800 * 1500);
        opt.inJustDecodeBounds = false;
        opt.inDither = true;

        BitmapFactory.decodeFile(img_file, opt);
        bmp = BitmapFactory.decodeFile(img_file, opt);

        if (bmp != null) {
            return bmp;
        } else {
            return null;
        }
    }

    public static String getFileExtension(String type) {
        String sType = "";
        if (type.equals(""))
            return "";

        int idx = type.indexOf("/");
        if (idx != -1)
            sType = type.substring(idx + 1);

        return sType;
    }

    public static int computeSampleSize(BitmapFactory.Options opts, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(opts, minSideLength, maxNumOfPixels);
        int roundedSize;

        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    public static int computeInitialSampleSize(BitmapFactory.Options opts, int minSideLength, int maxNumOfPixels) {
        double w = opts.outWidth;
        double h = opts.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (maxNumOfPixels == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    public static String getMimeType(String url) {
        String mimeType = "";
        try {
            String extension = url.substring(url.lastIndexOf("."));
            String mimeTypeMap = MimeTypeMap.getFileExtensionFromUrl(extension.toLowerCase());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(mimeTypeMap);
        } catch (Exception ex) {
            ////Log.w("Utility", "Error in getMimeType: " + ex.getMessage());
        }
        return mimeType;
    }

    public static Display GetScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay();
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int rotate) {
        if (bitmap == null)
            return null;

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        ////Log.w("U", "rotateBitmap rotate=" + rotate + ",w=" + w + ",h=" + h);
        // Setting post rotate to 90
        Matrix mtx = new Matrix();
        mtx.postRotate(rotate);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            ////Log.w("U", "readPictureDegree orientation=" + orientation);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static Bitmap getSmallBitmap(String filePath) {
        Bitmap bm;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = computeSampleSize(options, -1, 500 * 500);
        options.inJustDecodeBounds = false;
        options.inDither = true;

        bm = BitmapFactory.decodeFile(filePath, options);

        if (bm != null) {
            return bm;
        } else {
            return null;
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
// Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

// Calculate ratios of height and width to requested height and
// width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
        }

        return inSampleSize;
    }

    public static void promptSettings(final Activity mActivity) {
        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mActivity);
        builder.setTitle(mActivity.getString(R.string.PNG));
        builder.setMessage(mActivity.getString(R.string.YHDTP));
        builder.setPositiveButton(mActivity.getString(R.string.GTS)
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        goToSettings(mActivity);
                    }
                });
//        builder.setNegativeButton(mActivity.getString(R.string.bookeddetail_cancel), null);
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    public static void goToSettings(Activity mActivity) {
        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + mActivity.getPackageName()));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mActivity.startActivity(myAppSettings);
    }

    public static String checkExtension(String string) {
        String filenameArray[] = string.split("\\.");
        String extension = filenameArray[filenameArray.length - 1];

        return extension;
    }

    public static void PrintLog(String ClassName, String Content) {
        int pos = ClassName.lastIndexOf('.') + 1;
        String onlyClass = ClassName.substring(pos);
        Log.w(onlyClass, Content);
    }

    //check the string have chinese or not
    public static boolean isCJK(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            Character.UnicodeBlock block = Character.UnicodeBlock.of(ch);
            if (Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(block) ||
                    Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS.equals(block) ||
                    Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A.equals(block)) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(21)
    public static void SetTaskBarBackground(Activity context, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            context.getWindow().setStatusBarColor(context.getResources().getColor(color));
            context.getWindow().setNavigationBarColor(context.getResources().getColor(color));
        }
    }


}


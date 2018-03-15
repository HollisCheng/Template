package template.cheng.hollis.template.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hollischeng on 15/2/2018.
 */

public class AppPermissions {

    private Activity mActivity;

    public AppPermissions(Activity activity) {
        mActivity = activity;
    }

    public boolean hasPermission(String permission) {
        return ActivityCompat.checkSelfPermission(mActivity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasPermission(String[] permissionsList) {
        for (String permission : permissionsList) {
            if (ActivityCompat.checkSelfPermission(mActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public void requestPermission(String permission, int requestCode) {
        if (ActivityCompat.checkSelfPermission(mActivity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity, new String[]{permission}, requestCode);
        }
    }

    public void requestPermission(String[] permissionsList, int requestCode) {

        List<String> permissionNeeded = new ArrayList<>();

        for (String permission : permissionsList) {
            if (ActivityCompat.checkSelfPermission(mActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionNeeded.add(permission);
            }
        }

        if (permissionNeeded.size() > 0) {
            ActivityCompat.requestPermissions(mActivity, permissionNeeded.toArray(new String[permissionNeeded.size()]), requestCode);
        }
    }
}
package template.cheng.hollis.template.util;


import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Locale;

public class LogUtil {

    private static final Gson gson = new Gson();

    public static void log(String mString) {
        Logger.d(mString);
    }

    public static void logJson(String jsonString) {
        Logger.json(jsonString);
    }

    public static <T> void logArrayList(ArrayList<T> objectArrayList) {
        logJson(gson.toJson(objectArrayList));
    }

    public static void logWithTag(String tag, String mString) {
        Logger.t(tag).d(mString);
    }

    public static void logJsonWithTag(String tag, String jsonString) {
        Logger.t(tag).json(jsonString);
    }

    public static <T> void logArrayListWithTag(String tag, ArrayList<T> objectArrayList) {
        logJsonWithTag(tag, gson.toJson(objectArrayList));
    }

    public static void logFragmentArray(String tag, ArrayList<Fragment> fragmentArrayList) {
        String logString = "";
        if (fragmentArrayList.size() == 0) {
            logString = "Empty";
        } else {
            for (int i = 0; i < fragmentArrayList.size(); i++) {
                logString += String.format(Locale.getDefault(), "%d. %s",
                        i + 1, fragmentArrayList.get(i).getClass().getSimpleName());
                if (i != fragmentArrayList.size() - 1) {
                    logString += "\n";
                }
            }
        }
        logWithTag(tag, logString);
    }
}

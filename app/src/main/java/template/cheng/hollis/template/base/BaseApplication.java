package template.cheng.hollis.template.base;

import android.support.multidex.MultiDexApplication;
import android.support.v4.app.Fragment;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;

import template.cheng.hollis.template.BuildConfig;
import template.cheng.hollis.template.Constant;
import template.cheng.hollis.template.R;

import static template.cheng.hollis.template.LogUtil.log;
import static template.cheng.hollis.template.LogUtil.logFragmentArray;

public class BaseApplication extends MultiDexApplication {

    private Gson gson;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
//    Fabric.with(this, new Crashlytics());
//        initLeakCanary();
        initLogger();
//        initRongIM();
//        initHawk();
    }

    private void initHawk() {
//        Hawk.init(this).build();
    }

    //Init third party libraries
//    private void initLeakCanary() {
//        if (BuildConfig.DEBUG) {
//            if (LeakCanary.isInAnalyzerProcess(this)) {
//                return;
//            }
//            LeakCanary.install(this);
//        }
//    }

    private void initLogger() {
        System.out.println("initLogger START");
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(2)
                .methodOffset(1)
                .tag(getResources().getString(R.string.APP_NAME))
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.ENABLE_LOG;
            }
        });
        System.out.println("initLogger DONE");
    }

//    private void initRongIM() {
//
//        try {
//            RongPushClient.checkManifest(this);
//        } catch (RongException e) {
//            log("Rong CheckManifest " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
//            try {
//                RongPushClient.registerGCM(this);
//            } catch (RongException e) {
//                e.printStackTrace();
//            }
//
//            RongIM.init(this);
//            RongIM.registerMessageType(EventMessage.class);
//            RongIM.registerMessageTemplate(new EventMessageItemProvider());
//        }
//    }

    //Methods to update fragment array list
    public void addToFragmentArrayList(Fragment fragment) {
        fragmentArrayList.add(fragment);
        logFragmentArray(Constant.TAG_FRAGMENT_AFTER_ADD, fragmentArrayList);
    }

    public void removeFromFragmentArrayList(Fragment fragment) {
        fragmentArrayList.remove(fragment);
        logFragmentArray(Constant.TAG_FRAGMENT_AFTER_REMOVE, fragmentArrayList);
    }

    public void clearFragmentArrayList() {
        fragmentArrayList.clear();
    }

    public ArrayList<Fragment> getFragmentArrayList() {
        return fragmentArrayList;
    }

    public Gson getGson() {
        return gson == null ? new Gson() : gson;
    }
}

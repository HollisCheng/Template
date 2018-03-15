package template.cheng.hollis.template.util;

import android.content.Context;

/**
 * Created by hollischeng on 19/1/2018.
 */

public class MyExceptionHandler implements
        java.lang.Thread.UncaughtExceptionHandler {
    private final Context myContext;
//    private final Class<?> myActivityClass;

    public MyExceptionHandler(Context context
//            , Class<?> c
    ) {

        myContext = context;
//        myActivityClass = c;
    }

    public void uncaughtException(Thread thread, Throwable exception) {
//        Intent intent = new Intent(activity, MainActivity.class);
//        intent.putExtra("crash", true);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//                | Intent.FLAG_ACTIVITY_CLEAR_TASK
//                | Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(MyApplication.getInstance().getBaseContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
//
//        AlarmManager mgr = (AlarmManager) MyApplication.getInstance().getBaseContext().getSystemService(Context.ALARM_SERVICE);
//        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);

//        Log.e("", "restarting app");
//        Intent restartIntent = myContext.getPackageManager()
//                .getLaunchIntentForPackage(myContext.getPackageName() );
//        PendingIntent intent = PendingIntent.getActivity(
//                myContext, 0,
//                restartIntent, Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        AlarmManager manager = (AlarmManager) myContext.getSystemService(Context.ALARM_SERVICE);
//        manager.set(AlarmManager.RTC, System.currentTimeMillis() + 10, intent);
//        System.exit(2);


//        StringWriter stackTrace = new StringWriter();
//        exception.printStackTrace(new PrintWriter(stackTrace));
//        System.err.println(stackTrace);// You can use LogCat too
//        Intent intent = new Intent(myContext, myActivityClass);
//        String s = stackTrace.toString();
//        you can use this String to know what caused the exception and in which Activity
//        intent.putExtra("uncaughtException",
//                "Exception is: " + stackTrace.toString());
//        intent.putExtra("stacktrace", s);
//        myContext.startActivity(intent);
        //for restarting the Activity
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
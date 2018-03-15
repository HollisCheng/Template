package template.cheng.hollis.template.util;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

/**
 * Created by hollischeng on 19/1/2018.
 */

public class LogExceptionHandler implements
        java.lang.Thread.UncaughtExceptionHandler {
    Process process;
    private final Context myContext;
    private final Class<?> myActivityClass;

    public LogExceptionHandler(Context context, Class<?> c) {

        myContext = context;
        myActivityClass = c;
    }

    public void uncaughtException(Thread thread, Throwable exception) {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        System.err.println(stackTrace);// You can use LogCat too
//        try{
//            process = new ProcessBuilder()
//                    .command("logcat", "-c")
//                    .redirectErrorStream(true)
//                    .start();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()), 1024);
//            String line = bufferedReader.readLine();
//        } catch (Exception e) {
//        }
        Utility.PrintLog("Alert",stackTrace.toString());
        Intent intent = new Intent(myContext, myActivityClass);
        String s = stackTrace.toString();
        //you can use this String to know what caused the exception and in which Activity
        intent.putExtra("uncaughtException",
                "Exception is: " + stackTrace.toString());
        intent.putExtra("stacktrace", s);

//                try{
//                    process = new ProcessBuilder().command("logcat", "-c").redirectErrorStream(true).start();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()), 1024);
//                    String line = bufferedReader.readLine();
//                    } catch (Exception e) {
//                    }
        String name = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Documents";
        File dir = new File(name);
        dir.mkdirs();
        Calendar c = Calendar.getInstance();
//        Utility.PrintLog("Alert","Lets See if it Works11111111 !!!");
        File appDir = new File(name,c.getTime().toString()+".txt");
        try{
            boolean bool = appDir.createNewFile();
            bool = appDir.createNewFile();
        }catch(Exception e){
            e.printStackTrace();
        }
        FileOutputStream fileOutPutStream1=null;
        try {
            fileOutPutStream1 = new FileOutputStream(appDir);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutPutStream1);
            try {
                Calendar c1 = Calendar.getInstance();
                process = Runtime.getRuntime().exec("logcat -v raw -d *:E *:W" );
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
//                Utility.PrintLog("Alert","Lets See if it Works333333333333 !!!");
                StringBuilder log=new StringBuilder();
                String line;
                log.append(c1.getTime());
                log.append("\n");
                while ((line = bufferedReader.readLine()) != null) {
                    log.append(line);
                    log.append("\n");
                }
                outputStreamWriter.write(log.toString());
            } catch (IOException e) {
            }
            outputStreamWriter.close();
            fileOutPutStream1.write(100);
            fileOutPutStream1.close();
//            Utility.PrintLog("Alert","Lets See if it Works5555555555555555 !!!");
        } catch (FileNotFoundException e) {
//            Utility.PrintLog("Alert","Lets See if it Works99999999 !!!");
        } catch (IOException e) {
//            Utility.PrintLog("Alert","Lets See if it Works5556666666666 !!!");
        }
        try{
            process = Runtime.getRuntime().exec("logcat -c");
        } catch (IOException e) {
        }
//        Utility.PrintLog("Alert","Lets See if it Works777777777777777 !!!");
        myContext.startActivity(intent);
        //for restarting the Activity
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
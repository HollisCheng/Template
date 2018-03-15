package template.cheng.hollis.template.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class CampaignBroadcastReceiver extends BroadcastReceiver {
    final String REFERRER = "REF";

    final String UTM_CAMPAIGN = "utm_campaign";
    final String UTM_SOURCE = "utm_source";
    final String UTM_MEDIUM = "utm_medium";
    final String UTM_TERM = "utm_term";
    final String UTM_CONTENT = "utm_content";

    private final String[] sources = {
            UTM_CAMPAIGN, UTM_SOURCE, UTM_MEDIUM, UTM_TERM, UTM_CONTENT
    };

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();

        String referrerString = extras.getString("referrer");
        Log.i("GAv4", "referrer:" + referrerString);

        try {
            Map<String, String> getParams = getHashMapFromQuery(referrerString);

            SharedPreferences preferences = context
                    .getSharedPreferences(REFERRER, Context.MODE_PRIVATE);

            SharedPreferences.Editor preferencesEditor = preferences.edit();
            Boolean isReceivedCampaign = true;
            preferencesEditor.putBoolean("isReceivedCampaign", isReceivedCampaign);
            for (String sourceType : sources) {
                String source = getParams.get(sourceType);

                if (source != null) {
                    Log.i("GAv4", "sourceType:" + sourceType + " source:" + source);
                    preferencesEditor.putString(sourceType, source);

                }
            }

            preferencesEditor.commit();
        } catch (UnsupportedEncodingException e) {

            Log.e("Referrer Error", e.getMessage());
        } finally {

            // Pass along to google
//            CampaignTrackingReceiver receiver = new CampaignTrackingReceiver();
//            receiver.onReceive(context, intent);
        }


    }

    public static Map<String, String> getHashMapFromQuery(String query)
            throws UnsupportedEncodingException {

        Map<String, String> query_pairs = new LinkedHashMap<String, String>();

        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                    URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
}
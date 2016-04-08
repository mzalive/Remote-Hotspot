package org.mzalive.remotehotspot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by mzalive on 4/8/16.
 */
public class SMSRecv extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (SmsMessage message : messages) {
            if (message != null) {
                String smsContent = message.getDisplayMessageBody();
                String smsSender = message.getDisplayOriginatingAddress();
                Log.d("SMSRecvd", "Sender:" + smsSender + " ; Content:" + smsContent);

                if (smsContent.contains("#RH#")) {
                    Log.d("SMSRecvd", "Target Msg");
                    if (smsContent.contains("on")) toggleHotspot(context, true);
                    if (smsContent.contains("off")) toggleHotspot(context, false);
                }
            }

        }
    }

    private void toggleHotspot(Context context, boolean enable) {
        Log.d("SMSRecvd", "Toggle Hotspot");
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiConfiguration wifiConfiguration = null;
        wifiManager.setWifiEnabled(false);
        try {
            Method method = wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
            method.invoke(wifiManager, wifiConfiguration, enable);
        }
        catch (NoSuchMethodException e) {}
        catch (IllegalAccessException e) {}
        catch (InvocationTargetException e) {}
    }
}

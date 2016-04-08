package org.mzalive.remotehotspot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by mzalive on 4/8/16.
 */
public class BootRecv extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BootRecv", "Broadcast received.");
        Intent mainServiceIntent = new Intent(context, MainSrv.class);
        context.startService(mainServiceIntent);
    }
}

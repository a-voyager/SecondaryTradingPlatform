package com.swpuiot.stp.localbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by DELL on 2016/12/24.
 */
public class ShoppingBroadcast extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
        Log.e("ShoppingBoardcastt","success");
    }
}

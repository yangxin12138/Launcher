package com.twd.launcher;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.view.View;
import android.widget.ImageView;

public  class Utils {
    private static WifiManager wifiManager;

    public static void updateWifiSignalStrength(ImageView wifiImageView,Context context) {
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int signalLevel = WifiManager.calculateSignalLevel(wifiManager.getConnectionInfo().getRssi(), 5);
        if (wifiManager.getConnectionInfo().getSupplicantState() == SupplicantState.COMPLETED) {
            wifiImageView.setVisibility(View.VISIBLE);

            switch (signalLevel) {
                case 0:
                    wifiImageView.setImageResource(R.drawable.wifi_signal_low);
                    break;
                case 1:
                    wifiImageView.setImageResource(R.drawable.wifi_signal_medium);
                    break;
                case 2:
                    wifiImageView.setImageResource(R.drawable.wifi_signal_high);
                    break;
                default:
                    wifiImageView.setImageResource(R.drawable.wifi_signal_high);
                    break;
            }
        } else {
            wifiImageView.setVisibility(View.GONE);
        }
    }

    public static void updateBatteryStatus(ImageView batteryImageView, Context context){
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null,intentFilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        int batteryPercentage = (int) ((level / (float) scale) * 100);

        if (batteryPercentage >= 80){
            batteryImageView.setImageResource(R.drawable.battery_icon_full);
        } else if (batteryPercentage >= 40) {
            batteryImageView.setImageResource(R.drawable.battery_icon_medium);
        } else {
            batteryImageView.setImageResource(R.drawable.battery_icon_low);
        }
    }
}

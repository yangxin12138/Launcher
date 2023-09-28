package com.twd.launcher;

import android.net.wifi.SupplicantState;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.ImageView;

public  class Utils {
    private static WifiManager wifiManager;

    public static void updateWifiSignalStrength(ImageView wifiImageView) {
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
            wifiImageView.setVisibility(View.INVISIBLE);
        }
    }
}

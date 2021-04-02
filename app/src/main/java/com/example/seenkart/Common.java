package com.example.seenkart;

import android.content.Context;
import android.net.ConnectivityManager;

public class Common {
    public static boolean connectivityAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!= null;
    }
}

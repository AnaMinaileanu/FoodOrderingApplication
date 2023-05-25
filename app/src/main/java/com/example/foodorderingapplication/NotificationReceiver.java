package com.example.foodorderingapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.foodorderingapplication.ui.home.HomeFragment;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Open the desired activity or fragment when the notification is clicked
        Intent openActivityIntent = new Intent(context, HomeFragment.class);
        openActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(openActivityIntent);
    }
}


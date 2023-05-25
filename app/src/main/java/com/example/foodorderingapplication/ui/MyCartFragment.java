package com.example.foodorderingapplication.ui;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodorderingapplication.MainActivity;
import com.example.foodorderingapplication.R;
import com.example.foodorderingapplication.adapters.CartAdapter;
import com.example.foodorderingapplication.models.CartModel;

import java.util.ArrayList;
import java.util.List;


public class MyCartFragment extends Fragment {

    List<CartModel> list;
    CartAdapter cartAdapter;
    RecyclerView recyclerView;

    private static final String CHANNEL_ID = "channel1";
    private static final int NOTIFICATION_ID = 1;

    public MyCartFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);
        recyclerView = view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.add(new CartModel(R.drawable.s1, "Order1", "40", "4.5"));
        list.add(new CartModel(R.drawable.s2, "Order1", "20", "4.5"));
        list.add(new CartModel(R.drawable.fav1, "Order1", "30", "4.5"));

        list.add(new CartModel(R.drawable.s1, "Order1", "40", "4.5"));
        list.add(new CartModel(R.drawable.s2, "Order1", "40", "4.5"));
        list.add(new CartModel(R.drawable.fav1, "Order1", "40", "4.5"));

        cartAdapter = new CartAdapter(list);
        recyclerView.setAdapter(cartAdapter);

        Button button = view.findViewById(R.id.order);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MyCartFragment", "Button clicked!");
                sendOnChannel1(v);
            }
        });


        return view;
    }

    private void sendOnChannel1(View view) {
        String title = "Your Order has been placed";
        String message = "While your order is ready visit the other products from our store";
        Context context = getActivity();

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        Intent fullScreenIntent = new Intent(context, MainActivity.class);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setFullScreenIntent(fullScreenPendingIntent, true)
                .setContentIntent(pendingIntent) // Add the pending intent to handle the click event
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(1, notification);
    }
}
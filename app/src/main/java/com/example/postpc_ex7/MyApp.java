package com.example.postpc_ex7;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyApp extends Application {
    public String orderId;
//    private static MyApp staticSp = null;
    SharedPreferences sharedPref;

    public MyApp(Context context){
        this.orderId="";
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
//        staticSp=this;
        loadOrderId();
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
    public void loadOrderId() {
        this.orderId= sharedPref.getString("order", "");
    }

    public void saveOrderId(String id) {
        this.orderId=id;
        sharedPref.edit().putString("order", this.orderId).apply();
    }
//    public static MyApp getInstance()
//    {
//        return staticSp;
//    }
}

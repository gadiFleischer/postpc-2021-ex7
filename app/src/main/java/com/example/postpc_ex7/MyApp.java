package com.example.postpc_ex7;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyApp extends Application {
    public String orderId;
    public boolean isOrder;

    SharedPreferences sharedPref;
    Context context;

    public MyApp(Context context){
        this.context=context;
        this.orderId="";
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        loadTodoList();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    public void loadTodoList() {
        this.orderId= sharedPref.getString("order", "");
        this.isOrder= !this.orderId.equals("");

    }

    public void saveTodoList() {
        sharedPref.edit().putString("order", this.orderId).apply();
    }
}

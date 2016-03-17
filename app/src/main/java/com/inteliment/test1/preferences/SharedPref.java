package com.inteliment.test1.preferences;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    static String PREFRENCENAME="app_pref";
    public static String getTheme(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("THEME",Context.MODE_PRIVATE);
        return  sharedPreferences.getString("APPTHEME","BLUE");
    }
    public static void setTheme(Context context,String theme){
        SharedPreferences sharedPreferences=context.getSharedPreferences("THEME",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("APPTHEME",theme).apply();

    }
}

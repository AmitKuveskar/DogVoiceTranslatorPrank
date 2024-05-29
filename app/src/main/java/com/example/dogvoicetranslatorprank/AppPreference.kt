package com.example.dogvoicetranslatorprank

import android.content.Context
import android.content.SharedPreferences
import java.security.AccessControlContext
import java.security.Key
import java.util.prefs.Preferences

class AppPreference (context: Context){

    private val Preferences_NAME = "AppPreference"
    private  val  sharedPreferences: SharedPreferences
    private  val  editor:SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(Preferences_NAME,Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

    }

    //Method to save  a string value
    fun saveString (key: String,value: String){
        editor.putString(key,value)
        editor.apply()
    }

    //Method to retrive a string value
    fun getString(key: String, defaultvalue:String): String? {
        return sharedPreferences.getString(key, defaultvalue)
    }

    //Method to save a Integer value
    fun saveInt (key: String, value: Int){
        editor.putInt(key, value)
        editor.apply()
    }

    //Method to retrive a integer value
    fun  getInt(key: String, defaultvalue: Int): Int {
        return  sharedPreferences.getInt(key,defaultvalue)
    }

    //Method to save a Integer value
    fun  saveBoolean (key: String,value:Boolean ){
        editor.putBoolean(key , value)
        editor.apply()

    }
    // Method to retrive a boolean value
    fun  getBoolean (key: String, defaultvalue: Boolean): Boolean{
        return  sharedPreferences.getBoolean(key,defaultvalue)
    }

    //
    fun clear (){
        editor.clear().apply()
    }

}
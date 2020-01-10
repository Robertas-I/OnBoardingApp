package com.mobile.sanitex.onboardingapp

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

@GlideModule
class MyGlideModule: AppGlideModule()

fun Activity.getNavController(): NavController =
    Navigation.findNavController(this, R.id.navHostFragment)

fun NavController.tryNavigate(directions: NavDirections){
    try {
        this.navigate(directions)
    } catch (e: Exception){
        e.printStackTrace()
    }
}

fun Fragment.tryNavigate(directions: NavDirections){
    try {
        activity?.getNavController()?.tryNavigate(directions)
    } catch (e: Exception){
        e.printStackTrace()
    }
}

fun Activity.tryNavigate(directions: NavDirections){
    try {
        getNavController().tryNavigate(directions)
    } catch (e: Exception){
        e.printStackTrace()
    }
}

fun String.toJsonObject(F: JSONObject.() -> Unit){
    try {
        F(JSONObject(this))
    } catch (e: JSONException){
        e.printStackTrace()
    }
}

fun JSONArray.getJsonObject(index: Int, F: JSONObject.() -> Unit){
    try {
        F(this.getJSONObject(index))
    } catch (e: JSONException){
        e.printStackTrace()
    }
}
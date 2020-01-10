package com.mobile.sanitex.onboardingapp.data

import android.annotation.SuppressLint
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

data class NewData(
    val author: String,
    val title: String,
    val text: String,
    private val date: String,
    val image: String,
    val url: String
): Serializable{
    @SuppressLint("SimpleDateFormat")
    fun getFormattedDate(): String{
        val format = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
        return SimpleDateFormat("yyyy-MMM-dd hh:mm").format(format.parse(date)?: Date())
    }
}
package com.mobile.sanitex.onboardingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        getNavController().addOnDestinationChangedListener { controller, destination, arguments ->
            val show = destination.id == R.id.newFragment
            nestedView.isNestedScrollingEnabled = show
            appbar.setExpanded(show, false)
            if (!show)
                app_bar_image.setImageDrawable(null)

            supportActionBar?.setDisplayHomeAsUpEnabled(show)
            supportActionBar?.setDisplayShowHomeEnabled(true)

        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

}

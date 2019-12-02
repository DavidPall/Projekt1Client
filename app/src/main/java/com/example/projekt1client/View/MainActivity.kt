package com.example.projekt1client.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projekt1client.R

class MainActivity : AppCompatActivity() {

    var isFragmentOneLoaded = true
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ShowFragmentLogin()
    }

    //Loading the Login fragment on the fragment holder in the Main Activity
    fun ShowFragmentLogin() {
        val transaction = manager.beginTransaction()
        val fragment = FragmentLogin()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentOneLoaded = true
    }


    override fun onBackPressed() {
        // not calling super.onBackPressed() disable onbackpressed
    }
}

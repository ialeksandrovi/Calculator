package com.axwel.calculator


import android.os.Bundle
import com.arellomobile.mvp.MvpActivity

class MainActivity : MvpActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
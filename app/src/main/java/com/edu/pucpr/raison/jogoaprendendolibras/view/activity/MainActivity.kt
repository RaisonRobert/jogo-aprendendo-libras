package com.edu.pucpr.raison.jogoaprendendolibras.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edu.pucpr.raison.jogoaprendendolibras.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, BemVindoActivity::class.java))
        finish()
    }
}
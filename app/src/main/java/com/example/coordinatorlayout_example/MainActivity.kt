package com.example.coordinatorlayout_example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coordinatorlayout_example.rv.RecyclerViewBasedActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLayoutBased.setOnClickListener {
            startActivity(Intent(this, LayoutBasedActivity::class.java))
        }

        btnScrollBased.setOnClickListener {
            startActivity(Intent(this, ScrollBasedActivity::class.java))
        }


        btnRecyclerViewBased.setOnClickListener {
            startActivity(Intent(this, RecyclerViewBasedActivity::class.java))
        }
    }

}
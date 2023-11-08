package com.example.coordinatorlayout_example.rv

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coordinatorlayout_example.databinding.ActivityRecyclerviewBasedBinding
import com.example.coordinatorlayout_example.mainCoroutine
import kotlinx.coroutines.delay

/**
 * @author Hamza Khalid
 * Sr. Software Engineer
 * Created on 08 Nov,2023 12:43
 * Copyright (c) All rights reserved.
 * @see "<a href="https://www.linkedin.com/in/the-hamzakhalid/">Linkedin Profile</a>"
 */

/*Ref Chat GPT*/

class RecyclerViewBasedActivity : AppCompatActivity() {


    private val TAG = RecyclerViewBasedActivity::class.java.simpleName
    private var binding: ActivityRecyclerviewBasedBinding? = null
    private var activity: AppCompatActivity = this

    private val list =
        mutableListOf<String>("A", "A", "A", "A", "A", "A")
    private var rvCollapseAdapter: RvCollapseAdapter? = null

    private val scrollThreshold = 200 // Adjust this threshold as needed

    private val gridLayoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
    private val linearLayoutManager =
        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    private var currentLayoutManagerIsGrid = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerviewBasedBinding.inflate(layoutInflater)

        binding?.run {
            setContentView(root)
            init()
            initRv()
        }

    }

    private fun ActivityRecyclerviewBasedBinding.init() {


        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            Log.d(
                TAG,
                "setOnScrollChangeListener:\nscrollX $scrollX \nscrollY $scrollY \ncurrentLayoutManagerIsGrid : $currentLayoutManagerIsGrid"
            )
            mainCoroutine {
                delay(300)
                if (scrollY > scrollThreshold && currentLayoutManagerIsGrid) {
                    //  rvCollapse.disableAnimationRecyclerView()
                    currentLayoutManagerIsGrid = false
                    rvCollapse.layoutManager = linearLayoutManager


                } else if (scrollY <= scrollThreshold && !currentLayoutManagerIsGrid) {
                    currentLayoutManagerIsGrid = true
                    // rvCollapse.disableAnimationRecyclerView()
                    rvCollapse.layoutManager = gridLayoutManager
                }
            }


        })

    }


    private fun ActivityRecyclerviewBasedBinding.initRv() {
        rvCollapseAdapter = RvCollapseAdapter() { pos, name ->

        }

        rvCollapse.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            adapter = rvCollapseAdapter
        }

        rvCollapseAdapter?.submitList(list)

    }


}
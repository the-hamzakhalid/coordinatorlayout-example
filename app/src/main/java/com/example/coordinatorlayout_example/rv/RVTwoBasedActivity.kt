package com.example.coordinatorlayout_example.rv

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coordinatorlayout_example.R
import com.example.coordinatorlayout_example.databinding.ActivityRvTwoBasedBinding
import com.example.coordinatorlayout_example.mainCoroutine
import kotlinx.coroutines.delay

/**
 * @author Hamza Khalid
 * Sr. Software Engineer
 * Created on 08 Nov,2023 12:43
 * Copyright (c) All rights reserved.
 * @see "<a href="https://www.linkedin.com/in/the-hamzakhalid/">Linkedin Profile</a>"
 */

/*TODO ===================> Using Double Recyclerview with animation*/

/* REF CHAT GPT

 I want a scenario  in which there are two recycler views, one (Grid Recyclerview) inside in nested scroll view and
* the other (Linear Recyclerview) outside nested scroll view and I want when I scroll to some threshold the Grid Recyclerview
* is gone and Linear Recyclerview but like a smooth animation  in java code


Reference App in which i see this scenario

https://play.google.com/store/apps/details?id=com.everimaging.photoeffectstudio&&referrer=utm_campaign%3D(old)dl%252520link%252520for%252520mobile%252520fotor%26utm_medium%3Dad-analytics%26utm_content%3D1fc6ee3d-78e8-436c-9f48-270db2ea5fa2%26utm_source%3Dflurry
*/


class RVTwoBasedActivity : AppCompatActivity() {


    private val TAG = RVTwoBasedActivity::class.java.simpleName
    private var binding: ActivityRvTwoBasedBinding? = null
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
        binding = ActivityRvTwoBasedBinding.inflate(layoutInflater)

        binding?.run {
            setContentView(root)
            init()
            initRv()
        }

    }

    private fun ActivityRvTwoBasedBinding.init() {


        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            Log.d(
                TAG,
                "setOnScrollChangeListener:\nscrollX $scrollX \nscrollY $scrollY \ncurrentLayoutManagerIsGrid : $currentLayoutManagerIsGrid"
            )
            mainCoroutine {
                delay(300)
                if (scrollY > scrollThreshold && currentLayoutManagerIsGrid) {
                    Log.d(TAG, "LinearRVVisible - scrollY $scrollY")
                    currentLayoutManagerIsGrid = false
                    // Animation for the Grid RecyclerView to disappear
                    val fadeOut = ObjectAnimator.ofFloat(rvCollapseGrid, "alpha", 1f, 0f)
                    fadeOut.setDuration(1000) // Set duration for fade-out animation
                    fadeOut.addListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}
                        override fun onAnimationEnd(animation: Animator) {
                            fadeOut.removeAllListeners()
                            Log.d(
                                TAG,
                                "onAnimationEnd - LinearRVVisible - fadeOut- rvCollapseGrid"
                            )
                        }

                        override fun onAnimationCancel(animation: Animator) {}
                        override fun onAnimationRepeat(animation: Animator) {}
                    })

                    // Animation for the Linear RecyclerView to appear
                    val fadeIn = ObjectAnimator.ofFloat(rvCollapseLinear, "alpha", 0f, 1f)
                    fadeIn.setDuration(1000) // Set duration for fade-in animation
                    fadeIn.addListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}
                        override fun onAnimationEnd(animation: Animator) {
                            rvCollapseLinear.setVisibility(View.VISIBLE) // Show the Linear RecyclerView after animation
                            fadeIn.removeAllListeners()
                            Log.d(
                                TAG,
                                "onAnimationEnd - LinearRVVisible - fadeIn- rvCollapseLinear"
                            )
                        }

                        override fun onAnimationCancel(animation: Animator) {}
                        override fun onAnimationRepeat(animation: Animator) {}
                    })

                    // Start animations
                    fadeOut.start()
                    fadeIn.start()
                } else if (scrollY <= scrollThreshold && !currentLayoutManagerIsGrid) {
                    Log.d(TAG, "GridRVVisible - scrollY $scrollY")
                    currentLayoutManagerIsGrid = true
                    // Animation for the Linear RecyclerView to disappear
                    val fadeOut = ObjectAnimator.ofFloat(rvCollapseLinear, "alpha", 1f, 0f)
                    fadeOut.setDuration(1000) // Set duration for fade-out animation
                    fadeOut.addListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}
                        override fun onAnimationEnd(animation: Animator) {
                            //rvCollapseLinear.setVisibility(View.GONE); // Hide the Grid RecyclerView after animation
                            fadeOut.removeAllListeners()
                            Log.d(
                                TAG,
                                "onAnimationEnd - GridRVVisible - fadeOut - rvCollapseLinear"
                            )
                        }

                        override fun onAnimationCancel(animation: Animator) {}
                        override fun onAnimationRepeat(animation: Animator) {}
                    })

                    // Animation for the Linear RecyclerView to appear
                    val fadeIn = ObjectAnimator.ofFloat(rvCollapseGrid, "alpha", 0f, 1f)
                    fadeIn.setDuration(1000) // Set duration for fade-in animation
                    fadeIn.addListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}
                        override fun onAnimationEnd(animation: Animator) {
                            fadeIn.removeAllListeners()
                            Log.d(
                                TAG,
                                "onAnimationEnd - GridRVVisible - fadeIn - rvCollapseGrid"
                            )
                        }

                        override fun onAnimationCancel(animation: Animator) {}
                        override fun onAnimationRepeat(animation: Animator) {}
                    })
                    // Start animations
                    fadeOut.start()
                    fadeIn.start()
                }
            }
        });


    }


    private fun ActivityRvTwoBasedBinding.initRv() {
        rvCollapseAdapter = RvCollapseAdapter() { pos, name ->

        }

        rvCollapseGrid.apply {
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen._10sdp)
            addItemDecoration(SpacesItemDecoration(spacingInPixels))
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            adapter = rvCollapseAdapter
        }

        rvCollapseLinear.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = rvCollapseAdapter
        }

        rvCollapseAdapter?.submitList(list)

    }


}
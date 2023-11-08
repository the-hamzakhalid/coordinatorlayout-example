package com.example.coordinatorlayout_example

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @author Hamza Khalid
 * Sr. Software Engineer
 * Created on 08 Nov,2023 12:43
 * Copyright (c) All rights reserved.
 * @see "<a href="https://www.linkedin.com/in/the-hamzakhalid/">Linkedin Profile</a>"
 */

fun RecyclerView.disableAnimationRecyclerView() {
    val animator = itemAnimator
    if (animator is SimpleItemAnimator) {
        animator.supportsChangeAnimations =
            false
    }
}

fun mainCoroutine(work: suspend (() -> Unit)): Job {
    return CoroutineScope(Dispatchers.Main).launch {
        work()
    }
}


fun ioCoroutine(work: suspend (() -> Unit)): Job {
    return CoroutineScope(Dispatchers.IO).launch {
        work()
    }
}
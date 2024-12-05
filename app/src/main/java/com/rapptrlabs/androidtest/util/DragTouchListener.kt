package com.rapptrlabs.androidtest.util

import android.animation.ObjectAnimator
import android.view.MotionEvent
import android.view.View

class DragTouchListener : View.OnTouchListener {
    private var initialX = 0f
    private var initialY = 0f
    private var dX = 0f
    private var dY = 0f

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                initialX = view.x
                initialY = view.y
                dX = event.rawX - view.x
                dY = event.rawY - view.y
            }
            MotionEvent.ACTION_MOVE -> {
                val newX = event.rawX - dX
                val newY = event.rawY - dY
                ObjectAnimator.ofFloat(view, "x", view.x, newX).apply {
                    duration = 0
                    start()
                }
                ObjectAnimator.ofFloat(view, "y", view.y, newY).apply {
                    duration = 0
                    start()
                }
            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return true
    }
}
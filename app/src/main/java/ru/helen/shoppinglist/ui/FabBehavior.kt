package ru.helen.shoppinglist.ui

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator



class FabBehavior(context: Context?, attrs: AttributeSet? = null
) : FloatingActionButton.Behavior(context, attrs) {

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        if (dyConsumed > 0) {
            val layoutParams = child.layoutParams as CoordinatorLayout.LayoutParams
            val fabBottomMargin = layoutParams.bottomMargin
            child.animate().translationY((child.height + fabBottomMargin).toFloat()).setInterpolator(LinearInterpolator()).start()
        } else if (dyConsumed < 0) {
            child.animate().translationY(0F).setInterpolator(LinearInterpolator()).start()
        }
    }


}
package com.miramicodigo.floatingactionbutton

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.widget.FrameLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import kotlin.math.absoluteValue


class MainActivity : AppCompatActivity() {

    var fab: FloatingActionButton? = null

    var fab1: FloatingActionButton? = null
    var fab2: FloatingActionButton? = null
    var fab3: FloatingActionButton? = null

    val rootLayout: CoordinatorLayout? = null

    var show_fab_1: Animation? = null
    var hide_fab_1: Animation? = null
    var show_fab_2: Animation? = null
    var hide_fab_2: Animation? = null
    var show_fab_3: Animation? = null
    var hide_fab_3: Animation? = null

    var STATUS: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById<FloatingActionButton>(R.id.fabMain)

        fab1 = findViewById<FloatingActionButton>(R.id.fab_1)
        fab2 = findViewById<FloatingActionButton>(R.id.fab_2)
        fab3 = findViewById<FloatingActionButton>(R.id.fab_3)

        show_fab_1 = AnimationUtils.loadAnimation(applicationContext, R.anim.fab1_show)
        hide_fab_1 = AnimationUtils.loadAnimation(applicationContext, R.anim.fab1_hide)
        show_fab_2 = AnimationUtils.loadAnimation(applicationContext, R.anim.fab2_show)
        hide_fab_2 = AnimationUtils.loadAnimation(applicationContext, R.anim.fab2_hide)
        show_fab_3 = AnimationUtils.loadAnimation(applicationContext, R.anim.fab3_show)
        hide_fab_3 = AnimationUtils.loadAnimation(applicationContext, R.anim.fab3_hide)

        fab!!.setOnClickListener {
            if(!STATUS) {
                expandFAB()
                STATUS = true
            }else {
                hideFAB()
                STATUS = false
            }
        }

        fab1!!.setOnClickListener {

        }
    }

    private fun expandFAB() {
        val layoutParams = fab1!!.layoutParams as FrameLayout.LayoutParams
        layoutParams.rightMargin += (fab1!!.width * 1.7).toInt()
        layoutParams.bottomMargin += (fab1!!.height * 0.25).toInt()
        fab1!!.layoutParams = layoutParams
        fab1!!.startAnimation(show_fab_1)
        fab1!!.isClickable = true

        val layoutParams2 = fab2!!.layoutParams as FrameLayout.LayoutParams
        layoutParams2.rightMargin += (fab2!!.width * 1.5).toInt()
        layoutParams2.bottomMargin += (fab2!!.height * 1.5).toInt()
        fab2!!.layoutParams = layoutParams2
        fab2!!.startAnimation(show_fab_2)
        fab2!!.isClickable = true

        val layoutParams3 = fab3!!.layoutParams as FrameLayout.LayoutParams
        layoutParams3.rightMargin += (fab3!!.width * 0.25).toInt()
        layoutParams3.bottomMargin += (fab3!!.height * 1.7).toInt()
        fab3!!.layoutParams = layoutParams3
        fab3!!.startAnimation(show_fab_3)
        fab3!!.isClickable = true
    }

    private fun hideFAB() {
        val layoutParams = fab1!!.layoutParams as FrameLayout.LayoutParams
        layoutParams.rightMargin -= (fab1!!.width * 1.7).toInt()
        layoutParams.bottomMargin -= (fab1!!.height * 0.25).toInt()
        fab1!!.layoutParams = layoutParams
        fab1!!.startAnimation(hide_fab_1)
        fab1!!.isClickable = false

        val layoutParams2 = fab2!!.layoutParams as FrameLayout.LayoutParams
        layoutParams2.rightMargin -= (fab2!!.width * 1.5).toInt()
        layoutParams2.bottomMargin -= (fab2!!.height * 1.5).toInt()
        fab2!!.layoutParams = layoutParams2
        fab2!!.startAnimation(hide_fab_2)
        fab2!!.isClickable = false

        val layoutParams3 = fab3!!.layoutParams as FrameLayout.LayoutParams
        layoutParams3.rightMargin -= (fab3!!.width * 0.25).toInt()
        layoutParams3.bottomMargin -= (fab3!!.height * 1.7).toInt()
        fab3!!.layoutParams = layoutParams3
        fab3!!.startAnimation(hide_fab_3)
        fab3!!.isClickable = false
    }
}

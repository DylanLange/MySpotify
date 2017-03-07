package com.dylange.myspotify.base

import android.animation.ValueAnimator
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mcxiaoke.koi.ext.getApp
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Dylan on 28-Feb-17.
 */
abstract class BaseActivity: AppCompatActivity(), BaseContract.BaseView {

    companion object{
        @JvmStatic val AUTH_TOKEN_PREFS_KEY = "_AUTH_TOKEN_PREFS_KEY"
    }

    lateinit var mProgressDialog: ProgressDialog
    //lateinit var mPlayer: SpotifyPlayer

    abstract fun setupActivityComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivityComponent()

        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setCancelable(false)
        mProgressDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroy() {
        //if(mPlayer != null) mPlayer.destroy()
        super.onDestroy()
    }

    override fun showAlertDialog(title : String){
        var builder : AlertDialog.Builder = AlertDialog.Builder(getApp())
        builder.setMessage(title)
                .setPositiveButton("Ok",
                        DialogInterface.OnClickListener {
                            dialog, i ->
                            dialog.dismiss()
                        })
        builder.create().show()
    }

    override fun fadeInCircleMenu() {
        var anim: ValueAnimator = ValueAnimator.ofFloat(0f, 1f)
        anim.duration = 300
        anim.addUpdateListener({listener ->
            circle_menu.alpha = listener.animatedValue as Float
        })
        circle_menu.visibility = View.VISIBLE
        anim.start()
    }

    override fun fadeOutCircleMenu() {
        var anim: ValueAnimator = ValueAnimator.ofFloat(1f, 0f)
        anim.duration = 600
        anim.addUpdateListener({listener ->
            circle_menu.alpha = listener.animatedValue as Float
            if(listener.animatedValue === 0f){
                circle_menu.visibility = View.GONE
            }
        })
        anim.start()
    }

    override fun showFab() {
        fab.show()
    }

    override fun hideFab() {
        fab.hide()
    }

    override fun showProgressDialog(){
        mProgressDialog.show()
    }

    override fun hideProgressDialog(){
        if(mProgressDialog.isShowing){
            mProgressDialog.dismiss()
        }
    }

}
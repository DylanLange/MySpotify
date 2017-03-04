package com.dylange.myspotify.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mcxiaoke.koi.ext.getApp

/**
 * Created by Dylan on 28-Feb-17.
 */
abstract class BaseActivity: AppCompatActivity(), BaseContract.BaseView {

    companion object{
        @JvmStatic val AUTH_TOKEN_PREFS_KEY = "_AUTH_TOKEN_PREFS_KEY"
    }

    //lateinit var mPlayer: SpotifyPlayer

    abstract fun setupActivityComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivityComponent()
    }

    override fun onDestroy() {
        //if(mPlayer != null) mPlayer.destroy()
        super.onDestroy()
    }

    override fun showAlertDialog(msg : String){
        var builder : AlertDialog.Builder = AlertDialog.Builder(getApp())
        builder.setMessage(msg)
                .setPositiveButton("Ok",
                        DialogInterface.OnClickListener {
                            dialog, i ->
                            dialog.dismiss()
                        })
        builder.create().show()
    }

}
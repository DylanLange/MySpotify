package com.dylange.myspotify.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import com.mcxiaoke.koi.ext.getApp

/**
 * Created by Dylan on 04-Mar-17.
 */
abstract class BaseFragment: Fragment(), BaseContract.BaseView{
	abstract fun setupFragmentComponent()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setupFragmentComponent()
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
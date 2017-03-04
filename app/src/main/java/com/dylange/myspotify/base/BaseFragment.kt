package com.dylange.myspotify.base

import android.animation.ValueAnimator
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.mcxiaoke.koi.ext.getApp
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Dylan on 04-Mar-17.
 */
abstract class BaseFragment: Fragment(), BaseContract.BaseView{
	abstract fun setupFragmentComponent()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setupFragmentComponent()
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
}
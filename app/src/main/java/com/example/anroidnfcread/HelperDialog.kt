package com.example.anroidnfcread

import android.content.Context
import android.content.DialogInterface
import android.view.ContextThemeWrapper
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog



/*
Created by ​
Hannure Abdulrahim


on 10/27/2020.
 */

object HelperDialog {

    //Android alert dialog kotlin Extension
    fun alertDialog(context: Context, @StyleRes style: Int = R.style.AlertDialogCustom, @DrawableRes icon:Int=android.R.drawable.ic_dialog_alert, dialogBuilder: AlertDialog.Builder.() -> Unit): AlertDialog {
        /// for full screen , dont take ContextThemeWrapper
        //AlertDialog.Builder(context, style)
        /// sample style
        // R.style.AlertDialog_AppCompat_Light
        // R.style.AlertDialogCustom //  if you dont pass it will take as default

        val builder = AlertDialog.Builder(ContextThemeWrapper(context, style)).also {
            it.setCancelable(false)
            it.dialogBuilder()
            it.setIcon(icon)
        }
        return builder.create()

    }

    fun AlertDialog.Builder.negativeButton(negativeButtonText: String = "No", handleClick: (dialogInterface: DialogInterface) -> Unit = {}) {
        this.setPositiveButton(negativeButtonText) { dialogInterface, which -> handleClick(dialogInterface) }
    }

    fun AlertDialog.Builder.positiveButton(positiveButtonText: String = "Yes", handleClick: (dialogInterface: DialogInterface) -> Unit = {}) {
        this.setNegativeButton(positiveButtonText) { dialogInterface, which -> handleClick(dialogInterface) }
    }





        //// normal extension without extension
//    fun customAlertDialog(title:String="Title",message:String="Message",positiveButtonText:String="Okay",negativeButtonText:String="Cancel",context: Context)
//        {
//            ///without cutom theme
//            //AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom)).apply {}
//
//            AlertDialog.Builder(ContextThemeWrapper(context, R.style.AlertDialogCustom)).apply {
//                setTitle(title)
//                setMessage(message)
//                setPositiveButton(positiveButtonText) { _, _ ->
//
//                    ///
//                    context.toast("todo")
//
//                }
//                setNegativeButton(negativeButtonText) { _, _ ->
//                }
//            }.create().show()
//        }



}

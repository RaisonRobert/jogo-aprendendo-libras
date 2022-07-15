package com.edu.pucpr.raison.jogoaprendendolibras.model.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.edu.pucpr.raison.jogoaprendendolibras.R

object Load {
    fun createLoadDialog(context: Context, show: Boolean): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(R.layout.dialog_load)
        builder.setCancelable(false)
        val modal = builder.create()
        modal.setCanceledOnTouchOutside(false)
        if (show) modal.show()
        return modal
    }
}
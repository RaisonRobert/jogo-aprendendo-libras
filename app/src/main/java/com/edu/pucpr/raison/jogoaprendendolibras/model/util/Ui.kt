package com.edu.pucpr.raison.jogoaprendendolibras.model.util

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.edu.pucpr.raison.jogoaprendendolibras.R
import kotlinx.android.synthetic.main.dialog_modal.view.*

object Ui {
    /**
     *
     * Sobrecarga do método que cria modal padrão do load
     * @param context Contexto
     * @param show boolean
     * @return AlertDialog
     *
     * */
    fun createLoadDialog(context: Context, show: Boolean): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(R.layout.dialog_load)
        builder.setCancelable(false)
        val modal = builder.create()
        modal.setCanceledOnTouchOutside(false)
        if (show) modal.show()
        return modal
    }
    /**
     *
     * Sobrecarga do método que cria modal padrão do aplicativo recebendo uma ação
     * @param context Contexto
     * @param icon Ícone
     * @param title Título do modal
     * @param subtitle Subtítulo do modal
     * @param description Descrição do modal
     * @return AlertDialog
     *
     * */
    fun createModal(
        context: Context,
        icon: Int,
        title: String,
        subtitle: String,
        description: String?
    ): AlertDialog? {

        val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_modal, null)

        var modalIcon = mDialogView.findViewById<ImageView>(R.id.iconDialog)
        var modalTitle = mDialogView.findViewById<TextView>(R.id.titleDialog)
        var modalSubtitle = mDialogView.findViewById<TextView>(R.id.subtitleDialog)
        var modalDescription = mDialogView.findViewById<TextView>(R.id.descriptionDialog)

        modalIcon.setImageResource(icon)
        modalTitle.text = title
        modalSubtitle.text = subtitle
        modalDescription.text = description

        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()

        mDialogView.buttonOK.setOnClickListener{
            mAlertDialog.dismiss()

        }
        return mAlertDialog
    }

}
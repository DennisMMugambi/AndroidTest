package com.rapptrlabs.androidtest.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rapptrlabs.androidtest.R
import com.rapptrlabs.androidtest.databinding.DialogConnectionBinding
import com.rapptrlabs.androidtest.databinding.DialogErrorBinding
import com.rapptrlabs.androidtest.databinding.DialogLoadingBinding

sealed interface DialogContent

data class ErrorContent(
    val code: String,
    val responseMessage: String,
    val requestDuration: String,
    var dismissAction: (() -> Unit)? = null,
) : DialogContent

data class NoNetworkContent(
    val message: String,
    val dismissAction: (() -> Unit)? = null,
) : DialogContent

data class LoadingContent(
    val message: String,
) : DialogContent

private fun ErrorContent.getLayout(
    layoutInflater: LayoutInflater,
    dismissAction: (() -> Unit),
): View = DialogErrorBinding.inflate(
    layoutInflater
).apply {
    title.text = code
    message.text = responseMessage
    duration.text = requestDuration

    dismissBtn.setOnClickListener{
        dismissAction.invoke()
    }
}.root

private fun NoNetworkContent.getLayout(
    layoutInflater: LayoutInflater,
    dismissAction: (() -> Unit),
): View = DialogConnectionBinding.inflate(
    layoutInflater
).apply {
    title.text = message
    dismissBtn.setOnClickListener {
        dismissAction.invoke()
    }
}.root

private fun LoadingContent.getLayout(
    layoutInflater: LayoutInflater,
    dismissAction: (() -> Unit),
): View = DialogLoadingBinding.inflate(
    layoutInflater
).apply {

}.root

object Dialog {
    private var dialog: AlertDialog? = null

    fun show(context: Context, content: DialogContent): AlertDialog {
        val layoutInflater = LayoutInflater.from(context)
        if (dialog != null && dialog!!.isShowing) {
            dialog?.setOnDismissListener(null)
            dialog?.dismiss()
            dialog = null
        }

        dialog = MaterialAlertDialogBuilder(
            context,
            R.style.AlertTheme
        ).create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setGravity(Gravity.CENTER_VERTICAL)
            setCancelable(false)
            when (content) {

                is ErrorContent -> {
                    setView(
                        content.getLayout(layoutInflater) {
                            this.dismiss()
                        },
                    )
                    setOnDismissListener {
                        content.dismissAction?.invoke()
                    }
                }

                is NoNetworkContent -> {
                    setView(
                        content.getLayout(layoutInflater) {
                            this.dismiss()
                        },
                    )
                    setOnDismissListener {
                        content.dismissAction?.invoke()
                    }
                }

                is LoadingContent -> {
                    setView(
                        content.getLayout(layoutInflater) {
                            this.dismiss()
                        },
                    )
                    setOnDismissListener {

                    }
                }

            }
            dialog?.window?.setGravity(Gravity.BOTTOM)
            show()
        }

        return dialog as AlertDialog
    }

    fun dimiss() {
        dialog?.dismiss()
    }
}
package com.rapptrlabs.androidtest.util

import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout

class Validator {

    companion object
    {

        fun validateEmail(emailLayout: TextInputLayout): Boolean {
            return validateRequired(emailLayout)
        }

        fun validatePassword(passwordLayout: TextInputLayout): Boolean {
            return validateRequired(passwordLayout)
        }

        fun validateRequired(layout: TextInputLayout): Boolean {
            val input = layout.editText?.text.toString()
            return if (input.isEmpty()) {
                layout.error = "Field Required"
                layout.editText?.doAfterTextChanged {
                    layout.error = null
                }
                false
            } else {
                layout.error = null
                true
            }
        }
    }
}
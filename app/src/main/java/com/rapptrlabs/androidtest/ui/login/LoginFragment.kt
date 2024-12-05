package com.rapptrlabs.androidtest.ui.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewOutlineProvider
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rapptrlabs.androidtest.R
import com.rapptrlabs.androidtest.data.model.UiState
import com.rapptrlabs.androidtest.databinding.FragmentLoginBinding
import com.rapptrlabs.androidtest.util.Dialog
import com.rapptrlabs.androidtest.util.ErrorContent
import com.rapptrlabs.androidtest.util.LoadingContent
import com.rapptrlabs.androidtest.util.Validator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

// TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.
// TODO: Add a ripple effect when the buttons are clicked
// TODO: Save screen state on screen rotation, inputted username and password should not disappear on screen rotation

// TODO: Send 'email' and 'password' to http://dev.rapptrlabs.com/Tests/scripts/login.php as FormUrlEncoded parameters.

// TODO: When you receive a response from the login endpoint, display an AlertDialog.
// TODO: The AlertDialog should display the 'code' and 'message' that was returned by the endpoint.
// TODO: The AlertDialog should also display how long the API call took in milliseconds.
// TODO: When a login is successful, tapping 'OK' on the AlertDialog should bring us back to the MainActivity

// TODO: The only valid login credentials are:
// TODO: email: info@rapptrlabs.com
// TODO: password: Test123
// TODO: so please use those to test the login.

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding : FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        addFrostedGlassEffect()
        handleState()

        lifecycleScope.launch {
            viewModel.email.collect { email ->
                binding.emailEt.editText?.setText(email)
            }
        }

        lifecycleScope.launch {
            viewModel.password.collect { password ->
                binding.passwordEt.editText?.setText(password)
            }
        }


        binding.loginButton.setOnClickListener {
            login()
        }
    }

    private fun addFrostedGlassEffect() {
        binding.loginCard.setupWith(binding.parentView)
            .setBlurRadius(5f)
        binding.loginCard.outlineProvider = ViewOutlineProvider.BACKGROUND
        binding.loginCard.clipToOutline = true
    }

    private fun login() {
        // Validate the email and password before calling the login function
        if (Validator.validateEmail(binding.emailEt) && Validator.validatePassword(binding.passwordEt)) {

            dismissKeyBoard()

            viewModel.loginUser(binding.emailEt.editText?.text.toString(), binding.passwordEt.editText?.text.toString())
        }
    }

    private fun dismissKeyBoard() {
        val view = activity?.currentFocus
        if (view != null) {
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun handleState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->

                when (state) {
                    is UiState.Empty -> {

                    }
                    is UiState.Loading -> {
                        Dialog.show(requireContext(), LoadingContent("Loading..."))
                    }
                    is UiState.Success -> {
                        Timber.i("Response received: code: ${viewModel.code}, message: ${viewModel.message}")
                        Dialog.show(
                            requireContext(),
                            ErrorContent(viewModel.code, viewModel.message, "Network request took ${viewModel.duration} milliseconds", dismissAction = {
                                if(viewModel.code == "Success") {
                                    findNavController().navigateUp()
                                }

                            })
                        )
                    }
                    is UiState.Error -> {

                    }

                    UiState.Initial -> {

                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.resetStates()
    }
}

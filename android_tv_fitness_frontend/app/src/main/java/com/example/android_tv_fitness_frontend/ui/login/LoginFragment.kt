package com.example.android_tv_fitness_frontend.ui.login

import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.example.android_tv_fitness_frontend.R
import com.example.android_tv_fitness_frontend.nav.Navigation

/**
 * PUBLIC_INTERFACE
 * LoginFragment implements a TV-friendly login UI following the Polytracon/Titan style guide.
 * D-pad focus order: Email -> Password -> Sign In -> Register -> Forgot password.
 */
class LoginFragment : Fragment() {

    companion object {
        const val TAG = "LoginFragment"

        // PUBLIC_INTERFACE
        /** Factory to create a new instance of LoginFragment. */
        fun newInstance(): LoginFragment = LoginFragment()
    }

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var signInBtn: Button
    private lateinit var registerBtn: Button
    private lateinit var forgotLink: TextView
    private lateinit var errorText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstance: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        emailField = root.findViewById(R.id.input_email)
        passwordField = root.findViewById(R.id.input_password)
        signInBtn = root.findViewById(R.id.btn_sign_in)
        registerBtn = root.findViewById(R.id.btn_register)
        forgotLink = root.findViewById(R.id.link_forgot)
        errorText = root.findViewById(R.id.text_error)

        // TV: ensure focusable order and styling
        listOf(emailField, passwordField, signInBtn, registerBtn, forgotLink).forEach { v ->
            v.isFocusable = true
            v.isFocusableInTouchMode = true
            applyFocusOutline(v)
        }

        // Set initial focus to the email field per design
        emailField.requestFocus()

        emailField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        passwordField.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        // D-pad explicit next focus
        emailField.nextFocusDownId = R.id.input_password
        passwordField.nextFocusDownId = R.id.btn_sign_in
        signInBtn.nextFocusDownId = R.id.btn_register
        registerBtn.nextFocusDownId = R.id.link_forgot

        // Actions
        signInBtn.setOnClickListener {
            // Placeholder validation; backend integration can be added later
            val email = emailField.text?.toString().orEmpty()
            val pwd = passwordField.text?.toString().orEmpty()
            if (email.isBlank() || pwd.isBlank()) {
                showError(getString(R.string.login_error_missing))
                return@setOnClickListener
            }
            // Navigate to Home on success
            Navigation.toHome(requireActivity())
        }
        registerBtn.setOnClickListener {
            // For now, navigate to Home; could open register flow later
            Navigation.toHome(requireActivity())
        }
        forgotLink.setOnClickListener {
            showError(getString(R.string.login_forgot_placeholder))
        }

        // Enter key on password triggers sign in
        passwordField.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                if (event.action == KeyEvent.ACTION_UP) {
                    signInBtn.performClick()
                }
                true
            } else false
        }

        // Adjust top margin of the logo stack for 80dp from safe top as per notes
        val header = root.findViewById<View>(R.id.login_header)
        header.updateLayoutParams<MarginLayoutParams> {
            topMargin = resources.getDimensionPixelSize(R.dimen.tv_space_6xl) // ~64dp baseline; near 80dp target
        }

        return root
    }

    private fun showError(message: String) {
        errorText.text = message
        errorText.visibility = View.VISIBLE
    }

    private fun applyFocusOutline(view: View) {
        val ring = ContextCompat.getDrawable(requireContext(), R.drawable.tv_focus_ring_gold)

        view.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                v.scaleX = 1.03f
                v.scaleY = 1.03f
                // Use foreground so backgrounds (inputs/buttons) remain visible
                v.foreground = ring
            } else {
                v.scaleX = 1.0f
                v.scaleY = 1.0f
                v.foreground = null
            }
        }
    }
}

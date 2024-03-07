package com.tegar.sedekah.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tegar.sedekah.R
import com.tegar.sedekah.ui.auth.register.RegisterActivity
import com.tegar.sedekah.core.data.Resource
import com.tegar.sedekah.databinding.ActivityLoginBinding
import com.tegar.sedekah.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeSession()
        supportActionBar?.title = "Login"
        setForm()
        setUpAction()
        setButtonStatus()
    }

    private fun setUpAction() {
        binding.btnLogin.setOnClickListener { login() }
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun setButtonStatus() {
        setMyButtonEnable()
        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
        binding.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
    }

    private fun setMyButtonEnable() {
        val email = binding.edtEmail.text?.toString().orEmpty()
        val password = binding.edtPassword.text?.toString().orEmpty()

        binding.btnLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty()
    }

    private fun login() {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        loginViewModel.login(email, password).observe(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.btnLogin.isEnabled = false
                    binding.btnLogin.text = getString(R.string.loading)
                    binding.progressIndicator.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    Toast.makeText(
                        this,
                        getString(R.string.login_success), Toast.LENGTH_SHORT
                    ).show()
                    binding.progressIndicator.visibility = View.INVISIBLE
                    binding.btnLogin.isEnabled = true
                    binding.btnLogin.text = getString(R.string.btn_login_text)

                    it.data?.let { it1 -> loginViewModel.saveSession(it1) }



                }

                is Resource.Error -> {
                    Toast.makeText(
                        this,
                        it.message, Toast.LENGTH_SHORT
                    ).show()

                    binding.progressIndicator.visibility = View.INVISIBLE
                    binding.btnLogin.isEnabled = true
                    binding.btnLogin.text = getString(R.string.btn_login_text)
                }
            }

        }
    }


    private fun setForm() {
        binding.edtEmail.isEmailInput = true
        binding.edtPassword.isPasswordInput = true
    }
    private fun observeSession() {
        loginViewModel.getSession.observe(this) { user ->
            if (user.token != "") {
                finishAffinity()
                startActivity(Intent(this@LoginActivity, MainActivity   ::class.java))
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        loginViewModel.getSession.removeObservers(this)
    }


}
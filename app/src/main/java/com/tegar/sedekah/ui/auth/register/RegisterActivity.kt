package com.tegar.sedekah.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.tegar.sedekah.R
import com.tegar.sedekah.core.data.Resource
import com.tegar.sedekah.databinding.ActivityLoginBinding
import com.tegar.sedekah.databinding.ActivityRegisterBinding
import com.tegar.sedekah.ui.auth.login.LoginActivity
import com.tegar.sedekah.ui.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setForm()
        setContentView(binding.root)
        setUpAction()
        buttonListener()
    }

    private fun setForm() {
        binding.edtEmail.isEmailInput = true
        binding.edtPassword.isPasswordInput = true
    }

    private fun buttonListener() {
        setMyButtonEnable()
        binding.edtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
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

    private fun setUpAction() {
        binding.btnRegister.setOnClickListener { register() }
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setMyButtonEnable() {
        val email = binding.edtEmail.text?.toString().orEmpty()
        val password = binding.edtPassword.text?.toString().orEmpty()
        val name = binding.edtPassword.text?.toString().orEmpty()

        binding.btnRegister.isEnabled =
            email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()
    }

    private fun register() {
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        val intent = Intent(this, LoginActivity::class.java)

        registerViewModel.register(name, email, password).observe(this) { it ->
            when (it) {
                is Resource.Loading -> {
                    binding.btnRegister.isEnabled = false
                    binding.btnRegister.text = getString(R.string.loading)
                    binding.progressIndicator.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    Toast.makeText(
                        this,
                        getString(R.string.login_success), Toast.LENGTH_SHORT
                    ).show()
                    binding.progressIndicator.visibility = View.INVISIBLE
                    binding.btnRegister.isEnabled = true
                    binding.btnRegister.text = getString(R.string.btn_login_text)

                    startActivity(intent)


                }

                is Resource.Error -> {
                    Toast.makeText(
                        this,
                        it.message, Toast.LENGTH_SHORT
                    ).show()

                    binding.progressIndicator.visibility = View.INVISIBLE
                    binding.btnRegister.isEnabled = true
                    binding.btnRegister.text = getString(R.string.btn_login_text)
                }
            }
        }
    }
}
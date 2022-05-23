package com.example.desaa.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.desaa.databinding.ActivityLoginBinding
import com.example.desaa.ui.backwood.BackwoodActivity
import com.example.desaa.ui.headman.HomeHeadmanActivity
import com.example.desaa.utils.ResponseMessage
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_ROLE
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance
import com.example.desaa.utils.StatusResponse
import com.example.desaa.utils.Validation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    private lateinit var sharePreferenceApp: SharePreferenceApp

    private lateinit var viewModelLogin: ViewModelLogin

    private lateinit var viewModelFactory: FactoryLogin

    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            loadingLoginActivity.visibility = View.GONE

            sharePreferenceApp = getInstance(this@LoginActivity)

            viewModelFactory = FactoryLogin(this@LoginActivity,loadingLoginActivity)
            viewModelLogin =
                ViewModelProvider(this@LoginActivity, viewModelFactory)[ViewModelLogin::class.java]

            btnLogin.setOnClickListener {
                val email = inputEmail.editText?.text.toString().trim()
                val password = inputPassword.editText?.text.toString().trim()

                if (email.isEmpty()) {
                    inputEmail.error = "Email anda kosong"
                } else if (password.isEmpty()) {
                    inputPassword.error = "Password anda kosong"
                } else if (!Validation.formatEmail(email)) {
                    inputEmail.error = "Format email salah"
                } else if (password.length < 5) {
                    inputPassword.error = "Password terlalu pendek"
                } else {
                    inputEmail.error = null
                    inputPassword.error = null
                    loadingLoginActivity.visibility = View.VISIBLE

                    viewModelLogin.getEmailPass(email, password)
                    lifecycleScope.launch {
                        viewModelLogin.getToken()
                        delay(1000)
                        moveActivity(sharePreferenceApp.getData(KEY_ROLE, ""))
                    }
                }
            }
        }
    }

    private fun moveActivity(role: String) {
        CoroutineScope(Dispatchers.Main).launch {

            if (role != null) {

                when {
                    role == "kepala_desa" -> {
                        ResponseMessage.alert(
                            "Berhasil Login",
                            StatusResponse.SUCCESS,
                            this@LoginActivity
                        )
                        viewModelLogin.getProfile(role)

                        delay(1000)

                        startActivity(
                            Intent(
                                this@LoginActivity,
                                HomeHeadmanActivity::class.java
                            )
                        )

                        finishAffinity()
                    }

                    role.contains("kepala_dusun") -> {

                        ResponseMessage.alert(
                            "Berhasil Login",
                            StatusResponse.SUCCESS,
                            this@LoginActivity
                        )

                        viewModelLogin.getProfile(role)

                        delay(1000)

                        startActivity(
                            Intent(
                                this@LoginActivity,
                                BackwoodActivity::class.java
                            )
                        )
                        finishAffinity()
                    }
                }
            }
        }
    }
}


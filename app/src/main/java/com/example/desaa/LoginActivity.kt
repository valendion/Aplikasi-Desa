package com.example.desaa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.desaa.databinding.ActivityLoginBinding
import com.example.desaa.ui.headman.HomeHeadmanActivity
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.Validation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    lateinit var sharePreferenceApp: SharePreferenceApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            sharePreferenceApp = SharePreferenceApp(this@LoginActivity)

            loadingLoginActivity.visibility = View.GONE

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

                    getToken(email, password)
                    getRole()

                }
            }
        }

    }


    private fun getToken(email: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val dataToken =
                NetworkConfig.apiServiceAdminVillage.getToken(email, password)

            withContext(Dispatchers.IO) {
                dataToken.apply {
                    sharePreferenceApp.editData("token", token)

                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }
        }


    }

    private fun getRole() {
        CoroutineScope(Dispatchers.Main).launch {
            val dataRole = NetworkConfig.apiServiceAdminVillage.getAparatureRole(
                "Bearer ${
                    sharePreferenceApp.getData(
                        "token",
                        ""
                    )
                }"
            )
            withContext(Dispatchers.IO) {
                val role = dataRole.data?.role

                withContext(Dispatchers.Main) {
                    if (role != null) {
                        Log.e("role", role)
                    }
                    when (role) {
                        "kepala_desa" -> {

                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    HomeHeadmanActivity::class.java
                                )

                            )

                            finishAffinity()
                        }
                    }
                }
            }
        }
    }

}

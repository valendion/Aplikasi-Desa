package com.example.desaa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.desaa.databinding.ActivityLoginBinding
import com.example.desaa.ui.backwood.BackwoodActivity
import com.example.desaa.ui.headman.HomeHeadmanActivity
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_TOKEN
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance
import com.example.desaa.utils.Validation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    private lateinit var sharePreferenceApp: SharePreferenceApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            sharePreferenceApp = getInstance(this@LoginActivity)

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
//                    getRole()
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

                    token?.let {
                        getRole(it)
                        sharePreferenceApp.editData(KEY_TOKEN, it)
                    }

                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
                            .show()

                    }

                }
            }
        }


    }

    private fun getRole(token: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val dataRole = NetworkConfig.apiServiceAdminVillage.getAparatureRole(
                "Bearer $token"
            )
            withContext(Dispatchers.IO) {
                val role = dataRole.data?.role

                withContext(Dispatchers.Main) {
                    sharePreferenceApp.editData(SharePreferenceApp.KEY_ROLE, role)

                    if (role != null) {
                        when  {
                            role == "kepala_desa" -> {

                                startActivity(
                                    Intent(
                                        this@LoginActivity,
                                        HomeHeadmanActivity::class.java
                                    )
                                )
                                getProfile()
                                Log.e("role", sharePreferenceApp.getData(SharePreferenceApp.KEY_ROLE,""))
                                finishAffinity()
                            }

                            role.contains("kepala_dusun") ->{
                                startActivity(
                                    Intent(
                                        this@LoginActivity,
                                        BackwoodActivity::class.java
                                    )
                                )
                                getProfile()
                                finishAffinity()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getProfile(){
        CoroutineScope(Dispatchers.Main).launch {
            val dataProfile = NetworkConfig.apiServiceAdminVillage.getAparatureogged("Bearer ${sharePreferenceApp.getData(
                KEY_TOKEN, "")}")
            withContext(Dispatchers.IO){
                dataProfile.data.apply {
                    sharePreferenceApp.editData(SharePreferenceApp.KEY_NAME_HEADMAN, namaKepalaDesa)
                    sharePreferenceApp.editData(SharePreferenceApp.KEY_NAME_VILLAGE, namaDesa)
                }
            }
        }
    }

}

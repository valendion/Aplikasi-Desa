package com.example.desaa.ui.backwood

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.desaa.R
import com.example.desaa.databinding.ActivityBackwoodBinding
import com.example.desaa.ui.user.HomeUserActivity
import com.example.desaa.utils.ChangeTheme
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.NetworkConnection
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class BackwoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBackwoodBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var sharePreferenceApp: SharePreferenceApp
    private lateinit var networkChange: NetworkConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBackwoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val changeTheme = ChangeTheme(this)

        if (changeTheme.isDarkTheme){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        sharePreferenceApp = getInstance(this)

        setSupportActionBar(binding.toolbar)

        networkChange = NetworkConnection(this)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_approve_fragment,
                R.id.nav_detail_backwood_Fragment,
                R.id.nav_dashboard_backwood_fragment,
            )
        )

        val navController = findNavController(R.id.nav_host_fragment_content_backwood)

        setupActionBarWithNavController(navController)

        binding.imageLogout.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val dataLogout = NetworkConfig.apiServiceAdminVillage.logout(
                    "Bearer ${
                        sharePreferenceApp.getData(
                            SharePreferenceApp.KEY_TOKEN, ""
                        )
                    }"
                )

                withContext(Dispatchers.Main) {
                    SweetAlertDialog(this@BackwoodActivity, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Apakah anda ingin keluar ?")
                        .setConfirmText("Ya")
                        .setConfirmClickListener {
                            it.dismissWithAnimation()
                            sharePreferenceApp.clearDate()
                            startActivity(
                                Intent(
                                    this@BackwoodActivity,
                                    HomeUserActivity::class.java
                                )
                            )
                            finishAffinity()
                        }
                        .setCancelButton(
                            "Tidak"
                        ) { sDialog -> sDialog.dismissWithAnimation() }
                        .show()
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_backwood)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()

        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChange, filter)
    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(networkChange)
    }
}
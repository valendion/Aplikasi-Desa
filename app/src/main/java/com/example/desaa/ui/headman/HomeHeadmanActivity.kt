package com.example.desaa.ui.headman

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.desaa.R
import com.example.desaa.databinding.ActivityHomeHeadmanBinding
import com.example.desaa.ui.user.HomeUserActivity
import com.example.desaa.utils.ChangeTheme
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.NetworkConnection
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_TOKEN
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.*


class HomeHeadmanActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeHeadmanBinding
    private lateinit var networkChange: NetworkConnection
    private lateinit var sharePreferenceApp: SharePreferenceApp

    companion object {
        val TAG = HomeHeadmanActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {

            val changeTheme = ChangeTheme(this)

            if (changeTheme.isDarkTheme) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            binding = ActivityHomeHeadmanBinding.inflate(layoutInflater)
            setContentView(binding.root)

            networkChange = NetworkConnection(this)

            sharePreferenceApp = getInstance(this)

            setSupportActionBar(binding.appBarHomeHeadman.toolbar)

            val drawerLayout: DrawerLayout = binding.drawerLayoutHeadman
            val navView: NavigationView = binding.navViewHeadman
            val navControllerHeadman =
                findNavController(R.id.nav_host_fragment_content_home_headman)

            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_village_government_officials_fragment,
                    R.id.nav_village_head_decision_fragment,
                    R.id.nav_dashboard_headman,
                    R.id.nav_village_rules_fragment,

                    ), drawerLayout
            )
            setupActionBarWithNavController(navControllerHeadman, appBarConfiguration)
            navView.setupWithNavController(navControllerHeadman)

            binding.appBarHomeHeadman.apply {
                btnLogout.setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        val dataLogout = NetworkConfig.apiServiceAdminVillage.logout(
                            "Bearer ${
                                sharePreferenceApp.getData(
                                    KEY_TOKEN, ""
                                )
                            }"
                        )
                        withContext(Dispatchers.Main) {

                            SweetAlertDialog(
                                this@HomeHeadmanActivity,
                                SweetAlertDialog.WARNING_TYPE
                            )
                                .setTitleText("Apakah anda ingin keluar ?")
                                .setConfirmText("Ya")
                                .setConfirmClickListener { sDialog ->
                                    sDialog.dismissWithAnimation()

                                    sharePreferenceApp.clearDate()
                                    startActivity(
                                        Intent(
                                            this@HomeHeadmanActivity,
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
        }catch (e: Exception){
            Log.d(TAG, e.message.toString())
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home_headman)
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
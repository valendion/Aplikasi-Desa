package com.example.desaa.ui.headman

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.desaa.R
import com.example.desaa.databinding.ActivityHomeHeadmanBinding
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.NetworkConnection
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeHeadmanActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeHeadmanBinding
    private lateinit var networkChange: NetworkConnection
    private lateinit var sharePreferenceApp: SharePreferenceApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeHeadmanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        networkChange = NetworkConnection(this)

        sharePreferenceApp = getInstance(this)

        setSupportActionBar(binding.appBarHomeHeadman.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayoutHeadman
        val navView: NavigationView = binding.navViewHeadman
        val navControllerHeadman = findNavController(R.id.nav_host_fragment_content_home_headman)

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

        getProfile()
    }

    fun getProfile(){
        CoroutineScope(Dispatchers.Main).launch {
            val dataProfile = NetworkConfig.apiServiceAdminVillage.getAparatureogged("Bearer ${sharePreferenceApp.getData("token", "")}")
            withContext(Dispatchers.IO){
                dataProfile.data.apply {
                    sharePreferenceApp.editData("nama_kelapa_desa", namaKepalaDesa)
                    sharePreferenceApp.editData("nama_desa", namaDesa)
                }
            }
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
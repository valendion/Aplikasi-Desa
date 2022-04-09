package com.example.desaa.ui.backwood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.desaa.R
import com.example.desaa.databinding.ActivityBackwoodBinding
import com.example.desaa.utils.SharePreferenceApp

class BackwoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBackwoodBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var sharePreferenceApp: SharePreferenceApp
//    private val viewModelDashboard: DashBoardBackwoodViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBackwoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_approve_fragment,
                R.id.nav_detail_backwood_Fragment,
                R.id.nav_dashboard_backwood_fragment,
            )
        )

//        val navController = findNavController(R.id.nav_host_fragment_content_backwood)
//
//        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_backwood)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
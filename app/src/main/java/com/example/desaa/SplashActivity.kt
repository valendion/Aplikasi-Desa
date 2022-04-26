package com.example.desaa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.desaa.ui.backwood.BackwoodActivity
import com.example.desaa.ui.headman.HomeHeadmanActivity
import com.example.desaa.ui.user.HomeUserActivity
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_ROLE
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    lateinit var sharePreferenceApp: SharePreferenceApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharePreferenceApp = getInstance(this)

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)

            if (sharePreferenceApp.getData(KEY_ROLE,"").isEmpty()){
                startActivity(Intent(this@SplashActivity, HomeUserActivity::class.java))
                finishAffinity()
            }else{
                when {
                    sharePreferenceApp.getData(KEY_ROLE,"") == "kepala_desa" -> {
                        startActivity(Intent(this@SplashActivity, HomeHeadmanActivity::class.java))
                        finishAffinity()
                    }
                    sharePreferenceApp.getData(KEY_ROLE,"").contains("kepala_dusun") -> {
                        startActivity(Intent(this@SplashActivity, BackwoodActivity::class.java))
                        finishAffinity()
                    }
                }
            }
        }
    }
}
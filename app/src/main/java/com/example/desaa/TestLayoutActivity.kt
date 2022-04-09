package com.example.desaa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desaa.adapter.user.AdapterSocialAssistanceBMPT
import com.example.desaa.utils.Data

class TestLayoutActivity : AppCompatActivity() {

    private val adapterSocialAssistanceBMPT: AdapterSocialAssistanceBMPT by lazy {
        AdapterSocialAssistanceBMPT()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_layout)

        val recycerlew = findViewById<RecyclerView>(R.id.listTest)

        recycerlew.apply {
            adapterSocialAssistanceBMPT.setList(Data.dataDummy)
            layoutManager = LinearLayoutManager(this@TestLayoutActivity)
            adapter = adapterSocialAssistanceBMPT
        }
    }
}
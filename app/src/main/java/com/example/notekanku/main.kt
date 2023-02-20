package com.example.notekanku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notekanku.databinding.ActivityMain2Binding
import java.text.SimpleDateFormat
import java.util.*

class main : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initDateNow()
    }


    private fun initDateNow(){
        var calendar = Calendar.getInstance().time
        var dateFormat = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault())
        var formatDate: String = dateFormat.format(calendar)

        binding.tvDate.text = formatDate

    }


}
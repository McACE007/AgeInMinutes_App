package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker.setOnClickListener{view ->
            clickDatePicker(view)

        }
    }
    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, picked_year, picked_month, picked_day ->
                val pickedDate = "$picked_day/${picked_month + 1}/$picked_year"
                tvSelectedDate.text = pickedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selectedDate = sdf.parse(pickedDate)
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val ageInMinutes = (currentDate!!.time/60000 - selectedDate!!.time/60000)

                tvAgeInMinutes.text = ageInMinutes.toString()
            },
            year,
            month,
            day)
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()

    }
}
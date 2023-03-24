package com.example.widgetspart2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.graphics.component1
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var startpoint=0
        var endpoint=0

        //1)spinner
        val spinnerId=findViewById<Spinner>(R.id.spinnerId)
        val result=findViewById<TextView>(R.id.result)
        val countryArray= arrayOf("India","Australia","NYC","USA","Japan","China")

        val arrayAdapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,countryArray)
        spinnerId.adapter=arrayAdapter
        spinnerId.onItemSelectedListener= object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                result.text=" I am living in "+countryArray[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        //2)AutoCompleteTextview
        val autoCompleteTextView=findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val countries=resources.getStringArray(R.array.countries)
        val arrayAdapter2=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countries)
//        autoCompleteTextView.threshold
        autoCompleteTextView.setAdapter(arrayAdapter2)

//        3)RatingBar
        val ratingBar=findViewById<RatingBar>(R.id.ratingBar)
        ratingBar.rating=2.5f
        ratingBar.stepSize=.5f
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(this,"$rating",Toast.LENGTH_LONG).show()
        }
//      4) Seekbar

        val seekbar=findViewById<SeekBar>(R.id.seekbar)
        val textview=findViewById<TextView>(R.id.tv)
        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textview.text=progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if(seekBar!=null){
                    startpoint=seekbar.progress
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    if(seekBar!=null){
                        endpoint=seekbar.progress
                    }
                Toast.makeText(this@MainActivity,"changed by ${endpoint-startpoint}",Toast.LENGTH_LONG).show()
            }

        })

        //5) Datepicker
        val picker= findViewById<DatePicker>(R.id.datepicker)
        val today= Calendar.getInstance()
        picker.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH))
        { view,year,month,day->
            val month=month+1
            val toast="You've selected: $day/$month/$year"
            Toast.makeText(this,toast,Toast.LENGTH_LONG).show()
        }

        //6) Timepicker
        val timepicker= findViewById<TimePicker>(R.id.timepicker)
        val tv2=findViewById<TextView>(R.id.tv2)
        timepicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            var hour=hourOfDay
            var am_pm=""
            when{
                hour==0->{
                    hour+=12
                    am_pm="AM"
                }
                hour==12->{
                    am_pm = "PM"
                }
                hour>12 ->
                {
                    hour-=12
                    am_pm="PM"
                }
                else->
                {
                    am_pm="AM"
                }
            }
            if(tv2!=null){
                val hour=if(hour<10) "0"+hour else hourOfDay
                val min=if(minute<10) "0"+minute else minute
                val toast="Time is: $hour:$min:$am_pm"
                tv2.text=toast
                Toast.makeText(this,toast,Toast.LENGTH_LONG).show()
            }
        }
    }
}
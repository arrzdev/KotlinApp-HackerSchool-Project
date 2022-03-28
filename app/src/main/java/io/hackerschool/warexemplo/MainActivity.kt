package io.hackerschool.warexemplo

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import java.util.*





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get elements
        val nameInput = findViewById<EditText>(R.id.name_input)
        val genderInput = findViewById<EditText>(R.id.gender_input)
        val cursoInput = findViewById<EditText>(R.id.curso_input)

        val btnDatePicker = findViewById<Button>(R.id.btn_date_picker)
        val textViewBirthday = findViewById<TextView>(R.id.textview_birthday)

        val btnGo = findViewById<Button>(R.id.btn_go)


        //get current date so that we can open calendar with the current date selected
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //when date picker button pressed
        btnDatePicker.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, picked_year, picked_month, picked_day ->

                //calc user age
                var user_age = Utils.calc_age(picked_day, picked_month, picked_year)

                // display selected date in the pre-visualizer
                textViewBirthday.setText("You have ${user_age} years old!")

            }, year, month, day) //define default date

            dpd.show()
        }

        //when go button pressed
        btnGo.setOnClickListener {
            //check if user interacted with every element
            if (nameInput.text.isNotEmpty() && genderInput.text.isNotEmpty() && cursoInput.text.isNotEmpty() && textViewBirthday.text.isNotEmpty()){

                val sharedPrefs = getSharedPreferences("francesinha_com_massa", Context.MODE_PRIVATE)

                //save data
                val editor = sharedPrefs.edit()

                //push changes
                editor.putString("Name", nameInput.text.toString())
                editor.putString("Gender", genderInput.text.toString())
                editor.putString("Curso", cursoInput.text.toString())
                editor.putInt("Age", textViewBirthday.text.split(" ")[2].toInt())

                //commit :))
                editor.apply()

                //go to menu
                val menu = Intent(this, MenuActivity::class.java)
                startActivity(menu)

            } else {
                //trow error
                Toast.makeText(this, "There is something missing!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
package io.hackerschool.warexemplo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //get elements
        val greetingText = findViewById<TextView>(R.id.txt_greeting)
        val btnApi = findViewById<Button>(R.id.btn_api)
        val btnColor = findViewById<Button>(R.id.btn_color)
        val btnInfo = findViewById<Button>(R.id.btn_info)

        //get user values
        val sharedPrefs = getSharedPreferences("francesinha_com_massa", Context.MODE_PRIVATE)

        val name = sharedPrefs.getString("Name", "")
        val gender = sharedPrefs.getString("Gender", "")
        val curso = sharedPrefs.getString("Curso", "")
        val age = sharedPrefs.getInt("Age", 0)

        //update welcome message
        greetingText.text = "Hello " + name.toString().split(" ")[0]

        //buttons logic
        btnApi.setOnClickListener {
            if (age >= 18) {
                //open third activity with info
                val api_page = Intent(this, ApiActivity::class.java)
                startActivity(api_page)
            } else {
                //throw error
                Toast.makeText(this, "You have to be over 18!", Toast.LENGTH_SHORT).show()
            }
        }

        btnInfo.setOnClickListener {
            //open third activity with info
            val info_page = Intent(this, InfoActivity::class.java)
            startActivity(info_page)
        }

        btnColor.setOnClickListener {
            //open color activity
            val color_page = Intent(this, ColorActivity::class.java)
            startActivity(color_page)
        }
    }
}
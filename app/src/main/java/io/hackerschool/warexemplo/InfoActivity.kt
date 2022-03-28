package io.hackerschool.warexemplo

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


class InfoActivity : AppCompatActivity() {

    lateinit var card_title:TextView
    lateinit var card_content:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        //get elements
        val frame = findViewById<ConstraintLayout>(R.id.frame)
        card_title = findViewById(R.id.title)
        card_content = findViewById(R.id.content)

        //get user values
        val sharedPrefs = getSharedPreferences("francesinha_com_massa", Context.MODE_PRIVATE)

        val name = sharedPrefs.getString("Name", "")
        val gender = sharedPrefs.getString("Gender", "")
        val curso = sharedPrefs.getString("Curso", "")
        val age = sharedPrefs.getInt("Age", 0)

        //get color
        val Red = sharedPrefs.getInt("oldRed", 0)
        val Green = sharedPrefs.getInt("oldGreen", 0)
        val Blue = sharedPrefs.getInt("oldBlue", 0)

        //update frame with the color
        frame.setBackgroundColor(
            Color.rgb(
                Red,
                Green,
                Blue
            )
        )

        //update text color with the inverse of the background
        card_title.setTextColor(
            Color.rgb(
                255-Red,
                255-Green,
                255-Blue
            )
        )
        card_content.setTextColor(
            Color.rgb(
                255-Red,
                255-Green,
                255-Blue
            )
        )

        //create the cards list
        val cards = arrayOf("Name", name.toString(), "Gender", gender.toString(), "Curso", curso.toString(), "Age", age.toString())

        var index:Int = 0

        updateCard(index, cards)

        //set listener to swipes on layout
        frame.setOnTouchListener(object: OnSwipeTouchListener(this@InfoActivity) {
            override fun onSwipeLeft() {
                if (index == 6) {
                    index = 0
                } else {
                    index += 2
                }
                //update
                updateCard(index, cards)
            }
            override fun onSwipeRight() {
                if (index == 0) {
                    index = 6
                } else {
                    index -= 2
                }
                //update
                updateCard(index, cards)
            }

        })

    }


    //set update function
    fun updateCard(i:Int, cards:Array<String>){
        card_title.text = cards[i]
        card_content.text = cards[i+1]
    }
}
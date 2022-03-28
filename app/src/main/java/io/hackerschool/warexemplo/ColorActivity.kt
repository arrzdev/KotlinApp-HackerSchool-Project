package io.hackerschool.warexemplo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.graphics.Color

import android.widget.SeekBar
import android.widget.TextView

class ColorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        //get elements
        val viewColor = findViewById<View>(R.id.view_color)

        //textviews
        val textViewRed = findViewById<TextView>(R.id.textView_red)
        val textViewGreen = findViewById<TextView>(R.id.textView_green)
        val textViewBlue = findViewById<TextView>(R.id.textView_blue)

        //sliders
        val sliderRed = findViewById<SeekBar>(R.id.seekBar_red)
        val sliderGreen = findViewById<SeekBar>(R.id.seekBar_green)
        val sliderBlue = findViewById<SeekBar>(R.id.seekBar_blue)

        //color code
        val sharedPrefs = getSharedPreferences("francesinha_com_massa", Context.MODE_PRIVATE)

        //set 2 functions so we do not repeat the same code over and over
        fun updateColor(red:Int, green:Int, blue:Int){

            //update square color
            viewColor.setBackgroundColor(
                Color.rgb(
                    red,
                    green,
                    blue
                )
            )

            //update texts
            textViewRed.text = "Red - ${red}"
            textViewGreen.text = "Green - ${green}"
            textViewBlue.text = "Blue - ${blue}"

            //missing change progress on the sliders / seekbars
            //this needs to be here so that when we come back to this module the seekbars \
            //get moved to the right place
            sliderRed.progress = red
            sliderGreen.progress = green
            sliderBlue.progress = blue

        }

        fun saveColors(red:Int, green:Int, blue:Int){
            //save data
            val editor = sharedPrefs.edit()

            //push changes
            editor.putInt("oldRed", red)
            editor.putInt("oldGreen", green)
            editor.putInt("oldBlue", blue)

            //commit :))
            editor.apply()
        }

        //get old values
        val oldRed = sharedPrefs.getInt("oldRed", 0)
        val oldGreen = sharedPrefs.getInt("oldGreen", 0)
        val oldBlue = sharedPrefs.getInt("oldBlue", 0)

        updateColor(oldRed, oldGreen, oldBlue)

        //

        //set the listeners on the sliders
        //RED
        sliderRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                //update square and
                updateColor(sliderRed.progress, sliderGreen.progress, sliderBlue.progress)

            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
                //saves the current values
                saveColors(sliderRed.progress, sliderGreen.progress, sliderBlue.progress)
            }
        })

        //GREEN
        sliderGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                //update square and
                updateColor(sliderRed.progress, sliderGreen.progress, sliderBlue.progress)

            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
                //saves the current values
                saveColors(sliderRed.progress, sliderGreen.progress, sliderBlue.progress)
            }
        })

        //BLUE
        sliderBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                //update square and
                updateColor(sliderRed.progress, sliderGreen.progress, sliderBlue.progress)

            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
                //saves the current values
                saveColors(sliderRed.progress, sliderGreen.progress, sliderBlue.progress)
            }
        })
    }
}
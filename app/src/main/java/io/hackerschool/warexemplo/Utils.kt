package io.hackerschool.warexemplo

import android.graphics.Color
import java.util.*
import kotlin.random.Random

class Utils {

    companion object {
        /**
         * Returna uma cor aleatoria
         */
        fun calc_age(b_day:Int, b_month:Int, b_year:Int): Int{

            //get current date
            val c = Calendar.getInstance()
            val c_year = c.get(Calendar.YEAR)
            val c_month = c.get(Calendar.MONTH)
            val c_day = c.get(Calendar.DAY_OF_MONTH)

            //start by defining age as 0
            var age = 0

            //calculate age
            age = c_year - b_year

            if (((c_month < b_month) || (c_month == b_month && c_day < b_day)) && (age > 0)){
                age -= 1
            }

            return age

        }
    }

}

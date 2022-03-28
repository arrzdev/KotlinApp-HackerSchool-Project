package io.hackerschool.warexemplo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ApiActivity : AppCompatActivity() {

    private var requestQueue: RequestQueue? = null
    lateinit var knowledge_input:EditText
    lateinit var knowledge_text:TextView
    lateinit var fetch_btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        //get elements
        knowledge_input = findViewById(R.id.editTextTopic)
        knowledge_text = findViewById(R.id.txt_data)
        fetch_btn = findViewById(R.id.button_fetch)

        //start
        requestQueue = Volley.newRequestQueue(this)

        fetch_btn.setOnClickListener{
            //get input text
            val topic = knowledge_input.text

            knowledge_text.text = "Fetching Info.."

            //make the request
            getKnowledge(topic.toString())


        }
    }

    fun getKnowledge(topic:String){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://knowledge-api-v1.herokuapp.com/${topic}"

        // Request a string response from the provided URL.
        val stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->

                val jsonObj: JSONObject = JSONObject(response.toString())

                val status = jsonObj.get("status")

                if (status == "ok"){
                    //get data
                    knowledge_text.text = jsonObj.get("data").toString()
                } else {
                    knowledge_text.text = "I don't know anything about that"
                }
            },
            Response.ErrorListener {
                knowledge_text.text = "Something did not work"
            }
        )

        queue.add(stringReq)
    }

}
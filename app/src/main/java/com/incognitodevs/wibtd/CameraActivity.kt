package com.incognitodevs.wibtd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_camera.*
import org.json.JSONObject

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        var queue = Volley.newRequestQueue(this)
        val url = "https://incognitodevs.000webhostapp.com/IncognitoServer/src/Waiter.php"
        val postRequest = object : StringRequest(Request.Method.POST, url,
                Response.Listener { response ->
                    val obj = JSONObject(response)
                    if (!obj.getBoolean("error")) {
                        DownloadImageTask(imageViewCamera).execute(obj.getString("pictureUrl"))
                    }
                    Log.d("response", response.toString())
                },
                Response.ErrorListener { error ->
                    Log.d("Error.Response", error.toString())
                }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("id", "1")
                params.put("client", "android")
                params.put("request", "camera")
                params.put("data", "")
                return params
            }
        }
        queue.add(postRequest)


        btnGetPicture.setOnClickListener{
            queue = Volley.newRequestQueue(this)
            val postRequest2 = object : StringRequest(Request.Method.POST, url,
                    Response.Listener { response ->
                        val obj = JSONObject(response)
                        if (!obj.getBoolean("error")) {
                            DownloadImageTask(imageViewCamera).execute(obj.getString("pictureUrl"))
                        }
                        Log.d("response", response.toString())
                    },
                    Response.ErrorListener { error ->
                        Log.d("Error.Response", error.toString())
                    }
            ) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params.put("id", "1")
                    params.put("client", "android")
                    params.put("request", "camera")
                    params.put("data", "")
                    return params
                }
            }
            queue.add(postRequest2)

        }
    }

}


package com.incognitodevs.wibtd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        btnSend.setOnClickListener{
            var response =  PostRequest("android", "camera").execute()
            Log.d("post btn", "Post send")
            Log.d("camera response", response.get())
        }
    }

}


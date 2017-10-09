package com.incognitodevs.wibtd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_camera.*
import org.json.JSONObject
import android.widget.Toast
import android.os.AsyncTask
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection


class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        btnSend.setOnClickListener{
            SendPostRequest().execute()
            Log.d("post btn", "Post send")
        }
    }

    inner class SendPostRequest : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {}

        override fun doInBackground(vararg arg0: String): String {
            try {
                val url = URL("https://incognitodevs.000webhostapp.com/IncognitoServer/src/Post.php") // here is your URL path

                val postDataParams = JSONObject()
                postDataParams.put("id", "1")
                postDataParams.put("client", "android")
                postDataParams.put("request", "camera")
                Log.e("params", postDataParams.toString())

                val conn = url.openConnection() as HttpURLConnection
                conn.readTimeout = 15000
                conn.connectTimeout = 15000
                conn.requestMethod = "POST"
                conn.doInput = true
                conn.doOutput = true

                val os = conn.outputStream
                val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                writer.write(getPostDataString(postDataParams))

                writer.flush()
                writer.close()
                os.close()

                val responseCode = conn.responseCode

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    val bufferIn = BufferedReader(InputStreamReader(conn.inputStream))

                    val sb = StringBuffer("")
                    var line = ""

                    line = bufferIn.readLine()

                    while (line != null) {
                        line = bufferIn.readLine()
                        sb.append(line)
                        break
                    }

                    bufferIn.close()
                    return sb.toString()

                } else {
                    Log.d("post send", "http not ok")
                }
            } catch (e: Exception) {
                Log.d("post send", e.message)
            }

            return String()
        }

        override fun onPostExecute(result: String) {
            Toast.makeText(applicationContext, result,
                    Toast.LENGTH_SHORT).show()
        }
    }

    @Throws(Exception::class)
    fun getPostDataString(params: JSONObject): String {

        val result = StringBuilder()
        var first = true

        val itr = params.keys()

        while (itr.hasNext()) {

            val key = itr.next()
            val value = params.get(key)

            if (first)
                first = false
            else
                result.append("&")

            result.append(URLEncoder.encode(key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(value.toString(), "UTF-8"))

        }
        return result.toString()
    }
}


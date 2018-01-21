package com.incognitodevs.wibtd

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceOnClickListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spaceNavigationView.initWithSaveInstanceState(savedInstanceState)
        spaceNavigationView.addSpaceItem(SpaceItem("GALLERY", R.drawable.ic_gallery))
        spaceNavigationView.addSpaceItem(SpaceItem("SETTINGS", R.drawable.ic_settings))
        spaceNavigationView.setCentreButtonIcon(R.drawable.ic_camera)
        spaceNavigationView.setCentreButtonIconColorFilterEnabled(false)
        spaceNavigationView.setSpaceOnClickListener(object: SpaceOnClickListener {
            override fun onCentreButtonClick() {
                val intent = Intent(this@MainActivity, CameraActivity::class.java)
                startActivity(intent)
            }

            override fun onItemClick(itemIndex: Int, itemName: String) {
                if(itemIndex == 0 ){
                    val intent = Intent(this@MainActivity, GalleryActivity::class.java)
                    startActivity(intent)
                }else if(itemIndex == 1){
                    val intent = Intent(this@MainActivity, SettingsActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onItemReselected(itemIndex: Int, itemName: String) {
                Toast.makeText(this@MainActivity, itemIndex.toString() + " " + itemName, Toast.LENGTH_SHORT).show()
            }

        })

//        {"error":false,
//            "message":"Default message",
//            "pictureUrl":"https:\/\/incognitodevs.000webhostapp.com\/IncognitoServer\/src\/uploads\/robbery.jpg"}

        val queue = Volley.newRequestQueue(this)
        val url = "https://incognitodevs.000webhostapp.com/IncognitoServer/src/Waiter1.php"
        val postRequest = object : StringRequest(Request.Method.POST, url,
                Response.Listener { response ->
                    Log.d("response", response.toString())
                    val obj = JSONObject(response)
                    if (!obj.getBoolean("error")) {
                        DownloadImageTask(imageViewMain).execute(obj.getString("pictureUrl"))
                    }

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
        postRequest.retryPolicy = DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        queue.add(postRequest)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        spaceNavigationView.onSaveInstanceState(outState)
    }
}

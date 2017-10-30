package com.incognitodevs.wibtd

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.hanuor.pearl.Pearl
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceOnClickListener
import kotlinx.android.synthetic.main.activity_main.*

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

        val urlFromPostRequest =  PostRequest("android", "photo").execute()
        Log.d("url request", "Post send")
        Log.d("url request", urlFromPostRequest.get())

        val urlOfImage =  "http://armaghi.com/wp-content/uploads/2014/09/Burglary-crime-burglar-opening-a-door-1839912781.jpg"
        val imageView = ImageView(applicationContext)
        imageView.visibility = View.VISIBLE
//        Pearl.imageLoader(applicationContext, urlOfImage, imageView, 1)
        mainLayout.addView(imageView)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        spaceNavigationView.onSaveInstanceState(outState)
    }
}

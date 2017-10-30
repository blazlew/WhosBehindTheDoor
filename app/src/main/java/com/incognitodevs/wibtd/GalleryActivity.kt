package com.incognitodevs.wibtd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_gallery.*


class GalleryActivity : AppCompatActivity() {

    private val image_titles = arrayOf("Img1", "Img2", "Img3", "Img4", "Img5", "Img6", "Img7", "Img8", "Img9", "Img10", "Img11", "Img12", "Img13")

    private val image_ids = arrayOf("http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg", "http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg", "http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg", "http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg", "http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg", "http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg", "http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg", "http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg", "http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg", "http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg", "http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg", "http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg", "http://l7.alamy.com/zooms/13655bc17eac4dbda992f5ba29ae8938/thief-stealing-laptop-computer-eadfym.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        
        imageGallery.setHasFixedSize(true)

        val layoutManager = GridLayoutManager(applicationContext, 2)
        imageGallery.layoutManager = layoutManager
        val createLists = prepareData()
        val adapter = MyAdapter(applicationContext, createLists)
        imageGallery.adapter = adapter
    }

    private fun prepareData():ArrayList<CreateList> {
        val theimage:ArrayList<CreateList> = ArrayList()
        for (i in 0 until image_titles.size)
        {
            val createList = CreateList()
            createList.image_title = image_titles[i]
            createList.image_ID = image_ids[i]
            theimage.add(createList)
        }
        return theimage
    }
}

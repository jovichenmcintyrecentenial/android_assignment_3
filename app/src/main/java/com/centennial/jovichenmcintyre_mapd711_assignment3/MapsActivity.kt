package com.centennial.jovichenmcintyre_mapd711_assignment3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.centennial.jovichenmcintyre_mapd711_assignment3.databinding.ActivityMapsBinding
import com.centennial.jovichenmcintyre_mapd711_assignment3.models.Company
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.gson.Gson
import android.graphics.Bitmap

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.model.Marker


//Name: Jovi Chen-Mcintyre
//ID: 301125059

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var company:Company;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get data send from previous page and deserialize the data
        company = Gson().fromJson(intent.getStringExtra("company"),Company::class.java)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //load menu
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //update map type base on selected option
        when(item.itemId) {
            R.id.hybrid -> mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            R.id.normal -> mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            R.id.satellite -> mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            R.id.terrain -> mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //set custom info window adapter view
        mMap.setInfoWindowAdapter(MyInfoWindowAdapter(this,company))

        //loop the list of location and add marker for each
        for((index, location) in company.locations.withIndex()) {
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(location.latitude, location.longitude))
                    .title(location.name)
                        //save item position so item can be retrieve in MyInfoWindowAdapter to extract more data
                    .snippet(index.toString())
                        //resize image because image was too large
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(
                            resizeMapIcons(
                                company.pin_icon,
                                80,
                                106
                            )
                        )
                    )
            )
        }
        //adjust camera to first store in list of locations
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(company.locations[0].latitude, company.locations[0].longitude),
            8.0F
        ))
    }
    //use to resize image got this code from the below link
    //https://stackoverflow.com/questions/14851641/change-marker-size-in-google-maps-api-v2
    private fun resizeMapIcons(iconName: String?, width: Int, height: Int): Bitmap {
        val imageBitmap = BitmapFactory.decodeResource(
            resources, resources.getIdentifier(
                iconName, "drawable",
                packageName
            )
        )
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false)
    }
}


class MyInfoWindowAdapter(context: Context, private val company:Company) : GoogleMap.InfoWindowAdapter {
    val mWindow: View = LayoutInflater.from(context).inflate(R.layout.map_window_info, null)
    val contex = context

    override fun getInfoWindow(marker: Marker): View {

        //use the snippet infomation and cast it to an int so app know which marker is being pressed
        var index = marker.snippet.toString().toIntOrNull()
        if(index != null){
            //take index and retrieve storelocation object
            var storeLocation = company.locations[index];

            //find views
            val companyNameTextView = mWindow.findViewById<TextView>(R.id.company_name)
            val companyImageView = mWindow.findViewById<ImageView>(R.id.company_image)
            val addressTextView = mWindow.findViewById<TextView>(R.id.address)
            val phoneTextView = mWindow.findViewById<TextView>(R.id.phone)
            val openingTextView = mWindow.findViewById<TextView>(R.id.opening)
            val websiteTextView = mWindow.findViewById<TextView>(R.id.website)

            //update view data
            companyNameTextView.text = storeLocation.name
            addressTextView.text = storeLocation.address
            phoneTextView.text = storeLocation.phoneNumber
            openingTextView.text = storeLocation.openHours +  " - " + storeLocation.closeHours
            websiteTextView.text = storeLocation.website

            //load store image in view base on name
            val resourceImage: Int = contex.resources.getIdentifier(storeLocation.image, "drawable", contex.packageName)
            companyImageView?.setImageResource(resourceImage)

        }


        return mWindow
    }

    override fun getInfoContents(marker: Marker): View {
        return mWindow
    }
}
package com.centennial.jovichenmcintyre_mapd711_assignment3

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




class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var company:Company;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        company = Gson().fromJson(intent.getStringExtra("company"),Company::class.java)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        for(location in company.locations) {
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(location.latitude, location.longitude))
                    .title(location.name)
                    .snippet("Population: 4,137,400")
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
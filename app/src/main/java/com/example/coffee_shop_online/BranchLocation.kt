package com.example.coffee_shop_online

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class BranchLocation : AppCompatActivity() , OnMapReadyCallback {

    private var myMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        // Initialize the map fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this) ?: throw IllegalStateException("Map fragment not found!")
    }

    override fun onMapReady(googleMap: GoogleMap) {
        myMap = googleMap

        // List of locations with their corresponding marker colors
        val locations = listOf(
            Triple(LatLng(6.0329, 80.2168), "Galle-Karapitiya", BitmapDescriptorFactory.HUE_BLUE),
            Triple(LatLng(6.0331, 80.2202), "Galle-Bus Stand", BitmapDescriptorFactory.HUE_RED),
            Triple(LatLng(6.0568, 80.2104), "Galle-Wackwella", BitmapDescriptorFactory.HUE_GREEN),
            Triple(LatLng(6.0339, 80.2255), "Galle-Magalle", BitmapDescriptorFactory.HUE_YELLOW),
            Triple(LatLng(5.9485, 80.5353), "Matara", BitmapDescriptorFactory.HUE_ORANGE),
            Triple(LatLng(5.9488, 80.5376), "Matara-Nupe", BitmapDescriptorFactory.HUE_AZURE),
            Triple(LatLng(5.9439, 80.5495), "Matara-Beach Road", BitmapDescriptorFactory.HUE_CYAN),
            Triple(LatLng(5.9493, 80.5480), "Matara-Kotuwegoda", BitmapDescriptorFactory.HUE_VIOLET)
        )

        // Add markers and adjust the camera to fit all markers
        val boundsBuilder = LatLngBounds.Builder()
        for ((latLng, title, color) in locations) {
            myMap?.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(title)
                    .icon(BitmapDescriptorFactory.defaultMarker(color))
            )
            boundsBuilder.include(latLng)
        }

        // Adjust the camera to fit all the markers
        val bounds = boundsBuilder.build()
        myMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150)) // Adjust padding if needed
    }
}

package com.example.kamtrainig

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.kamtrainig.databinding.ActivityMapsBinding
import com.example.kamtrainig.databinding.DetailedViewBinding
import java.util.*
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MapsActivity : AppCompatActivity(), Observer, OnMapReadyCallback,
    GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener, LocationListener
{

    private lateinit var mMap: GoogleMap
    private lateinit var myLocation: Location
    private lateinit var binding: ActivityMapsBinding
    private lateinit var details_binding: DetailedViewBinding
    private lateinit var locationManager: LocationManager

    private val locationPermissionCode = 2

    var lat: Double = 0.0
    var long: Double = 0.0
//    public lateinit var triageModel: TriageModel;

    //private lateinit var connector: UDPconnector

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        TriageModel.reset()
        Thread(UDPconnector).start()

        binding = ActivityMapsBinding.inflate(layoutInflater)
        details_binding = DetailedViewBinding.inflate(layoutInflater)
        setContentView(details_binding.root)

        TriageModel.addObserver(this)

        details_binding.button2.setOnClickListener {
            setContentView(binding.root)
            //Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }

        binding.floatingActionButton.setOnClickListener {
            setContentView(details_binding.root)
            //Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.isMyLocationEnabled = true
        mMap.setOnMyLocationButtonClickListener(this)
        mMap.setOnMyLocationClickListener(this)


        //getLocation()

        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)

        //val myLoc = LatLng(lat, long)
        //mMap.addMarker(MarkerOptions().position(myLoc).title("Pozycja KAM"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(myLocation.latitude, myLocation.longitude)))
    }

    override fun update(o: Observable?, arg: Any?) {
        details_binding.editTextNumber.text = TriageModel.red.toString();
        details_binding.editTextNumber3.text = TriageModel.orange.toString();
        details_binding.editTextNumber4.text = TriageModel.green.toString();
        details_binding.editTextNumber5.text = TriageModel.black.toString();
    }


    override fun onMyLocationClick(location: Location) {
//        Toast.makeText(this, "Current location:\n$location", Toast.LENGTH_LONG)
//            .show()
    }

    override fun onMyLocationButtonClick(): Boolean {
//        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT)
//            .show()
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onLocationChanged(location: Location) {
        myLocation=location
    }


//
//    @SuppressLint("MissingPermission")
//    private fun getLocation() {
//        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
//    }

//    override fun onLocationChanged(location: Location) {
//        lat = location.latitude
//        long = location.longitude
//
//    }
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        if (requestCode == locationPermissionCode) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
//            }
//            else {
//                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}
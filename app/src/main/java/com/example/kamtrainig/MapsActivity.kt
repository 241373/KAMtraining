package com.example.kamtrainig

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


class MapsActivity : AppCompatActivity(), Observer, OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var details_binding: DetailedViewBinding
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

        details_binding.button2.callOnClick()

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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }


    override fun update(o: Observable?, arg: Any?) {
        details_binding.editTextNumber.text = TriageModel.red.toString();
        details_binding.editTextNumber3.text = TriageModel.orange.toString();
        details_binding.editTextNumber4.text = TriageModel.green.toString();
        details_binding.editTextNumber5.text = TriageModel.black.toString();
    }
}
package com.pedra.conexa.views.users.selectedUser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pedra.conexa.R
import com.pedra.conexa.databinding.FragmentSelectedNewBinding
import com.pedra.conexa.databinding.FragmentSelectedUserBinding
import com.pedra.conexa.databinding.FragmentUsersBinding
import com.pedra.core.ConstantsConexa
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectedUserFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentSelectedUserBinding
    private var lat: Double = 0.0
    private var lng: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSelectedUserBinding.inflate(layoutInflater)
        getBundleArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createMap()
        setUpComponents()
    }

    private fun setUpComponents() {
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getBundleArguments() {
        arguments?.getString(ConstantsConexa.LAT)?.let { lat = it.toDouble() }
        arguments?.getString(ConstantsConexa.LONG)?.let { lng = it.toDouble() }
    }

    private fun createMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        createMarkerAndMoveCamera(googleMap)
    }

    private fun createMarkerAndMoveCamera(googleMap: GoogleMap) {
        val coordinates = LatLng(lat, lng)
        val marker = MarkerOptions().position(coordinates)
        googleMap.addMarker(marker)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 10f))
    }
}
package com.example.ing_app.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ing_app.databinding.FragmentUserBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf
import kotlin.properties.Delegates

class UserFragment : Fragment() {
    // Why kotlin sugested Delegates
    var args by Delegates.notNull<Int>()
    private val viewModel: UserViewModel by sharedViewModel{ parametersOf(args) }

    /*
    // Google maps
    private lateinit var mapView: MapView
    private lateinit var mMap: GoogleMap
    */


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUserBinding.inflate(inflater)

        binding.lifecycleOwner = this

        args = UserFragmentArgs.fromBundle(requireArguments()).id

        binding.viewModel = viewModel

        viewModel.navigateToSelectedPhotos.observe(viewLifecycleOwner, Observer {userId ->
            userId?.let{
                this.findNavController().navigate(
                    UserFragmentDirections.userToImages(userId))
                viewModel.displayPhotosCopmlete()
            }
        })

        viewModel.navigateToPosts.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    UserFragmentDirections.userToPosts())
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }

    /*override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val lat = viewModel.user.value?.address?.geo?.lat?.toDouble()
        val lng = viewModel.user.value?.address?.geo?.lng?.toDouble()
        val userLocation = lat?.let { lat ->
            lng?.let { lng ->
                LatLng(lat, lng)
            }
        }
        mMap.addMarker(userLocation?.let { MarkerOptions().position(it) })
    }*/


    // https://developers.google.com/maps/documentation/android-sdk/map#mapview
    // We don't really need fully interactive mode because we only show location
    // We can easily use lite mode of google maps
}
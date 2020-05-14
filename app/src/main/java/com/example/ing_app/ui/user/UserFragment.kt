package com.example.ing_app.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ing_app.databinding.FragmentUserBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber
import kotlin.properties.Delegates

class UserFragment : Fragment(), OnMapReadyCallback {
    // Why kotlin sugested Delegates
    var args by Delegates.notNull<Int>()
    private val viewModel: UserViewModel by viewModel{ parametersOf(args) }

    private lateinit var mMap: GoogleMap
    private var latLng: LatLng = LatLng(0.0,0.0)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView called")
        val binding = FragmentUserBinding.inflate(inflater)

        binding.lifecycleOwner = this

        args = UserFragmentArgs.fromBundle(requireArguments()).id

        Timber.d("On user args given: ${args}")

        binding.userListener = UserListener {
            it -> viewModel.onUserPhotosClicked(it)
        }

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

        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Timber.d("onUserObserve called")
                val lat = it.address.geo.lat.toDouble()
                val lng = it.address.geo.lng.toDouble()
                latLng = LatLng(lat, lng)
                mMap.addMarker(MarkerOptions().position(latLng))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.d("onActivityCreated called")
        super.onActivityCreated(savedInstanceState)
        map.onCreate(savedInstanceState)
        map.onResume()
        map.getMapAsync(this)
    }

    // TODO: change how i pass parameter, passing lat and lng as safeargs???
    override fun onMapReady(googleMap: GoogleMap) {
        Timber.d("onMapReady called")
        mMap = googleMap
    }

    // https://developers.google.com/maps/documentation/android-sdk/map#mapview
    // We don't really need fully interactive mode because we only show location
    // We can easily use lite mode of google maps, but it's documented badly
}
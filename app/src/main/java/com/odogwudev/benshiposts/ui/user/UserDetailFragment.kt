package com.odogwudev.benshiposts.ui.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.odogwudev.benshiposts.R
import com.odogwudev.benshiposts.data.local.dto.post.PostDto
import com.odogwudev.benshiposts.data.local.dto.user.AddressDto
import com.odogwudev.benshiposts.data.local.dto.user.UserDto
import com.odogwudev.benshiposts.databinding.FragmentUserDetailBinding
import com.odogwudev.benshiposts.shared.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class UserDetailFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    private val args: UserDetailFragmentArgs by navArgs()
    private val viewModel: UserDetailViewModel by viewModels()
    private lateinit var googleMap: GoogleMap
    private var location: LatLng? = null
    private var address: AddressDto? = null
    private var post: PostDto? = null
    private var user: UserDto? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserDetail(args.userId)
        val mapFragment = childFragmentManager.findFragmentById(binding.googleMap.id) as SupportMapFragment
        mapFragment.getMapAsync(this)




        binding.apply {
            toolbar.setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }
            user.apply {
                userPhone.setOnClickListener {
                    user?.phone?.let { it1 -> dialAuthorNumber(it1) }
                }
                userEmail.setOnClickListener {
                    user?.email?.let { it1 -> openEmail(it1) }
                }
                userWebsite.setOnClickListener {
                    user?.website?.let { it1 -> openWebsite(it1) }
                }
                user?.address?.geo?.let {
                    location = LatLng(it.lat.toDouble(), it.lng.toDouble())
                }
            }
        }

        observeContent()
    }

    private fun observeContent() {
        viewModel.user.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Error -> {
                    Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {

                }
                is UIState.Success -> {
                    binding.user = uiState.data
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onMapReady(gmap: GoogleMap) {
        googleMap = gmap

        if (user?.username != null) {
            googleMap.addMarker(
                MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))

                    .position(LatLng(location?.latitude!!, location?.longitude!!))
            )

            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        location?.latitude!!,
                        location?.longitude!!
                    ), 12.5F
                ), 6000, null
            )

        }
    }

    private fun openEmail(address: String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        val data: Uri = Uri.parse(
            "mailto:"
                    + address

        )
        intent.data = data
        startActivity(intent)
    }

    private fun dialAuthorNumber(userNumber: String) {
        val setCaller = Intent(Intent.ACTION_DIAL)
        setCaller.data = Uri.parse("tel:$userNumber")
        startActivity(setCaller)
        return
    }

    private fun openWebsite(address: String?) {
        var url = user?.website
        if (!address?.startsWith("https://")!! && !address.startsWith("http://")) {
            url = "http://$address";
        }
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }


}

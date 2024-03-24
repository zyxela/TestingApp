package com.example.testingapp.location.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testingapp.R
import com.example.testingapp.data.models.LocationResponse
import com.example.testingapp.databinding.ImageHolderBinding
import com.example.testingapp.databinding.LocationFragmentBinding
import com.example.testingapp.location.LocationViewModel
import com.example.testingapp.location.RvAdapter
import com.example.testingapp.location.TapItemListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationFragment @Inject constructor() : Fragment(), TapItemListener {

    override var addPosition: Int = 0

    override val position: MutableList<Int> = mutableListOf()
    override var locationList: MutableList<LocationResponse> = mutableListOf()

    val vm: LocationViewModel by viewModels<LocationViewModel>()

    private lateinit var binding: LocationFragmentBinding


    private val locationAdapter = RvAdapter(this)


    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uris: Uri? ->
        if (uris != null) {
            val bind =
                (binding.locationRv.findViewHolderForAdapterPosition(addPosition) as LocationViewHolder).getBinding()

            val inflater = LayoutInflater.from(binding.root.context)
            val imageHolder = ImageHolderBinding.inflate(inflater)
            imageHolder.apply {
                iv.setImageURI(uris)
                val img = requireActivity().contentResolver.openInputStream(uris)?.readBytes()
                img?.let {
                    locationList[addPosition].images.add(it)
                }
                this.root.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("Uri", uris.toString())
                    findNavController().navigate(
                        R.id.action_locationFragment_to_fullImageFragment,
                        bundle
                    )
                }

                this.root.setOnLongClickListener {
                    position.add(addPosition)
                    bind.grid.forEach {
                        it.findViewById<CheckBox>(R.id.checkBox).visibility = View.VISIBLE
                    }

                    val delList = mutableListOf<View>()
                    bind.delete.apply {
                        visibility = View.VISIBLE
                        setOnClickListener {
                            bind.grid.forEach {
                                val cb = it.findViewById<CheckBox>(R.id.checkBox)
                                cb.visibility = View.INVISIBLE
                                if (cb.isChecked) {
                                    delList.add(it)
                                }

                            }
                            delList.forEach {
                                bind.grid.removeView(it)
                            }
                            bind.delete.visibility = View.INVISIBLE
                        }

                    }
                    true
                }
            }
            bind.grid.addView(imageHolder.root)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        backListener()
        binding = LocationFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        observeLocations()

    }

    private fun setRecyclerView() {
        binding.locationRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            adapter = locationAdapter

        }

        binding.refreshAfisha.setOnRefreshListener {
            locationAdapter.addNewLocation()
            vm.saveData(locationList)
            binding.refreshAfisha.isRefreshing = false
        }
    }


    override fun getImageUri() {
        requestPermissionLauncher.launch("image/*")
    }

    private fun observeLocations() {
        vm.getData()
        vm.locations.observe(viewLifecycleOwner) {
            it?.forEach { item ->
                locationAdapter.updateRecycler(item)
            }
            it?.apply {
                locationAdapter.addNewLocation()
            }


        }
    }

    private fun backListener() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (position.isNotEmpty()) {
                        position.forEach {
                            locationAdapter.repaint(it)
                        }
                    }


                }

            })
    }

    override fun onDestroy() {
        super.onDestroy()
        vm.saveData(locationList)
    }
}
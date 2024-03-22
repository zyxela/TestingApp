package com.example.testingapp.location.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.testingapp.databinding.FullImageFragmentBinding

class FullImageFragment:Fragment() {
    private lateinit var binding: FullImageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FullImageFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uri = arguments?.getString("Uri", "")?.toUri()
        binding.ivFull.setImageURI(uri)
    }
}
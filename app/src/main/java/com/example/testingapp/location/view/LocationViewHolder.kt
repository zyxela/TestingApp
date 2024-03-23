package com.example.testingapp.location.view


import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.view.forEach
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testingapp.R
import com.example.testingapp.databinding.ImageHolderBinding
import com.example.testingapp.databinding.RvLocationItemBinding
import com.example.testingapp.location.TapItemListener
import com.example.testingapp.data.models.LocationResponse

class LocationViewHolder(private val binding: RvLocationItemBinding) :
    RecyclerView.ViewHolder(binding.root) {


    @SuppressLint("MissingInflatedId")
    fun onBind(position:Int, data: LocationResponse, tapListener: TapItemListener) {
        binding.etLocationName.setText(data.name)
        binding.addPhoto.setOnClickListener {
            tapListener.addPosition = position
            tapListener.getImageUri()
        }
        binding.etLocationName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                tapListener.locationList[position].name = s.toString()
            }

        })

        data.images.forEach { img ->
            val inflater = LayoutInflater.from(binding.root.context)
            val imageHolder = ImageHolderBinding.inflate(inflater)
            imageHolder.apply {
                iv.setImageURI(img.toUri())
            }

        }


    }
    fun getBinding(): RvLocationItemBinding {
        return binding
    }
}

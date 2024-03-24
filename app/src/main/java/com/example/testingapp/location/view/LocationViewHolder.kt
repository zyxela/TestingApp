package com.example.testingapp.location.view


import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.testingapp.data.models.LocationResponse
import com.example.testingapp.databinding.ImageHolderBinding
import com.example.testingapp.databinding.RvLocationItemBinding
import com.example.testingapp.location.TapItemListener

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
            val image = BitmapFactory.decodeByteArray(img, 0, img.size)
            imageHolder.iv.setImageBitmap(image)
        }


    }
    fun getBinding(): RvLocationItemBinding {
        return binding
    }
}

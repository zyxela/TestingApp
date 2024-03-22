package com.example.testingapp.location

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testingapp.databinding.RvLocationItemBinding
import com.example.testingapp.location.view.LocationViewHolder
import com.example.testingapp.data.models.LocationResponse

class RvAdapter(private val tapListener: TapItemListener) :
    RecyclerView.Adapter<LocationViewHolder>() {
    var locationList = mutableListOf<LocationResponse>()
    val holders: MutableList<RvLocationItemBinding> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val item = RvLocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        holders.add(item)
        return LocationViewHolder(item)
    }

    override fun getItemCount(): Int = locationList.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.onBind(locationList[position], tapListener)
    }

    fun addNewLocation() {
        locationList.add(0, LocationResponse(locationList.size, "", arrayListOf()))
        notifyItemInserted(0)
    }

    fun updateRecycler(data: LocationResponse) {
        locationList.add(data)
        notifyItemInserted(data.uid)
    }

    fun repaint(position: Int) {
        notifyItemChanged(position)
        tapListener.position.remove(position)
    }
}
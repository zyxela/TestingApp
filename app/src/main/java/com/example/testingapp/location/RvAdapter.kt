package com.example.testingapp.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testingapp.data.models.LocationResponse
import com.example.testingapp.databinding.RvLocationItemBinding
import com.example.testingapp.location.view.LocationViewHolder

class RvAdapter(private val tapListener: TapItemListener) :
    RecyclerView.Adapter<LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val item = RvLocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(item)
    }

    override fun getItemCount(): Int = tapListener.locationList.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.onBind(position, tapListener.locationList[position], tapListener)
    }


    fun addNewLocation() {
        val index = tapListener.locationList.size
        tapListener.locationList.add(0, LocationResponse(index, "", arrayListOf()))
        notifyItemInserted(0)
        notifyItemRangeChanged(0, tapListener.locationList.size - 1)
    }

    fun updateRecycler(data: LocationResponse) {
        tapListener.locationList.add(data)
        notifyItemInserted(data.uid)
        notifyItemRangeChanged(0, tapListener.locationList.size - 1)

    }

    fun repaint(position: Int) {
        notifyItemChanged(position)
        tapListener.position.remove(position)
    }
}
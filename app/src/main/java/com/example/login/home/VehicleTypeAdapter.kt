package com.example.login.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.VehicleType

class VehicleTypeAdapter(private val mList: List<VehicleType>) :
    RecyclerView.Adapter<VehicleTypeAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTypeName: TextView = itemView.findViewById(R.id.txtTypeName)
        val txtPrimary: TextView = itemView.findViewById(R.id.txtPrimary)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_vehicle_type, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.apply {
            txtTypeName.text = item.name
            txtPrimary.showHide(item.isPrimary!!)
        }
    }

    private fun View.showHide(show: Boolean) {
        visibility = if (show)
            View.VISIBLE
        else
            View.INVISIBLE
    }
}



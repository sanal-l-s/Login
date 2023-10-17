package com.example.login.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.Result

class CarsAdapter(private val list: List<Result>) : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtBrandName: TextView = itemView.findViewById(R.id.txtCarName)
        val txtCountryName: TextView = itemView.findViewById(R.id.txtCarCountry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return CarsAdapter.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_car, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemList = list[position]
        holder.apply {
            txtBrandName.text = itemList.mfrCommonName
            txtCountryName.text = itemList.country
        }
    }
}
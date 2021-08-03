package com.example.myweather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R
import com.example.myweather.models.CurrentWeather
import com.example.myweather.viewmodels.MainFragViewModels
import kotlinx.android.synthetic.main.location_item.view.*

class MainAdapter(private val currentWeather: List<CurrentWeather>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false).let {
                MainViewHolder(it)
            }

        return layoutInflater
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentWeather = currentWeather.get(position);
        holder.bind(currentWeather)

    }

    override fun getItemCount(): Int {
        return currentWeather.size
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val res = itemView.resources

        fun bind(currentWeather: CurrentWeather) {
            itemView.cityName.text = currentWeather.location.name + "," + currentWeather.location.country
        }

    }

}
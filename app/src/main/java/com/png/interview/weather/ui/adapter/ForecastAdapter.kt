package com.png.interview.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.R
import com.png.interview.weather.api.model.Forecastday
import com.png.interview.weather.ui.viewholder.ForecastViewHolder
import com.png.interview.weather.utils.PreferenceUtils

class ForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {
    private val _items = mutableListOf<Forecastday>()

    var forecastDays: List<Forecastday>? get() = _items.toList()
        set(value) {
            _items.clear()
            if (value != null) {
                _items.addAll(value)
            }

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_forecast_day, parent, false)
        val prefs = PreferenceManager.getDefaultSharedPreferences(parent.context)
        val isMetric = prefs.getInt(PreferenceUtils.PREF_UNITS, 0) == 1
        return ForecastViewHolder(view, isMetric)
    }

    override fun getItemCount(): Int {
        return _items.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(_items[position])
    }
}
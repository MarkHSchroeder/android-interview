package com.png.interview.weather.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.weather.api.model.Forecastday
import kotlinx.android.synthetic.main.view_forecast_day.view.*

class ForecastViewHolder(itemView: View,
                         private val isMetric: Boolean
) : RecyclerView.ViewHolder(itemView) {
    fun bind(forecastday: Forecastday) {
        itemView.tv_date_value.text = forecastday.date
        itemView.tv_max_temp_value.text = if (isMetric) {
            "${forecastday.day.maxtemp_c} C"
        } else {
            "${forecastday.day.maxtemp_f} F"
        }
        itemView.tv_min_temp_value.text = if (isMetric) {
            "${forecastday.day.mintemp_c} C"
        } else {
            "${forecastday.day.mintemp_f} F"
        }
        itemView.tv_wind_value.text = if (isMetric) {
            "${forecastday.day.maxwind_kph} KPH"
        } else {
            "${forecastday.day.maxwind_mph} MPH"
        }
        itemView.tv_condition_value.text = forecastday.day.condition.text
    }
}

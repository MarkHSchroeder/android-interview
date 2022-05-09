package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.png.interview.databinding.FragmentForecastBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.binder.ForecastFragmentViewBinder

class ForecastFragment : InjectedFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val location: String = if (arguments != null) {
            ForecastFragmentArgs.fromBundle(requireArguments()).location
        } else {
            ""
        }

        val forecastViewBinder = ForecastFragmentViewBinder(
            getViewModel()
        )
        val root = FragmentForecastBinding.inflate(inflater, container,false).apply {
            viewBinder = forecastViewBinder
            this.lifecycleOwner = viewLifecycleOwner
        }.root
        forecastViewBinder.onSearchForecast(location)
        return root
    }
}
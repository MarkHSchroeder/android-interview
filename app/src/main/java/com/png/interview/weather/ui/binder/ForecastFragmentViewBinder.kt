package com.png.interview.weather.ui.binder

import com.png.interview.weather.ui.viewmodel.ForecastViewModel

class ForecastFragmentViewBinder(
    private val viewModel: ForecastViewModel
) {
    val forecastViewData = viewModel.availableForecastLiveData
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible

    fun onSearchForecast(location: String) {
        viewModel.submitForecastSearch(location)
    }
}
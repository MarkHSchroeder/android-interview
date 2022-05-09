package com.png.interview.weather.ui.binder

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.png.interview.weather.ui.listener.AutocompleteOnClickListener
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel

class CurrentWeatherFragmentViewBinder(
    private val viewModel: CurrentWeatherViewModel,
    private val activity: Activity,
    private val settingsAction: () -> Unit,
    private val forecastAction: (location: String) -> Unit
) {

    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible
    val availableAutocompleteData = viewModel.availableAutocompleteLiveData

    var input = MutableLiveData("")

    fun refreshClicked() {
        // The input can change after fetching.
        // Parse out the location from the response.
        // If that data has been lost, default to the input
        val fetchedLocation = availableWeatherViewData.value?.getLocation() ?: ""
        viewModel.submitCurrentWeatherSearch(fetchedLocation)
    }

    fun seeForecastClicked() {
        val fetchedLocation = availableWeatherViewData.value?.getLocation() ?: ""
        forecastAction(fetchedLocation)
    }

    fun settingsClicked() {
        settingsAction()
    }

    fun goClicked() {
        val inputVal = input.value?: ""
        when {
            inputVal.isEmpty() ->
                Toast.makeText(activity, "Please Enter Query", Toast.LENGTH_LONG).show()
            inputVal.length < 3 ->
                Toast.makeText(activity, "Please Enter More than 3 Characters", Toast.LENGTH_LONG).show()
            else ->
                viewModel.submitCurrentWeatherSearch(inputVal)
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.length > 2) {
            viewModel.submitAutocompleteSearch(s.toString(),
                object : AutocompleteOnClickListener {
                    override fun onItemClick(item: String) {
                        viewModel.submitCurrentWeatherSearch(item)
                        viewModel.clearAutocomplete()
                        input.value = ""
                    }
                }
            )
        } else {
            viewModel.clearAutocomplete()
        }
    }

}
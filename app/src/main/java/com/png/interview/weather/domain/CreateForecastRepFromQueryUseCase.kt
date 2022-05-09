package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.api.model.Forecastday
import com.png.interview.weather.ui.adapter.ForecastAdapter
import com.png.interview.weather.ui.model.ForecastViewData
import com.png.interview.weather.ui.model.ForecastViewRepresentation
import javax.inject.Inject

interface CreateForecastRepFromQueryUseCase {
    suspend operator fun invoke(query: String): ForecastViewRepresentation
}

class DefaultCreateForecastRepFromQueryUseCase @Inject constructor(
    private val getForecastDataUseCase: GetForecastDataUseCase
) : CreateForecastRepFromQueryUseCase {
    override suspend fun invoke(query: String): ForecastViewRepresentation {
        return when (val result = getForecastDataUseCase(query)) {
            is NetworkResponse.Success -> {
                val dayList = ArrayList<Forecastday>()
                dayList.addAll(result.body.forecast.forecastday)
                if (dayList.isEmpty()) {
                    ForecastViewRepresentation.Empty
                } else {
                    val adapter = ForecastAdapter()
                    adapter.forecastDays = dayList

                    ForecastViewRepresentation.ForecastViewRep(
                        ForecastViewData(
                            adapter = adapter
                        )
                    )
                }
            }
            else -> {
                ForecastViewRepresentation.Error
            }
        }
    }
}
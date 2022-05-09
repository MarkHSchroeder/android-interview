package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.adapter.AutocompleteAdapter
import com.png.interview.weather.ui.listener.AutocompleteOnClickListener
import com.png.interview.weather.ui.model.AutocompleteViewData
import com.png.interview.weather.ui.model.AutocompleteViewRepresentation
import javax.inject.Inject

interface CreateAutocompleteRepFromQueryUseCase {
    suspend operator fun invoke(query: String, listener: AutocompleteOnClickListener): AutocompleteViewRepresentation
}

class DefaultCreateAutocompleteRepFromQueryUseCase @Inject constructor(
    private val getAutocompleteDataUseCase: GetAutocompleteDataUseCase
) : CreateAutocompleteRepFromQueryUseCase {
    override suspend fun invoke(query: String, listener: AutocompleteOnClickListener): AutocompleteViewRepresentation {
        return when (val result = getAutocompleteDataUseCase(query)) {
            is NetworkResponse.Success -> {
                val responses = ArrayList<String>()
                for (location in result.body) {
                    if (responses.size < 5) {
                        responses.add("${location.name}, ${location.region}, ${location.country}")
                    } else {
                        break
                    }
                }

                if (responses.isEmpty()) {
                    AutocompleteViewRepresentation.Empty
                } else {
                    val autocompleteAdapter = AutocompleteAdapter()
                    autocompleteAdapter.autocompleteLocations = responses
                    autocompleteAdapter.listener = listener
                    AutocompleteViewRepresentation.AutocompleteViewRep(
                        AutocompleteViewData(
                            adapter = autocompleteAdapter
                        )
                    )
                }
            }
            else -> {
                AutocompleteViewRepresentation.Empty
            }
        }
    }
}
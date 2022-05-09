package com.png.interview.weather.ui.model

sealed class AutocompleteViewRepresentation {
    class AutocompleteViewRep(val data: AutocompleteViewData) : AutocompleteViewRepresentation()
    object Empty : AutocompleteViewRepresentation()
}

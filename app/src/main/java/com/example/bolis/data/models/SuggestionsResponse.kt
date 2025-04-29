package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class SuggestionsResponse(
    @SerializedName("suggestions")
    val suggestions: List<Suggestion>
)
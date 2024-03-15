package br.com.movieapp.core.data.remote.response

import br.com.movieapp.core.data.remote.model.MovieResult
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("results")
    val results: List<MovieResult>,
    @SerializedName("total_results")
    val totalResults: Int = 0
)
package br.com.movieapp.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("originalLanguage")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("genre_ids")
    val genreIds: List<Integer>?,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("vote_count")
    val voteCount: Int = 0
)
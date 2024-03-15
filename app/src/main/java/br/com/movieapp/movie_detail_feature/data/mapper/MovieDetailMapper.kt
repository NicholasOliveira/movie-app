package br.com.movieapp.movie_detail_feature.data.mapper

import br.com.movieapp.BuildConfig

fun String.toPostUrl() = "${BuildConfig.BASE_URL}this"
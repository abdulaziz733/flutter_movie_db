package com.aziz.flutter_movie_db.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MovieLocalDb:  RealmObject() {
    @SerializedName("overview")
    var overview: String = ""
    @SerializedName("original_language")
    var originalLanguage: String = ""
    @SerializedName("original_title")
    var originalTitle: String = ""
    @SerializedName("video")
    var video: Boolean = false
    @SerializedName("title")
    var title: String = ""
//    @SerializedName("genre_ids")
//    var genreIds: List<Integer>? = arrayListOf()
    @SerializedName("poster_path")
    var posterPath: String = ""
    @SerializedName("backdrop_path")
    var backdropPath: String = ""
    @SerializedName("release_date")
    var releaseDate: String = ""
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0
    @SerializedName("popularity")
    var popularity: Double = 0.0
    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("adult")
    var adult: Boolean = false
    @SerializedName("vote_count")
    var voteCount: Int = 0
}
package me.gookven.commonsense.api

import io.reactivex.Observable
import me.gookven.commonsense.api.dto.Comment
import retrofit2.http.GET

interface PlaceholderApi {
    @GET("comments")
    fun comments(): Observable<List<Comment>>
}
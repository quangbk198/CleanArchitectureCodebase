package com.quangnh.data.datasource.remote

import com.quangnh.data.datasource.remote.dto.WordDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by quangnh
 * Date: 31/1/2023
 * Time: 9:54 PM
 * Project DictionaryApp
 */
interface ApplicationApi {

    @GET("api/v2/entries/en-gb/{word}?fields=definitions&strictMatch=true")
    suspend fun getWordInfo(
        @Path("word") word: String
    ): WordDto

}
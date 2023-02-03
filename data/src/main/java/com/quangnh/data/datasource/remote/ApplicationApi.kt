package com.quangnh.data.datasource.remote

import com.quangnh.data.datasource.remote.api.Api
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

    @GET(Api.GET_WORD)
    suspend fun getWordInfo(
        @Path("word") word: String
    ): WordDto

}
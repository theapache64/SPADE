package com.theapache64.abcd.data.remote

import androidx.lifecycle.LiveData
import com.theapache64.abcd.data.remote.submitmap.SubmitMapResponse
import com.theapache64.abcd.data.remote.updaterandom.UpdateRandomResponse
import com.theapache64.twinkill.network.utils.Resource
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * To hold all API methods
 */
interface ApiInterface {

    @POST("nvidia_gaugan_submit_map")
    fun submitMap(
        @Query("imageBase64") imageBase64: String,
        @Query("name") name: String
    ): LiveData<Resource<SubmitMapResponse>>


    @FormUrlEncoded
    @POST("update_random")
    fun updateRandom(
        @Field("name") name: String
    ): LiveData<Resource<UpdateRandomResponse>>

}


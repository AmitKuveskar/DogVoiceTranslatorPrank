package com.example.dogvoicetranslatorprank

import com.example.dogvoicetranslatorprank.APICalling.Sounds.SoundsPojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("v3/dog_translator/all_sound_list")
    fun Sounds(): Call<SoundsPojo>
}
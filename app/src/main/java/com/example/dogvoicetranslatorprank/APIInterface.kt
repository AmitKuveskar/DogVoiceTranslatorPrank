package com.example.dogvoicetranslatorprank

import com.example.dogvoicetranslatorprank.APICalling.Sounds.SoundsPojo
import com.example.dogvoicetranslatorprank.APICalling.Tranings.TraningsPojo
import com.example.dogvoicetranslatorprank.APICalling.VoiceTranslator.VoiceTranslatorPojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("/v3/dog_translator/all_sound_list")
    fun Sounds(): Call<SoundsPojo>


    @GET("/v3/dog_translator/trainings_list")
    fun Training(): Call<TraningsPojo>

    @GET("/v3/dog_translator/voice_translator_list")
    fun Translate(): Call<VoiceTranslatorPojo>
}


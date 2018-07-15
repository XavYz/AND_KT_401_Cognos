package com.miramicodigo.restful_1.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.miramicodigo.restful_1.model.PokemonResponse

interface PokeInterface {

    @GET("pokemon")
    fun obtenerListaPokemon(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokemonResponse>

}
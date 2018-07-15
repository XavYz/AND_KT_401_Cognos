package com.miramicodigo.restful_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.miramicodigo.restful_1.model.PokemonResponse
import com.miramicodigo.restful_1.service.PokeInterface
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.GridLayoutManager
import com.miramicodigo.restful_1.adapter.PokemonAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var retrofit: Retrofit? = null
    var listaPokemonAdapter: PokemonAdapter? = null
    var cantidadLote: Int = 0
    var sePuedeCargar: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaPokemonAdapter = PokemonAdapter(this)
        recyclerView.adapter = listaPokemonAdapter
        recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    val visibleCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                    if (sePuedeCargar) {
                        if (visibleCount + pastVisibleItems >= totalItemCount) {
                            sePuedeCargar = false
                            cantidadLote += 20
                            obtenerDatos(cantidadLote)
                        }
                    }
                }
            }
        })

        retrofit = Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        sePuedeCargar = true
        cantidadLote = 0
        obtenerDatos(cantidadLote)
    }

    private fun obtenerDatos(offset: Int) {

        val service = retrofit!!.create(PokeInterface::class.java)
        val pokemonResponseCall = service.obtenerListaPokemon(20, offset)

        pokemonResponseCall.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                sePuedeCargar = true
                if (response.isSuccessful) {
                    val pokemonResponse = response.body()
                    val listaPokemon = pokemonResponse!!.results

                    listaPokemonAdapter!!.adicionarListaPokemon(listaPokemon!!)

                    for (poke in listaPokemon) {
                        println("----------> " + poke.name!!)
                        println("----------> " + poke.url!!)
                    }

                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {

            }
        })
    }

}
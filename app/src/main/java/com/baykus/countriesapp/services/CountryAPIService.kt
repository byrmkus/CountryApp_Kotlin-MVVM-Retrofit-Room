package com.baykus.countriesapp.service

import com.baykus.countriesapp.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {

    //BASE URL->/https://raw.githubusercontent.com
    //EXT-> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
   // http://www.bayramkus.com/countriesapp/countrydataset.json
    private val BASE_URL ="http://www.bayramkus.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountryAPI::class.java)

    fun getData():Single<List<Country>>{
        return api.getCountries()
    }
}
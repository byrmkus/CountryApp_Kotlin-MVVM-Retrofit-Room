package com.baykus.countriesapp.service

import com.baykus.countriesapp.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //GET verileri çekerken,POST veri değiştirirken kullanılır

    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    //BASE URL->/https://raw.githubusercontent.com
    //EXT-> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("countriesapp/countrydataset.json")
    fun getCountries():Single<List<Country>>     //Rxjava Single:bir deva işlemi yapar sonra durur


}
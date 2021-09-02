package com.baykus.countriesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baykus.countriesapp.model.Country

class InfoViewModel : ViewModel() {

    val countryInfoLiveData = MutableLiveData<Country>()

    fun getDataFromRoom() {

        val country = Country("Turkiye", "Asia", "Ankara", "TRY", "Turkish", "www.sfdsf.com")

        countryInfoLiveData.value = country

    }
}
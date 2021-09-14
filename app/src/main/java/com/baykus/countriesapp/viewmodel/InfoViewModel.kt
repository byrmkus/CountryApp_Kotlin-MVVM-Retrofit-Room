package com.baykus.countriesapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baykus.countriesapp.model.Country
import com.baykus.countriesapp.services.CountryDatabase
import kotlinx.coroutines.launch

class InfoViewModel(application: Application) : BaseViewModel(application) {

    val countryInfoLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int) {

        launch {

            val dao = CountryDatabase(getApplication()).countryDAO()
            val country = dao.getCountry(uuid)
            countryInfoLiveData.value = country

        }

    }
}
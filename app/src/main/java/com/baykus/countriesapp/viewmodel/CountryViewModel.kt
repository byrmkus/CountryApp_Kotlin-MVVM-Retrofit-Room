package com.baykus.countriesapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baykus.countriesapp.model.Country
import com.baykus.countriesapp.service.CountryAPIService
import com.baykus.countriesapp.services.CountryDatabase
import com.baykus.countriesapp.util.CustomSharedPreferences
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CountryViewModel(application: Application) : BaseViewModel(application) {

    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()          //hafızada yer tutmasını engelliyor  verimli kullanır
    private val customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {

        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {

            getDataFromSQLite()
        } else {
            getDataFromIPA()

        }
    }

     fun refreshFromAPI() {
        getDataFromIPA()
    }

    private fun getDataFromSQLite() {
        countryLoading.value = true

        launch {
            val countries = CountryDatabase(getApplication()).countryDAO().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(), "Country From SQLite", Toast.LENGTH_LONG).show()
        }
    }

    fun getDataFromIPA() {
        countryLoading.value = true
        disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        storeInSqlite(t)
                        Toast.makeText(getApplication(), "Country From API", Toast.LENGTH_LONG)
                            .show()

                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryLoading.value = true
                        e.printStackTrace()
                    }

                })
        )

    }

    private fun showCountries(countryList: List<Country>) {
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

    private fun storeInSqlite(list: List<Country>) {                        //Main de çalıimayacak arka planda çalıiacak o yüzden coroutine kullanıcaz
        launch {
            val dao = CountryDatabase(getApplication()).countryDAO()
            dao.deleteAllCountries()
            var listLong = dao.insertAll(*list.toTypedArray())                         //toTypedArray bir listeyi tekil(individual) elemanlar haline getirir

            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i = i + 1
            }

            showCountries(list)
        }

        customPreferences.saveTime((System.nanoTime()))

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}
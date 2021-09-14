package com.baykus.countriesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {


    private val job = Job()

    override val coroutineContext: CoroutineContext              //Coroutine ne yapazağını söylücez

        get() = job + Dispatchers.Main


    override fun onCleared() {                                   // app kapatılırsa iş parçacığımızda iptal edilecek
        super.onCleared()
        job.cancel()
    }
}
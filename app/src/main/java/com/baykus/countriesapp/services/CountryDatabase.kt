package com.baykus.countriesapp.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baykus.countriesapp.model.Country


@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase :RoomDatabase(){

    abstract fun countryDAO(): CountryDAO

    //Singleton
    companion object {                                                                               //static metot gibi çalışır class oruşturulduğunda  direk çalışır

        @Volatile                                                                                //volatile -> geçiçi anlamı var farklı threadlere görünür hala getirir
        private var instance: CountryDatabase? = null

        private val lock = Any()
                                                                                                //instance var mı kontrol ediyoruz yoksa makeDatabase fonksiyonunu çağırıyoruz
                                                                                                //singleton patern kullanıyoruz
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {                //invoke->Çağırmak,yarvarmak

            instance ?: makeDatabase(context).also {
                instance = it
            }

        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(

            context.applicationContext,CountryDatabase::class.java,"countrydatabase"
        ).build()
    }



}
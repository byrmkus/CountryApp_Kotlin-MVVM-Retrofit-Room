package com.baykus.countriesapp.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.baykus.countriesapp.model.Country
@Dao
interface CountryDAO {
    //Data Access Object

    @Insert
    suspend fun insertAll(vararg countries:Country):List<Long>

    //suspend -> coroutine içerisinde çağrılan foksiyonu durdurup devam ettirmeye olanak sağlar

    //vararg -> (variable number of arguments) "multible country objects" farklı sayıda veri ekleriz
    //List<Long> -> primary keys döndürüyor

    @Query("SELECT * FROM country")
    suspend fun getAllCountries():List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun  getCountry(countryId : Int) : Country

    @Query("Delete from country")
    suspend fun deleteAllCountries()

}
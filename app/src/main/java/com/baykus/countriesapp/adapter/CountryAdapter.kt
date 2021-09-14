package com.baykus.countriesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.baykus.countriesapp.view.CountriesFragmentDirections
import com.baykus.countriesapp.R
import com.baykus.countriesapp.databinding.ItemCountryBinding
import com.baykus.countriesapp.model.Country
import com.baykus.countriesapp.util.downloadFromUrlImage
import com.baykus.countriesapp.util.placeHolderProgresBar

import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {


    class CountryViewHolder(var binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
       // val view = inflater.inflate(R.layout.item_country, parent, false)
        val binding =DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)
        return CountryViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.binding.country = countryList[position]
        holder.binding.listener = this



/*
        holder.view.name.text = countryList[position].countryName
        holder.view.region.text = countryList[position].countryRegion

        holder.view.setOnClickListener {
           val action = CountriesFragmentDirections.actionCountriesFragmentToInfoFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)

        }

        holder.view.countryImage.downloadFromUrlImage(
            countryList[position].ImageUrl,
            placeHolderProgresBar(holder.view.context)
        )
*/
    }


    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val action = CountriesFragmentDirections.actionCountriesFragmentToInfoFragment(v.countryUuidText.text.toString().toInt())
        Navigation.findNavController(v).navigate(action)


    }
}
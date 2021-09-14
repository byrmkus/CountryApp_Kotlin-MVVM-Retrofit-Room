package com.baykus.countriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import androidx.recyclerview.widget.LinearLayoutManager
import com.baykus.countriesapp.R
import com.baykus.countriesapp.adapter.CountryAdapter
import com.baykus.countriesapp.databinding.FragmentCountriesBinding
import com.baykus.countriesapp.viewmodel.CountryViewModel


class CountriesFragment : Fragment() {

    private lateinit var binding: FragmentCountriesBinding

    private lateinit var viewModel: CountryViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      binding=DataBindingUtil.inflate(inflater, R.layout.fragment_countries,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.refreshData()

        binding.countryList.layoutManager = LinearLayoutManager(context)
        binding.countryList.adapter = countryAdapter


        binding.swipeRefreshCountry.setOnRefreshListener {
            binding.countryList.visibility=View.GONE
            binding.countryError.visibility=View.GONE
            binding.countryLoading.visibility=View.VISIBLE
            viewModel.refreshFromAPI()
            binding.swipeRefreshCountry.isRefreshing=false
        }



        observeLiveData()

    }

    fun observeLiveData() {

        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                binding.countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })


        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->

            error?.let {
                if (it) {
                    binding.countryError.visibility = View.VISIBLE
                } else {
                    binding.countryError.visibility = View.GONE

                }
            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    binding.countryLoading.visibility = View.VISIBLE
                    binding.countryList.visibility = View.GONE
                    binding.countryError.visibility = View.GONE
                } else
                    binding.countryLoading.visibility = View.GONE

            }


        })
    }

}
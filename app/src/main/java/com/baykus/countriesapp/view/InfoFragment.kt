package com.baykus.countriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.baykus.countriesapp.databinding.FragmentInfoBinding
import com.baykus.countriesapp.viewmodel.InfoViewModel


class InfoFragment : Fragment() {

    private lateinit var viewModel: InfoViewModel
    private lateinit var binding: FragmentInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(InfoViewModel::class.java)
        viewModel.getDataFromRoom()
    }

    private fun observeLiveData() {
        viewModel.countryInfoLiveData.observe(viewLifecycleOwner, Observer { conutry ->
            conutry?.let {
                binding.countryName.text = conutry.countryName
                binding.countryCapital.text = conutry.countryCapital
                binding.countryCurrency.text = conutry.countryCurrency
                binding.countryLanguage.text = conutry.countryLanguage
                binding.countryRegion.text = conutry.countryRegion
            }

        })
    }


}
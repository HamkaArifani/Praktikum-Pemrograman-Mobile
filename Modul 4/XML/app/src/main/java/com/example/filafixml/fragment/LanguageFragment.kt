package com.example.filafixml.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.filafixml.R
import com.example.filafixml.databinding.FragmentlanguageBinding
import com.example.filafixml.viewmodel.LanguageViewModel
import com.example.filafixml.viewmodel.LanguageViewModelFactory

class LanguageFragment : Fragment(R.layout.fragmentlanguage) {
    private val viewModel : LanguageViewModel by viewModels {
        LanguageViewModelFactory(getString(R.string.languagepage))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentlanguageBinding.bind(view)

        binding.rbEnglish.setOnClickListener { changeLanguage("en") }
        binding.rbIndonesia.setOnClickListener { changeLanguage("in") }

        binding.headerBackLanguage.btnBack.setOnClickListener { findNavController().navigateUp() }

        Toast.makeText(requireContext(), viewModel.pageInfo, Toast.LENGTH_SHORT).show()
    }

    fun changeLanguage(languageCode : String){
        val appLocale : LocaleListCompat = LocaleListCompat.forLanguageTags(languageCode)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

}
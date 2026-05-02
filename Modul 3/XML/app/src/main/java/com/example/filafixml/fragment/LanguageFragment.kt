package com.example.filafixml.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.filafixml.R
import com.example.filafixml.databinding.FragmentlanguageBinding

class LanguageFragment : Fragment(R.layout.fragmentlanguage) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentlanguageBinding.bind(view)

        binding.rbEnglish.setOnClickListener { changeLanguage("en") }
        binding.rbIndonesia.setOnClickListener { changeLanguage("in") }

        binding.headerBackLanguage.btnBack.setOnClickListener { findNavController().navigateUp() }
    }

    fun changeLanguage(languageCode : String){
        val appLocale : LocaleListCompat = LocaleListCompat.forLanguageTags(languageCode)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

}
package com.emmanuelamet.covid19tracker.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emmanuelamet.covid19tracker.DetailFragmentArgs
import com.emmanuelamet.covid19tracker.R
import com.emmanuelamet.covid19tracker.utils.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            val covid = DetailFragmentArgs.fromBundle(it).covidArgs
            country.text = "Country: ${covid[0]}"
            loadImage(country_flag, covid[1])
            todayCases.text = "Today Cases: ${covid[2]}"
            deaths.text = "Deaths: ${covid[3]}"
            todayDeaths.text = "Today Deaths: ${covid[4]}"
            recovered.text = "Recovered: ${covid[5]}"
            todayRecovered.text = "Today Recovered: ${covid[6]}"
            active.text = "Active: ${covid[7]}"
            critical.text = "Critical: ${covid[8]}"
            casesPerOneMillion.text = "Cases Per One Million: ${covid[9]}"
            deathsPerOneMillion.text = "Deaths Per One Million: ${covid[10]}"
            tests.text = "Tests: ${covid[11]}"
            testsPerOneMillion.text = "Tests Per One Million: ${covid[12]}"
            population.text = "Population: ${covid[13]}"
            continent.text = "Continent: ${covid[14]}"
            oneCasePerPeople.text = "One Case Per People: ${covid[15]}"
            oneDeathPerPeople.text = "One Death Per People: ${covid[16]}"
            oneTestPerPeople.text = "One Test Per People: ${covid[17]}"
            activePerOneMillion.text = "Active Per One Million: ${covid[18]}"
            recoveredPerOneMillion.text = "Recovered Per One Million: ${covid[19]}"
            criticalPerOneMillion.text = "Critical Per One Million: ${covid[20]}"
        }
    }
}
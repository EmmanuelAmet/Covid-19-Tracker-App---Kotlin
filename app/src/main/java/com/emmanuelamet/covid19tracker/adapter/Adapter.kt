package com.emmanuelamet.covid19tracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.emmanuelamet.covid19tracker.ListFragmentDirections
import com.emmanuelamet.covid19tracker.R
import com.emmanuelamet.covid19tracker.model.Case
import com.emmanuelamet.covid19tracker.model.countryInfo
import com.emmanuelamet.covid19tracker.utils.loadImage
import kotlinx.android.synthetic.main.item.view.*

class Adapter: RecyclerView.Adapter<Adapter.MyViewHolder>() {
    var myList = emptyList<Case>()
    fun setData(newList: List<Case>){
        myList = newList
        notifyDataSetChanged()
    }
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.country_item.text = myList[position].country
        holder.itemView.continent_item.text = myList[position].todayCases
//        holder.itemView.death_item.text = myList[position].deaths
//        holder.itemView.active_cases_item.text = myList[position].active
        val targetImage = holder.itemView.flage_image
        loadImage(targetImage, myList[position].countryInfo.flag)

        holder.itemView.setOnClickListener {
            val country = myList[position].country
            val countryInfo = myList[position].countryInfo.flag
            val todayCases = myList[position].todayCases
            val deaths = myList[position].deaths
            val todayDeaths = myList[position].todayDeaths
            val recovered = myList[position].recovered
            val todayRecovered= myList[position].todayRecovered
            val active= myList[position].active
            val critical= myList[position].critical
            val casesPerOneMillion = myList[position].casesPerOneMillion
            val deathsPerOneMillion = myList[position].deathsPerOneMillion
            val tests = myList[position].tests
            val testsPerOneMillion = myList[position].testsPerOneMillion
            val population = myList[position].population
            val continent = myList[position].continent
            val oneCasePerPeople = myList[position].oneCasePerPeople
            val oneDeathPerPeople = myList[position].oneDeathPerPeople
            val oneTestPerPeople = myList[position].oneTestPerPeople
            val activePerOneMillion = myList[position].activePerOneMillion
            val recoveredPerOneMillion = myList[position].recoveredPerOneMillion
            val criticalPerOneMillion = myList[position].criticalPerOneMillion
            val action =ListFragmentDirections.actionListFragmentToDetailFragment(
                arrayOf(country, countryInfo, todayCases, deaths, todayDeaths, recovered, todayRecovered, active,
                    critical, casesPerOneMillion, deathsPerOneMillion, tests, testsPerOneMillion, population,
                    continent, oneCasePerPeople, oneDeathPerPeople, oneTestPerPeople, activePerOneMillion,
                    recoveredPerOneMillion, criticalPerOneMillion)
            )
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return myList.size
    }


}
package com.emmanuelamet.covid19tracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.emmanuelamet.covid19tracker.ListFragmentDirections
import com.emmanuelamet.covid19tracker.R
import com.emmanuelamet.covid19tracker.model.Case
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
        holder.itemView.death_item.text = myList[position].deaths
        holder.itemView.active_cases_item.text = myList[position].active
        val targetImage = holder.itemView.flage_image
        loadImage(targetImage, myList[position].countryInfo.flag)

        holder.itemView.setOnClickListener {
            val action =ListFragmentDirections.actionListFragmentToDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return myList.size
    }


}
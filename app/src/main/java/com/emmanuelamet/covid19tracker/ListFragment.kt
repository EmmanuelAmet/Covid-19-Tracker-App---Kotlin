package com.emmanuelamet.covid19tracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.emmanuelamet.covid19tracker.adapter.Adapter
import com.emmanuelamet.covid19tracker.repository.Repository
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    companion object{
        private lateinit var viewModel: MainViewModel
        private val adapter by lazy{Adapter()}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try{
            loadData()
            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            viewModel.getAllCases()
            viewModel.myResponse.observe(this, Observer { response->
                response.body()?.let { adapter.setData(it) }
                if(response.isSuccessful){
                    Log.d("Response", response.body().toString())
                    Log.d("Response", response.code().toString())
                    response.body()?.forEach {
                        Log.d("Response", it.country)
                        Log.d("Response", it.active)
                        Log.d("Response", it.deaths)
                        Log.d("Response", it.continent)
                        Log.d("Response", it.countryInfo.flag)
                        Log.d("Response", "----------------------")
                    }
                }else{
                    Toast.makeText(context, response.code().toString(), Toast.LENGTH_LONG).show()
                }
            })
        }catch (e:Exception){

        }

    }

    private fun loadData(){
        recyclerView_all.adapter = adapter
    }
    /*
    private fun splash(){
        button.alpha = 0f
        button.animate().setDuration(3000).alpha(1f).withEndAction {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment()
            Navigation.findNavController(button).navigate(action)
        }
    }

     */

}
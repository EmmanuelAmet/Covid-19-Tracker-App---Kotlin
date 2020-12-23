package com.emmanuelamet.covid19tracker.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.emmanuelamet.covid19tracker.R
import com.emmanuelamet.covid19tracker.adapter.Adapter
import com.emmanuelamet.covid19tracker.adapter.AdapterCountry
import com.emmanuelamet.covid19tracker.repository.Repository
import com.emmanuelamet.covid19tracker.viewModel.MainViewModel
import com.emmanuelamet.covid19tracker.viewModelFactory.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*

class ListFragment : Fragment(), View.OnClickListener {

    companion object{
        private lateinit var viewModel: MainViewModel
        private val adapter by lazy{Adapter()}
        private val adapterCountry by lazy{AdapterCountry()}
        private val RQ_SPEECH_REC = 102
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
            loadRepository()
        }catch (e:Exception){
            errorToastMessage()
        }

        img_search_by_speech.setOnClickListener(this)
        search_editText.setOnClickListener(this)
        btn_search.setOnClickListener(this)
        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            try{
                loadRepository()
            }catch(e:Exception){
                errorToastMessage()
            }
        }
    }

    private fun loadRepository(){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAllCases()
        viewModel.myResponse.observe(this, Observer { response->
            response.body()?.let { adapter.setData(it) }
            if(response.isSuccessful){
                progressBar_loading.visibility = View.GONE
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
                errorToastMessage()
            }
        })
    }

    private fun filterRepository(country:String){
        try{
            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            viewModel.getCountry(country)
            viewModel.myCountryResponse.observe(this, Observer { response->

                if(response.isSuccessful){
                    progressBar_loading.visibility = View.GONE
                    Log.d("Response", response.body().toString())
                    Log.d("Response", response.code().toString())
                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context, response.code().toString(), Toast.LENGTH_LONG).show()
                    errorToastMessage()
                }
            })
        }catch (e:Exception){
            errorToastMessage()
        }
    }

    private fun errorToastMessage(){
        Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_LONG).show()
    }
    private fun loadData(){
        recyclerView_all.adapter = adapter
    }

    private fun loadDataCountry(){
        recyclerView_all.adapter = adapterCountry
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            //search_location.editableText = //result?.get(0).toString()
            search_editText.setText( result?.get(0).toString())
        }
    }

    private fun askSpeechInput(){
        if(!SpeechRecognizer.isRecognitionAvailable(context)){
            Toast.makeText(context, "Speech recognizer is not available", Toast.LENGTH_LONG).show()
        }else{
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something!")
            startActivityForResult(i, RQ_SPEECH_REC)
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.img_search_by_speech ->{
                try {
                    askSpeechInput()
                }catch(e:Exception){
                    errorToastMessage()
                }
            }
            R.id.btn_search ->{
                if(TextUtils.isEmpty(search_editText.text)){
                    Toast.makeText(context, "Enter a country", Toast.LENGTH_LONG).show()
                }else{
                    val country = search_editText.text.toString().trim()
                    filterRepository(country)
                }
            }
        }
    }

}
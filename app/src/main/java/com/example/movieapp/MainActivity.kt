package com.example.movieapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.model.Search
import com.example.movieapp.ui.adapter.MovieAdapter
import com.example.movieapp.ui.adapter.MovieClickListener
import com.example.movieapp.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private  val viewModel by viewModels<MovieViewModel>()
    private val adapter by lazy {
        MovieAdapter(object :MovieClickListener{
            override fun movieClickListener(imdbID: String) {
                val intent=Intent(this@MainActivity,MovieDetailActivity::class.java)
                intent.putExtra("imdbId",imdbID)
                startActivity(intent)
            }

        },emptyList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rv.layoutManager = GridLayoutManager(this,2)
        binding.rv.adapter=adapter

        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {

                if (query.trim()!=""){
                    viewModel.getMovie(query)
                }

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                if (newText.trim()==""){
                    adapter.update(emptyList())
                  }
            return false
    }


})

        viewModel.moviewList.observe(this){
            adapter.update(it)
            Log.e("Search",it.toString())
        }
    }

}
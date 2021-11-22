package com.uriolus.mvvbeers.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.uriolus.mvvbeers.databinding.ActivityMainBinding
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.presentation.viewmodel.MainViewModel
import com.uriolus.mvvbeers.presentation.wathceslistview.BeersAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpRecyclerView()
        setUpListeners()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.getAllBeersFlow()
                        .collect { beers: List<Beer> ->
                            (binding.beers.adapter as BeersAdapter).updateBeers(beers)
                        }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.beers.layoutManager = linearLayoutManager
        binding.beers.adapter = BeersAdapter(this, lifecycleScope)
    }

    private fun setUpListeners() {

    }
}
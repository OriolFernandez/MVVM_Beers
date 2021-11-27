package com.uriolus.mvvbeers.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.uriolus.mvvbeers.databinding.ActivityMainBinding
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.presentation.model.MainState
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
        initViewModel()
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.getMainStateFlow()
                        .collect { mainState ->
                            when (mainState) {
                                MainState.Idle -> {
                                    println("State:Idle")
                                    viewModel.requestAllBeers()}
                                MainState.Loading -> println("State:Loading") // TODO
                                is MainState.Loaded -> {
                                    println("State:Loaded")
                                    showBeers(mainState.beers)}
                                MainState.Empty -> println("State:Empty") // TODO
                            }
                        }
                }
            }
        }
    }

    private fun showBeers(beers: List<Beer>) {
        (binding.beers.adapter as BeersAdapter).updateBeers(beers)
    }

    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.beers.layoutManager = linearLayoutManager
        binding.beers.adapter = BeersAdapter(this, lifecycleScope)
    }
}
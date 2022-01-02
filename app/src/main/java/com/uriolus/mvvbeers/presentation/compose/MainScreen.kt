package com.uriolus.mvvbeers.presentation.compose

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.presentation.model.MainState
import com.uriolus.mvvbeers.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = getViewModel()
    val state: MainState by remember(viewModel) { viewModel.mainScreenState }.collectAsState()

    Scaffold(
        content = {
            state.let { state: MainState ->
                when (state) {
                    MainState.Empty -> println("State $state")
                    MainState.Idle -> println("State $state")
                    is MainState.Loaded -> {
                        println("State $state")
                        val list: List<Beer> = state.beers
                        if (list.isNotEmpty()) {
                            BeerList(list)
                        }
                    }
                    MainState.Loading -> {
                        println("State $state")
                        Loading()
                    }
                    is MainState.Error -> println("State $state ${state.error}")
                }
            }
        })
}

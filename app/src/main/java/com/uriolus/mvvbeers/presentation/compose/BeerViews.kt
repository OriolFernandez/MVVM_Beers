package com.uriolus.mvvbeers.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.uriolus.mvvbeers.R
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.domain.model.BeerId

@Composable
internal fun BeerList(beers: List<Beer>) {

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(beers) { beer ->
            BeerRow(beer) { beerId: BeerId ->
                println("Beer clicker:${beerId.s}")
            }
        }
    }

}

@Composable
internal fun BeerRow(beer: Beer, onBeerClick: (BeerId) -> Unit) {

    Row(
        modifier = Modifier
            .clickable(onClick = { onBeerClick(beer.id) })
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = beer.imageUrl,
                onExecute = ImagePainter.ExecuteCallback { _, _ -> true },
                builder = {
                    crossfade(true)
                    scale(Scale.FIT)
                    placeholder(R.drawable.default_beer)
                }

            ),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Column {
            Text(beer.name)
            Text(beer.description ?: "")
        }
    }

}
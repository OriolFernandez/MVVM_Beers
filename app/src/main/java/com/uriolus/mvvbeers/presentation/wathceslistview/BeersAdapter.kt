package com.uriolus.mvvbeers.presentation.wathceslistview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uriolus.mvvbeers.databinding.BeerItemBinding
import com.uriolus.mvvbeers.domain.model.Beer

class BeersAdapter(private val context: Context, private val scope: LifecycleCoroutineScope) :
    RecyclerView.Adapter<BeerViewHolder>() {
    private val beers: MutableList<Beer> = mutableListOf()
    fun updateBeers(beers: List<Beer>) {
        this.beers.clear()
        this.beers.addAll(beers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val binding = BeerItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return BeerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        beers[position].let { beer: Beer ->
            holder.binding.beerName.text = beer.name
            loadImageWithPicasso(holder.binding.beerImage, beer.imageUrl)
            holder.binding.beerDescription.text = beer.description
        }
    }

    private fun loadImageWithPicasso(imageView: ImageView, url: String?) {
        Picasso.get()
            .load(url)
            .resize(300, 300)
            .centerInside()
            .into(imageView)
    }

    override fun getItemCount(): Int {
        return beers.size
    }
}
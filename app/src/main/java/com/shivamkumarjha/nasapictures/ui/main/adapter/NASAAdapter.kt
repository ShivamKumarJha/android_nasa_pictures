package com.shivamkumarjha.nasapictures.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import com.shivamkumarjha.nasapictures.R
import com.shivamkumarjha.nasapictures.model.NASA

class NASAAdapter : RecyclerView.Adapter<NASAAdapter.NASAViewHolder>() {

    private var nasa: List<NASA> = arrayListOf()

    override fun getItemCount(): Int = nasa.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NASAViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nasa, parent, false)
        return NASAViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NASAViewHolder, position: Int) {
        holder.bind(nasa[position])
    }

    fun setNASA(nasa: ArrayList<NASA>) {
        this.nasa = nasa.sortedByDescending { it.date }
        notifyDataSetChanged()
    }

    inner class NASAViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val card: MaterialCardView = itemView.findViewById(R.id.card)
        private val image: ImageView = itemView.findViewById(R.id.nasa_iv)
        private val title: TextView = itemView.findViewById(R.id.title_tv)
        private val time: TextView = itemView.findViewById(R.id.time_tv)

        fun bind(nasa: NASA) {
            title.text = nasa.title
            time.text = nasa.date
            Glide.with(image.context)
                .load(nasa.url)
                .placeholder(R.drawable.ic_image_placeholder)
                .apply(RequestOptions.centerCropTransform())
                .into(image)
        }
    }
}
package com.shivamkumarjha.nasapictures.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import com.shivamkumarjha.nasapictures.databinding.ItemNasaBinding
import com.shivamkumarjha.nasapictures.model.NASA

class NASAAdapter(private val clickListener: NASAClickListener) :
    RecyclerView.Adapter<NASAAdapter.NASAViewHolder>() {

    private var nasa: List<NASA> = arrayListOf()

    override fun getItemCount(): Int = nasa.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NASAViewHolder {
        val binding = ItemNasaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NASAViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NASAViewHolder, position: Int) {
        holder.bind(position)
    }

    fun getNASA() = nasa

    fun setNASA(nasa: List<NASA>) {
        this.nasa = nasa
        notifyDataSetChanged()
    }

    inner class NASAViewHolder(binding: ItemNasaBinding) : RecyclerView.ViewHolder(binding.root) {
        private val card: MaterialCardView = binding.card
        private val image: ImageView = binding.nasaIv
        private val title: TextView = binding.titleTv
        private val time: TextView = binding.timeTv
        private var circularProgressDrawable: CircularProgressDrawable =
            CircularProgressDrawable(image.context)

        init {
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
        }

        fun bind(position: Int) {
            card.setOnClickListener {
                clickListener.onCardClick(position)
            }
            title.text = nasa[position].title
            time.text = nasa[position].date
            Glide.with(image.context)
                .load(nasa[position].url)
                .placeholder(circularProgressDrawable)
                .apply(RequestOptions.centerCropTransform())
                .into(image)
        }
    }
}
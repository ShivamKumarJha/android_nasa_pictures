package com.shivamkumarjha.nasapictures.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.shivamkumarjha.nasapictures.databinding.FragmentDetailBinding
import com.shivamkumarjha.nasapictures.model.NASA
import com.shivamkumarjha.nasapictures.ui.SharedViewModel
import com.shivamkumarjha.nasapictures.ui.detail.adapter.SlidesAdapter
import com.shivamkumarjha.nasapictures.utility.ZoomOutPageTransformer
import com.shivamkumarjha.nasapictures.utility.onPageSelected

class DetailFragment : Fragment() {
    //Views
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewPager: ViewPager
    private lateinit var slidesAdapter: SlidesAdapter

    //ViewModel
    private val viewModel: SharedViewModel by activityViewModels()

    //Navigation arguments
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = binding.slidesViewPager
        observer()
    }

    private fun observer() {
        viewModel.images.observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty()) {
                updateViewPager(it)
            }
        })
    }

    private fun updateViewPager(data: List<NASA>) {
        slidesAdapter = SlidesAdapter(data)
        viewPager.adapter = slidesAdapter
        viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        val currentPosition = args.slidePosition
        viewPager.currentItem = currentPosition
        (activity as AppCompatActivity).supportActionBar?.title = data[currentPosition].title
        viewPager.onPageSelected { position ->
            (activity as AppCompatActivity).supportActionBar?.title = data[position].title
        }
    }
}
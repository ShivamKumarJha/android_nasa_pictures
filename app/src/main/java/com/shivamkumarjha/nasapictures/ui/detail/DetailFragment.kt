package com.shivamkumarjha.nasapictures.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.shivamkumarjha.nasapictures.R
import com.shivamkumarjha.nasapictures.config.Constants
import com.shivamkumarjha.nasapictures.databinding.FragmentDetailBinding
import com.shivamkumarjha.nasapictures.network.Status
import com.shivamkumarjha.nasapictures.ui.SharedViewModel
import com.shivamkumarjha.nasapictures.ui.detail.adapter.SlidesAdapter

class DetailFragment : Fragment() {
    //Views
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewPager: ViewPager
    private lateinit var slidesAdapter: SlidesAdapter

    private val viewModel: SharedViewModel by activityViewModels()

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
        viewModel.nasa.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    Status.SUCCESS -> {
                        if (!it.data.isNullOrEmpty()) {
                            slidesAdapter = SlidesAdapter(it.data)
                            viewPager.adapter = slidesAdapter
                            viewPager.currentItem = arguments?.getInt(Constants.SLIDE_POSITION) ?: 0
                        }
                    }
                    Status.ERROR -> {
                        val bundle = bundleOf(Constants.ERROR_MESSAGE to it.message)
                        findNavController().navigate(R.id.action_global_errorDialog, bundle)
                    }
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.OFFLINE -> {
                    }
                }
                if (it.status != Status.LOADING) {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }
}
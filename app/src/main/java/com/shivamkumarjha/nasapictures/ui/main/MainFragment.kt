package com.shivamkumarjha.nasapictures.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shivamkumarjha.nasapictures.R
import com.shivamkumarjha.nasapictures.config.Constants
import com.shivamkumarjha.nasapictures.databinding.FragmentMainBinding
import com.shivamkumarjha.nasapictures.network.ConnectionLiveData
import com.shivamkumarjha.nasapictures.network.Status
import com.shivamkumarjha.nasapictures.ui.SharedViewModel
import com.shivamkumarjha.nasapictures.ui.main.adapter.NASAAdapter
import com.shivamkumarjha.nasapictures.ui.main.adapter.NASAClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    //Views
    private lateinit var binding: FragmentMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var nasaAdapter: NASAAdapter
    private lateinit var snackbar: Snackbar

    private val viewModel: SharedViewModel by activityViewModels()

    @Inject
    lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        observer()
    }

    override fun onDestroyView() {
        snackbar.dismiss()
        super.onDestroyView()
    }

    private fun setViews() {
        //Recycler view
        nasaAdapter = NASAAdapter(getClickListener())
        recyclerView = binding.recyclerView
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = nasaAdapter
        }
        //SnackBar
        snackbar = Snackbar.make(
            binding.rootLayout,
            resources.getString(R.string.no_internet_connection),
            Snackbar.LENGTH_INDEFINITE
        ).setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.red_500))
    }

    private fun observer() {
        viewModel.nasa.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    Status.SUCCESS -> {
                        if (!it.data.isNullOrEmpty()) {
                            nasaAdapter.setNASA(it.data)
                        }
                    }
                    Status.ERROR -> {
                        val bundle = bundleOf(Constants.ERROR_MESSAGE to it.message)
                        findNavController().navigate(R.id.action_global_errorDialog, bundle)
                    }
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.OFFLINE -> {
                        //Empty as we have ConnectionLiveData here
                    }
                }
                if (it.status != Status.LOADING) {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
        connectionLiveData.observe(viewLifecycleOwner, {
            if (it && nasaAdapter.getNASA().isNullOrEmpty()) {
                viewModel.getData()
            }
            if (it) {
                snackbar.dismiss()
            } else {
                snackbar.show()
            }
        })
    }

    private fun getClickListener(): NASAClickListener {
        return object : NASAClickListener {
            override fun onCardClick(position: Int) {
                if (findNavController().currentDestination?.id == R.id.mainFragment) {
                    val action = MainFragmentDirections.actionMainFragmentToDetailFragment(position)
                    findNavController().navigate(action)
                }
            }

            override fun updateBookmark(isBookmarked: Boolean, url: String) {
                viewModel.updateBookmark(isBookmarked, url)
            }
        }
    }
}
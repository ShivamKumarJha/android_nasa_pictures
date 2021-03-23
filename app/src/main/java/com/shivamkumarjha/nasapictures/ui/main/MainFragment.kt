package com.shivamkumarjha.nasapictures.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.shivamkumarjha.nasapictures.R
import com.shivamkumarjha.nasapictures.databinding.FragmentMainBinding
import com.shivamkumarjha.nasapictures.network.ConnectionLiveData
import com.shivamkumarjha.nasapictures.ui.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    //Views
    private lateinit var binding: FragmentMainBinding

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
        observer()
    }

    private fun observer() {
        connectionLiveData.observe(viewLifecycleOwner, {
            if (it)
                viewModel.getData()
            else
                findNavController().navigate(R.id.action_global_offlineDialog)
        })
    }
}
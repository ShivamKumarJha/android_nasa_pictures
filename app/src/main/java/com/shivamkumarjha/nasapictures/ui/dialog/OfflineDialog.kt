package com.shivamkumarjha.nasapictures.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.shivamkumarjha.nasapictures.databinding.DialogOfflineBinding
import com.shivamkumarjha.nasapictures.network.ConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OfflineDialog : DialogFragment() {

    private lateinit var binding: DialogOfflineBinding

    @Inject
    lateinit var connectionLiveData: ConnectionLiveData

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DialogOfflineBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeConnection()
    }

    private fun observeConnection() {
        connectionLiveData.observe(viewLifecycleOwner, {
            if (it) {
                dismiss()
            }
        })
    }
}
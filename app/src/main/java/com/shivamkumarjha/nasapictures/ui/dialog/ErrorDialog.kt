package com.shivamkumarjha.nasapictures.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.shivamkumarjha.nasapictures.config.Constants
import com.shivamkumarjha.nasapictures.databinding.DialogErrorBinding

class ErrorDialog : DialogFragment() {

    private lateinit var binding: DialogErrorBinding
    private lateinit var messageTextView: TextView
    private lateinit var doneButton: MaterialButton

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        dialog?.window?.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DialogErrorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set views
        messageTextView = binding.errorTextView
        messageTextView.text = arguments?.getString(Constants.ERROR_MESSAGE)
        doneButton = binding.doneButton
        doneButton.setOnClickListener {
            dismiss()
        }
    }
}
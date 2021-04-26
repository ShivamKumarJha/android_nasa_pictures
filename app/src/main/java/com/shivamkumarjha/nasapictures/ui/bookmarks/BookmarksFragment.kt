package com.shivamkumarjha.nasapictures.ui.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.nasapictures.databinding.FragmentBookmarksBinding
import com.shivamkumarjha.nasapictures.ui.SharedViewModel
import com.shivamkumarjha.nasapictures.ui.main.adapter.NASAAdapter
import com.shivamkumarjha.nasapictures.ui.main.adapter.NASAClickListener

class BookmarksFragment : Fragment() {
    //Views
    private lateinit var binding: FragmentBookmarksBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var nasaAdapter: NASAAdapter

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBookmarksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        observer()
    }

    private fun setViews() {
        //Recycler view
        nasaAdapter = NASAAdapter(getClickListener())
        recyclerView = binding.recyclerView
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = nasaAdapter
        }
    }

    private fun observer() {
        viewModel.getBookmarkedList()
        viewModel.bookmarks.observe(viewLifecycleOwner, { bookmarks ->
            bookmarks?.let {
                nasaAdapter.setNASA(bookmarks)
            }
        })
    }

    private fun getClickListener(): NASAClickListener {
        return object : NASAClickListener {
            override fun onCardClick(position: Int) {
                val action =
                    BookmarksFragmentDirections.actionBookmarksFragmentToDetailFragment(position)
                findNavController().navigate(action)
            }

            override fun updateBookmark(isBookmarked: Boolean, url: String) {
                viewModel.updateBookmark(isBookmarked, url)
            }
        }
    }
}
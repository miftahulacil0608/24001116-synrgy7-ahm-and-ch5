package com.example.recyclerviewwithnavigationcomponent.ui.mvvm.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.DataItemCollections
import com.example.recyclerviewwithnavigationcomponent.databinding.FragmentDetailMovieBinding
import com.example.recyclerviewwithnavigationcomponent.ui.adapter.DetailTeamsRecyclerview
import com.example.recyclerviewwithnavigationcomponent.ui.mvvm.SharedViewModel

class DetailMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailMovieBinding

    private val viewModel: SharedViewModel by activityViewModels<SharedViewModel> {
        SharedViewModel.provideFactory(this, requireContext())
    }

    private lateinit var recyclerAdapter: DetailTeamsRecyclerview

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailMovieBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDisplayDataDetailMovie()
    }

    private fun setDisplayDataDetailMovie() {
        viewModel.dataCollections.observe(viewLifecycleOwner) {
            recyclerAdapter.submitList(it)
        }
        viewModel.dataDetailMovie.observe(viewLifecycleOwner) {
            Log.d("getCollection", "dataCollection id: ${it.getCollections?.id}")
            binding.bannerImage.load(it.backdropPath)
            binding.posterImage.load(it.posterPath)
            binding.titleMovie.text = it.originalTitle
            binding.genres.text = it.genres
            binding.releaseDate.text = it.releaseDate
            binding.duration.text = it.runtime
            binding.voteAverage.text = it.voteAverage
            binding.detailSummary.text = it.overview
            binding.titleCollections.text = it.getCollections?.name
            if (it.getCollections != null) {
                viewModel.setDetailCollection(it.getCollections.id)
            } else {
                viewModel.setDetailCollection(1)
            }
        }
        recyclerviewSetup()
    }

    private fun recyclerviewSetup() {
        recyclerAdapter = DetailTeamsRecyclerview {
            itemClicked(it)
        }
        binding.rvCollections.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = recyclerAdapter
        }
    }

    private fun itemClicked(data: DataItemCollections) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.google.com/search?q=${data.originalTitle}")
        )
        context?.startActivity(intent)
    }
}
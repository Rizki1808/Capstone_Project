package com.example.capstoneproject.ui.explore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.data.response.Article
import com.example.capstoneproject.data.tools.ViewModelFactory
import com.example.capstoneproject.databinding.FragmentExploreBinding

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private lateinit var exploreViewModel: ExploreViewModel
    private lateinit var adapter: ExploreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        val factory = ViewModelFactory.getInstance(requireContext())
        exploreViewModel = ViewModelProvider(this, factory).get(ExploreViewModel::class.java)

        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter = ExploreAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : ExploreAdapter.OnItemClickCallback {
            override fun onItemClickCallBack(data: Article) {
                val intent = Intent(context, DetailExploreActivity::class.java)
                intent.putExtra(DetailExploreActivity.EXTRA_URL, data.url)
                startActivity(intent)
            }
        })

        binding.apply {
            rvNews.layoutManager = LinearLayoutManager(context)
            rvNews.setHasFixedSize(true)
            rvNews.adapter = adapter

            swipeRefreshLayout.setOnRefreshListener {
                refreshData()
            }
        }

        exploreViewModel.news.observe(viewLifecycleOwner, Observer { result ->
            result.onSuccess { newsResponse ->
                ArrayList(newsResponse.articles).let { adapter.setData(it) }
                showLoading(false)
            }
            result.onFailure { exception ->
                showLoading(false)
                // Handle error
            }
        })

        exploreViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            showLoading(isLoading)
        })

        exploreViewModel.getNews()

        return root
    }

    private fun refreshData() {
        exploreViewModel.getNews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}

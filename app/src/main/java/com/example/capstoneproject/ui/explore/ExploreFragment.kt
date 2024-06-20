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
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.data.response.Article
import com.example.capstoneproject.data.tools.ViewModelFactory
import com.example.capstoneproject.databinding.FragmentExploreBinding

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private lateinit var exploreViewModel: ExploreViewModel
    private lateinit var adapter: ExploreAdapter
    private var isLoading = false
    private var currentPage = 1
    private val PAGE_SIZE = 20

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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

            rvNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                    if (!isLoading && lastVisibleItemPosition + 1 >= totalItemCount) {
                        loadMoreItems()
                    }
                }
            })

            swipeRefreshLayout.setOnRefreshListener {
                refreshData()
            }
        }

        exploreViewModel.news.observe(viewLifecycleOwner, Observer { result ->
            result.onSuccess { newsResponse ->
                if (currentPage == 1) {
                    adapter.setData(ArrayList(newsResponse.articles))
                } else {
                    adapter.removeLoadingFooter()
                    adapter.addData(ArrayList(newsResponse.articles))
                }
                binding.swipeRefreshLayout.isRefreshing = false
                isLoading = false
            }
            result.onFailure { exception ->
                binding.swipeRefreshLayout.isRefreshing = false
                isLoading = false
                // Handle error
            }
        })

        loadMoreItems()

        return root
    }

    private fun loadMoreItems() {
        isLoading = true
        adapter.addLoadingFooter()
        exploreViewModel.getNews(currentPage, PAGE_SIZE)
        currentPage++
    }

    private fun refreshData() {
        currentPage = 1
        exploreViewModel.getNews(currentPage, PAGE_SIZE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.example.capstoneproject.ui.home

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
import com.example.capstoneproject.databinding.FragmentHomeBinding
import com.example.capstoneproject.ui.explore.DetailExploreActivity
import com.example.capstoneproject.ui.explore.ExploreViewModel
import com.example.capstoneproject.ui.feature.FeatureFragment
import com.example.capstoneproject.ui.feature.item.diari.DiaryActivity
import com.example.capstoneproject.ui.feature.item.infopenyakit.InfoPenyakitActivity
import com.example.capstoneproject.ui.feature.item.minumobat.MinumObatActivity
import com.example.capstoneproject.ui.feature.item.rumahsakit.MapsActivity
import com.example.capstoneproject.ui.feature.item.pendeteksi.pendeteksiwajah.PendeteksiWajahActivity
import com.example.capstoneproject.ui.feature.item.pendeteksi.pendeteksikulit.PendeteksiKulitActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var exploreViewModel: ExploreViewModel
    private lateinit var horizontalAdapter: HomeExploreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize ViewModel
        val factory = ViewModelFactory.getInstance(requireContext())
        exploreViewModel = ViewModelProvider(this, factory).get(ExploreViewModel::class.java)

        // Set up horizontal RecyclerView
        horizontalAdapter = HomeExploreAdapter()
        horizontalAdapter.notifyDataSetChanged()

        horizontalAdapter.setOnItemClickCallback(object : HomeExploreAdapter.OnItemClickCallback {
            override fun onItemClickCallBack(data: Article) {
                val intent = Intent(context, DetailExploreActivity::class.java)
                intent.putExtra(DetailExploreActivity.EXTRA_URL, data.url)
                startActivity(intent)
            }
        })

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = horizontalAdapter
        }

        // Observe data from ViewModel
        exploreViewModel.news.observe(viewLifecycleOwner, Observer { result ->
            result.onSuccess { newsResponse ->
                ArrayList(newsResponse.articles).let { horizontalAdapter.setData(it) }
            }
            result.onFailure { exception ->
                // Handle error
            }
        })

        // Fetch news data
        exploreViewModel.getNews()

        // Set onClickListeners for other features
        binding.icInfoPenyakit.setOnClickListener {
            val intent = Intent(activity, InfoPenyakitActivity::class.java)
            startActivity(intent)
        }

        binding.icPendeteksiKulit.setOnClickListener {
            val intent = Intent(activity, PendeteksiKulitActivity::class.java)
            startActivity(intent)
        }

        binding.icPendeteksiWajah.setOnClickListener {
            val intent = Intent(activity, PendeteksiWajahActivity::class.java)
            startActivity(intent)
        }

        binding.icRumahSakitTerdekat.setOnClickListener {
            val intent = Intent(activity, MapsActivity::class.java)
            startActivity(intent)
        }

        binding.icPengingatMinumObat.setOnClickListener {
            val intent = Intent(activity, MinumObatActivity::class.java)
            startActivity(intent)
        }

        binding.icDiariKesehatan.setOnClickListener {
            val intent = Intent(activity, DiaryActivity::class.java)
            startActivity(intent)
        }

        binding.icFiturLain.setOnClickListener {
            val intent = Intent(activity, FeatureFragment::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
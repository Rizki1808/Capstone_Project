package com.example.capstoneproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.R
import com.example.capstoneproject.data.response.Article
import com.example.capstoneproject.data.response.DataItem
import com.example.capstoneproject.data.tools.ViewModelFactory
import com.example.capstoneproject.databinding.FragmentHomeBinding
import com.example.capstoneproject.ui.explore.DetailExploreActivity
import com.example.capstoneproject.ui.explore.ExploreViewModel
import com.example.capstoneproject.ui.feature.item.diari.DiaryActivity
import com.example.capstoneproject.ui.feature.item.infopenyakit.InfoPenyakitActivity
import com.example.capstoneproject.ui.feature.item.infopenyakit.InfoPenyakitAdapter
import com.example.capstoneproject.ui.feature.item.infopenyakit.InfoPenyakitViewModel
import com.example.capstoneproject.ui.feature.item.infopenyakit.detailpenyakit.DetailPenyakitActivity
import com.example.capstoneproject.ui.feature.item.minumobat.MinumObatActivity
import com.example.capstoneproject.ui.feature.item.rumahsakit.MapsActivity
import com.example.capstoneproject.ui.feature.item.pendeteksi.pendeteksiwajah.PendeteksiWajahActivity
import com.example.capstoneproject.ui.feature.item.pendeteksi.pendeteksikulit.PendeteksiKulitActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var exploreViewModel: ExploreViewModel
    private lateinit var infoViewModel: InfoPenyakitViewModel
    private lateinit var horizontalAdapter: HomeExploreAdapter
    private lateinit var verticalAdapter: InfoPenyakitAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize ViewModel
        val factory = ViewModelFactory.getInstance(requireContext())
        exploreViewModel = ViewModelProvider(this, factory).get(ExploreViewModel::class.java)
        infoViewModel = ViewModelProvider(this, factory).get(InfoPenyakitViewModel::class.java)

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
        // Fetch news data
        exploreViewModel.getNews()

        verticalAdapter = InfoPenyakitAdapter()
        verticalAdapter.notifyDataSetChanged()

        verticalAdapter.setOnItemClickCallback(object : InfoPenyakitAdapter.OnItemClickCallback {
            override fun onItemClickCallBack(data: DataItem) {
                val intent = Intent(context, DetailPenyakitActivity::class.java)
                intent.putExtra(DetailPenyakitActivity.EXTRA_DISEASE, data.id)
                startActivity(intent)
            }
        })

        binding.rvInfo.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = verticalAdapter
        }

        infoViewModel.diseases.observe(viewLifecycleOwner, Observer { result ->
            result.onSuccess { diseasesResponse ->
                ArrayList(diseasesResponse.data).let { verticalAdapter.setData(it) }
                showLoading(false)
            }
            result.onFailure { exception ->
                showLoading(false)
                // Handle error
            }
        })

        infoViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            showLoading(isLoading)
        })

        infoViewModel.getDiseases()

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
            findNavController().navigate(R.id.action_navigation_home_to_navigation_feature, null, navOptions = null)
        }

        binding.tvLihatNews.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_explore, null, navOptions = null)
        }

        binding.tvLihatInfo.setOnClickListener {
            val intent = Intent(activity, InfoPenyakitActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBar2.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.progressBar2.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
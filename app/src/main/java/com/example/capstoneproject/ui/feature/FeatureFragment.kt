package com.example.capstoneproject.ui.feature

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstoneproject.databinding.FragmentFeatureBinding
import com.example.capstoneproject.ui.feature.item.diari.DiaryActivity
import com.example.capstoneproject.ui.feature.item.infopenyakit.InfoPenyakitActivity
import com.example.capstoneproject.ui.feature.item.minumobat.MinumObatActivity
import com.example.capstoneproject.ui.feature.item.pendeteksi.pendeteksiwajah.PendeteksiWajahActivity
import com.example.capstoneproject.ui.feature.item.pendeteksi.pendeteksikulit.PendeteksiKulitActivity
import com.example.capstoneproject.ui.feature.item.rumahsakit.MapsActivity

class FeatureFragment : Fragment() {

    private var _binding: FragmentFeatureBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val featureViewModel =
            ViewModelProvider(this).get(FeatureViewModel::class.java)

        _binding = FragmentFeatureBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
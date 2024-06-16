package com.example.capstoneproject.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.capstoneproject.data.ViewModelFactory
import com.example.capstoneproject.databinding.FragmentProfileBinding
import com.example.capstoneproject.ui.profile.detail.DetailAksesibilitas
import com.example.capstoneproject.ui.profile.detail.DetailNGD
import com.example.capstoneproject.ui.profile.detail.DetailTentang

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val factory = ViewModelFactory.getInstance(requireContext())
        val profileViewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)

        binding.btnLogout.setOnClickListener {
            profileViewModel.logout()
        }

        binding.btnAmbulance.setOnClickListener {
            val intent = Intent(this@ProfileFragment.requireContext(), DetailNGD::class.java)
            startActivity(intent)
        }

        binding.btnAksi.setOnClickListener{
            val intent = Intent(this@ProfileFragment.requireContext(), DetailAksesibilitas::class.java)
            startActivity(intent)
        }

        binding.btnInfo.setOnClickListener{
            val intent = Intent(this@ProfileFragment.requireContext(), DetailTentang::class.java)
            startActivity(intent)
        }


        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

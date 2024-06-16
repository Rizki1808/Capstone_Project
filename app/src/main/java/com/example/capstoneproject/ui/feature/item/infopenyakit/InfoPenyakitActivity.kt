package com.example.capstoneproject.ui.feature.item.infopenyakit

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.data.ViewModelFactory
import com.example.capstoneproject.databinding.ActivityInfoPenyakitBinding
import com.example.capstoneproject.ui.login.signin.SignInActivity

class InfoPenyakitActivity : AppCompatActivity() {

    private val viewModel by viewModels<InfoPenyakitViewModel>() {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityInfoPenyakitBinding
    private lateinit var adapter: InfoPenyakitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoPenyakitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }

        adapter = InfoPenyakitAdapter()
        adapter.notifyDataSetChanged()

        setupRecyclerView()

        viewModel.stories.observe(this, Observer { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        })
    }

    private fun setupRecyclerView() {
        adapter = InfoPenyakitAdapter()
        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@InfoPenyakitActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
        }
    }
}
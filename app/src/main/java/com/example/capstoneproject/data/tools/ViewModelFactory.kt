package com.example.capstoneproject.data.tools

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.capstoneproject.data.di.Injection
import com.example.capstoneproject.ui.explore.ExploreViewModel
import com.example.capstoneproject.ui.feature.item.diari.guladarah.GulaViewModel
import com.example.capstoneproject.ui.feature.item.diari.guladarah.tambahgula.TambahGulaViewModel
import com.example.capstoneproject.ui.feature.item.diari.tekanandarah.TekananViewModel
import com.example.capstoneproject.ui.feature.item.diari.tekanandarah.tambahtekanan.TambahTekananViewModel
import com.example.capstoneproject.ui.feature.item.infopenyakit.InfoPenyakitViewModel
import com.example.capstoneproject.ui.feature.item.infopenyakit.detailpenyakit.DetailPenyakitViewModel
import com.example.capstoneproject.ui.feature.item.pendeteksi.history.HistoryViewModel
import com.example.capstoneproject.ui.feature.item.pendeteksi.pendeteksikulit.KulitViewModel
import com.example.capstoneproject.ui.feature.item.pendeteksi.pendeteksiwajah.WajahViewModel
import com.example.capstoneproject.ui.login.signin.SignInViewModel
import com.example.capstoneproject.ui.login.signup.SignUpViewModel
import com.example.capstoneproject.ui.main.MainViewModel
import com.example.capstoneproject.ui.profile.ProfileViewModel

class ViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                SignInViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(InfoPenyakitViewModel::class.java) -> {
                InfoPenyakitViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailPenyakitViewModel::class.java) -> {
                DetailPenyakitViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TekananViewModel::class.java) -> {
                TekananViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TambahTekananViewModel::class.java) -> {
                TambahTekananViewModel(repository) as T
            }
            modelClass.isAssignableFrom(KulitViewModel::class.java) -> {
                KulitViewModel(repository) as T
            }
            modelClass.isAssignableFrom(GulaViewModel::class.java) -> {
                GulaViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TambahGulaViewModel::class.java) -> {
                TambahGulaViewModel(repository) as T
            }
            modelClass.isAssignableFrom(WajahViewModel::class.java) -> {
                WajahViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ExploreViewModel::class.java) -> {
                ExploreViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection.provideRepository(context)).also {
                    INSTANCE = it
                }
            }
        }
    }
}
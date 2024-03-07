package com.tegar.sedekah.ui.profile

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.finishAffinity
import com.tegar.sedekah.R
import com.tegar.sedekah.databinding.FragmentHomeBinding
import com.tegar.sedekah.databinding.FragmentProfileBinding
import com.tegar.sedekah.ui.auth.login.LoginActivity
import com.tegar.sedekah.ui.home.HomeViewModel
import com.tegar.sedekah.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null

    private val profileViewModel: ProfileViewModel by activityViewModel<ProfileViewModel>()

    private val binding get() = _binding!!
    private lateinit var handler : Handler
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val switchTheme = binding.switchTheme

        profileViewModel.getThemeSettings().observe(requireActivity()) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            profileViewModel.saveThemeSetting(isChecked)
        }

        binding.btnLogout.setOnClickListener{
            profileViewModel.clearSesion()
            finishAffinity(requireActivity())
            startActivity(Intent(requireActivity(), LoginActivity   ::class.java))
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
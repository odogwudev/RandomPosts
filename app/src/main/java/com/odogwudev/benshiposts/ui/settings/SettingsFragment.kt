package com.odogwudev.benshiposts.ui.settings

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.odogwudev.benshiposts.R
import com.odogwudev.benshiposts.base.BaseFragment
import com.odogwudev.benshiposts.databinding.FragmentSettingsBinding


class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_settings

    override fun prepareView(savedInstanceState: Bundle?) {
        initListener()
    }

    private fun initListener() {
        binding.button.setOnClickListener {
            val text = "Hello There; no event stated in the test!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }

    }
}
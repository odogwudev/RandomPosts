package com.odogwudev.benshiposts.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        prepareView(savedInstanceState)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun prepareView(savedInstanceState: Bundle?)
}
package com.example.a20220401_dilipkumarnara_nycschools.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.a20220401_dilipkumarnara_nycschools.R
import com.example.a20220401_dilipkumarnara_nycschools.databinding.ActivityMainBinding
import com.example.a20220401_dilipkumarnara_nycschools.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // bind the view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // fetch the data when activity is created
        viewModel.getSchoolsData()
        viewModel.getSATData()
    }
}
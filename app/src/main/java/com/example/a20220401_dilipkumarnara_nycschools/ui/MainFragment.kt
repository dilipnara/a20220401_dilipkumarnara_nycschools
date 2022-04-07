package com.example.a20220401_dilipkumarnara_nycschools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dish.celltech.api.APIResponse
import com.example.a20220401_dilipkumarnara_nycschools.R
import com.example.a20220401_dilipkumarnara_nycschools.adapters.SchoolsAdapter
import com.example.a20220401_dilipkumarnara_nycschools.data.SchoolDataItem
import com.example.a20220401_dilipkumarnara_nycschools.databinding.MainFragmentBinding
import com.example.a20220401_dilipkumarnara_nycschools.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : Fragment(), SchoolsAdapter.SchoolsAdapterListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        updateUI()
    }

    override fun onSchoolClicked(selectedSchool: SchoolDataItem) {
        // save the currently selected school
        viewModel.selectedSchool = selectedSchool
        // fetch sat for selected school
        viewModel.getSingleSATData()
        // navigate to SAT fragment screen uses jetpack navigation component
        findNavController().navigate(R.id.action_mainFragment_to_SATFragment)
    }

    private fun setUpRecyclerView() {
        binding.rvSchools.visibility = View.VISIBLE
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvSchools.layoutManager = layoutManager

        // add divider for each item in recycler view
        binding.rvSchools.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                layoutManager.orientation
            )
        )
    }

    private fun updateUI() {
        // update UI based on the values emitted by the flow api response
        // currently handling three cases loading/success/error
        lifecycleScope.launch {
            viewModel.schoolData.collect {
                when (it.status) {
                    APIResponse.Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvError.visibility = View.GONE
                    }
                    APIResponse.Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.GONE
                        val data = it.data
                        if (data.isNullOrEmpty()) {

                        } else {
                            binding.rvSchools.adapter =
                                SchoolsAdapter(requireActivity(), data, this@MainFragment)
                        }
                    }
                    APIResponse.Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
package com.example.a20220401_dilipkumarnara_nycschools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.a20220401_dilipkumarnara_nycschools.R
import com.example.a20220401_dilipkumarnara_nycschools.databinding.SATFragmentBinding
import com.example.a20220401_dilipkumarnara_nycschools.viewmodel.MainViewModel

class SATFragment : Fragment() {

    companion object {
        fun newInstance() = SATFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    lateinit var binding: SATFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.s_a_t_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateView()
    }

    private fun updateView() {
        viewModel.selectedSATData?.let {
            binding.llDataView.visibility = View.VISIBLE
            binding.tvNoSatError.visibility = View.GONE
            binding.schoolName.text = it.school_name
            binding.tvTest.text = "Test Takers: ${it.num_of_sat_test_takers}"
            binding.tvMath.text = "Math Avg Score: ${it.sat_math_avg_score}"
            binding.tvReading.text = "Reading Avg Score: ${it.sat_critical_reading_avg_score}"
            binding.tvWriting.text = "Writing Avg Score: ${it.sat_critical_reading_avg_score}"
        } ?: kotlin.run {
            binding.llDataView.visibility = View.GONE
            binding.tvNoSatError.visibility = View.VISIBLE
            binding.tvNoSatError.text = "We could not find any SAT data for this school!!"
        }
    }

}
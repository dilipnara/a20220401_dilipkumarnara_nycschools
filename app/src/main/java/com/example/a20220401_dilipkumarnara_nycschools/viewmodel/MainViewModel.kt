package com.example.a20220401_dilipkumarnara_nycschools.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dish.celltech.api.APIResponse
import com.example.a20220401_dilipkumarnara_nycschools.Repository.GetSchoolsRepository
import com.example.a20220401_dilipkumarnara_nycschools.data.SATData
import com.example.a20220401_dilipkumarnara_nycschools.data.SATDataItem
import com.example.a20220401_dilipkumarnara_nycschools.data.SchoolData
import com.example.a20220401_dilipkumarnara_nycschools.data.SchoolDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var selectedSchool: SchoolDataItem? = null
    var selectedSATData: SATDataItem? = null

    private val schoolsRepository =
        GetSchoolsRepository()
    private val _schoolsData =
        MutableStateFlow<APIResponse<SchoolData>>(APIResponse.Empty())
    val schoolData: MutableStateFlow<APIResponse<SchoolData>> = _schoolsData

    private val _satData =
        MutableStateFlow<APIResponse<SATData>>(APIResponse.Empty())
    private val satData: MutableStateFlow<APIResponse<SATData>> = _satData

    /*
    * Get all school's data
    * */
    fun getSchoolsData() {
        viewModelScope.launch {
            schoolsRepository.getSchoolData().collect {
                _schoolsData.value = it
            }
        }
    }

    /*
    * Get SAT data for all schools
    * */
    fun getSATData() {
        viewModelScope.launch {
            schoolsRepository.getSATData().collect {
                _satData.value = it
            }
        }
    }

    /*
    * Get selected school SAT details
    * */
    fun getSingleSATData() {
        viewModelScope.launch {
            selectedSchool?.let { selectedSchool ->
                val data = satData.value.data
                // find the school using dbn value
                selectedSATData = data?.find { it.dbn == selectedSchool.dbn }
            }
        }
    }
}
package com.example.a20220401_dilipkumarnara_nycschools.Repository

import com.dish.celltech.api.APIResponse
import com.example.a20220401_dilipkumarnara_nycschools.api.APIClient
import com.example.a20220401_dilipkumarnara_nycschools.api.APIInterface
import com.example.a20220401_dilipkumarnara_nycschools.data.SATData
import com.example.a20220401_dilipkumarnara_nycschools.data.SchoolData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody

class GetSchoolsRepository() {

    suspend fun getSchoolData(): Flow<APIResponse<SchoolData>> {

        return flow<APIResponse<SchoolData>> {
            emit(APIResponse.Loading())
            try {
                var apiInterface = APIClient.client?.create(APIInterface::class.java)
                val data = apiInterface?.getSchools()
                if (data is SchoolData) {
                    emit(APIResponse.Success(data))
                } else {
                    emit(APIResponse.Error(error = data as ResponseBody, message = ""))
                }
            } catch (ex: Exception) {
                emit(APIResponse.Error(message = ""))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSATData(): Flow<APIResponse<SATData>> {

        return flow<APIResponse<SATData>> {
            emit(APIResponse.Loading())
            try {
                var apiInterface = APIClient.client?.create(APIInterface::class.java)
                val data = apiInterface?.getSatScores()
                if (data is SATData) {
                    emit(APIResponse.Success(data))
                } else {
                    emit(APIResponse.Error(error = data as ResponseBody, message = ""))
                }
            } catch (ex: Exception) {
                emit(APIResponse.Error(message = ""))
            }
        }.flowOn(Dispatchers.IO)
    }

}
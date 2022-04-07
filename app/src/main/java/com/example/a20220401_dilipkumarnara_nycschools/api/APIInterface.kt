package com.example.a20220401_dilipkumarnara_nycschools.api

import com.example.a20220401_dilipkumarnara_nycschools.data.SATData
import com.example.a20220401_dilipkumarnara_nycschools.data.SchoolData
import retrofit2.http.*

interface APIInterface {
    @GET("s3k6-pzi2")
    suspend fun getSchools(): SchoolData

    @GET("f9bf-2cp4")
    suspend fun getSatScores(
    ): SATData
}
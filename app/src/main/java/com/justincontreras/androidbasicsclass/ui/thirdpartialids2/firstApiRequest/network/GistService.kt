package com.justincontreras.androidbasicsclass.ui.thirdpartialids2.firstApiRequest.network

import com.justincontreras.androidbasicsclass.ui.thirdpartialids2.firstApiRequest.model.StudentsResponse
import retrofit2.http.GET


interface GistService {
    @GET("ingjromo/5f3c4549cfc64984c9131112fd87d718/raw/6e36ceff88f29b046f2d8529041526a05b8b3dde/students26.json")
    suspend fun getStudents(): StudentsResponse
}

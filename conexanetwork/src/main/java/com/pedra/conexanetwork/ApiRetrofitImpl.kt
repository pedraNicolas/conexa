package com.pedra.conexanetwork

import android.accounts.NetworkErrorException
import com.pedra.conexanetwork.dtos.news.NewsDTO
import com.pedra.conexanetwork.dtos.users.UserDTO
import com.pedra.core.ConstantsNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

internal class ApiRetrofitImpl : ApiRetrofitInterface {

    @Throws(Exception::class)
    override suspend fun getNews(): List<NewsDTO> {
        val retrofit =
            ApiManager.getInstance(ConstantsNetwork.JSON_PLACE_HOLDER_URL).getApiRetrofit()

        return withContext(Dispatchers.IO) {

            val response = retrofit.getAllNews()

            response.let {

                when (it.code()) {
                    200 -> {
                        it.body()
                    }

                    else -> {
                        throw Exception(it.message())
                    }
                }
            } ?: throw Exception()
        }
    }

    @Throws(Exception::class)
    override suspend fun getAllUsers(): List<UserDTO> {
        val retrofit =
            ApiManager.getInstance(ConstantsNetwork.JSON_PLACE_HOLDER_URL).getApiRetrofit()

        return withContext(Dispatchers.IO) {

            val response = retrofit.getAllUsers()

            response.let {

                when (it.code()) {
                    200 -> {
                        it.body()
                    }

                    else -> {
                        throw Exception(it.message())
                    }
                }
            } ?: throw Exception()
        }
    }

    @Throws(Exception::class)
    override suspend fun getUserById(id: String): UserDTO? {
        val retrofit = ApiManager.getInstance(ConstantsNetwork.JSON_PLACE_HOLDER_URL).getApiRetrofit()

        return withContext(Dispatchers.IO) {

            val response = retrofit.getUserByID(id)

            response.let {

                when (it.code()) {
                    200 -> {
                        it.body()
                    }

                    else -> {
                        throw Exception(it.message())
                    }
                }
            } ?: throw Exception()
        }
    }
}
package com.pedra.conexanetwork

import android.accounts.NetworkErrorException
import com.pedra.conexanetwork.dtos.news.NewsDTO
import com.pedra.core.ConstantsNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ApiRetrofitImpl : ApiRetrofitInterface {

    @Throws(Exception::class)
    override suspend fun getNews(): List<NewsDTO> {
        val retrofit = ApiManager().getNews()

        return withContext(Dispatchers.IO) {

            val response = retrofit.getAllNews()

            response.let {

                when (it.code()) {
                    200 -> {
                        it.body()
                    }

                    else -> {
                        throw Exception()
                    }
                }
            } ?:
            throw Exception()
        }
    }
}
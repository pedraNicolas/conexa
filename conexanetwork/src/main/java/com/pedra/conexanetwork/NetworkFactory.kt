package com.pedra.conexanetwork

object NetworkFactory {

    fun getApiManager(): ApiRetrofitInterface{
        return ApiRetrofitImpl()
    }

}
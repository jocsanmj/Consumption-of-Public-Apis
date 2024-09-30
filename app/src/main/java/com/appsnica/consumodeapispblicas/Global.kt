package com.appsnica.consumodeapispblicas

data class ApiResponse(
    val id: Int,
    val Year: Int,
    val Title: String,
    val handle: String,
    val Publisher: String,
    val ISBN: String,
    val Pages: Int
)


//Lista global para almacenar los resultados
var detaList: MutableList<ApiResponse> = mutableListOf()
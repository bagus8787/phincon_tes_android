package com.gib.pokemon_bagus.model.response

data class BannerResponse(
    val data: List<Banner>
)

data class Banner(
    val kode_negara: String,
    val logo: String,
    val nama_negara: String
)
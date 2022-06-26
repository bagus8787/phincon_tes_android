package com.gib.pokemon_bagus.helper

class GlobalVar {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val ENDPOINT = "rest-api-v3/"

        const val STATUS_AKTIF = "Aktif"
        const val STATUS_PENDING = "Pending"

        const val GPS_REQUEST = 1001

        var URL_FAQ = BASE_URL + ENDPOINT + "14_faq.php"
        var URL_SYARAT_KETENTUAN = BASE_URL + ENDPOINT + "07_syarat_ketentuan.php"
        var URL_KEBIJAKAN_PRIVACY = BASE_URL + ENDPOINT + "08_kebijakan_privacy.php"
        var URL_TENTANG_KAMI = BASE_URL + ENDPOINT + "15_tentang_kami.php"
        var URL_INFO_APPS = BASE_URL + ENDPOINT + "16_identitas_apps_view.php"

        var NO_SELECTED_INDEX = -1

        val ALLOWED_URI_CHARS = ":"
        val API_SECRET = "2463999a631713c277fb9e3e1b07071898de1bc0bbc3677bc6aea3ea5851525e"

        const val DB_NAME = "pokemon.db"
        const val TABEL_POKEMON_EFFECT = "tbl_pokemon_effect"
    }
}
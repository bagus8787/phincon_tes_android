package com.gib.pokemon_bagus.helper

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

class SessionManager(context: Context) {

    private val PRIVATE_MODE = 0

    private val PREF_NAME = "fadi_pref"
    private val PREF_IS_LOGIN = "is_login"
    private val PREF_DATA_MEMBER = "data_member"
    private val PREF_IMEI = "imei"
    private val PREF_DEVICE_ID = "device_id"
    private val PREF_USERNAME = "username"
    private val PREF_PASSWORD = "password"
    private val PREF_INFO_APLIKASI = "info_aplikasi"
    private val KEY_NO_TRANS = "key_no_trans"
    private val KEY_NO_TRANS_TOPUP = "key_no_trans_topup"
    private val KEY_NO_TRANS_VOUCHER = "key_no_trans_voucher"
    private val KEY_REK_VA = "key_rek_va"
    private val KEY_PIN = "key_pin"
    private val KEY_ACCOUNT_ACTIVE = "key_akun_aktif"
    private val KEY_FOTO_PROFILE = "key_foto_profile"
    private val KEY_SAVED_PHONE = "key_phone_login"
    private val KEY_SAVED_NEGARA = "key_phone_negara"

    val TOKEN = "token"

    lateinit var pref: SharedPreferences
    private val editor: SharedPreferences.Editor

    fun isLogin(bol: Boolean) {
        editor.putBoolean(PREF_IS_LOGIN, bol).apply()
    }

    fun isLogin(): Boolean {
        return pref.getBoolean(PREF_IS_LOGIN, false)
    }


    fun pinLogin(str: String) {
        editor.putString(KEY_PIN, str).apply()
    }

    fun pinLogin(): String {
        return pref.getString(KEY_PIN, "")!!
    }

//    var member: LoginResponse?
//        get() {
//            return get(PREF_DATA_MEMBER)
////            val json = pref.getString(PREF_DATA_MEMBER, "")
////            return Gson().fromJson(json, LoginResponse::class.java)
//        }
//        set(data) {
//            put(data, PREF_DATA_MEMBER)
////            val json = data.toString()
////            editor.putString(PREF_DATA_MEMBER, json).apply()
//        }

    var fotoProfile: String
        get() {
            return pref.getString(KEY_FOTO_PROFILE, "")!!
        }
        set(data) {
            editor.putString(KEY_FOTO_PROFILE, data).apply()
        }

    var savedPhoneLogin: String
        get() {
            return pref.getString(KEY_SAVED_PHONE, "")!!
        }
        set(data) {
            editor.putString(KEY_SAVED_PHONE, data).apply()
        }

    var savedKodeNegara: String
        get() {
            return pref.getString(KEY_SAVED_NEGARA, "")!!
        }
        set(data) {
            editor.putString(KEY_SAVED_NEGARA, data).apply()
        }

//    fun saveInfoAplikasi(info: InfoResponse) {
//        val json = info.toString()
//        editor.putString(PREF_INFO_APLIKASI, json).apply()
//    }
//
//    fun getInfoAplikasi(): InfoResponse {
//        val json = pref.getString(PREF_INFO_APLIKASI, "")
//        return Gson().fromJson(json, InfoResponse::class.java)
//    }

    fun saveUsername(username: String) {
        editor.putString(PREF_USERNAME, username).apply()
    }

    fun getUsername(): String {
        return pref.getString(PREF_USERNAME, "")!!
    }

    fun savePassword(pass: String) {
        editor.putString(PREF_PASSWORD, pass).apply()
    }

    fun getPassword(): String {
        return pref.getString(PREF_PASSWORD, "")!!
    }

    var noTransSpp: String
        get() {
            return pref.getString(KEY_NO_TRANS, "")!!
        }
        set(value) {
            editor.putString(KEY_NO_TRANS, value).apply()
        }

    var noTransVoucher: String
        get() {
            return pref.getString(KEY_NO_TRANS_VOUCHER, "")!!
        }
        set(value) {
            editor.putString(KEY_NO_TRANS_VOUCHER, value).apply()
        }

    var noTransTopup: String
        get() {
            return pref.getString(KEY_NO_TRANS_TOPUP, "")!!
        }
        set(value) {
            editor.putString(KEY_NO_TRANS_TOPUP, value).apply()
        }

    var rekeningVA: String
        get() {
            return pref.getString(KEY_REK_VA, "")!!
        }
        set(value) {
            editor.putString(KEY_REK_VA, value).apply()
        }

    var token: String
        get(){
            return pref.getString(TOKEN, "")!!
        }
        set(value){
            editor.putString(TOKEN, value).apply()
        }

    var accountActive: Boolean
        get() {
            return pref.getBoolean(KEY_ACCOUNT_ACTIVE, false)
        }
        set(value) {
            editor.putBoolean(KEY_ACCOUNT_ACTIVE, value).apply()
        }


    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    /**
     * Saves object into the Preferences.
     *
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> put(`object`: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        pref.edit().putString(key, jsonString).apply()
    }

    /**
     * Used to retrieve object from the Preferences.
     *
     * @param key Shared Preference key with which object was saved.
     **/
    inline fun <reified T> get(key: String): T? {
        //We read JSON String which was saved.
        val value = pref.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}
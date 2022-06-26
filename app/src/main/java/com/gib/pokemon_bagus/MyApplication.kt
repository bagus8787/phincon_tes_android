package com.gib.pokemon_bagus

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.gib.pokemon_bagus.helper.GlideImageLoadingService
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import ss.com.bannerslider.Slider

class MyApplication : MultiDexApplication() {

    companion object {
        var imei = "dummy"
        // harcode for testing
        var device_id = "failed_load_firebase_uid"
//        var device_id = "6cc1116b785f6403"

        var TOKEN = ""

        var total_tagihan_spp = 0L

        var last_opened_tab = 0

        var saldo_ewallet = 0L

        private var instance: MyApplication? = null

        fun getInstance(): MyApplication? {
            return instance
        }

        fun getContext(): Context? {
            return instance
        }
    }

    override fun onCreate() {
        instance = this

        super.onCreate()
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )
        Slider.init(GlideImageLoadingService(this))
    }



//    fun touch() {
//        ApplockManager.getInstance().updateTouch()
////        ApplockManager.getInstance().enableDefaultAppLockIfAvailable(this)
//    }

}
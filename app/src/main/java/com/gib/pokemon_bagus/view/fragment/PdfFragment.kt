package com.gib.pokemon_bagus.view.fragment

import android.os.Bundle
import android.view.View
import com.gib.pokemon_bagus.MyApplication
import com.gib.pokemon_bagus.R
import com.gib.pokemon_bagus.databinding.FragmentMyPlansBinding
import com.gib.pokemon_bagus.helper.GlobalVar
import com.gib.pokemon_bagus.helper.viewBinding
import com.gib.pokemon_bagus.model.request.BannerRequest
import com.gib.pokemon_bagus.view.base.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.toolbar_noback.view.*

class PdfFragment : BaseFragment(R.layout.fragment_my_plans) {

    private val binding by viewBinding(FragmentMyPlansBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.toolbar.tvTitleToolbar.text = "My Plan"


        setListener()
        getMyPlan()
    }

    private fun setListener() {

    }

    private fun getMyPlan() {
        disposable?.add(
            apiInterface.banner(
                BannerRequest(
                    "40_list_negara",
                    GlobalVar.API_SECRET,
                    MyApplication.imei,
                    MyApplication.device_id
                )
            )
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                    },
                    {
                        //toast(it.stackTraceToString())
                        toast("Gagal memproses data, terjadi gangguan server")
                    })
        )
    }

}
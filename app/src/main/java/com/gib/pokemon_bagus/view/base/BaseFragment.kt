package com.gib.pokemon_bagus.view.base

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.gib.pokemon_bagus.R
import com.gib.pokemon_bagus.data.api.ApiClient
import com.gib.pokemon_bagus.data.api.ApiInterface
import com.gib.pokemon_bagus.db.DatabaseHelperImpl
import com.gib.pokemon_bagus.db.StokRoomDatabase
import com.gib.pokemon_bagus.helper.DialogHelper
import com.gib.pokemon_bagus.helper.Loading
import com.gib.pokemon_bagus.helper.SessionManager
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment(fragment: Int) : Fragment(fragment) {

    lateinit var session: SessionManager
    lateinit var apiInterface: ApiInterface
    var disposable: CompositeDisposable? = null

    private var toast: Toast? = null
    lateinit var pLoading: Loading
    lateinit var dialogHelper: DialogHelper
    lateinit var progressBar: ProgressBar

    lateinit var dbHelper: DatabaseHelperImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pLoading = Loading(requireContext())
        disposable = CompositeDisposable()
        session = SessionManager(requireContext())
        apiInterface = ApiClient.client.create(ApiInterface::class.java)
        dialogHelper = DialogHelper(requireContext(), apiInterface, session)

        progressBar = ProgressBar(requireContext())
        dbHelper = DatabaseHelperImpl(StokRoomDatabase.getDatabase(requireContext()))

    }

    fun toast(@StringRes message: Int) {
        toast(getString(message))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.clear()
    }

    fun toast(toastMessage: String?) {
        if (toastMessage != null && toastMessage.isNotEmpty()) {
            if (toast != null) toast!!.cancel()
            toast = Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG)
            toast!!.show()

        }
    }

    fun showProgress(status: Boolean) {
        if (status) {
            pLoading.showLoading(resources.getString(R.string.label_loading_title_dialog), false)
        } else {
            pLoading.dismissDialog()
        }
    }

    fun calculateNoOfColumns(
        context: Context,
        columnWidthDp: Float
    ): Int { // For example columnWidthdp=180
        val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }

}
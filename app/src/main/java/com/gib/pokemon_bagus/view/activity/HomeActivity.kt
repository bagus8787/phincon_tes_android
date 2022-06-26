package com.gib.pokemon_bagus.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gib.pokemon_bagus.MyApplication.Companion.last_opened_tab
import com.gib.pokemon_bagus.R
import com.gib.pokemon_bagus.databinding.ActivityHomeBinding
import com.gib.pokemon_bagus.helper.viewBinding
import com.gib.pokemon_bagus.view.base.BaseActivity
import com.gib.pokemon_bagus.view.fragment.HomeFragment

class HomeActivity : BaseActivity() {
    private val binding by viewBinding(ActivityHomeBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListener()
    }

    override fun onResume() {
        super.onResume()
        if (intent.hasExtra("tab")) {
            setTabActive(intent.getIntExtra("tab", 0))
            binding.bottomBar.setActiveItem(1)
        } else
            setTabActive(last_opened_tab)
    }

    private fun setListener() {

        binding.bottomBar.setActiveItem(0)
        binding.bottomBar.onItemSelected = {
            last_opened_tab = it
            when (it) {
                0 -> findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
            }
        }


        binding.bottomBar.onItemLongClick = {
//            status.text = "Item $it long click"
        }

    }

    private fun setTabActive(position: Int) {
        when (position) {
            0 -> findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)

        }
    }

    override fun onBackPressed() {
        if (getForegroundFragment() is HomeFragment) {
            dialogLogout()
//            finish()
        } else {

//            supportFragmentManager.popBackStack()
            super.onBackPressed()
        }
    }

    fun dialogLogout() {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(this)
        builder1.setMessage("Yakin ingin keluar dari Aplikasi?")
        builder1.setCancelable(true)
        builder1.setPositiveButton(
            "Ya"
        ) { dialog, id ->
            finish()
        }
        builder1.setNegativeButton(
            "Tidak"
        ) { dialog, id -> dialog.cancel() }
        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }

    // to check current active fragment
    fun getForegroundFragment(): Fragment? {
        val navHostFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }

    private fun intentLogin(){
        toast("Anda harus Login dahulu")
        val intent = Intent(this@HomeActivity, HomeActivity::class.java)
        startActivity(intent)
        setOveridePendingTransisi(this@HomeActivity)
    }


    fun openActivity(intent: Intent) {
        startActivity(intent)
        setOveridePendingTransisi(this@HomeActivity)
    }

}
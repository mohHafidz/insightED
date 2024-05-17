package com.example.insighted.View

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.insighted.*
import com.example.insighted.databinding.ActivityMainBinding
import com.example.insighted.model.beasiswa
import com.example.insighted.model.beasiswaManager
import com.example.insighted.model.kampus
import com.example.insighted.model.kampusManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    lateinit var binding: ActivityMainBinding

    private var currentFragmentTag: String? = null

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val fragment = Home_fragment()
                    goToFragment(fragment, "home")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.chat -> {
                    val fragment = chat_fragment()
                    goToFragment(fragment, "chat")
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val intent = Intent(this, ListBeasiswa::class.java)
//        startActivity(intent)

        val initialFragment = Home_fragment()
        goToFragment(initialFragment, "home")

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        if (kampusManager.getKampusList().size == 0){
            val kampus1 = kampus()
            kampus1.addKampus("Bina Nusantara University", "Jakarta", "Unggulan", R.drawable.binus,R.drawable.logo_binus)
            kampusManager.addKampus(kampus1)
            val kampus2 = kampus()
            kampus2.addKampus("Institut Teknlogi Bandung", "Bandung", "A", R.drawable.itb,R.drawable.logo_binus)
            kampusManager.addKampus(kampus2)
        }
        if (beasiswaManager.getBeasiswaList().size == 0){
            val beasiswa1 = beasiswa()
            beasiswa1.addBeasiswa("Beasiswa S1 BCA Finance", "Beasiswa BCA Finance salah satu program beasiswa S1 yang disediakan bagi  mahasiswa di Indonesia. Kelebihannya, beasiswa terbuka untuk seluruh  mahasiswa PTN/PTS di tanah air. Jika Anda sudah menginjak semester II ke  atas, beasiswa ini bisa dicoba.", R.drawable.bca)
            beasiswaManager.addBeasiswa(beasiswa1)
            val beasiswa2 = beasiswa()
            beasiswa2.addBeasiswa("Beasiswa S1 BCA Finance", "Beasiswa BCA Finance salah satu program beasiswa S1 yang disediakan bagi  mahasiswa di Indonesia. Kelebihannya, beasiswa terbuka untuk seluruh  mahasiswa PTN/PTS di tanah air. Jika Anda sudah menginjak semester II ke  atas, beasiswa ini bisa dicoba.", R.drawable.bca)
            beasiswaManager.addBeasiswa(beasiswa2)
        }


    }

    fun goToFragment(fragment: Fragment, tag: String){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        currentFragmentTag = tag
        updateBottomNavigationIconColor()
    }

    private fun updateBottomNavigationIconColor() {
        val homeIcon = binding.bottomNavigationView.menu.findItem(R.id.home)
        val chatIcon = binding.bottomNavigationView.menu.findItem(R.id.chat)

        val activeColor = resources.getColor(R.color.blue_card) // Ganti dengan warna aktif yang diinginkan
        val inactiveColor = resources.getColor(R.color.unselect) // Ganti dengan warna tidak aktif yang diinginkan

        homeIcon.icon?.setColorFilter(if (currentFragmentTag == "home") activeColor else inactiveColor, PorterDuff.Mode.SRC_IN)
        chatIcon.icon?.setColorFilter(if (currentFragmentTag == "chat") activeColor else inactiveColor, PorterDuff.Mode.SRC_IN)
    }
}

package com.example.insighted.View

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.insighted.*
import com.example.insighted.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore


    private var currentFragmentTag: String? = null

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val fragment = HomeFragment()
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

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance() // Inisialisasi FirebaseFirestore

        if (auth.currentUser == null) {
            // Pengguna belum login, arahkan ke LoginActivity
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        } else {
            auth.currentUser?.let { checkFirstLogin(it) }
        }
        val initialFragment = HomeFragment()
        goToFragment(initialFragment, "home")

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)



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

    private fun checkFirstLogin(user: FirebaseUser) {
        val email = user.email
        if (email != null) {
            val userRef = db.collection("users").whereEqualTo("email", email)
            Log.d("LoginActivity", "Checking if email exists: $email")

            userRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val documents = task.result
                    if (documents != null && !documents.isEmpty) {
                        // Pengguna sudah pernah login sebelumnya
                        Log.d("LoginActivity", "Email already exists, user has logged in before.")

                    } else {
                        // Pengguna belum pernah login sebelumnya, arahkan ke UserDetailsActivity
                        Log.d("LoginActivity", "First time login, redirecting to UserDetailsActivity.")
                        val intent = Intent(this, UserDetail::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    Log.e("LoginActivity", "Failed to check user data.", task.exception)
                }
            }
        } else {
            Log.e("LoginActivity", "User email is null.")
        }
    }
}

package com.example.insighted.View

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.insighted.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class CustomBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    lateinit var userTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_custom_bottom_sheet_dialog, container, false)

        val closeButton: ImageView = view.findViewById(R.id.close)
        closeButton.setOnClickListener {
            dismiss()
        }

        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        val logoutButton: Button = view.findViewById(R.id.logout)
        logoutButton.setOnClickListener {
            signOut()
        }

        userTextView = view.findViewById(R.id.account_name)
        val currentUser = auth.currentUser
        if (currentUser != null) {
            setUserName(currentUser)
        } else {
            userTextView.text = "Fardan Titi \uD83D\uDC4B"
        }

        return view
    }

    private fun signOut() {
        // Sign out from Firebase
        auth.signOut()

        // Sign out from Google
        googleSignInClient.signOut().addOnCompleteListener(requireActivity()) {
            // Redirect to Login Activity
            val loginIntent = Intent(activity, MainActivity::class.java)
            startActivity(loginIntent)
            requireActivity().finish()
        }
    }

    fun setUserName(user: FirebaseUser){
        val displayName = user.displayName
        if (displayName != null) {
            userTextView.text = "$displayName \uD83D\uDC4B"
        } else {
            userTextView.text = "Fardan Titi \uD83D\uDC4B"
        }
    }
}
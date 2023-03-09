package com.example.washingcarpetosh.ui.fragment

import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.washingcarpetosh.R
import com.example.washingcarpetosh.databinding.FragmentRegisterBinding
import com.example.washingcarpetosh.ui.base.BaseFragment
import com.example.washingcarpetosh.ui.base.isEmail
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {
    private val auth by lazy {
        Firebase.auth
    }
//    private lateinit var signInClient: SignInClient
//    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
//    private lateinit var launcher: ActivityResultLauncher<Intent>
//    private fun getClient(): GoogleSignInClient {
//        val gso = GoogleSignInOptions
//            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        return GoogleSignIn.getClient(requireActivity(), gso)
//    }
//
//
//    private fun signInWithGoogle() {
//        val signInClient = getClient()
//        launcher.launch(signInClient.signInIntent)
//    }
//
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential).addOnCompleteListener {
//            if (it.isSuccessful) {
//                findNavController().navigateUp()
//                findNavController().navigate(R.id.homeFragment)
//            }
//        }
//    }
//    private fun initFirebase() {
//        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                auth.signInWithCredential(credential).addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        makeText(requireContext(), "welcome", Toast.LENGTH_SHORT).show()
//                        findNavController().navigateUp()
//                        findNavController().navigate(R.id.registerNameFragment)
//                    } else {
//                        makeText(requireContext(), task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//
//            override fun onVerificationFailed(p0: FirebaseException) {
//                makeText(requireContext(), p0.message.toString(), Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
//                val bundle = Bundle()
//                findNavController().navigateUp()
//                findNavController().navigate(R.id.registerNameFragment, bundle)
//            }
//        }
//    }
    override fun initListener() {
        initEditListener()
        initButtonListener()
    }

    private fun initButtonListener() {
        binding.btnSignIn.setOnClickListener {
            if (binding.edtPassword.text.toString().length >= 8 ){
                Toast.makeText(requireActivity(), "Success", Toast.LENGTH_LONG).show()
                controller.navigate(R.id.registerNameFragment)
            }else Toast.makeText(requireActivity(), "Error", Toast.LENGTH_LONG).show()
        }
    }

    private fun initEditListener() {
      binding.edtPassword.addTextChangedListener(object : TextWatcher{
          override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

          }

          override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
              if (binding.edtPassword.text.toString().length >= 8){
                  binding.btnSignIn.setBackgroundResource(R.drawable.edt_register_bc)
              }else binding.btnSignIn.setBackgroundResource(R.drawable.btn_register_bc)
          }
          override fun afterTextChanged(s: Editable?) {
          }
      })
      binding.edtEmail.addTextChangedListener(object : TextWatcher{
          override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

          }

          override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

          }
          override fun afterTextChanged(s: Editable?) {
              if (binding.edtEmail.text.toString().isEmail()){
                  binding.btnSignIn.setOnClickListener {
                      Toast.makeText(requireActivity(), "Email", Toast.LENGTH_SHORT).show()
                  }
              }else{
                  binding.btnSignIn.setOnClickListener {
                      Toast.makeText(requireActivity(), "is not Email", Toast.LENGTH_SHORT).show()
                  }
              }
          }
      })
    }

    override fun initView() {
//        auth = Firebase.auth
//        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
//            try {
//                val account = task.getResult(ApiException::class.java)
//                if (account != null) {
//                    account.idToken?.let { it1 -> firebaseAuthWithGoogle(it1) }
//                }
//            } catch (e: ApiException) {
//            }
//        }
//        initFirebase()
//        getClient()
//
//        binding.google.setOnClickListener {
//            signInWithGoogle()
//        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }
    }

    private fun reload() {

    }
}
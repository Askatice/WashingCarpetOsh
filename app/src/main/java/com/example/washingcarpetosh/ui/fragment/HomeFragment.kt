package com.example.washingcarpetosh.ui.fragment

import android.content.ContentValues.TAG
import android.util.Log
import com.example.washingcarpetosh.databinding.FragmentHomeBinding
import com.example.washingcarpetosh.ui.base.BaseFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment: BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
){
    val db = Firebase.firestore
    override fun initListener() {
        TODO("Not yet implemented")
    }

    override fun initView() {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

}
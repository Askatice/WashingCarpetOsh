package com.example.washingcarpetosh.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.provider.MediaStore
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.washingcarpetosh.R
import com.example.washingcarpetosh.databinding.FragmentAddNewOrderBinding
import com.example.washingcarpetosh.ui.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Suppress("DEPRECATION")
class AddNewOrderFragment : BaseFragment<FragmentAddNewOrderBinding>(
    FragmentAddNewOrderBinding::inflate), onClick {

    val db = Firebase.firestore

    private var camera = true
    private lateinit var getResult: ActivityResultLauncher<Intent>

    override fun initListener() {
        initImageClick()
        initFireStore()
//        val user = hashMapOf(
//            "first" to "Alan",
//            "middle" to "Mathison",
//            "last" to "Turing",
//            "born" to 1912
//        )
//
//        // Add a new document with a generated ID
//        db.collection("test")
//            .document().set(user)
//            .addOnSuccessListener { documentReference ->
//            }
//            .addOnFailureListener { e ->
//            }
    }


   private fun initFireStore() {
        binding.btnSave.setOnClickListener {
//            Toast.makeText(requireContext(), "dffvvdvdbvidfv", Toast.LENGTH_SHORT).show()
            val user = hashMapOf(
                "name" to binding.edtName.text.toString(),
                "surname" to binding.edtSurname.text.toString(),
                "status" to binding.txtStatus.text.toString(),
                "image" to binding.imgAddOrder.resources.toString(),
                "address" to binding.edtAddress.text.toString(),
                "number" to binding.edtNumber.text.toString(),
                "size" to binding.edtSize.text.toString()
            )

            db.collection("test").document().set(user).addOnSuccessListener{
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                }
        }
    }


    private fun initImageClick() {
        getResult =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()) {
                if(it.resultCode == Activity.RESULT_OK) {
                    if (camera) {
                        Glide.with(this).load(it.data?.extras?.get("data"))
                            .into(binding.imgAddOrder)
                    } else {
                        Glide.with(this).load(it.data?.data)
                            .into(binding.imgAddOrder)
                    }
                }
            }
        binding.imgAddOrder.setOnClickListener {
            BottomSheetFragment(this).show(requireActivity().supportFragmentManager, "qwerty")

        }
    }



    override fun initView() {
        initAdapterSpinner()
    }



    @SuppressLint("ResourceAsColor")
    private fun initAdapterSpinner() {
        val position = resources.getStringArray(R.array.Status)
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.rv_spinner,
            position)
        binding.spinnerStatus.setAdapter(adapter)
        binding.spinnerStatus.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, _ ->

                when(binding.spinnerStatus.text.toString()){
                    "Новый" ->{
                        binding.cvStatus.setCardBackgroundColor(Color.GREEN)
                        binding.txtStatus.text = binding.spinnerStatus.text.toString()
                    }
                    "Доставщик увидел" ->{
                        binding.cvStatus.setCardBackgroundColor(Color.CYAN)
                        binding.txtStatus.text = binding.spinnerStatus.text.toString()
                    }
                    "Доставщик едет" ->{
                        binding.cvStatus.setCardBackgroundColor(Color.YELLOW)
                        binding.txtStatus.text = binding.spinnerStatus.text.toString()
                    }
                    "Доставщик принес" ->{
                        binding.cvStatus.setCardBackgroundColor(R.color.purple)
                        binding.txtStatus.text = binding.spinnerStatus.text.toString()
                    }
                    "В мойке" ->{
                        binding.cvStatus.setCardBackgroundColor(Color.TRANSPARENT)
                        binding.txtStatus.text = binding.spinnerStatus.text.toString()
                    }
                    "В сушке" ->{
                        binding.cvStatus.setCardBackgroundColor(Color.BLUE)
                        binding.txtStatus.text = binding.spinnerStatus.text.toString()
                    }
                    "Упакуется" ->{
                        binding.cvStatus.setCardBackgroundColor(Color.RED)
                        binding.txtStatus.text = binding.spinnerStatus.text.toString()
                    }
                    "Доставляется" ->{
                        binding.cvStatus.setCardBackgroundColor(Color.MAGENTA)
                        binding.txtStatus.text = binding.spinnerStatus.text.toString()
                    }
                    "Завершено" ->{
                        binding.cvStatus.setCardBackgroundColor(Color.BLACK)
                        binding.txtStatus.text = binding.spinnerStatus.text.toString()
                    }
                }
            }
    }

    override fun click(index: Int) {
        when(index){
            0->{
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                camera = true
                getResult.launch(intent)
            }
            1->{
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                camera = false
                getResult.launch(intent)
            }
        }

    }
}
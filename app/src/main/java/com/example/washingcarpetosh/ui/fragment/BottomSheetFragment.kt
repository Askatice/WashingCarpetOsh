package com.example.washingcarpetosh.ui.fragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.example.washingcarpetosh.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


@Suppress("DEPRECATION")
class BottomSheetFragment(private val onClick: onClick) : BottomSheetDialogFragment(){
    private lateinit var binding: FragmentBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
        initClickListenerGallery()
    }



    private fun initClickListenerGallery() {

        binding.btnGallery.setOnClickListener {
            onClick.click(1)
            dismiss()

        }
    }

    private fun initClickListener() {
        binding.btnCamera.setOnClickListener {
            if (checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PermissionChecker.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 1)
            } else {
                onClick.click(0)
                dismiss()
          }
       }
        }
    }
interface onClick{
    fun click(index : Int)
}
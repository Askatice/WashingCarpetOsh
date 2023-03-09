package com.example.washingcarpetosh.ui.fragment

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import com.example.washingcarpetosh.R
import com.example.washingcarpetosh.databinding.FragmentRegisterNameBinding
import com.example.washingcarpetosh.ui.base.BaseFragment

class RegisterNameFragment : BaseFragment<FragmentRegisterNameBinding>(
    FragmentRegisterNameBinding::inflate
) {
    override fun initListener() {

    }

    override fun initView() {
        initAdapterSpinner()
    }


    private fun initAdapterSpinner() {
        val position = resources.getStringArray(R.array.Должность)
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.rv_spinner,
            position)
        binding.spinnerPosition.
        setDropDownBackgroundResource(
            R.drawable.btn_register_bc)
        binding.spinnerPosition.setAdapter(adapter)
        binding.spinnerPosition.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, _ ->
                makeText(requireActivity(),
                     binding.spinnerPosition.text.toString(),
                    LENGTH_SHORT
                ).show()
            }
    }
}
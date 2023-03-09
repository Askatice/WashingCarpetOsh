package com.example.washingcarpetosh.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.example.washingcarpetosh.R

@Suppress("UNREACHABLE_CODE")
abstract class BaseFragment<VB: ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
): Fragment() {
    private var _controller: NavController? = null
    private var _binding: VB? = null
     val binding get() = _binding!!
    val controller get() = _controller!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
            if (_binding == null)
                throw java.lang.IllegalArgumentException("Binding can not be null")

        val navController = (requireActivity().supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?)!!
        _controller = navController.navController
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    abstract fun initListener()

    abstract fun initView()
}
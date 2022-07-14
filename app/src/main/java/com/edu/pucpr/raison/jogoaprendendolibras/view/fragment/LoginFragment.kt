package com.edu.pucpr.raison.jogoaprendendolibras.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.edu.pucpr.raison.jogoaprendendolibras.R

class LoginFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
//        loading = Load.createLoadDialog(requireContext(), false)
        return inflater.inflate(R.layout.layout_fragment_login, container, false)
    }
}
package com.edu.pucpr.raison.jogoaprendendolibras.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edu.pucpr.raison.jogoaprendendolibras.R
import com.edu.pucpr.raison.jogoaprendendolibras.view.activity.BemVindoActivity
import com.edu.pucpr.raison.jogoaprendendolibras.view.activity.HomeActivity
import kotlinx.android.synthetic.main.layout_fragment_login.view.*

class LoginFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
//        loading = Load.createLoadDialog(requireContext(), false)
        return inflater.inflate(R.layout.layout_fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        botoes(view)
    }

    private fun botoes(view: View) {
        view.criarConta.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_criar_conta)
        }
        view.btnLogar.setOnClickListener {
            startActivity(Intent(requireContext(), HomeActivity::class.java))
            requireActivity().finish()
        }
    }
}
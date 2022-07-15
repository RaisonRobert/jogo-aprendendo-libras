package com.edu.pucpr.raison.jogoaprendendolibras.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edu.pucpr.raison.jogoaprendendolibras.R
import com.edu.pucpr.raison.jogoaprendendolibras.model.banco.BancodeDados
import com.edu.pucpr.raison.jogoaprendendolibras.model.util.Load
import com.edu.pucpr.raison.jogoaprendendolibras.model.util.Preferences
import com.edu.pucpr.raison.jogoaprendendolibras.view.activity.BemVindoActivity
import com.edu.pucpr.raison.jogoaprendendolibras.view.activity.HomeActivity
import kotlinx.android.synthetic.main.layout_fragment_login.view.*

class LoginFragment : Fragment(){
    private lateinit var loading: AlertDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.layout_fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setPreferencesLogin("", "")
        loading = Load.createLoadDialog(requireContext(), false)
        loading.show()
        if(verificarLogin()){
            startHome()
        }else{
            setPreferencesLogin("", "")
        }
        loading.dismiss()
        botoes(view)
    }

    private fun startHome() {
        startActivity(Intent(requireContext(), HomeActivity::class.java))
        requireActivity().finish()
    }

    private fun verificarLogin(): Boolean {
        if((Preferences.getEmail(requireContext()) == BancodeDados.Login.email)&&(Preferences.getSenha(requireContext()) == BancodeDados.Login.senha) ){
            return true
        }
        return false
    }

    private fun botoes(view: View) {
        view.criarConta.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_criar_conta)
        }
        view.btnLogar.setOnClickListener {
            if(confirmaLogin(view)){
                startHome()
            }
        }
    }

    private fun confirmaLogin(view: View): Boolean {
        val email = view.emailfieldtext.text.toString()
        val senha = view.passwordfield.text.toString()
        Log.i("teste","email: $email Senha: $senha")
//        if(verificarLogin()){
            setPreferencesLogin(email, senha)
//            return true
//        }
        return false
    }
    private fun setPreferencesLogin(email: String, senha: String) {
        Preferences.setEmail(requireContext(),email)
        Preferences.setSenha(requireContext(),senha)
        Log.i("teste","Preferences Armazenado>>> email: ${Preferences.getEmail(requireContext())} Senha: ${Preferences.getSenha(requireContext())}")

    }
}
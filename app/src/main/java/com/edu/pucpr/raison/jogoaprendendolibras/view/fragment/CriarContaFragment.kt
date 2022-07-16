package com.edu.pucpr.raison.jogoaprendendolibras.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.edu.pucpr.raison.jogoaprendendolibras.R
import com.edu.pucpr.raison.jogoaprendendolibras.model.banco.BancodeDados
import com.edu.pucpr.raison.jogoaprendendolibras.model.body.DadosLogin
import com.edu.pucpr.raison.jogoaprendendolibras.model.util.Ui
import com.edu.pucpr.raison.jogoaprendendolibras.view.activity.HomeActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.layout_fragment_criar_conta.*
import kotlinx.android.synthetic.main.layout_fragment_criar_conta.view.*

class CriarContaFragment: Fragment() {
    private lateinit var loading: AlertDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_criar_conta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading = Ui.createLoadDialog(requireContext(), false)
        botoes(view)
        imprimir()
    }

    private fun imprimir() {
        Log.i("teste", "Arquivos: ${BancodeDados.arquivosDadosCadastrado}")
    }

    private fun botoes(view: View) {
        view.btnCriarConta.setOnClickListener {
            cadastro(view)
        }
    }

    private fun cadastro(view: View) {
        val nome = view.nomefield.text.toString()
        val email = view.emailfieldtextcriarConta.text.toString()
        val senha = view.passwordfieldCriarConta.text.toString()
        val nomeLayout = view.findViewById<TextInputLayout>(R.id.nomeLayout)
        val emailLayout = view.findViewById<TextInputLayout>(R.id.emailLayoutcriarconta)
        val senhaLayout  = view.findViewById<TextInputLayout>(R.id.passwordLayoutCriarConta)
        nomeLayout.isErrorEnabled = true
        emailLayout.isErrorEnabled = true
        senhaLayout.isErrorEnabled = true
        if(nome.isNotEmpty()&&email.isNotEmpty()&&senha.isNotEmpty()) {
            if (emailValid(email)) {
                nomeLayout.isErrorEnabled = false
                emailLayout.isErrorEnabled = false
                senhaLayout.isErrorEnabled = false
                if (validaConta(email)){
                    BancodeDados.arquivosDadosCadastrado.add(DadosLogin(
                        nome = nome,
                        email = email,
                        senha = senha,
                        rank = null
                    ))
                    dialogContaCadastrada()
                }else {
                    emailLayout.error = getString(R.string.emailCadastrado)
                }
            } else {
                emailLayout.error = getString(R.string.notValidEmail)
            }
        }else
        if (nome.isEmpty()){
            emailLayout.isErrorEnabled = false
            senhaLayout.isErrorEnabled = false
            nomeLayout.error = getString(R.string.vazio)
        }else
        if(email.isEmpty()){
            nomeLayout.isErrorEnabled = false
            senhaLayout.isErrorEnabled = false
            emailLayout.error = getString(R.string.vazio)
        }else
        if (senha.isEmpty()){
            nomeLayout.isErrorEnabled = false
            emailLayout.isErrorEnabled = false
            senhaLayout.error = getString(R.string.vazio)
        }

    }

    private fun dialogContaCadastrada() {
        Ui.createModal(requireContext(),
            R.drawable.ic_sucesso,
            "Sucesso",
            "Conta Cadastrada Com Sucesso",
            "Bem vindo ao Aprendendo Libras")!!
            .setOnDismissListener {
                startHome()
            }
    }

    private fun validaConta(email: String): Boolean {
        BancodeDados.arquivosDadosCadastrado.forEach{
            if(email == it.email){
                return false
            }
        }
        return true
    }

    /**
     * Método criado para validação do Email
     * @return Boolean
     */
    private fun emailValid(email: String): Boolean {
        return !(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }
    private fun startHome() {
        startActivity(Intent(requireContext(), HomeActivity::class.java))
        requireActivity().finish()
    }
}
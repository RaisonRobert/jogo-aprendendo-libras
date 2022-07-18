package com.edu.pucpr.raison.jogoaprendendolibras.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edu.pucpr.raison.jogoaprendendolibras.R
import com.edu.pucpr.raison.jogoaprendendolibras.model.banco.BancodeDados
import com.edu.pucpr.raison.jogoaprendendolibras.model.util.Ui
import com.edu.pucpr.raison.jogoaprendendolibras.model.util.Preferences
import com.edu.pucpr.raison.jogoaprendendolibras.view.activity.HomeActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.layout_fragment_login.view.*

class LoginFragment : Fragment() {
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
        loading = Ui.createLoadDialog(requireContext(), false)
        if (verificarLogin()) {
            startHome()
        } else {
            setPreferencesLogin("", "")
        }
        botoes(view)
    }
    private fun startHome() {
        startActivity(Intent(requireContext(), HomeActivity::class.java))
        requireActivity().finish()
    }

    private fun verificarLogin(): Boolean {
        BancodeDados.arquivosDadosCadastrado.forEach {
            if ((Preferences.getEmail(requireContext()) == it.email) && (Preferences.getSenha(requireContext()) == it.senha)) {
                BancodeDados.dadosUser.nome = it.nome
                BancodeDados.dadosUser.email = it.email
                BancodeDados.dadosUser.senha = it.senha
                BancodeDados.dadosUser.pontos = it.pontos
                return true
            }
        }
        return false
    }

    /**
     * Método criado para o click dos botoes
     * @param criarConta
     * @param btnLogar
     */
    private fun botoes(view: View) {
        view.criarConta.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_criar_conta)
        }
        view.btnLogar.setOnClickListener {
            if (confirmaLogin(view)) {
                loading.dismiss()
                dialogLogin()
            }
            loading.dismiss()
        }
    }
    /**
     * Método criado para validação do Login
     * @return Boolean
     */
    private fun confirmaLogin(view: View): Boolean {
        loading.show()
        val email = view.emailfieldtext.text.toString()
        val senha = view.passwordfield.text.toString()
        val emailLayout = view.findViewById<TextInputLayout>(R.id.emailLayout)
        val senhaLayout = view.findViewById<TextInputLayout>(R.id.passwordLayout)
        emailLayout.isErrorEnabled = true
        senhaLayout.isErrorEnabled = true

        if (emailValid(email)) {
            Log.i("teste", "email: $email Senha: $senha")
            emailLayout.error = null
            setPreferencesLogin(email, senha)
            if (verificarLogin()) {
                setPreferencesLogin(email, senha)

                emailLayout.isErrorEnabled = false
                senhaLayout.isErrorEnabled = false
                return true
            } else
                senhaLayout.error = getString(R.string.notValidLogin)
                emailLayout.error = getString(R.string.notValidLogin)

        } else {
            emailLayout.error = getString(R.string.notValidEmail)
        }
        return false
    }

    private fun dialogLogin() {
        Ui.createModal(requireContext(),
            R.drawable.ic_sucesso,
            "Sucesso",
            "Login Efetuado Com Sucesso",
            "Bem vindo ao Aprendendo Libras")!!
            .setOnDismissListener {
                startHome()
        }

    }

    private fun setPreferencesLogin(email: String, senha: String) {
        Preferences.setEmail(requireContext(), email)
        Preferences.setSenha(requireContext(), senha)
    }

    /**
     * Método criado para validação do Email
     * @return Boolean
     */
    private fun emailValid(email: String): Boolean {
        return !(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }
}
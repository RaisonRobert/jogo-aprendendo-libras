package com.edu.pucpr.raison.jogoaprendendolibras.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edu.pucpr.raison.jogoaprendendolibras.R
import com.edu.pucpr.raison.jogoaprendendolibras.model.banco.BancodeDados
import com.edu.pucpr.raison.jogoaprendendolibras.model.util.Ui
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.layout_fragment_alterar_dados.view.*

class AlterarDadosFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_alterar_dados, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDadosAlterar(view)
        botao(view)
        setHeader(view)
    }

    private fun botao(view: View) {

        view.btnAlterar.setOnClickListener {
                         salvaDadosAlterados(view)
                 }
    }


    private fun salvaDadosAlterados(view: View) {
        val nome = view.nomefieldAlterar.text.toString()
        val senha = view.passwordfieldAlterar.text.toString()
        Log.i("teste", "nome: $nome")
        Log.i("teste", "senha: $senha")
        val nomeLayout = view.findViewById<TextInputLayout>(R.id.nomeLayoutAlterar)
        val senhaLayout  = view.findViewById<TextInputLayout>(R.id.passwordLayoutAlterar)
        nomeLayout.isErrorEnabled = true
       senhaLayout.isErrorEnabled = true
        nomeLayout.error = null
        senhaLayout.error = null
        if(validaCampos(nome,senha)) {
            Log.i("teste", "Alterado com sucesso")
            BancodeDados.arquivosDadosCadastrado.forEach {
                if (it.email == BancodeDados.dadosUser.email) {
                    it.nome = nome
                    it.senha = senha
                }
            }
            dialogLogin()
        }else if(nome.isEmpty()){
            nomeLayout.isErrorEnabled = false
            nomeLayout.error = "Digite seu nome"
        }else {
            senhaLayout.isErrorEnabled = false
            senhaLayout.error = "Digite a senha"
        }
    }
    private fun dialogLogin() {
        Ui.createModal(requireContext(),
            R.drawable.ic_sucesso,
            "Sucesso",
            "Alteração Efetuada Com Sucesso",
            "")!!
            .setOnDismissListener {
                startHome()
            }
    }
    private fun startHome() {
        findNavController().popBackStack(R.id.home, false)
    }
    private fun setHeader(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.title = ""
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val headerLayout = view.findViewById<View>(R.id.headerView)
        val titlePage = headerLayout.findViewById<TextView>(R.id.title)
        titlePage.setText("Alterar Dados")
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack(R.id.home, false)
        }
    }
    private fun validaCampos(nome: String, senha: String): Boolean {
        if(nome.isNotEmpty()&&senha.isNotEmpty()){
            return true
        }
        return false
    }


    private fun setDadosAlterar(view: View) {
        view.nomefieldAlterar.setText(BancodeDados.dadosUser.nome)
        view.emailfieldtextAlterar.setText(BancodeDados.dadosUser.email)
        view.emailfieldtextAlterar.isEnabled = false
        view.passwordfieldAlterar.setText(BancodeDados.dadosUser.senha)
    }
}
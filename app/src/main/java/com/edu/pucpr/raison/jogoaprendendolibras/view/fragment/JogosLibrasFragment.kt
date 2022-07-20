package com.edu.pucpr.raison.jogoaprendendolibras.view.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edu.pucpr.raison.jogoaprendendolibras.R
import com.edu.pucpr.raison.jogoaprendendolibras.model.banco.BancodeDados
import com.edu.pucpr.raison.jogoaprendendolibras.model.body.DadosImagem
import com.edu.pucpr.raison.jogoaprendendolibras.model.util.Ui
import kotlinx.android.synthetic.main.layout_fragment_jogos_libras.view.*

class JogosLibrasFragment: Fragment() {

    private lateinit var loading: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_jogos_libras, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading = Ui.createLoadDialog(requireContext(), false)
        setImage(view)
        setHeader(view)
        qtdPerguntas(view)
    }

    private fun qtdPerguntas(view: View) {
        if(BancodeDados.num == 11){
            dialogPontuacao(view)
        }
    }

    private fun dialogPontuacao(view: View) {
        Ui.createModal(requireContext(),
            R.drawable.ic_sucesso,
            "Parabéns",
            "Você acertou ${BancodeDados.pontos} ",
            "Aprendendo Libras")!!
            .setOnDismissListener {
                BancodeDados.dadosUser.pontos = BancodeDados.pontos
                Ui.AlteraDados(BancodeDados.dadosUser)
                findNavController().popBackStack(R.id.home, false)
            }
    }
    /**
     * Método criado para setar os itens da Header
     * @param view View
     */
    private fun setHeader(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.title = ""
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val headerLayout = view.findViewById<View>(R.id.headerView)
        val titlePage = headerLayout.findViewById<TextView>(R.id.title)
        titlePage.setText("Vamos Lá")
        view.txtContaPergunta.text = "Pergunta ${BancodeDados.num}"
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack(R.id.home, false)
        }
    }
    private fun setImage(view: View) {
        loading.show()
        val objImagem1 = BancodeDados.arquivosDeImagem.random()
        var objImagem2 = BancodeDados.arquivosDeImagem.random()
        if(objImagem1==objImagem2){
            objImagem2 = BancodeDados.arquivosDeImagem.random()
            setImage(view)
        }else {
            val image641: Bitmap = Ui.convertBase64ToBitmap(objImagem1.image64)!!
            val image642: Bitmap = Ui.convertBase64ToBitmap(objImagem2.image64)!!
            view.imgPrimeira.setImageBitmap(reduzBitmap(image641))
            view.imgSegunda.setImageBitmap(reduzBitmap(image642))
            loading.dismiss()
            pergunta(objImagem1,objImagem2,view)
        }
    }
    private fun pergunta(objImagem1: DadosImagem, objImagem2: DadosImagem, view: View) {
        var pergunta : MutableList<DadosImagem> = mutableListOf(objImagem1,objImagem2)
        var selecionada = pergunta.random()
        view.txtPergunta.text = "Qual das figuras acima é a ${selecionada.nomeImagem}?"
        view.imgPrimeira.setOnClickListener {
            if (objImagem1.nomeImagem == selecionada.nomeImagem){
                BancodeDados.pontos++
//                Log.i("teste", "ACERTOU:sua Pontuação ${BancodeDados.pontos}")
            }
            BancodeDados.num ++
           navigate()
        }
        view.imgSegunda.setOnClickListener {
            if(objImagem2.nomeImagem == selecionada.nomeImagem){
                BancodeDados.pontos++
//                Log.i("teste", "ACERTOU:sua Pontuação ${BancodeDados.pontos}")
            }
            BancodeDados.num ++
           navigate()
        }
    }

    private fun navigate() {
        findNavController().navigate(R.id.action_inicia_jogo_self2)
        val navController = findNavController()
        navController.popBackStack(R.id.inicia_jogo, false)
    }

    private fun reduzBitmap(bmpFotoRotation: Bitmap): Bitmap? {
        return Bitmap.createScaledBitmap(
            bmpFotoRotation,  //                (int) (bmpFotoRotation.getWidth() * 0.1), (int) (bmpFotoRotation.getHeight() * 0.1)
            250, 250
            , true
        )
    }
}
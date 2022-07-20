package com.edu.pucpr.raison.jogoaprendendolibras.view.fragment

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edu.pucpr.raison.jogoaprendendolibras.R
import com.edu.pucpr.raison.jogoaprendendolibras.model.banco.BancodeDados
import com.edu.pucpr.raison.jogoaprendendolibras.model.body.DadosLogin
import com.edu.pucpr.raison.jogoaprendendolibras.model.util.Ui
import com.edu.pucpr.raison.jogoaprendendolibras.presenter.itemClickListenerRank
import com.edu.pucpr.raison.jogoaprendendolibras.view.adapter.RecyclerViewRank
import kotlinx.android.synthetic.main.layout_fragment_rank.view.*

class RankFragment : Fragment(), itemClickListenerRank {
    private lateinit var recycler_lista: RecyclerView
    private lateinit var adapterLista: RecyclerViewRank
    private lateinit var loading: AlertDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_rank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading = Ui.createLoadDialog(requireContext(), false)
        loading.show()
        setHeader(view)
        adapterLista = RecyclerViewRank(this)
        setupRecyclerView(view)
        addDados()
        loading.dismiss()
        zerarPontos(view)
    }

    private fun zerarPontos(view: View) {
        view.btnZerar.setOnClickListener {
            loading.show()
            BancodeDados.dadosUser.pontos = null
            BancodeDados.arquivosDadosCadastrado.forEach {
                if(it.email == BancodeDados.dadosUser.email){
                    it.pontos = BancodeDados.dadosUser.pontos
                }
            }
//            adapterLista.notifyDataSetChanged()
            navigate()
            loading.dismiss()
        }

    }
    private fun navigate() {
        findNavController().navigate(R.id.action_rank_self)
        val navController = findNavController()
        navController.popBackStack(R.id.rank, false)
    }
    private fun addDados() {
        BancodeDados.arquivosDadosCadastrado.sortByDescending { it.pontos }
        val listaOrdenada: MutableList<DadosLogin> = mutableListOf()
        BancodeDados.arquivosDadosCadastrado.forEach{
            if (it.pontos != null){
                listaOrdenada.add(it)
            }
        }
        adapterLista.popularLista(listaOrdenada)
    }
    private fun setupRecyclerView(view: View) {
        recycler_lista = view.findViewById(R.id.recyclerRank)
        recycler_lista.layoutManager = LinearLayoutManager(requireContext())
        recycler_lista.adapter = adapterLista

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
        titlePage.setText("Rank de Jogadores")
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack(R.id.home, false)
        }
    }

    override fun itemClick(dado: DadosLogin, position: Int) {
        Log.i("teste","dados click $dado")
    }


}
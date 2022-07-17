package com.edu.pucpr.raison.jogoaprendendolibras.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edu.pucpr.raison.jogoaprendendolibras.R
import com.edu.pucpr.raison.jogoaprendendolibras.model.banco.BancodeDados
import com.edu.pucpr.raison.jogoaprendendolibras.model.body.DadosLogin
import com.edu.pucpr.raison.jogoaprendendolibras.presenter.itemClickListenerRank
import com.edu.pucpr.raison.jogoaprendendolibras.view.adapter.RecyclerViewRank

class RankFragment : Fragment(), itemClickListenerRank {
    private lateinit var recycler_lista: RecyclerView
    private lateinit var adapterLista: RecyclerViewRank
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_rank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHeader(view)
        adapterLista = RecyclerViewRank(this)
        setupRecyclerView(view)
        addDados()
    }
    private fun addDados() {
        adapterLista.popularLista(BancodeDados.arquivosDadosCadastrado)
    }
    private fun setupRecyclerView(view: View) {
        recycler_lista = view.findViewById(R.id.recyclerRank)
        recycler_lista.layoutManager = LinearLayoutManager(requireContext())
        recycler_lista.adapter = adapterLista
        adapterLista.apply {
            itemListener = object :
                itemClickListenerRank{
                override fun itemClick(dado: DadosLogin, position: Int) {
                }
            }
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
        titlePage.setText("Rank de Jogadores")
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack(R.id.home, false)
        }
    }

    override fun itemClick(dado: DadosLogin, position: Int) {
        TODO("Not yet implemented")
    }


}
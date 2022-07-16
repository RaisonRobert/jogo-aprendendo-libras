package com.edu.pucpr.raison.jogoaprendendolibras.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.edu.pucpr.raison.jogoaprendendolibras.R
import com.edu.pucpr.raison.jogoaprendendolibras.model.util.Preferences
import com.edu.pucpr.raison.jogoaprendendolibras.view.activity.HomeActivity
import com.edu.pucpr.raison.jogoaprendendolibras.view.activity.LoginActivity

class HomeFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHeader(view)
    }
    /**
     * Método criado para setar os itens da Header
     * @param view View
     */
    private fun setHeader(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        setHasOptionsMenu(true)
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.title = ""
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val headerLayout = view.findViewById<View>(R.id.headerView)
        val titlePage = headerLayout.findViewById<TextView>(R.id.title)
        titlePage.setText("Aprendendo Libras")
    }

    @Suppress("DEPRECATION")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuSair -> {
                startLogin()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun startLogin() {
        setPreferencesLogin("", "")
        startActivity(Intent(requireContext(), LoginActivity::class.java))
        requireActivity().finish()
    }
    private fun setPreferencesLogin(email: String, senha: String) {
        Preferences.setEmail(requireContext(), email)
        Preferences.setSenha(requireContext(), senha)
    }
}
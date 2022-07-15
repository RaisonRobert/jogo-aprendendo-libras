package com.edu.pucpr.raison.jogoaprendendolibras.model

import android.content.Context
import android.content.SharedPreferences


object Preferences {
    private var preference: SharedPreferences? = null
    private val PREFS_FILENAME = "com.edu.pucpr.raison.jogoaprendendolibras.preferences"
    private var chaveAutenticacao: String? = null
    private const val preferenceTOKEN = "token"
    /**
     * Método para criar preferências compartilhadas na memória interna do device
     * @param context Contexto
     * @return SharedPreferences
     */
    private fun preferences(context: Context): SharedPreferences {
        if (preference == null) {
            preference = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        }
        return preference!!
    }
    fun saveToken(context: Context, token: String) {
        preferences(context).edit().putString(preferenceTOKEN, token).apply()
        chaveAutenticacao = token
    }
    /**
     * Método para retornar token do usuário na memória do device
     * @param context Contexto
     * @return String
     */
    fun getToken(context: Context): String? {
        if(chaveAutenticacao.isNullOrBlank()){
            val token = preferences(context)
                .getString(preferenceTOKEN, null)
            return token
        } else{
            return chaveAutenticacao
        }
    }
}
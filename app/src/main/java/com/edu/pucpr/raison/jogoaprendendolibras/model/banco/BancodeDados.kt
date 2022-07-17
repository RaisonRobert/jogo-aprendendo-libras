package com.edu.pucpr.raison.jogoaprendendolibras.model.banco

import com.edu.pucpr.raison.jogoaprendendolibras.model.body.DadosLogin

object BancodeDados {
    var User1 = (
            DadosLogin(
                nome = "Raison",
                email = "raison@pucpr.com",
                senha = "123",
                rank = 1,
                tempo = "02:00",
                pontos = 10
            ))
     var User2 = (
            DadosLogin(
                nome = "Robert",
                email = "robert@pucpr.com",
                senha = "123",
                rank = 2,
                tempo = "02:00",
                pontos = 9
            ))
     var User3 = (
            DadosLogin(
                nome = "Lucas",
                email = "lucas@pucpr.com",
                senha = "123",
                rank = 3,
                tempo = "02:00",
                pontos = 8
            ))
     var User4 = (
            DadosLogin(
                nome = "Rosângela",
                email = "rosangela@pucpr.com",
                senha = "123",
                rank = 4,
                tempo = "02:00",
                pontos = 7
            ))
    var User5 = (
            DadosLogin(
                nome = "Rosângela",
                email = "rosangela@pucpr.com",
                senha = "123",
                rank = 5,
                tempo = "02:00",
                pontos = 7
            ))
    var dadosUser = (
            DadosLogin(
                nome = "",
                email = "",
                senha = "",
                rank = null,
                tempo = null,
                pontos = null
            ))
    var arquivosDadosCadastrado: MutableList<DadosLogin> = mutableListOf(User1, User2, User3, User4, User5)
}
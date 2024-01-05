package com.poker.yks.data.game

import android.util.Log
import com.poker.yks.R

class PokerGame(
    val updateTable: UpdateTable,
    val myNick: String = "",
    var myTokens: Int,
    var vip:Int

) {
    var cardsOnTable = mutableListOf<Card>()
    var playersInGame = mutableListOf<PlayerInGameDTO>()
    var lastCall = 0
    var winPercentage: Float? = 0F
    var lastPlayerTokensPut = 0
    var tokensOnTable = 0
    var playerInMove = ""
    var mePlaying : PlayerInGameDTO? = null

init {
    Log.d("pizda1",updateTable.cardsOnTable.toString())
    Log.d("pizda1","mysz".toString())
    val cardList = updateTable.cardsOnTable.map { it.toCard() }
    Log.d("pizda1",cardList.toString())
    cardsOnTable.clear()
    cardsOnTable.addAll(cardList)

    playersInGame.clear()
    playersInGame.addAll(updateTable.playersInGame)
    Log.d("pizda1",playersInGame.toString())
    playerInMove = updateTable.nextPlayer!!
    Log.d("pizda1",playerInMove.toString())
    tokensOnTable = updateTable.tokensOnTable
    Log.d("pizda1",tokensOnTable.toString())
    lastCall = updateTable.lastCall
    Log.d("pizda1",lastCall.toString())

    val mePlayingDTO = playersInGame.find { it.nick == myNick }
    Log.d("pizda1",mePlayingDTO.toString())
    if (mePlayingDTO!= null){
        try {
            winPercentage = mePlaying!!.winPercentage
        }
        catch (e:Exception){
            winPercentage = 0f
        }



    }
}
    //    fun updatePokerGame(updateTable: UpdateTable){
//        listOfPlayers = updateTable.playersStatus
//        cardsOnTable.apply {
//            clear()
//            addAll(
//                updateTable.cardsOnTable
//            )
//        }
//        lastPlayerTokensPut = updateTable.lastCall
//        tokensOnTable = updateTable.tokensOnTable
//        playerInMove = updateTable.nextPlayer
//
//    }
    fun isMyTurn():Boolean{
        return playerInMove == myNick
    }
    fun getPlayerCards(nick:String, card:Int):Int{
        val playerInGame = playersInGame.find { it.nick == nick }!!.toPlayerInGame()
        return if (card == 0){
            if (playerInGame.card1!= null) playerInGame.card1.image else R.drawable.backcard
        } else{
            if (playerInGame.card2!= null) playerInGame.card2.image else R.drawable.backcard
        }

    }
    fun isMoveValid(move: Move): Boolean {
        if (!isMyTurn()) return false

        return when (move.moveType) {
            "Fold" -> {
                true
            }

            "Call" -> {
                lastPlayerTokensPut <= myTokens
            }

            "Raise" -> {
                lastPlayerTokensPut < myTokens
            }

            else -> { //all in
                true
            }
        }
    }
    fun refreshTable(updateTable: UpdateTable){

    }

}
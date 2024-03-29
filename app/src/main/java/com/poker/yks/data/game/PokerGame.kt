package com.poker.yks.data.game

import android.util.Log
import com.poker.yks.R

class PokerGame(
    val updateTable: UpdateTable,
    val myNick: String = "",
    var myTokens: Int,
    var vip: Int

) {
    var cardsOnTable = mutableListOf<Card>()
    var playersInGame = mutableListOf<PlayerInGameDTO>()
    var lastCall = 0
    var winPercentage: Float? = 0F
    var lastPlayerTokensPut = 0
    var tokensOnTable = 0
    var playerInMove = ""
    var mePlaying: PlayerInGameDTO? = null
    var isGameFinished = false

    init {
        val cardList = updateTable.cardsOnTable.map { it.toCard() }
        cardsOnTable.clear()
        cardsOnTable.addAll(cardList)
        playersInGame.clear()
        playersInGame.addAll(updateTable.playersInGame)
        tokensOnTable = updateTable.tokensOnTable
        lastCall = updateTable.lastCall
        mePlaying = playersInGame.find { it.nick == myNick }
        isGameFinished = false
        if (mePlaying != null) {
            if (mePlaying!!.winPercentage != null) {
                winPercentage = mePlaying!!.winPercentage
            } else {
                mePlaying!!.winPercentage = 0f
                winPercentage = 0f
            }
            playerInMove = updateTable.nextPlayer!!

        }
    }

    fun refreshTable(updateTable: UpdateTable) {
        cardsOnTable.clear()
        cardsOnTable.addAll(updateTable.cardsOnTable.map { it.toCard() })
        playersInGame.clear()
        playersInGame.addAll(updateTable.playersInGame)
        tokensOnTable = updateTable.tokensOnTable
        lastCall = updateTable.lastCall
        mePlaying = playersInGame.find { it.nick == myNick }
        isGameFinished = updateTable.isFinished
        if (mePlaying != null) {
            winPercentage = try {
                mePlaying!!.winPercentage
            } catch (e: Exception) {
                0f
            }
        }
        playerInMove = updateTable.nextPlayer!!
    }

    fun isMyTurn(): Boolean {
        return playerInMove == myNick
    }

    fun getPlayerCards(nick: String, card: Int): Int? {
//        Log.i("raczysko0",nick.toString())
//        Log.i("raczysko00",playersInGame.toString())
        try {
            val playerDTO = playersInGame.find { it.nick == nick }
//            Log.i("raczysko1",playerDTO.toString())
            val player = playerDTO?.toPlayerInGame()
//            Log.i("raczysko1",player.toString())
            if (player?.nick != myNick && !isGameFinished) {
                return R.drawable.backcard
            }
            return if (card == 0) {
                if (player!!.card1 != null) player.card1!!.image else R.drawable.backcard
            } else {
                if (player!!.card2 != null) player.card2!!.image else R.drawable.backcard
            }

        } catch (e: Exception) {
            Log.e("dupnik", e.toString())
            return null
        }


    }

    fun getTableCard(id: Int): Int {
        return try {
            cardsOnTable[id].image
        } catch (e: Exception) {
            R.drawable.backcard
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


}
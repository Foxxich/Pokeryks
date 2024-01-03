package com.poker.yks.data.game

class PokerGame(
    val updateTable: UpdateTable,
    val myNick: String = "",
    var myTokens: Int

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
    val cardList = updateTable.cardsOnTable.map { it.toCard() }
    cardsOnTable.clear()
    cardsOnTable.addAll(cardList)
    playersInGame.clear()
    playersInGame.addAll(updateTable.playersInGame)
    playerInMove = updateTable.nextPlayer
    tokensOnTable = updateTable.tokensOnTable
    lastCall = updateTable.lastCall

    val mePlayingDTO = playersInGame.find { it.nick == myNick }
    if (mePlayingDTO!= null){
        winPercentage = mePlaying!!.winPercentage



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
package com.poker.yks.data.game

class PokerGame(
    var listOfPlayers: List<PlayerInGame>,
    val Card1:Card,
    val Card2: Card,
    val myNick: String,
    val myTokens: Int

) {
    val cardsOnTable = mutableListOf<Card>()
    var winPercentage: Float = 0F
    var lastPlayerTokensPut = 0
    var tokensOnTable = 0
    var playerInMove = ""

    fun updatePokerGame(updateTable: UpdateTable){
        listOfPlayers = updateTable.playersStatus
        cardsOnTable.apply {
            clear()
            addAll(
                updateTable.cardsOnTable
            )
        }
        lastPlayerTokensPut = updateTable.lastCall
        tokensOnTable = updateTable.tokensOnTable
        playerInMove = updateTable.nextPlayer

    }
    fun isMoveValid(move:Move): Boolean{
        if (playerInMove != myNick) return false

        return when (move.moveType){
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
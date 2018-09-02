package com.montanher.models

import java.util.*
import kotlin.collections.ArrayList

class Board(
        val properties: ArrayList<Property>,
        val players: ArrayList<Player>) {

    var roundNumber = 0

    fun playRound() {
        players.forEach { player ->
            if (player.hasMoney()) {
                walk(player, throwDice())

                val currentProperty = properties[player.position]

                if (currentProperty.owner != null && currentProperty.owner != player) {
                    player.pay(currentProperty.owner!!, currentProperty.rent)
                    if (!player.hasMoney()) {
                        properties.filter { it.owner == player }
                                .forEach { it.owner = null }
                    }
                } else if (player.wantsToBuy(currentProperty)) {
                    player.buy(currentProperty)
                }
            }
        }
        roundNumber++
    }

    fun isGameFinished(): Boolean {
        var activePlayers = 0

        players.filter { it.hasMoney() }
                .map { activePlayers++ }

        return activePlayers <= 1
    }

    fun getWinnerPlayer(): Player? {
        val sortedPlayers = players.clone() as? ArrayList<Player>
        sortedPlayers?.sortBy { it.money }
        return sortedPlayers?.last()
    }

    private fun throwDice(): Int {
        return Random().nextInt(6) + 1
    }

    private fun walk(player: Player, squares: Int) {
        var currentPosition = player.position

        currentPosition += squares
        if (currentPosition >= properties.size) {
            currentPosition -= properties.size
            player.finishLap()
        }
        player.position = currentPosition
    }

}
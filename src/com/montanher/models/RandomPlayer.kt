package com.montanher.models

import java.util.*

class RandomPlayer(
        money: Double,
        position: Int,
        numberOfVictory: Int): Player(money, position, numberOfVictory) {

    override fun getPlayerType(): String {
        return "Random Player"
    }

    override fun wantsToBuy(property: Property): Boolean {

        val wantToBuy = Random().nextBoolean()

        return canBuyProperty(property) && wantToBuy
    }

}
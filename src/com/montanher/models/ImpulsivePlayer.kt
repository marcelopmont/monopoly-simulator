package com.montanher.models

class ImpulsivePlayer(
        money: Double,
        position: Int,
        numberOfVictory: Int): Player(money, position, numberOfVictory) {

    override fun getPlayerType(): String {
        return "Impulsive Player"
    }

    override fun wantsToBuy(property: Property): Boolean {
        return canBuyProperty(property)
    }

}
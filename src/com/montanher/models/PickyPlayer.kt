package com.montanher.models

class PickyPlayer(
        money: Double,
        position: Int,
        numberOfVictory: Int): Player(money, position, numberOfVictory) {
    companion object {
        const val MINIMUM_RENT_VALUE = 50
    }

    override fun getPlayerType(): String {
        return "Picky Player"
    }

    override fun wantsToBuy(property: Property): Boolean {
        return canBuyProperty(property) && property.rent > MINIMUM_RENT_VALUE
    }

}
package com.montanher.models

class CautiousPlayer(
        money: Double,
        position: Int,
        numberOfVictory: Int): Player(money, position, numberOfVictory) {

    companion object {
        const val MARGIN_VALUE_TO_BUY = 80
    }

    override fun getPlayerType(): String {
        return "Cautious Player"
    }

    override fun wantsToBuy(property: Property): Boolean {
        val moneyAfterPurchase = money - property.price

        return canBuyProperty(property) && moneyAfterPurchase >= MARGIN_VALUE_TO_BUY
    }

}
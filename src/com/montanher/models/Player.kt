package com.montanher.models

open class Player(
    var money: Double,
    var position: Int,
    var totalNumberOfVictory: Int) {

    companion object {
        const val MONEY_TO_EARN_WHEN_FINISH_LAP = 100
    }

    open fun wantsToBuy(property: Property): Boolean {
        return false
    }

    open fun getPlayerType(): String {
        return "Generic Player"
    }

    fun finishLap() {
        money += MONEY_TO_EARN_WHEN_FINISH_LAP
    }

    fun hasMoney(): Boolean {
        return money > 0
    }

    fun buy(property: Property) {
        money -= property.price
        property.owner = this
    }

    fun pay(player: Player, value: Double) {
        var valueToPay = value
        if (money < valueToPay) {
            valueToPay = money
        }
        money -= valueToPay
        player.money += valueToPay
    }

    protected fun canBuyProperty(property: Property): Boolean {
        return property.owner == null && property.price <= money
    }
}
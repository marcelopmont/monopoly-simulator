package com.montanher

import com.montanher.models.*
import java.io.File

class Main {

    companion object {

        const val START_PLAYER_MONEY = 300.0
        const val MAX_NUMBER_ROUNDS = 1000
        const val NUMBER_OF_MATCHES = 3000

        var players = loadPlayers()
        var properties = loadProperties()

        var totalTimeOutMatches = 0
        var totalRounds: Long = 0

        @JvmStatic
        fun main(args: Array<String>) {

            for (i in (0..NUMBER_OF_MATCHES)) {
                val board = Board(properties, players)

                executeMatch(board)

                analyzeMatch(board)

                resetValues()
            }

            showResults()
        }

        private fun executeMatch(board: Board) {
            while (!board.isGameFinished() && board.roundNumber <= MAX_NUMBER_ROUNDS) {
                board.playRound()
            }
        }

        private fun analyzeMatch(board: Board) {
            if (board.roundNumber >= MAX_NUMBER_ROUNDS) {
                totalTimeOutMatches++
            } else {
                totalRounds += board.roundNumber
            }

            board.getWinnerPlayer()!!.totalNumberOfVictory++
        }

        private fun showResults() {
            println("\nTotal of time outs: ${totalTimeOutMatches}")
            if (NUMBER_OF_MATCHES - totalTimeOutMatches > 0) {
                println("Rounds average: ${totalRounds/(NUMBER_OF_MATCHES - totalTimeOutMatches)}")
            }

            players.sortByDescending { it.totalNumberOfVictory }
            println("\nWinner rate")
            players.forEach {
                println(String.format("%s: %.2f%%", it.getPlayerType(), it.totalNumberOfVictory.toDouble()/NUMBER_OF_MATCHES))
            }
        }

        private fun resetValues() {
            players.forEach {
                it.money = START_PLAYER_MONEY
                it.position = -1
            }

            val playerWhoStarted = players.first()
            players.remove(playerWhoStarted)
            players.add(playerWhoStarted)

            properties.forEach {
                it.owner = null
            }
        }

        private fun loadPlayers(): ArrayList<Player> {
            return arrayListOf (
                    ImpulsivePlayer(START_PLAYER_MONEY, -1, 0),
                    PickyPlayer(START_PLAYER_MONEY, -1, 0),
                    CautiousPlayer(START_PLAYER_MONEY, -1, 0),
                    RandomPlayer(START_PLAYER_MONEY, -1, 0)
            )
        }

        private fun loadProperties(): ArrayList<Property> {
            val defaultProperties = ArrayList<Property>()

            val fileName = "gameConfig.txt"

            val lines: List<String> = File(fileName).readLines()

            lines.forEach { line ->
                val inputLine = line.split(" ")

                if (inputLine.isNotEmpty()) {
                    defaultProperties.add(Property(inputLine.first().toDouble(), inputLine.last().toDouble(), null))
                }
            }
            return defaultProperties
        }
    }
}
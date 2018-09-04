## How to run:
  The code was developed with IntelliJ IDE, one option is to open the project with
interface and click on run button.

  To execute using terminal:
  - Open project folder and execute: java -cp bankrupt.jar com.montanher.Main


## How to compile using terminal:
  - First you need kotlin installed:
  brew install kotlin

  - Open project folder and compile with
  kotlinc . -include-runtime -d bankrupt.jar


## Parameters:
  This code was made for easily change some parameters if necessary.
  The class "Main" has the logic of the simulation, at there we can change the
initial amount of money of each player (START_PLAYER_MONEY), limit of rounds for
each match (MAX_NUMBER_ROUNDS) and the total number of match of the simulation
(NUMBER_OF_MATCHES). In the function "loadPlayer" is possible change the types
of players in the match, we have 4 types: impulsive, picky, cautious and random.
If you you like a match with two impulsive players and other cautious one,
the method would be:
  private fun loadPlayers(): ArrayList<Player> {
    return arrayListOf (
      ImpulsivePlayer(START_PLAYER_MONEY, -1, 0),
      ImpulsivePlayer(START_PLAYER_MONEY, -1, 0),
      CautiousPlayer(START_PLAYER_MONEY, -1, 0)
    )
  }
  You must notice that you can change the numbers of player as well.

  In the class Player it's possible to change the amount of money each player
earns when finishes one lap in the board(MONEY_TO_EARN_WHEN_FINISH_LAP).

  To change number of squares of board and values of bought and rent, you must
edit the gameConfig.txt file.

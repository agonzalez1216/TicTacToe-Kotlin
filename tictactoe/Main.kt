package tictactoe
const val XWINS = "X X X"
const val OWINS = "O O O"
const val space = ' '
fun main() {
    var xWins = false
    var oWins = false
    val validRange = 1..3
    var playerTurn = 1
    var playGame = true
    var gameGrid: MutableList<MutableList<Char>> = mutableListOf(
        MutableList(3) { ' ' },
        MutableList(3) { ' ' },
        MutableList(3) { ' ' }
    )

    
    println("---------")
    println("|       |")
    println("|       |")
    println("|       |")
    println("---------")

    loop@while (playGame) {
        var emptyCells = false
        println("Enter the coordinates: ")
        var (a, b) = readLine()!!.split(' ').map { it.toIntOrNull() }
        if (a == null || b == null) {
            println("You should enter numbers!")
        } else if (a in validRange && b in validRange) {
            a--
            b--
            if (gameGrid[a][b] == 'X' || gameGrid[a][b] == 'O') {
                println("This cell is occupied! Choose another one!")
            } else {
                when (playerTurn % 2 == 0) {
                    false -> gameGrid[a][b] = 'X'
                    else -> gameGrid[a][b] = 'O'
                }
                playerTurn++
            }
        } else {
            println("Coordinates should be from 1 to 3!")
        }

        println("---------")
        println("| ${gameGrid[0][0]} ${gameGrid[0][1]} ${gameGrid[0][2]} |")
        println("| ${gameGrid[1][0]} ${gameGrid[1][1]} ${gameGrid[1][2]} |")
        println("| ${gameGrid[2][0]} ${gameGrid[2][1]} ${gameGrid[2][2]} |")
        println("---------")

        var gameState = mutableListOf(
            mutableListOf(gameGrid[0][0], gameGrid[0][1], gameGrid[0][2]),
            mutableListOf(gameGrid[1][0], gameGrid[1][1], gameGrid[1][2]),
            mutableListOf(gameGrid[2][0], gameGrid[2][1], gameGrid[2][2]),
            mutableListOf(gameGrid[0][0], gameGrid[1][0], gameGrid[2][0]),
            mutableListOf(gameGrid[0][1], gameGrid[1][1], gameGrid[2][1]),
            mutableListOf(gameGrid[0][2], gameGrid[1][2], gameGrid[2][2]),
            mutableListOf(gameGrid[0][0], gameGrid[1][1], gameGrid[2][2]),
            mutableListOf(gameGrid[2][0], gameGrid[1][1], gameGrid[0][2])
        )

        for (list in gameState) {
            var listString = list.joinToString(" ")
            if (listString == XWINS) {
                xWins = true
                break@loop
            } else if (listString == OWINS) {
                oWins = true
                break@loop
            }
        }

        for (list in gameGrid) {
            if (list.contains(space)) emptyCells = true
        }

        if(!emptyCells) break@loop
    }

    if (xWins) {
        print("X wins")
    } else if (oWins) {
        print("O wins")
    } else {
        print("Draw")
    }
}


package tictactoe

import kotlin.math.pow

class Board(private val n: Int) {

    private val empty = "_"
    private var moveCount = 0
    var isGameOver = false
    private var board = Array(n) { Array(n) { empty } }

    fun printBoard() {
        board.forEach { row ->
            row.forEach { element ->
                print("|$element|")
            }
            println()
        }
        println()
    }

    fun placePiece(x: Int, y: Int, move: String) {
        if (!isGameOver
            && isPositionValid(x, y)
            && board[x][y] == empty
        ) {
            moveCount++
            board[x][y] = move
            printBoard()
            isGameOver = isWinningMove(x, y, move) || isDraw()
            if (isGameOver && !isDraw()) {
                println("$move wins")
            } else if (isDraw()) {
                println("Draw")
            }
        }
    }

    private fun isPositionValid(x: Int, y: Int): Boolean {
        return ((x in 0 until n) && (y in 0 until n))
    }

    private fun isWinningMove(x: Int, y: Int, move: String): Boolean {
        //row check
        for (i in 0 until n) {
            if (board[x][i] != move) {
                break
            }
            if (i == n - 1) {
                return true
            }
        }

        //col check
        for (i in 0 until n) {
            if (board[i][y] != move) {
                break
            }
            if (i == n - 1) {
                return true
            }
        }

        //diagonal check
        if (x == y) {
            for (i in 0 until n) {
                if (board[i][i] != move) {
                    break
                }
                if (i == n - 1) {
                    return true
                }
            }
        }

        //anti diag
        if (x + y == n - 1) {
            for (i in 0 until n) {
                if (board[i][(n - 1) - i] != move) {
                    break
                }
                if (i == n - 1) {
                    return true
                }
            }
        }

        return false
    }

    fun isDraw(): Boolean {
        return (moveCount == (n.toDouble().pow(2) - 1).toInt())
    }
}
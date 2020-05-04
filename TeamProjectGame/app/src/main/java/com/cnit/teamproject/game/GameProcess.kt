package com.cnit.teamproject.game

import android.util.Log
import kotlin.random.Random

class GameProcess {

    interface CallBacks {
        fun onPlayerMove(oldX : Int, oldY : Int, newX : Int, newY : Int) : Boolean
    }

    val moveLeft = 0
    val moveUp = 1
    val moveRight = 2
    val moveDown = 3

    var callbacks: CallBacks? = null

    private val fillChance = 0.2

    private val gridWidth = 4
    private val gridHeight = 4

    var gridArray = arrayOf<Array<Int>>()

    init {
        for(x in 0 until gridWidth) {

            var tempArr = arrayOf<Int>()

            for(y in 0 until gridHeight) {
                tempArr += 0
            }

            gridArray += tempArr
        }
    }

    fun randomFill() {

        for(y in 0 until gridHeight) {
            for(x in 0 until gridWidth) {
                if(Random.nextDouble() < fillChance) {
                    if(gridArray[x][y] == 0) {
                        gridArray[x][y] = 1 + Random.nextInt(0, 3)
                    }
                }
            }
        }
    }

    fun slideNumbers(inDirNum: Int) {

        //Did the user swipe vertically or horizontally
        val isHorizontal = (inDirNum == 0 || inDirNum == 2)

        //Which direction left/right or up/down
        val invertedDirection = (inDirNum == 0 || inDirNum == 3)

        //Convert $invertedDirection into a number we can use in our loops
        val offset = if(invertedDirection) 1 else -1

        //
        var endW = gridWidth
        var endH = gridHeight

        if(isHorizontal) {
            endW = gridHeight
            endH = gridWidth
        }

        Log.println(Log.DEBUG, "GameDebug", "Dir: $inDirNum, Hor: $isHorizontal, Inv: $invertedDirection")
//        println("Dir: $inDirNum, Hor: $isHorizontal, Inv: $invertedDirection")
//        println("-----")

        for(rawI in 0 until endW) {

            val i = if(invertedDirection) (endW - 1) - rawI else rawI

            for (j in 0 until endH) {

                val colNum : Int
                val rowNum : Int

                if(isHorizontal) {
                    colNum = i
                    rowNum = j
                }
                else {
                    colNum = j
                    rowNum = i
                }

                if(gridArray[colNum][rowNum] != 0) {

                    var moveCol : Int = colNum
                    var moveRow : Int = rowNum


                    //Stepping forward to move tile
                    for(k in 0 until (endW - (i * offset))) {

                        val realK = k * offset

                        val adjK = if(isHorizontal) colNum + realK
                        else rowNum + realK

                        if(adjK < 0) break

                        var tempC : Int = colNum
                        var tempR : Int = rowNum

                        if(isHorizontal) tempC = adjK
                        else tempR = adjK

                        if(colNum != tempC || rowNum != tempR) {

                            if(gridArray[tempC][tempR] == 0 || gridArray[tempC][tempR] == gridArray[colNum][rowNum]) {
                                moveCol = tempC
                                moveRow = tempR
                            }
                            else break
                        }
                    }

                    if(moveCol != colNum || moveRow != rowNum) {

                        if(gridArray[moveCol][moveRow] == 0) {
                            gridArray[moveCol][moveRow] = gridArray[colNum][rowNum]
                        }
                        else if(gridArray[moveCol][moveRow] == gridArray[colNum][rowNum]) {
                            gridArray[moveCol][moveRow]++
                        }

                        gridArray[colNum][rowNum] = 0

                        callbacks?.onPlayerMove(colNum, rowNum, moveCol, moveRow)
                        Log.println(Log.DEBUG, "GameDebug", "$colNum, $rowNum -> $moveCol, $moveRow")
                        //println("$colNum, $rowNum -> $moveCol, $moveRow")
                    }
                }
            }
        }

       // printArray()
    }

    fun printArray() {

        for(y in 0 until gridHeight) {

            var str = ""

            for(x in 0 until gridWidth) {
                str += gridArray[x][y]
                if(x != gridWidth -1) str += " "
            }

            Log.println(Log.DEBUG, "GameDebug", str)
        }
    }
}
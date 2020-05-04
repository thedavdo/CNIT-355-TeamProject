package com.cnit.teamproject.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cnit.teamproject.R
import com.cnit.teamproject.game.GameProcess

class GameFragment : Fragment(), GameProcess.CallBacks  {

    private val swipeDistance : Float = 150f

    private var xStart : Float = 0f
    private var yStart : Float = 0f

    private lateinit var game : GameProcess

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        game = GameProcess()
        game.callbacks = this

        game.randomFill()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_game, container, false)

        view.setOnTouchListener { v: View, event: MotionEvent ->

            when(event.action) {

                MotionEvent.ACTION_DOWN -> {
                    xStart = event.x
                    yStart = event.y
                }
                MotionEvent.ACTION_UP -> {
                    val dx = xStart - event.x
                    val dy = yStart - event.y

                    val dxAbs = kotlin.math.abs(dx)
                    val dyAbs = kotlin.math.abs(dy)

                    if(dxAbs > swipeDistance || dyAbs > swipeDistance) {

                        if(dxAbs > dyAbs) {
                            if(dx > 0) onSwipe(game.moveRight)
                            else onSwipe(game.moveLeft)
                        }
                        else {
                            if(dy < 0) onSwipe(game.moveDown)
                            else onSwipe(game.moveUp)
                        }
                    }
                }
            }
            return@setOnTouchListener true
        }

        return view
    }

    override fun onPlayerMove(oldX: Int, oldY: Int, newX: Int, newY: Int): Boolean {
        TODO("not implemented")
    }

    private fun onSwipe(direction : Int) {
        game.slideNumbers(direction)
    }
}

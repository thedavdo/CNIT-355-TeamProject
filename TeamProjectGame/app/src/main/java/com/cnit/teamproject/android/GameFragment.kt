package com.cnit.teamproject.android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.cnit.teamproject.R
import com.cnit.teamproject.game.GameProcess

class GameFragment : Fragment(), GameProcess.CallBacks, BackPressedListener  {

    private val swipeDistance : Float = 150f

    private var xStart : Float = 0f
    private var yStart : Float = 0f

    private lateinit var game : GameProcess

    private var mConfirmBack: AlertDialog? = null

    private var mGameOver: AlertDialog? = null

    private var numberDisp : TextView? = null
    private var scoreDisp : TextView? = null

    private var shouldQuit : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        game = GameProcess()
        game.callbacks = this

        game.randomFill(true)
    }

    override fun onBackPressed(): Boolean {

        if(!shouldQuit) mConfirmBack?.show()

        return !shouldQuit
    }

    private fun updateDisplay() {
        numberDisp?.text = game.arrayToString()
        scoreDisp?.text = "Score: ${game.score}"
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

        numberDisp = view.findViewById(R.id.display_grid)
        scoreDisp = view.findViewById(R.id.score)

        updateDisplay()

        val builder = AlertDialog.Builder(inflater.context)
        builder.setMessage("Quit game?")
        builder.setPositiveButton("QUIT") { _, _ ->
            shouldQuit = true
            activity?.onBackPressed()
        }
        builder.setNegativeButton("CANCEL") { _, _ -> }
        builder.setOnDismissListener {}

        mConfirmBack = builder.create()

        val gameOverBuilder = AlertDialog.Builder(inflater.context)
        gameOverBuilder.setMessage("GAME OVER!")
        gameOverBuilder.setPositiveButton("RETURN TO MAIN MENU") { _, _ ->
            shouldQuit = true
            activity?.onBackPressed()
        }
        gameOverBuilder.setOnDismissListener {}

        mGameOver = gameOverBuilder.create()

        return view
    }

    private var hasChanges = false

    override fun onPlayerMove(oldX: Int, oldY: Int, newX: Int, newY: Int): Boolean {

       // Log.println(Log.DEBUG, "test", "test");

        hasChanges = true

        return false
    }

    private fun onSwipe(direction : Int) {

        hasChanges = false
        game.slideNumbers(direction)

        if(game.isGameOver()) {
            mGameOver?.show()
        }
        else {
            if(hasChanges) game.randomFill(false)
            updateDisplay()
        }
    }
}

package com.cnit.teamproject.android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cnit.teamproject.R

import com.cnit.teamproject.game.Score

class ScoreListFragment : Fragment() {

    private lateinit var scoreRecyclerView: RecyclerView
    private lateinit var scoreListViewModel: ScoreListViewModel

    private var adapter : ScoreAdapter? = ScoreAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scoreListViewModel = ViewModelProvider(this).get(ScoreListViewModel::class.java)
        Log.println(Log.DEBUG, "Scores", "WHAT?")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_score_list, container, false)

        scoreRecyclerView = view.findViewById(R.id.score_recycler_view)
        scoreRecyclerView.layoutManager = LinearLayoutManager(context)

        scoreRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        scoreListViewModel.scoreListLiveData.observe(viewLifecycleOwner, Observer { scores ->
            updateDisplay(scores)
        })

        Log.println(Log.DEBUG, "Scores", "loading")
    }


    private fun updateDisplay(scores: List<Score>) {

        adapter = ScoreAdapter(scores)
        scoreRecyclerView.adapter = adapter

//        Log.println(Log.DEBUG, "Scores", "loading scores")
//
//        for (score in scores) {
//            Log.println(Log.DEBUG, "Scores", "score: ${score.name}, ${score.score}")
//        }
    }

    private inner class ScoreHolder(view: View) : RecyclerView.ViewHolder(view) {

        val scoreValue : TextView = itemView.findViewById(R.id.score_value)
        val scoreOwner : TextView = itemView.findViewById(R.id.score_owner)

        lateinit var score : Score

        fun bind(score: Score) {

            this.score = score

            scoreValue.text = "${score.score}"
            scoreOwner.text = score.name
        }
    }

    private inner class ScoreAdapter(var scores: List<Score>) : RecyclerView.Adapter<ScoreHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreHolder {
            val view = layoutInflater.inflate(R.layout.list_item_score, parent, false)
            return ScoreHolder(view)
        }

        override fun onBindViewHolder(holder: ScoreHolder, position: Int) {

            val  score : Score? = scores[position]

            score?.let {
                holder.bind(it)
            }
        }

        override fun getItemCount(): Int {
            return scores.size
        }
    }
}
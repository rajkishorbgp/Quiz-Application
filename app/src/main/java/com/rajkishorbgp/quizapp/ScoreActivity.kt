package com.rajkishorbgp.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rajkishorbgp.quizapp.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.score.text = "Congratulation!!! your score is ${intent.getIntExtra("SCORE",0)}"
        binding.secondaryScore.text="${intent.getIntExtra("SCORE",0)}/${intent.getIntExtra("COUNT",0)}"
    }
}
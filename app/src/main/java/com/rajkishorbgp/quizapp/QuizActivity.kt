package com.rajkishorbgp.quizapp

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.rajkishorbgp.quizapp.databinding.ActivityLoginBinding
import com.rajkishorbgp.quizapp.databinding.ActivityQuizBinding


class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var list: ArrayList<QuestionModel>
    private var count = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList<QuestionModel>()

        Firebase.firestore.collection("quiz").get().addOnSuccessListener { dock ->
            for (i in dock.documents) {
                val questionModel = i.toObject(QuestionModel::class.java)
                list.add(questionModel!!)
            }
            if (list.isNotEmpty()) {
                binding.question.text = list[0].question
                binding.option1.text = list[0].option1
                binding.option2.text = list[0].option2
                binding.option3.text = list[0].option3
                binding.option4.text = list[0].option4
            }
        }
        binding.option1.setOnClickListener{
            countScore(binding.option1.text.toString())
        }
        binding.option2.setOnClickListener{
            countScore(binding.option2.text.toString())
        }
        binding.option3.setOnClickListener{
            countScore(binding.option3.text.toString())
        }
        binding.option4.setOnClickListener{
            countScore(binding.option4.text.toString())
        }

        binding.next.setOnClickListener {
            count++
            if (count>=list.size){
                val intent = Intent(this,ScoreActivity::class.java)
                intent.putExtra("SCORE",score)
                intent.putExtra("COUNT",count)
                startActivity(intent)
                finish()
            }else{
                // To clear the selection:
                binding.optionsRadioGroup.clearCheck()
                binding.questionCount.text="${this.count +1}."
                binding.question.text = list[count].question
                binding.option1.text = list[count].option1
                binding.option2.text = list[count].option2
                binding.option3.text = list[count].option3
                binding.option4.text = list[count].option4
            }
        }
    }

    private fun countScore(i: String) {
        if (count<list.size) {
            if (list[count].answer == i) {
                score++
            }
        }
    }
}
package com.haero_kim.jetpack_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var numberViewModel: NumberViewModel

    lateinit var numberTextView: TextView
    lateinit var userInputEditText: EditText
    lateinit var plusButton: Button
    lateinit var minusButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberTextView = findViewById<TextView>(R.id.number_textview)
        plusButton= findViewById<Button>(R.id.plus_button)
        minusButton = findViewById<Button>(R.id.minus_button)
        userInputEditText = findViewById<EditText>(R.id.userinput_edittext)

        // ViewModel Provider 통해 ViewModel 인스턴스 생성
        // LifeCycle 을 가지고 있는 녀석을 넣어줌 (자기 자신)
        // 생성하고 싶은 (가져오고 싶은) ViewModel Class 를 명시
        numberViewModel = ViewModelProvider(this).get(NumberViewModel::class.java)

        // currentValue 를 Observing 하는 오너가 MainActivity 임을 명시
        numberViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - numberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            numberTextView.text = it.toString()
        })

        plusButton.setOnClickListener(this)
        minusButton.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val userInput = userInputEditText.text.toString().toInt()

        when(v){
            plusButton -> {
                numberViewModel.updateValue(actionType = ActionType.PLUS, userInput)
            }
            minusButton -> {
                numberViewModel.updateValue(actionType = ActionType.MINUS, userInput)
            }
        }
    }

    companion object {
        private const val TAG = "로그"
    }
}
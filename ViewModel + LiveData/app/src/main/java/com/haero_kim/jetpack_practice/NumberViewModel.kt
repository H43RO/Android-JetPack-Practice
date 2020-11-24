package com.haero_kim.jetpack_practice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// 데이터의 변경
// 뷰 모델은 데이터의 변경사항을 알려주는 라이브 데이터를 갖고 있음

enum class ActionType {
    PLUS,
    MINUS
}

class NumberViewModel : ViewModel() {
    companion object {
        private const val TAG = "로그"
    }

    // Mutable LiveData ( 수정 가능한 데이터 )
    // LiveData ( 수정 불가능한 데이터 - Immutable )

    // 내부에서 설정하는 자료형은 뮤터블로 변경 가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()

    // Getter 메소드 생성
    val currentValue: LiveData<Int>
        get() = _currentValue

    // 초기 값 설정 (생성자)
    init {
        Log.d(TAG, "NumverViewModel 생성자 호출")
        _currentValue.value = 0
    }

    // ViewModel 이 갖고 있는 값을 변경하는 메소드
    fun updateValue (actionType: ActionType, input: Int){
        when(actionType){
            ActionType.PLUS ->{
                _currentValue.value = _currentValue.value?.plus(input)
            }
            ActionType.MINUS ->{
                _currentValue.value = _currentValue.value?.minus(input)
            }

        }
    }

}
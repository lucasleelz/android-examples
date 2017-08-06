package com.lucaslz.geoquizforkotlin

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/8/6.
 */
data class Question(val textResId: Int, val answerTrue: Boolean) {

    var hasAnswered = false

    var isCheater = false
}
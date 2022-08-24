package me.maxzmeng.calculator.data

// 所有的仅横屏的按钮

val PLACE_HOLDER = PlaceHolderOperation

//平方
val SQUARE = ImmediateCalculateOperation("X²") { input ->
    return@ImmediateCalculateOperation input.pow(2)
}

//三次方
val CUBE = ImmediateCalculateOperation("X³") { input ->
    return@ImmediateCalculateOperation input.pow(3)
}

//次方
val POWER = MathCalculateOperation("x^y") { input1, input2 ->
    return@MathCalculateOperation input1

}
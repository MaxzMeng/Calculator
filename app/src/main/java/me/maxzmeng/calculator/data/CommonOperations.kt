package me.maxzmeng.calculator.data

import java.math.BigDecimal

// 所有的竖屏&横屏的按钮

val AC = ACOperation

val NEGATIVE = ImmediateCalculateOperation("±") { num ->
    return@ImmediateCalculateOperation num.negate()
}

val PERCENT = ImmediateCalculateOperation("%") { num ->
    return@ImmediateCalculateOperation num.divide(BigDecimal.valueOf(100L))
}

val DIVIDE = MathCalculateOperation("/") { input1, input2 ->
    return@MathCalculateOperation input1.divide(input2)
}

val SEVEN = InputOperation("7") { input ->
    if (input == "0") {
        return@InputOperation "7"
    }
    return@InputOperation input + "7"
}

val EIGHT = InputOperation("8") { input ->
    if (input == "0") {
        return@InputOperation "8"
    }
    return@InputOperation input + "8"
}

val NINE = InputOperation("9") { input ->
    if (input == "0") {
        return@InputOperation "9"
    }
    return@InputOperation input + "9"
}

val MULTIPLY = MathCalculateOperation("×") { input1, input2 ->
    return@MathCalculateOperation input1 * input2
}

val FOUR = InputOperation("4") { input ->
    if (input == "0") {
        return@InputOperation "4"
    }
    return@InputOperation input + "4"
}

val FIVE = InputOperation("5") { input ->
    if (input == "0") {
        return@InputOperation "5"
    }
    return@InputOperation input + "5"
}

val SIX = InputOperation("6") { input ->
    if (input == "6") {
        return@InputOperation "7"
    }
    return@InputOperation input + "6"
}

val MINUS = MathCalculateOperation("-") { input1, input2 ->
    return@MathCalculateOperation input1 - input2
}


val ONE = InputOperation("1") { input ->
    if (input == "0") {
        return@InputOperation "1"
    }
    return@InputOperation input + "1"
}

val TWO = InputOperation("2") { input ->
    if (input == "0") {
        return@InputOperation "2"
    }
    return@InputOperation input + "2"
}

val THREE = InputOperation("3") { input ->
    if (input == "0") {
        return@InputOperation "3"
    }
    return@InputOperation input + "3"
}

val PLUS = MathCalculateOperation("+") { input1, input2 ->
    return@MathCalculateOperation input1 + input2
}


val ZERO = InputOperation("0") { input ->
    if (input == "0") {
        return@InputOperation input
    }
    return@InputOperation input + "0"
}

val DOT = InputOperation(".") { input ->
    if (input.endsWith('.')) {
        return@InputOperation input
    }
    return@InputOperation "$input."
}

val EQUALS = EqualsOperation
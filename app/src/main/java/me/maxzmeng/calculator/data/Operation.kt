package me.maxzmeng.calculator.data

import java.math.BigDecimal

sealed class Operation(
    val text: String
)

class InputOperation(
    text: String,
    val input: (String) -> String
) : Operation(text)

class ImmediateCalculateOperation(
    text: String,
    val calculate: (input: BigDecimal) -> BigDecimal
) : Operation(text)

class MathCalculateOperation(
    text: String,
    val calculate: (input1: BigDecimal, input2: BigDecimal) -> BigDecimal
) : Operation(text)

object EqualsOperation : Operation("=")

object ACOperation: Operation("AC")

object PlaceHolderOperation : Operation("")
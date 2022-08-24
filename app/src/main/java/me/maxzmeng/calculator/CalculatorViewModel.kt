package me.maxzmeng.calculator


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import me.maxzmeng.calculator.data.*
import me.maxzmeng.calculator.ui.button.commonUiModel
import me.maxzmeng.calculator.ui.button.immediateCalculateUiModel
import me.maxzmeng.calculator.ui.button.operationUiModel
import me.maxzmeng.calculator.ui.button.zeroUiModel
import java.math.BigDecimal

class CalculatorViewModel : ViewModel() {
    private var currentInput = "0"
    var displayText by mutableStateOf(currentInput)
        private set
    private var needToReInput = false
    private var leftNum = BigDecimal.ZERO
    private var rightNum = BigDecimal.ZERO
    private var result = BigDecimal.ZERO

    val portraitButtonItems = listOf(
        AC to immediateCalculateUiModel, NEGATIVE to immediateCalculateUiModel, PERCENT to immediateCalculateUiModel, DIVIDE to operationUiModel,
        SEVEN to commonUiModel, EIGHT to commonUiModel, NINE to commonUiModel, MULTIPLY  to operationUiModel,
        FOUR to commonUiModel, FIVE to commonUiModel, SIX to commonUiModel, MINUS to operationUiModel,
        ONE to commonUiModel, TWO to commonUiModel, THREE to commonUiModel, PLUS to operationUiModel,
        ZERO to zeroUiModel, DOT to commonUiModel, EQUALS to operationUiModel
    )
    val landscapeButtonItems by lazy {
        listOf(
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            AC to immediateCalculateUiModel,
            NEGATIVE to immediateCalculateUiModel,
            PERCENT to immediateCalculateUiModel,
            DIVIDE to operationUiModel,
            PLACE_HOLDER to commonUiModel,
            SQUARE to commonUiModel,
            CUBE to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            SEVEN to commonUiModel,
            EIGHT to commonUiModel,
            NINE to commonUiModel,
            MULTIPLY to operationUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            FOUR to commonUiModel,
            FIVE to commonUiModel,
            SIX to commonUiModel,
            MINUS to operationUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            ONE to commonUiModel,
            TWO to commonUiModel,
            THREE to commonUiModel,
            PLUS to operationUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            PLACE_HOLDER to commonUiModel,
            ZERO to zeroUiModel,
            DOT to commonUiModel,
            EQUALS to operationUiModel
        )
    }

    var currentOperation: MathCalculateOperation? = null

    fun handleOperation(operation: Operation) {
        when (operation) {
            is InputOperation -> {
                val input = if (needToReInput) {
                    needToReInput = false
                    ""
                } else {
                    currentInput
                }
                currentInput = operation.input.invoke(input)
                displayText = currentInput
            }
            is MathCalculateOperation -> {
                currentOperation = operation
                leftNum = BigDecimal(currentInput)
                needToReInput = true
            }
            is ImmediateCalculateOperation -> {
                if (leftNum == BigDecimal.ZERO) {
                    leftNum = BigDecimal(currentInput)
                }
                result = operation.calculate.invoke(leftNum)
                leftNum = result
                displayText = try {
                    result.toBigIntegerExact().toString()
                } catch (e: Exception) {
                    result.toString()
                }
            }
            is EqualsOperation -> {
                rightNum = BigDecimal(currentInput)
                val result =
                    currentOperation?.calculate?.invoke(leftNum, rightNum)
                        ?: BigDecimal.ZERO
                leftNum = result
                displayText = try {
                    result.toBigIntegerExact().toString()
                } catch (e: Exception) {
                    result.toString()
                }
                needToReInput = true
            }
            is ACOperation -> {
                leftNum = BigDecimal.ZERO
                displayText = "0"
                needToReInput = true
            }
        }
    }
}
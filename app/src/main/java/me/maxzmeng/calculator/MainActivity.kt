package me.maxzmeng.calculator

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.*
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import me.maxzmeng.calculator.data.Operation
import me.maxzmeng.calculator.ui.button.*
import me.maxzmeng.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val viewModel: CalculatorViewModel = viewModel()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    // 设置状态栏颜色
                    val systemUiController = rememberSystemUiController()
                    SideEffect {
                        systemUiController.setStatusBarColor(Color.Black)
                        systemUiController.systemBarsBehavior =
                            BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    }
                    // 判断横竖屏
                    val configuration = LocalConfiguration.current
                    var orientation by remember { mutableStateOf(configuration.orientation) }
                    LaunchedEffect(configuration) {
                        orientation = configuration.orientation
                        systemUiController.isSystemBarsVisible =
                            orientation != Configuration.ORIENTATION_LANDSCAPE
                    }
                    Calculator(viewModel, orientation == Configuration.ORIENTATION_LANDSCAPE)
                }
            }
        }
    }

    @Composable
    fun Calculator(viewModel: CalculatorViewModel, isLandscape: Boolean) {
        Column(Modifier.fillMaxSize()) {
            CalculatorTextPanel(
                text = viewModel.displayText,
                fontSize = if (isLandscape) 42.sp else 64.sp,
                modifier = Modifier
                    .weight(1F)
                    .fillMaxWidth()
                    .padding(if (isLandscape) 4.dp else 8.dp)
            )
            CalculatorButtonsPanel(
                column = if (isLandscape) 10 else 4,
                values = if (isLandscape) viewModel.landscapeButtonItems else viewModel.portraitButtonItems,
                onClick = { viewModel.handleOperation(it) },
                buttonsPadding = if (isLandscape) 4.dp else 8.dp,
                aspectRatio = if (isLandscape) 1.4F else 1F,
                buttonTextFontSize = if (isLandscape) 18.sp else 28.sp,
                modifier = Modifier
                    .fillMaxHeight(if (isLandscape) 0.8F else 0.65F)
                    .fillMaxWidth()
            )
        }
    }

    @Composable
    fun CalculatorTextPanel(
        text: String,
        fontSize: TextUnit = TextUnit.Unspecified,
        modifier: Modifier = Modifier
    ) {
        Box(modifier = modifier) {
            Text(
                text = text,
                color = Color.White,
                fontSize = fontSize,
                modifier = Modifier.align(Alignment.BottomEnd),
                maxLines = 1
            )
        }
    }

    @Composable
    fun CalculatorButtonsPanel(
        column: Int,
        values: List<Pair<Operation, ButtonUiModel>>,
        onClick: (Operation) -> Unit,
        buttonTextFontSize: TextUnit = TextUnit.Unspecified,
        buttonsPadding: Dp = 0.dp,
        aspectRatio: Float = 1F,
        modifier: Modifier = Modifier
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(column),
            modifier = modifier,
        ) {
            items(values, span = { pair ->
                GridItemSpan(pair.second.weight)
            }) {
                CalculatorButton(
                    onClick = remember {
                        { onClick.invoke(it.first) }
                    },
                    backgroundColor = it.second.backgroundColor,
                    disabledBackGroundColor = it.second.disabledBackGroundColor
                        ?: it.second.backgroundColor,
                    pressedColor = it.second.pressedColor,
                    selectedColor = it.second.selectedColor,
                    modifier = Modifier
                        .padding(buttonsPadding)
                        .aspectRatio(aspectRatio * it.second.weight)
                        .clip(CircleShape)
                ) {
                    Text(
                        text = it.first.text,
                        color = it.second.textColor,
                        fontSize = buttonTextFontSize,
                        softWrap = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(it.second.textAlign),
                        maxLines = 1
                    )
                }
            }
        }
    }

    @Composable
    fun CalculatorButton(
        onClick: () -> Unit,
        backgroundColor: Color,
        disabledBackGroundColor: Color,
        pressedColor: Color,
        selectedColor: Color?,
        enabled: Boolean = true,
        selected: Boolean = false,
        modifier: Modifier = Modifier,
        content: @Composable RowScope.() -> Unit
    ) {

        val interactionSource = remember { MutableInteractionSource() }
        val pressed = interactionSource.collectIsPressedAsState().value

        val contentColor = when {
            pressed -> pressedColor
            selected && selectedColor != null -> selectedColor
            else -> backgroundColor
        }
        val color = ButtonDefaults.buttonColors(
            contentColor,
            contentColor,
            disabledBackGroundColor,
            disabledBackGroundColor
        )
        Button(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            interactionSource = interactionSource,
            colors = color
        ) {
            content()
        }
    }


}

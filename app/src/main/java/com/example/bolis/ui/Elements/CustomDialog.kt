package com.example.bolis.ui.Elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.bolis.R
import com.example.bolis.ui.theme.Dark50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey40
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit = {},
    onConfirmRequest: () -> Unit = {},
    cornerSize: Dp = 0.dp,
    strokeColor: Color = Color.Transparent,
    backgrounColor: Color = Color.White,
    titleColor: Color = Grey40,
    isOnlyDismiss: Boolean = false,
    title: String = "",
    description: String = "",
    dismissText: String = "",
    confirmText: String = "",
) {
    Dialog (
        onDismissRequest = onDismissRequest,
    ) {
        Column{
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgrounColor)
                    .border(
                        width = 1.dp,
                        color = strokeColor,
                        shape = RoundedCornerShape(cornerSize)
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cross),
                    contentDescription = "back",
                    tint = Dark50,
                    modifier = Modifier
                        .padding(top = 12.dp, end = 16.dp)
                        .align(Alignment.TopEnd)
                        .clickable(onClick = onDismissRequest)
                )
                Column(
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight(700),
                        textAlign = TextAlign.Center,
                        fontFamily = fontFamily,
                        color = titleColor
                    )
                    Text(
                        text = description,
                        fontSize = 13.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center,
                        fontFamily = fontFamily,
                        color = Grey40
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!isOnlyDismiss) {
                            Text(
                                text = dismissText,
                                fontSize = 13.sp,
                                fontWeight = FontWeight(500),
                                textAlign = TextAlign.Center,
                                fontFamily = fontFamily,
                                color = Green50,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier
                                    .width(40.dp)
                                    .clickable(onClick = onDismissRequest)
                            )
                        }
                        Text(
                            text = confirmText,
                            modifier = Modifier
                                .background(Green50)
                                .clickable(onClick = onConfirmRequest)
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            fontSize = 13.sp,
                            fontWeight = FontWeight(500),
                            textAlign = TextAlign.Center,
                            fontFamily = fontFamily,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
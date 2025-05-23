package com.example.bolis.ui.Elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Green20
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey15
import com.example.bolis.ui.theme.Grey20
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun CatalogName(
    catalogName: String = "Sample Catalog",
    onCancelClick: () -> Unit = {},
) {
    Row (
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Green20)
            .border(2.dp, Green50, RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = catalogName,
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            fontFamily = fontFamily,
            color = Black40
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_cross),
            contentDescription = "chat",
            tint = Black40,
            modifier = Modifier
                .size(20.dp)
                .clickable(onClick = onCancelClick)
        )
    }

}
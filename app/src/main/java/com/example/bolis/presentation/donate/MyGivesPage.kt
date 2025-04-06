package com.example.bolis.presentation.donate

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.bolis.data.models.Product
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.CustomDialog
import com.example.bolis.ui.Elements.MyGivesItem
import com.example.bolis.ui.Elements.OptionsButton
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Blue50
import com.example.bolis.ui.theme.Green20
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.Red40
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun MyGivesPage(
    backButtonClicked:() -> Unit = {},
    addButtonClick:() -> Unit = {}
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var isDelete by remember { mutableStateOf(false) }
    var isReason by remember { mutableStateOf(false) }

    var listProduct = listOf(
        Product(name = "Iphone ProMaxMaxMaxPlus"),
        Product(name = "Sofa with armchair", status = 1),
        Product(name = "Sofa with armchair"),
        Product(name = "Sofa with armchair", status = 2),
        Product(name = "Sofa with armchair"),
        Product(name = "Dongelek")
    )

    var listFilteredProduct = listProduct.filter {
        when (currentIndex) {
            0 -> it.status == 0
            1 -> it.status == 1
            2 -> it.status == 2
            else -> true
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                rememberScrollState(),
                orientation = Orientation.Vertical,
                enabled = true
            )
            .padding(start = 24.dp, end = 24.dp),
    ) {
        item {
            CustomBackButton(
                modifier = Modifier
                    .padding(top = 58.dp),
                name = "Back"
            ) { backButtonClicked() }
            Spacer(Modifier.size(20.dp))
            Text(
                text = "My gives",
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                color = Black50
            )
            Spacer(Modifier.size(10.dp))
            OptionsButton(
                listOf("Completed", "Pending", "Canceled"),
                currentIndex,
                onClick = {
                    currentIndex = it
                }
            )
            Spacer(Modifier.size(38.dp))
        }

        items(listFilteredProduct.size) { index ->
            MyGivesItem(listFilteredProduct[index], onDeleteButtonClick = { isDelete = true }, onReasonButtonClick = { isReason = true })
            Spacer(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Grey30)
            )
        }

        item {
            Spacer(Modifier.size(28.dp))
            CustomButton(
                "Add product",
                onClick = { addButtonClick() }
            )
            Spacer(Modifier.size(28.dp))
        }
    }

    if (isDelete) {
        CustomDialog(
            onDismissRequest = { isDelete = false },
            title = "Delete",
            description = "Are you sure you want to delete product? ",
            dismissText = "No",
            confirmText = "Yes, delete",
            strokeColor = Red40,
            titleColor = Red40
        )
    }

    if (isReason) {
        CustomDialog(
            onDismissRequest = { isReason = false },
            title = "Reason",
            description = "The product is too outdated",
            confirmText = "Ok",
            strokeColor = Red40,
            titleColor = Red40,
            isOnlyDismiss = true
        )
    }
}
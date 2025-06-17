package com.example.bolis.presentation.donate

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bolis.data.api.navBarStateChange
import com.example.bolis.data.models.ItemXX
import com.example.bolis.data.models.Product
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.CustomDialog
import com.example.bolis.ui.Elements.MyGivesItem
import com.example.bolis.ui.Elements.OptionsButton
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.Red40
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider

@Preview
@Composable
fun MyGivesPage(
    viewModel: DonateViewModel = viewModel(),
    addButtonClick:() -> Unit = {},
    onItemClick: (int: Int) -> Unit = {},
) {
    navBarStateChange(true)
    val context = LocalContext.current
    val sharedProvider = remember { SharedProvider(context) }

    var statusIndex by remember { mutableIntStateOf(0) }
    var itemIndex by remember { mutableIntStateOf(0) }
    var isDelete by remember { mutableStateOf(false) }
    var isReason by remember { mutableStateOf(false) }

    var listProduct: List<ItemXX>? by remember {
        mutableStateOf(
            List<ItemXX>(
                0,
                init = {
                    ItemXX()
                }
            )
        )
    }

    var listFilteredProduct = listProduct?.filter {
        when (statusIndex) {
            0 -> it.status == "Completed"
            1 -> it.status == "Pending"
            2 -> it.status == "Canceled"
            else -> true
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getMyGives(sharedProvider.getToken())
    }

    val myGivesResponse by viewModel.myGivesResponse.collectAsState()

    LaunchedEffect(myGivesResponse) {
        myGivesResponse?.let { gives ->
            listProduct = gives.items
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
            .padding(horizontal = 24.dp, vertical = 28.dp),
    ) {
        item {
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
                statusIndex,
                onClick = {
                    statusIndex = it
                }
            )
            Spacer(Modifier.size(38.dp))
        }

        items(listFilteredProduct?.size ?: 0) { index ->
            MyGivesItem(
                listFilteredProduct?.get(index) ?: ItemXX(),
                onDeleteButtonClick = { isDelete = true; itemIndex = index },
                onReasonButtonClick = { isReason = true },
                onItemClick = { onItemClick(it) }
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Grey30)
            )
        }

        item {
            Spacer(Modifier.size(48.dp))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 28.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        CustomButton(
            "Add product",
            onClick = { addButtonClick() }
        )
    }

    if (isDelete) {
        CustomDialog(
            onDismissRequest = { isDelete = false },
            onConfirmRequest = { isDelete = false;
                listProduct = listProduct?.toMutableList().apply {
                    this!!.remove(listFilteredProduct?.get(itemIndex) ?: ItemXX())
                }
            },
            title = "Delete",
            description = "Are you sure you want to delete product?",
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
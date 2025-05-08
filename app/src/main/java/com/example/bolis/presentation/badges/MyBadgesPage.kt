package com.example.bolis.presentation.badges

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.data.models.CatalogResponse
import com.example.bolis.data.models.Item
import com.example.bolis.data.models.LikedItem
import com.example.bolis.ui.Elements.BadgesItem
import com.example.bolis.ui.Elements.CatalogItem
import com.example.bolis.ui.Elements.OptionsButton
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.fontFamily
import kotlin.collections.chunked
import kotlin.collections.forEach

@Preview
@Composable
fun MyBadgesPage() {
    var statusIndex by remember { mutableIntStateOf(0) }

    var badgesList = List(10) { index ->
        Item(
            name = "Badge $index",
            description = "Description for Badge $index",
            iD = (10..100).random() // Random status count between 10 and 100
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Text(
                text = "Fill in the information",
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                color = Black50,
                modifier = Modifier.padding(top = 28.dp)
            )
            Spacer(Modifier.size(10.dp))
            OptionsButton(
                listOf("All achievments", "My achievments"),
                statusIndex,
                onClick = {
                    statusIndex = it
                }
            )
        }

        items(badgesList.chunked(2)) { pair ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    pair.forEach { item ->
                        BadgesItem(
                            name = item.name,
                            description = item.description,
                            status = item.iD,
                        )
                    }
                }
            }
        }

        item {
            Spacer(Modifier.size(4.dp))
        }
    }
}
package com.example.bolis.ui.Elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.bolis.R
import com.example.bolis.data.models.Author
import com.example.bolis.data.models.ItemXX
import com.example.bolis.data.models.Post
import com.example.bolis.data.models.Product
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Blue50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.Red40
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.Constants.Companion.BASE_URL
import com.example.bolis.utils.Constants.Companion.IMAGE_URL
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Preview
@Composable
fun BigNewsItem(
    post: Post = Post(
        author = Author(
            avatar = "https://example.com/avatar.jpg",
            firstName = "John",
            lastName = "Doe",
        ),
        content = "This is a sample content for the post.",
        createdAt = "2025-05-08 25:55",
        id = 0,
        title = "Sample Post Title",
        userId = 0
    ),
    onItemClick: (int: Int) -> Unit = {},
) {

    Column(
        modifier = Modifier
            .clickable(onClick = {
                onItemClick(post.id ?: 0)
            })
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = IMAGE_URL + post.author?.avatar,
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape)
                    .background(White50)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "${post.author?.lastName?.trim()} ${post.author?.firstName?.trim()}",
                fontSize = 14.sp,
                maxLines = 2,
                fontWeight = FontWeight(500),
                fontFamily = fontFamily,
                overflow = TextOverflow.Ellipsis,
                color = Black40,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = timeAgo(post.createdAt ?: ""),
                fontSize = 13.sp,
                maxLines = 1,
                fontWeight = FontWeight(400),
                fontFamily = fontFamily,
                overflow = TextOverflow.Ellipsis,
                color = Grey30,
            )
        }
        AsyncImage(
            model = IMAGE_URL + post.imageUrl,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
//                .clip(RoundedCornerShape(12.dp))
                .background(White50)
        )
        Spacer(Modifier.size(8.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = post.title?.trim() ?: "",
                fontSize = 14.sp,
                maxLines = 2,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                overflow = TextOverflow.Ellipsis,
                color = Black40,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.size(2.dp))
            Text(
                text = post.content.toString().trim(),
                fontSize = 14.sp,
                maxLines = 3,
                fontWeight = FontWeight(400),
                fontFamily = fontFamily,
                overflow = TextOverflow.Ellipsis,
                color = Black40,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

fun timeAgo(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dateTime = LocalDateTime.parse(dateString, formatter)
    val now = LocalDateTime.now()

    val years = ChronoUnit.YEARS.between(dateTime, now)
    if (years > 0) return "$years year"

    val months = ChronoUnit.MONTHS.between(dateTime, now)
    if (months > 0) return "$months month"

    val days = ChronoUnit.DAYS.between(dateTime, now)
    if (days > 0) return "$days day"

    val hours = ChronoUnit.HOURS.between(dateTime, now)
    if (hours > 0) return "$hours hour"

    val minutes = ChronoUnit.MINUTES.between(dateTime, now)
    if (minutes > 0) return "$minutes min"

    return "just now"
}
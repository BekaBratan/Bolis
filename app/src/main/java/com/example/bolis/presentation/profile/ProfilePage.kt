package com.example.bolis.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.data.api.navBarStateChange
import com.example.bolis.ui.Elements.CustomDialog
import com.example.bolis.ui.Elements.ProfileButton
import com.example.bolis.ui.Elements.SettingsButton
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Green20
import com.example.bolis.ui.theme.Grey40
import com.example.bolis.ui.theme.Red40
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider

@Preview
@Composable
fun ProfilePage(
    profileButtonClicked: () -> Unit = {},
    myBudgetButtonClicked: () -> Unit = {},
    postamatButtonClicked: () -> Unit = {},
    changePassButtonClicked: () -> Unit = {},
    supportButtonClicked: () -> Unit = {},
    languageButtonClicked: () -> Unit = {},
    deleteButtonClicked: () -> Unit = {},
    logOutButtonClicked: () -> Unit = {}
) {
    navBarStateChange(true)

    var isLogout by remember { mutableStateOf(false) }
    var isDeleteAccount by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "${stringResource(R.string.welcome)} ${sharedProvider.getFirstName()} !",
            fontSize = 22.sp,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Black50
        )
        Spacer(Modifier.size(22.dp))
        ProfileButton(name = sharedProvider.getFirstName(), onClick = profileButtonClicked)
        SettingsButton(name = "My badges", onClick = myBudgetButtonClicked, icon = R.drawable.ic_star)
        SettingsButton(name = "Postamat", onClick = postamatButtonClicked, icon = R.drawable.ic_box)
        Spacer(Modifier.size(24.dp))
        Text(
            text = "Settings",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Grey40
        )
        SettingsButton(name = "Change Password", onClick = changePassButtonClicked, icon = R.drawable.ic_lock)
        SettingsButton(name = "Help and Support", onClick = supportButtonClicked, icon = R.drawable.ic_headphone)
        SettingsButton(name = "Delete Account", onClick = { isDeleteAccount = true }, icon = R.drawable.ic_trash)
        SettingsButton(name = "Language", onClick = languageButtonClicked, icon = R.drawable.ic_language)
        SettingsButton(name = "Logout", onClick = { isLogout = true }, icon = R.drawable.ic_logout)
    }


    if (isLogout) {
        CustomDialog(
            onDismissRequest = { isLogout = false },
            title = "Logout",
            description = "Are you sure you want to logout?",
            dismissText = "No",
            confirmText = "Logout",
            backgrounColor = Green20,
            onConfirmRequest = {
                sharedProvider.clearShared()
                logOutButtonClicked()
            }
        )
    }

    if (isDeleteAccount) {
        CustomDialog(
            onDismissRequest = { isDeleteAccount = false },
            title = "Delete",
            description = "Are you sure you delete account?",
            dismissText = "No",
            confirmText = "Delete",
            backgrounColor = Green20,
            onConfirmRequest = {
                deleteButtonClicked()
            }
        )
    }

}
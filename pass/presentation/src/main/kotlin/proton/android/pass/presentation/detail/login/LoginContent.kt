package proton.android.pass.presentation.detail.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import proton.android.pass.presentation.detail.DetailNoteSection

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    state: LoginDetailUiState,
    onTogglePasswordClick: () -> Unit,
    onUsernameClick: () -> Unit,
    onCopyPasswordClick: () -> Unit,
    onWebsiteClicked: (String) -> Unit,
    onWebsiteLongClicked: (String) -> Unit,
    onCopyTotpClick: (String) -> Unit
) {
    Column(modifier.padding(horizontal = 16.dp)) {
        LoginUsernameRow(
            username = state.username,
            onUsernameClick = onUsernameClick
        )
        Spacer(modifier = Modifier.height(12.dp))
        LoginPasswordRow(
            password = state.password,
            onTogglePasswordClick = onTogglePasswordClick,
            onCopyPasswordClick = onCopyPasswordClick
        )
        if (state.websites.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            WebsiteSection(
                modifier = Modifier.padding(start = 16.dp),
                websites = state.websites,
                onWebsiteClicked = onWebsiteClicked,
                onWebsiteLongClicked = onWebsiteLongClicked
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        DetailNoteSection(
            modifier = Modifier.padding(start = 16.dp),
            text = state.note
        )
        if (state.totpUiState != null) {
            TotpSection(state = state.totpUiState) { onCopyTotpClick(it) }
        }
    }
}

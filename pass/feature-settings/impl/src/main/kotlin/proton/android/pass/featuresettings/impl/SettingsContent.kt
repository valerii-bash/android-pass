package proton.android.pass.featuresettings.impl

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import me.proton.core.compose.component.appbar.ProtonTopAppBar
import proton.android.pass.composecomponents.impl.bottomsheet.PassModalBottomSheetLayout
import proton.android.pass.composecomponents.impl.loading.LoadingDialog
import proton.android.pass.composecomponents.impl.topbar.TopBarTitleView
import proton.android.pass.composecomponents.impl.uievents.IsLoadingState
import proton.android.pass.preferences.ThemePreference

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsContent(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    state: SettingsUiState,
    onThemeChange: (ThemePreference) -> Unit,
    onCopyToClipboardChange: (Boolean) -> Unit,
    onForceSyncClick: () -> Unit,
    onAppVersionClick: (String) -> Unit,
    onReportProblemClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    BackHandler(
        enabled = bottomSheetState.isVisible
    ) {
        scope.launch { bottomSheetState.hide() }
    }

    PassModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            ThemeSelectionBottomSheetContents(
                onThemeSelected = { theme ->
                    scope.launch {
                        bottomSheetState.hide()
                        onThemeChange(theme)
                    }
                }
            )
        }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                ProtonTopAppBar(
                    title = {
                        TopBarTitleView(
                            title = stringResource(id = R.string.title_settings)
                        )
                    }
                )
            }
        ) { contentPadding ->
            if (state.isLoadingState == IsLoadingState.Loading) {
                LoadingDialog()
            }
            Settings(
                modifier = modifier.padding(contentPadding),
                state = state,
                onCopyToClipboardChange = onCopyToClipboardChange,
                onForceSyncClick = onForceSyncClick,
                onAppVersionClick = onAppVersionClick,
                onReportProblemClick = onReportProblemClick,
                onLogoutClick = onLogoutClick
            )
        }
    }
}

package proton.android.pass.featureitemcreate.impl.bottomsheets.createitem

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kotlinx.collections.immutable.toPersistentList
import me.proton.core.compose.theme.ProtonTheme
import me.proton.core.compose.theme.defaultSmallNorm
import proton.android.pass.common.api.Option
import proton.android.pass.common.api.toOption
import proton.android.pass.commonui.api.PassTheme
import proton.android.pass.commonui.api.ThemedBooleanPreviewProvider
import proton.android.pass.commonui.api.bottomSheet
import proton.android.pass.composecomponents.impl.bottomsheet.BottomSheetItem
import proton.android.pass.composecomponents.impl.bottomsheet.BottomSheetItemList
import proton.android.pass.composecomponents.impl.bottomsheet.BottomSheetItemTitle
import proton.android.pass.composecomponents.impl.bottomsheet.withDividers
import proton.android.pass.composecomponents.impl.item.icon.AliasIcon
import proton.android.pass.composecomponents.impl.item.icon.LoginIcon
import proton.android.pass.composecomponents.impl.item.icon.NoteIcon
import proton.android.pass.composecomponents.impl.item.icon.PasswordIcon
import proton.android.pass.featureitemcreate.impl.R
import proton.pass.domain.ShareId

enum class CreateItemBottomSheetMode {
    Full,
    Autofill;
}

@ExperimentalMaterialApi
@Composable
fun CreateItemBottomSheetContents(
    modifier: Modifier = Modifier,
    shareId: ShareId? = null,
    mode: CreateItemBottomSheetMode,
    onNavigate: (CreateItemBottomsheetNavigation) -> Unit
) {

    val items = when (mode) {
        CreateItemBottomSheetMode.Full -> listOf(
            createLogin(shareId) {
                onNavigate(CreateItemBottomsheetNavigation.CreateLogin(it))
            },
            createAlias(shareId) {
                onNavigate(CreateItemBottomsheetNavigation.CreateAlias(it))
            },
            createNote(shareId) {
                onNavigate(CreateItemBottomsheetNavigation.CreateNote(it))
            },
            createPassword {
                onNavigate(CreateItemBottomsheetNavigation.CreatePassword)
            }
        )
        CreateItemBottomSheetMode.Autofill -> listOf(
            createLogin(shareId) {
                onNavigate(CreateItemBottomsheetNavigation.CreateLogin(it))
            },
            createAlias(shareId) {
                onNavigate(CreateItemBottomsheetNavigation.CreateAlias(it))
            },
        )
    }

    BottomSheetItemList(
        modifier = modifier.bottomSheet(),
        items = items.withDividers().toPersistentList()
    )
}

private fun createLogin(
    shareId: ShareId?,
    onCreateLogin: (Option<ShareId>) -> Unit
): BottomSheetItem = object : BottomSheetItem {
    override val title: @Composable () -> Unit
        get() = { BottomSheetItemTitle(text = stringResource(id = R.string.action_login)) }
    override val subtitle: (@Composable () -> Unit)
        get() = {
            Text(
                text = stringResource(R.string.item_type_login_description),
                style = ProtonTheme.typography.defaultSmallNorm,
                color = ProtonTheme.colors.textWeak
            )
        }
    override val leftIcon: (@Composable () -> Unit)
        get() = { LoginIcon() }
    override val endIcon: (@Composable () -> Unit)?
        get() = null
    override val onClick: () -> Unit
        get() = { onCreateLogin(shareId.toOption()) }
    override val isDivider = false
}

private fun createAlias(
    shareId: ShareId?,
    onCreateAlias: (Option<ShareId>) -> Unit
): BottomSheetItem =
    object : BottomSheetItem {
        override val title: @Composable () -> Unit
            get() = { BottomSheetItemTitle(text = stringResource(id = R.string.action_alias)) }
        override val subtitle: (@Composable () -> Unit)
            get() = {
                Text(
                    text = stringResource(R.string.item_type_alias_description),
                    style = ProtonTheme.typography.defaultSmallNorm,
                    color = ProtonTheme.colors.textWeak
                )
            }
        override val leftIcon: (@Composable () -> Unit)
            get() = { AliasIcon() }
        override val endIcon: (@Composable () -> Unit)?
            get() = null
        override val onClick: () -> Unit
            get() = { onCreateAlias(shareId.toOption()) }
        override val isDivider = false
    }

private fun createNote(
    shareId: ShareId?,
    onCreateNote: (Option<ShareId>) -> Unit
): BottomSheetItem =
    object : BottomSheetItem {
        override val title: @Composable () -> Unit
            get() = { BottomSheetItemTitle(text = stringResource(id = R.string.action_note)) }
        override val subtitle: (@Composable () -> Unit)
            get() = {
                Text(
                    text = stringResource(R.string.item_type_note_description),
                    style = ProtonTheme.typography.defaultSmallNorm,
                    color = ProtonTheme.colors.textWeak
                )
            }
        override val leftIcon: (@Composable () -> Unit)
            get() = { NoteIcon() }
        override val endIcon: (@Composable () -> Unit)?
            get() = null
        override val onClick: () -> Unit
            get() = { onCreateNote(shareId.toOption()) }
        override val isDivider = false
    }

private fun createPassword(onCreatePassword: () -> Unit): BottomSheetItem =
    object : BottomSheetItem {
        override val title: @Composable () -> Unit
            get() = { BottomSheetItemTitle(text = stringResource(id = R.string.action_password)) }
        override val subtitle: (@Composable () -> Unit)
            get() = {
                Text(
                    text = stringResource(R.string.item_type_password_description),
                    style = ProtonTheme.typography.defaultSmallNorm,
                    color = ProtonTheme.colors.textWeak
                )
            }
        override val leftIcon: (@Composable () -> Unit)
            get() = { PasswordIcon() }
        override val endIcon: (@Composable () -> Unit)?
            get() = null
        override val onClick: () -> Unit
            get() = onCreatePassword
        override val isDivider = false
    }

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun CreateItemBottomSheetContentsPreview(
    @PreviewParameter(ThemedBooleanPreviewProvider::class) input: Pair<Boolean, Boolean>
) {
    val mode = if (input.second) CreateItemBottomSheetMode.Full else CreateItemBottomSheetMode.Autofill
    PassTheme(isDark = input.first) {
        Surface {
            CreateItemBottomSheetContents(
                mode = mode,
                onNavigate = {}
            )
        }
    }
}

package proton.android.pass.featuresettings.impl

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import me.proton.core.compose.theme.ProtonTheme
import proton.android.pass.commonui.api.PassTheme
import proton.android.pass.commonui.api.ThemePreviewProvider
import proton.android.pass.composecomponents.impl.container.roundedContainer
import proton.android.pass.composecomponents.impl.setting.SettingOption
import proton.android.pass.preferences.ThemePreference

@Composable
fun PreferencesSection(
    modifier: Modifier = Modifier,
    theme: ThemePreference,
    onSelectThemeClick: () -> Unit,
    onClipboardClick: () -> Unit
) {
    Column(
        modifier = modifier.roundedContainer(ProtonTheme.colors.separatorNorm)
    ) {
        val subtitle = stringResource(
            when (theme) {
                ThemePreference.System -> R.string.settings_appearance_preference_subtitle_match_system
                ThemePreference.Dark -> R.string.settings_appearance_preference_subtitle_dark
                ThemePreference.Light -> R.string.settings_appearance_preference_subtitle_light
            }
        )
        SettingOption(
            text = subtitle,
            label = stringResource(R.string.settings_appearance_preference_title),
            onClick = onSelectThemeClick
        )
        Divider()
        SettingOption(
            text = stringResource(R.string.settings_option_clipboard),
            onClick = onClipboardClick
        )
    }
}

@Preview
@Composable
fun PreferencesSectionPreview(
    @PreviewParameter(ThemePreviewProvider::class) isDark: Boolean
) {
    PassTheme(isDark = isDark) {
        Surface {
            PreferencesSection(
                theme = ThemePreference.Dark,
                onSelectThemeClick = {},
                onClipboardClick = {}
            )
        }
    }
}

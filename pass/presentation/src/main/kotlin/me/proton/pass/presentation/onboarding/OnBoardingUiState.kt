package me.proton.pass.presentation.onboarding

import androidx.compose.runtime.Stable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.proton.pass.presentation.onboarding.OnBoardingPageName.Autofill
import me.proton.pass.presentation.onboarding.OnBoardingPageName.Fingerprint
import me.proton.pass.presentation.onboarding.OnBoardingPageName.Last

@Stable
data class OnBoardingUiState(
    val selectedPage: Int,
    val enabledPages: Set<OnBoardingPageName>,
    val isCompleted: Boolean
) {
    companion object {
        val Initial = OnBoardingUiState(0, emptySet(), false)
    }
}

@Stable
enum class OnBoardingPageName {
    Autofill, Fingerprint, Last
}

open class OnBoardingUiStatePreviewProvider : PreviewParameterProvider<OnBoardingUiState> {
    override val values: Sequence<OnBoardingUiState> = sequenceOf(
        OnBoardingUiState(0, setOf(Autofill), false),
        OnBoardingUiState(0, setOf(Fingerprint), false),
        OnBoardingUiState(0, setOf(Last), false),
        OnBoardingUiState(0, setOf(Autofill, Fingerprint), false)
    )
}

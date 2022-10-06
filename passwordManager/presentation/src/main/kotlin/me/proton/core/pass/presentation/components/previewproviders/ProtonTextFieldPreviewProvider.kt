package me.proton.core.pass.presentation.components.previewproviders

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.proton.core.compose.theme.ProtonTheme
import me.proton.core.presentation.R

class ProtonTextFieldPreviewProvider : PreviewParameterProvider<ProtonTextFieldPreviewData> {
    override val values: Sequence<ProtonTextFieldPreviewData>
        get() = sequenceOf(
            ProtonTextFieldPreviewData(value = ""),
            ProtonTextFieldPreviewData(
                value = "",
                placeholder = me.proton.core.pass.presentation.R.string.field_title_hint
            ),
            ProtonTextFieldPreviewData(value = "", isError = true),
            ProtonTextFieldPreviewData(value = "contents with error", isError = true),
            ProtonTextFieldPreviewData(value = "not editable", isEditable = false),
            ProtonTextFieldPreviewData(
                value = "with icon",
                icon = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_proton_minus_circle
                        ),
                        contentDescription = null,
                        tint = ProtonTheme.colors.iconNorm
                    )
                }
            )
        )
}

data class ProtonTextFieldPreviewData(
    val value: String = "",
    val placeholder: Int? = null,
    val isError: Boolean = false,
    val isEditable: Boolean = true,
    val icon: (@Composable () -> Unit)? = null
)

package me.proton.core.pass.presentation.create.alias

import androidx.annotation.StringRes
import me.proton.android.pass.notifications.api.SnackbarMessage
import me.proton.android.pass.notifications.api.SnackbarType
import me.proton.core.pass.presentation.R

enum class AliasSnackbarMessage(
    @StringRes override val id: Int,
    override val type: SnackbarType
) : SnackbarMessage {
    EmptyShareIdError(R.string.create_alias_empty_share_id, SnackbarType.ERROR),
    InitError(R.string.create_alias_init_error, SnackbarType.ERROR),
    ItemCreationError(R.string.create_alias_item_creation_error, SnackbarType.ERROR),
    ItemUpdateError(R.string.create_alias_item_update_error, SnackbarType.ERROR),
    AliasCreated(R.string.alias_created, SnackbarType.SUCCESS),
    AliasUpdated(R.string.changes_saved, SnackbarType.SUCCESS)
}

package proton.android.pass.data.api.usecases

import proton.pass.domain.ShareId

interface MigrateVault {
    suspend operator fun invoke(origin: ShareId, dest: ShareId)
}


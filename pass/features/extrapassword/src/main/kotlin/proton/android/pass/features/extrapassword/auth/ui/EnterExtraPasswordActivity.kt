/*
 * Copyright (c) 2024 Proton AG
 * This file is part of Proton AG and Proton Pass.
 *
 * Proton Pass is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Proton Pass is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Proton Pass.  If not, see <https://www.gnu.org/licenses/>.
 */

package proton.android.pass.features.extrapassword.auth.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint
import me.proton.core.domain.entity.UserId
import proton.android.pass.log.api.PassLogger

@AndroidEntryPoint
class EnterExtraPasswordActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val userId = intent.getStringExtra(EXTRA_USER_ID)?.let { UserId(it) } ?: run {
            PassLogger.w(TAG, "Missing user id")
            finish()
            return
        }

        setContent {
            EnterExtraPasswordApp(
                userId = userId,
                onSuccess = {
                    setResult(RESULT_OK)
                    finish()
                },
                onLogout = {}
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // no-op, we do not allow back navigation
    }

    companion object {
        private const val TAG = "EnterExtraPasswordActivity"
        private const val EXTRA_USER_ID = "extra_user_id"

        fun createIntent(context: Context, userId: UserId) = Intent(
            context,
            EnterExtraPasswordActivity::class.java
        ).apply {
            putExtra(EXTRA_USER_ID, userId.id)
            setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

}

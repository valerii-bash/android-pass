package proton.android.pass.data.impl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import proton.android.pass.data.api.repositories.AliasRepository
import proton.android.pass.data.api.repositories.ItemRepository
import proton.android.pass.data.api.repositories.KeyPacketRepository
import proton.android.pass.data.api.repositories.ShareRepository
import proton.android.pass.data.api.repositories.VaultKeyRepository
import proton.android.pass.data.impl.repositories.AliasRepositoryImpl
import proton.android.pass.data.impl.repositories.EventRepository
import proton.android.pass.data.impl.repositories.EventRepositoryImpl
import proton.android.pass.data.impl.repositories.ItemRepositoryImpl
import proton.android.pass.data.impl.repositories.KeyPacketRepositoryImpl
import proton.android.pass.data.impl.repositories.ShareRepositoryImpl
import proton.android.pass.data.impl.repositories.VaultKeyRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataRepositoryModule {

    @Binds
    abstract fun bindAliasRepository(impl: AliasRepositoryImpl): AliasRepository

    @Binds
    abstract fun bindItemRepository(impl: ItemRepositoryImpl): ItemRepository

    @Binds
    abstract fun bindKeyPacketRepository(impl: KeyPacketRepositoryImpl): KeyPacketRepository

    @Binds
    abstract fun bindShareRepository(impl: ShareRepositoryImpl): ShareRepository

    @Binds
    abstract fun bindVaultKeyRepository(impl: VaultKeyRepositoryImpl): VaultKeyRepository

    @Binds
    abstract fun bindEventRepository(impl: EventRepositoryImpl): EventRepository

}

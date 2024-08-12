package costa.renan.imenu.di.modules

import android.content.Context
import androidx.room.Room
import costa.renan.imenu.data.local.db.MenuDatabase
import costa.renan.imenu.data.local.db.repository.CartItemRepositoryImpl
import costa.renan.imenu.domain.repository.ICartItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MenuDatabase =
        Room.databaseBuilder(
            context = context.applicationContext,
            klass = MenuDatabase::class.java,
            name = "torus.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCartItemRepository(db: MenuDatabase): ICartItemRepository {
        return CartItemRepositoryImpl(db)
    }
}
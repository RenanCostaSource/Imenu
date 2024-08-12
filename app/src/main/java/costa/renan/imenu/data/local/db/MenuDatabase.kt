package costa.renan.imenu.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import costa.renan.imenu.data.local.db.dao.CartItemDao
import costa.renan.imenu.data.local.db.entity.CartItemEntity

@Database(
    entities = [
        CartItemEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class MenuDatabase: RoomDatabase() {
    abstract val cartItemDao: CartItemDao
}
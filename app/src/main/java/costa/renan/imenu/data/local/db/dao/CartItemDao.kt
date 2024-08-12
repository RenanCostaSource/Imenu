package costa.renan.imenu.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import costa.renan.imenu.data.local.db.entity.CartItemEntity

@Dao
interface CartItemDao {
    //Simplified due to time restriction on challenge
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItemEntity)

    @Query("SELECT * FROM cart_item")
    suspend fun findAll(): List<CartItemEntity>

    @Query("DELETE FROM cart_item")
    suspend fun deleteAll()

    @Query("SELECT * FROM cart_item WHERE id = :id")
    suspend fun findById(id: String): CartItemEntity?

    @Query("DELETE FROM cart_item WHERE id = :id")
    suspend fun deleteById(id: String)

}
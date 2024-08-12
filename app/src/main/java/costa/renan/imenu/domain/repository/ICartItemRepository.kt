package costa.renan.imenu.domain.repository

import costa.renan.imenu.data.local.db.entity.CartItemEntity

interface ICartItemRepository {
    suspend fun insert(entity: CartItemEntity)

    suspend fun findById(id: String): CartItemEntity?

    suspend fun deleteById(id: String)

    suspend fun findAll(): List<CartItemEntity>

    suspend fun deleteAll()
}
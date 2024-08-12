package costa.renan.imenu.data.local.db.repository

import costa.renan.imenu.data.local.db.MenuDatabase
import costa.renan.imenu.data.local.db.entity.CartItemEntity
import costa.renan.imenu.domain.repository.ICartItemRepository
import javax.inject.Inject


class CartItemRepositoryImpl @Inject constructor(
    private val db: MenuDatabase
): ICartItemRepository {
    override suspend fun insert(entity: CartItemEntity) = db.cartItemDao.insert(entity)

    override suspend fun findById(id: String) = db.cartItemDao.findById(id)

    override suspend fun deleteById(id: String) = db.cartItemDao.deleteById(id)

    override suspend fun findAll() = db.cartItemDao.findAll()

    override suspend fun deleteAll() = db.cartItemDao.deleteAll()
}
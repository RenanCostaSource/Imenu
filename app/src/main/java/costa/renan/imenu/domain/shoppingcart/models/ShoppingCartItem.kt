package costa.renan.imenu.domain.shoppingcart.models

import costa.renan.imenu.data.local.db.entity.CartItemEntity
import costa.renan.imenu.domain.menulist.models.MenuListItem

data class ShoppingCartItem(
    val id: String = "",
    val imageURL: String,
    val name: String,
    val description: String,
    val price: Float,
    val rate: Int,
    val amount: Int
) {
    fun toEntity() = CartItemEntity(
        id = id,
        imageURL = imageURL,
        name = name,
        description = description,
        price = price,
        amount = amount,
        rate = rate
    )

    fun toPair() = Pair(
        MenuListItem(
            id = id,
            imageURL = imageURL,
            name = name,
            description = description,
            price = price,
            rate = rate
        ),
        amount
    )

    companion object {
        fun fromEntity(entity: CartItemEntity) = ShoppingCartItem(
            id = entity.id,
            imageURL = entity.imageURL,
            name = entity.name,
            description = entity.description,
            price = entity.price,
            rate = entity.rate,
            amount = entity.amount
        )

        fun fromPair(pair: Pair<MenuListItem, Int>) = ShoppingCartItem(
            id = pair.first.id,
            imageURL = pair.first.imageURL,
            name = pair.first.name,
            description = pair.first.description,
            price = pair.first.price,
            rate = pair.first.rate,
            amount = pair.second
        )

    }
}

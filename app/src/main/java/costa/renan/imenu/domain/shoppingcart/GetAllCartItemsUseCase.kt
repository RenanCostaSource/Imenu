package costa.renan.imenu.domain.shoppingcart

import costa.renan.imenu.domain.menulist.models.MenuListItem
import costa.renan.imenu.domain.repository.ICartItemRepository
import costa.renan.imenu.domain.shoppingcart.models.ShoppingCartItem
import javax.inject.Inject

class GetAllCartItemsUseCase @Inject constructor(
    private val repository: ICartItemRepository
) {
    suspend operator fun invoke(): List<Pair<MenuListItem, Int>> {
        return repository.findAll().map { ShoppingCartItem.fromEntity(it).toPair() }
    }
}
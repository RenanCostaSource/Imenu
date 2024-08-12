package costa.renan.imenu.domain.shoppingcart

import costa.renan.imenu.domain.menulist.models.MenuListItem
import costa.renan.imenu.domain.repository.ICartItemRepository
import costa.renan.imenu.domain.shoppingcart.models.ShoppingCartItem
import javax.inject.Inject

class GetCartItemByIdUseCase @Inject constructor(
    private val repository: ICartItemRepository
)  {
    suspend operator fun invoke(id: String): Pair<MenuListItem, Int>? {
        return repository.findById(id)?.let {
            ShoppingCartItem.fromEntity(it).toPair()
        }
    }
}
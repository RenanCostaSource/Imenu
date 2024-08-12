package costa.renan.imenu.domain.shoppingcart

import costa.renan.imenu.domain.repository.ICartItemRepository
import javax.inject.Inject

class DeleteAllCartItemsUseCase @Inject constructor(
    private val repository: ICartItemRepository
)  {
    suspend operator fun invoke() = repository.deleteAll()

}
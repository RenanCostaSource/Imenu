package costa.renan.imenu.domain.shoppingcart

import costa.renan.imenu.domain.repository.ICartItemRepository
import javax.inject.Inject

class DeleteCartItemsUseCase @Inject constructor(
    private val repository: ICartItemRepository
) {
    suspend operator fun invoke(id: String) = repository.deleteById(id)
}
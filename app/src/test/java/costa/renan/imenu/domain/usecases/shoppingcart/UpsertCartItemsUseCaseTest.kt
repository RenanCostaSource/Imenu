package costa.renan.imenu.domain.usecases.shoppingcart

import costa.renan.imenu.domain.menulist.models.MenuListItem
import costa.renan.imenu.domain.repository.ICartItemRepository
import costa.renan.imenu.domain.shoppingcart.UpsertCartItemsUseCase
import costa.renan.imenu.domain.shoppingcart.models.ShoppingCartItem
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UpsertCartItemsUseCaseTest {
    @Test
    fun `inserts new cart item successfully`() = runBlocking {
        // Arrange
        val repository = mockk<ICartItemRepository>(relaxed = true)
        val useCase = UpsertCartItemsUseCase(repository)
        val menuListItem = MenuListItem(
            id = "1",
            imageURL = "http://example.com/image.jpg",
            name = "Test Item",
            description = "Test Description",
            price = 10.0f,
            rate = 5
        )
        val itemPair = Pair(menuListItem, 2)
        val cartItemEntity = ShoppingCartItem.fromPair(itemPair).toEntity()

        // Act
        useCase(itemPair)

        // Assert
        coVerify { repository.insert(cartItemEntity) }
    }
}
package costa.renan.imenu.domain.usecases.shoppingcart

import costa.renan.imenu.domain.repository.ICartItemRepository
import costa.renan.imenu.domain.shoppingcart.DeleteCartItemsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class DeleteCartItemsUseCaseTest{
    @Test
    fun `delete cart item by id`() = runBlocking {
        // Arrange
        val repository = mock<ICartItemRepository>()
        val useCase = DeleteCartItemsUseCase(repository)
        val cartItemId = "123"

        // Act
        useCase.invoke(cartItemId)

        // Assert
        verify(repository).deleteById(cartItemId)
    }
}
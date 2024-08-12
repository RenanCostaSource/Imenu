package costa.renan.imenu.domain.usecases.shoppingcart
import costa.renan.imenu.domain.repository.ICartItemRepository
import costa.renan.imenu.domain.shoppingcart.DeleteAllCartItemsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class DeleteAllCartItemsUseCaseTest {
    @Test
    fun `delete all cart items`() = runBlocking {
        // Arrange
        val repository = mock<ICartItemRepository>()
        val useCase = DeleteAllCartItemsUseCase(repository)

        // Act
        useCase.invoke()

        // Assert
        verify(repository).deleteAll()
    }
}
package costa.renan.imenu.domain.usecases.shoppingcart

import costa.renan.imenu.data.local.db.entity.CartItemEntity
import costa.renan.imenu.domain.repository.ICartItemRepository
import costa.renan.imenu.domain.shoppingcart.GetAllCartItemsUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class GetAllCartItemsUseCaseTest {
    @Test
    fun `retrieve all cart items successfully`() = runBlocking {
        // Arrange
        val repository = mock<ICartItemRepository>()
        val cartItems = listOf(
            CartItemEntity("1", "name1", "item1", 1f, 1, "www1", 2),
            CartItemEntity("2", "name2", "item2", 2f, 2, "www2", 3)
        )
        `when`(repository.findAll()).thenReturn(cartItems)
        val useCase = GetAllCartItemsUseCase(repository)

        // Act
        val result = useCase.invoke()

        // Assert
        assertEquals(2, result.size)
        assertEquals("name1", result[0].first.name)
        assertEquals(2, result[0].second)
        assertEquals("name2", result[1].first.name)
        assertEquals(3, result[1].second)
    }

    @Test
    fun `handle repository returning empty list`() = runBlocking {
        // Arrange
        val repository = mock<ICartItemRepository>()
        `when`(repository.findAll()).thenReturn(emptyList())
        val useCase = GetAllCartItemsUseCase(repository)

        // Act
        val result = useCase.invoke()

        // Assert
        assertTrue(result.isEmpty())
    }
}
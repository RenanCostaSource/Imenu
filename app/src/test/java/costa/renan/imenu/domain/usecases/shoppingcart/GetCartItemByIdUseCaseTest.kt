package costa.renan.imenu.domain.usecases.shoppingcart

import costa.renan.imenu.data.local.db.entity.CartItemEntity
import costa.renan.imenu.domain.repository.ICartItemRepository
import costa.renan.imenu.domain.shoppingcart.GetCartItemByIdUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCartItemByIdUseCaseTest{
    @Test
    fun `retrieves cart item by valid id`() = runBlocking {
        // Arrange
        val repository = mockk<ICartItemRepository>()
        val useCase = GetCartItemByIdUseCase(repository)
        val cartItemEntity = CartItemEntity(
            id = "1",
            imageURL = "http://example.com/image.jpg",
            name = "Item 1",
            description = "Description 1",
            price = 10.0f,
            rate = 5,
            amount = 2
        )
        coEvery { repository.findById("1") } returns cartItemEntity

        // Act
        val result = useCase("1")

        // Assert
        assertNotNull(result)
        assertEquals("1", result?.first?.id)
        assertEquals(2, result?.second)
    }

    @Test
    fun handles_non_existent_id_gracefully() = runBlocking {
        // Arrange
        val repository = mockk<ICartItemRepository>()
        val useCase = GetCartItemByIdUseCase(repository)
        coEvery { repository.findById("non-existent-id") } returns null

        // Act
        val result = useCase("non-existent-id")

        // Assert
        assertNull(result)
    }
}
package costa.renan.imenu.domain.usecases.menulist

import costa.renan.imenu.data.remote.RemoteDataState
import costa.renan.imenu.data.remote.models.MenuItemDto
import costa.renan.imenu.data.remote.repositories.MenuListRepository
import costa.renan.imenu.domain.menulist.use_cases.GetMenuListUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
    class GetMenuListUseCaseTest {
    @Test
    fun `emits success state on successful response`() = runBlocking {
        // Arrange
        val mockRepository = mock<MenuListRepository>()
        val dto = MenuItemDto(
            id = "",
            name = "",
            dsc = "",
            img = "",
            price = 0.0,
            rate = 0,
            country = ""
        )
        val mockResponse = Response.success(listOf(dto))
        `when`(mockRepository.getMenuList()).thenReturn(mockResponse)
        val useCase = GetMenuListUseCase(mockRepository)

        // Act
        val flow = useCase.invoke()
        val result = flow.toList()

        // Assert
        assertTrue(result[0] is RemoteDataState.Loading)
        assertTrue(result[1] is RemoteDataState.Success)
        assertEquals((result[1] as RemoteDataState.Success).data, listOf(dto.toDomain()))
    }

    @Test
    fun `emits error state on unsuccessful response`() = runBlocking {
        // Arrange
        val mockRepository = mock<MenuListRepository>()
        val mockResponse = Response.error<List<MenuItemDto>>(400, ResponseBody.create(null, "Error"))
        `when`(mockRepository.getMenuList()).thenReturn(mockResponse)
        val useCase = GetMenuListUseCase(mockRepository)

        // Act
        val flow = useCase.invoke()
        val result = flow.toList()

        // Assert
        assertTrue(result[0] is RemoteDataState.Loading)
        assertTrue(result[1] is RemoteDataState.Error)
    }
}
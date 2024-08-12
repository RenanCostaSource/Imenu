package costa.renan.imenu.data.remote.services

import costa.renan.imenu.data.remote.models.MenuItemDto
import retrofit2.Response
import retrofit2.http.GET

interface IMenuListService {
    @GET("/our-foods")
    suspend fun getMenuList(): Response<List<MenuItemDto>>
}
package costa.renan.imenu.data.remote.repositories

import costa.renan.imenu.data.remote.services.IMenuListService
import retrofit2.Retrofit
import javax.inject.Inject

class MenuListRepository @Inject constructor (retrofit: Retrofit): AbstractRepository<IMenuListService>(retrofit) {
    override val servicesClass = IMenuListService::class.java

    suspend fun getMenuList() = services.getMenuList()
}
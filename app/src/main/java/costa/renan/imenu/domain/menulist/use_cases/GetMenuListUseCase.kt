package costa.renan.imenu.domain.menulist.use_cases

import android.util.Log
import costa.renan.imenu.data.remote.RemoteDataState
import costa.renan.imenu.data.remote.models.toDomain
import costa.renan.imenu.data.remote.repositories.MenuListRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMenuListUseCase @Inject constructor(
    private val menuListRepository: MenuListRepository
) {
    suspend operator fun invoke() = flow {
        try {
            emit(RemoteDataState.Loading())
            val response = menuListRepository.getMenuList()
            val body = response.body()
            if (response.isSuccessful) {
                emit(RemoteDataState.Success(data = body?.toDomain()))
            } else {
                emit(RemoteDataState.Error(message = response.message()))
            }

        } catch (e: Exception) {
            emit(RemoteDataState.Error(message = e.message ?: "Unknown error"))
        }
    }

}
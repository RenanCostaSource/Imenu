package costa.renan.imenu.data.remote.repositories

import retrofit2.Retrofit

abstract class AbstractRepository<T>(retrofit: Retrofit) {
    protected abstract val servicesClass: Class<T>
    protected val services: T by lazy { retrofit.create(servicesClass) }
}
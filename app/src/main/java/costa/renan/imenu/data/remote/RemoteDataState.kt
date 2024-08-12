package costa.renan.imenu.data.remote

sealed class RemoteDataState<T>(val data: T? = null, val message: String = "") {
    class Loading<T>: RemoteDataState<T>()
    class Success<T>(data: T?): RemoteDataState<T>(data)
    class Error<T>(message: String, data: T? = null): RemoteDataState<T>(data, message)
}
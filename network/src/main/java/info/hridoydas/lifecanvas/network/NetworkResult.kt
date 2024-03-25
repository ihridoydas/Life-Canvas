package info.hridoydas.lifecanvas.network

import java.lang.Exception

sealed class NetworkResult<out T> {
    data class Success<T>(val result: T) : NetworkResult<T>()
    data class Error<Nothing>(val body: String?, val exception: Exception) :
        NetworkResult<Nothing>()
}

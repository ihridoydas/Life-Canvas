package info.hridoydas.lifecanvas.auth.domain

sealed class Resource<out T> {
    data class Error(val e: ResourceError) : Resource<Nothing>()
    data class Success<R>(val result: R) : Resource<R>()
}


enum class ResourceError {
    UNAUTHORIZED,
    SERVICE_UNAVAILABLE,
    UNKNOWN
}

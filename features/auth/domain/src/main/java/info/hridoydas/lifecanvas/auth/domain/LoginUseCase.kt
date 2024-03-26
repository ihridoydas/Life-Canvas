package info.hridoydas.lifecanvas.auth.domain

import info.hridoydas.lifecanvas.auth.data.AuthRepository
import info.hridoydas.lifecanvas.auth.data.UserLoginRequest
import info.hridoydas.lifecanvas.network.NetworkException
import info.hridoydas.lifecanvas.network.NetworkResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val mapper: UserMapper
) {

    suspend fun invoke(email: String, password: String) : Resource<User> {
        val request = UserLoginRequest(email, password)
        return when(val result = authRepository.login(request)){
            is NetworkResult.Error -> result.toResourceError()
            is NetworkResult.Success -> Resource.Success(mapper.map(result.result.data))
        }
    }
}


fun NetworkResult.Error<*>.toResourceError(): Resource.Error {
    return when(exception) {
        is NetworkException.NotFoundException -> Resource.Error(ResourceError.SERVICE_UNAVAILABLE)
        is NetworkException.UnauthorizedException -> Resource.Error(ResourceError.UNAUTHORIZED)
        else -> Resource.Error(ResourceError.UNKNOWN)
    }
}

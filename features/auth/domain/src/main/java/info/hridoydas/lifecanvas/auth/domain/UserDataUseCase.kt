package info.hridoydas.lifecanvas.auth.domain

import info.hridoydas.lifecanvas.auth.data.UserRepository
import info.hridoydas.lifecanvas.network.NetworkResult
import javax.inject.Inject


class UserDataUseCase @Inject constructor(
    private val repository: UserRepository,
    private val mapper: UserMapper,
) {

    suspend fun invoke(): Resource<User> {
        return when (val result = repository.user()) {
            is NetworkResult.Error -> result.toResourceError()
            is NetworkResult.Success -> Resource.Success(mapper.map(result.result.data))
        }
    }
}


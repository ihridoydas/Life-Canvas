package info.hridoydas.lifecanvas.auth.data

import info.hridoydas.lifecanvas.network.NetworkResult
import info.hridoydas.lifecanvas.network.Response

interface AuthRepository {
    suspend fun login(request: UserLoginRequest): NetworkResult<Response<UserApiModel>>
}

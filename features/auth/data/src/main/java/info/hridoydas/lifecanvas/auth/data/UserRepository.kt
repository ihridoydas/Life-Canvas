package info.hridoydas.lifecanvas.auth.data

import info.hridoydas.lifecanvas.network.NetworkResult
import info.hridoydas.lifecanvas.network.Response

interface UserRepository {
    suspend fun user(): NetworkResult<Response<UserApiModel>>
}

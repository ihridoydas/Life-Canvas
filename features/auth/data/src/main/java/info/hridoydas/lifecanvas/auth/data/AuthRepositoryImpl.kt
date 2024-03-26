package info.hridoydas.lifecanvas.auth.data

import info.hridoydas.lifecanvas.network.NetworkResult
import info.hridoydas.lifecanvas.network.RequestHandler
import info.hridoydas.lifecanvas.network.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val requestHandler: RequestHandler
) : AuthRepository {
    override suspend fun login(request: UserLoginRequest): NetworkResult<Response<UserApiModel>> {

        return requestHandler.post(
            urlPathSegments = listOf("auth", "login"),
            body = request
        )
    }
}

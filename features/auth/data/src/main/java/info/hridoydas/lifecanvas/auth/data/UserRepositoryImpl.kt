package info.hridoydas.lifecanvas.auth.data

import info.hridoydas.lifecanvas.network.NetworkResult
import info.hridoydas.lifecanvas.network.RequestHandler
import info.hridoydas.lifecanvas.network.Response
import javax.inject.Inject

private const val USER = "user"
class UserRepositoryImpl @Inject constructor(
    private val requestHandler: RequestHandler,
) : UserRepository {

    override suspend fun user(): NetworkResult<Response<UserApiModel>> =
        requestHandler.get(urlPathSegments = listOf(USER))
}

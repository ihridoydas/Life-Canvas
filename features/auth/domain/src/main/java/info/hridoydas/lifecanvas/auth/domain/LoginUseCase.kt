/*
* MIT License
*
* Copyright (c) 2024 Hridoy Chandra Das
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
*/
package info.hridoydas.lifecanvas.auth.domain

import info.hridoydas.lifecanvas.auth.data.AuthRepository
import info.hridoydas.lifecanvas.auth.data.UserLoginRequest
import info.hridoydas.lifecanvas.network.NetworkException
import info.hridoydas.lifecanvas.network.NetworkResult
import info.hridoydas.lifecanvas.storage.SessionHandler
import javax.inject.Inject

class LoginUseCase
    @Inject
    constructor(
        private val repository: AuthRepository,
        private val sessionHandler: SessionHandler,
        private val mapper: UserMapper,
    ) {
        suspend fun invoke(
            email: String,
            password: String,
        ): Resource<User> {
            val request = UserLoginRequest(email, password)
            return when (val result = repository.login(request)) {
                is NetworkResult.Error -> result.toResourceError()
                is NetworkResult.Success -> {
                    sessionHandler.setCurrentUser(result.result.data.id,result.result.data.authToken)
                    Resource.Success(mapper.map(result.result.data))
                }
            }
        }
    }

fun NetworkResult.Error<*>.toResourceError(): Resource.Error {
    return when (exception) {
        is NetworkException.NotFoundException -> Resource.Error(ResourceError.SERVICE_UNAVAILABLE)
        is NetworkException.UnauthorizedException -> Resource.Error(ResourceError.UNAUTHORIZED)
        is NetworkException.UnknownException -> Resource.Error(ResourceError.UNKNOWN)
        else -> {
            Resource.Error(ResourceError.UNKNOWN)
        }
    }
}

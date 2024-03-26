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
package info.hridoydas.lifecanvas.network
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.parameter
import io.ktor.client.request.prepareRequest
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RequestHandler(val httpClient: HttpClient) {
    suspend inline fun <reified B, reified R> executeRequest(
        method: HttpMethod,
        urlPathSegments: List<Any>,
        body: B? = null,
        queryParams: Map<String, Any>? = null,
    ): NetworkResult<R> {
        return withContext(Dispatchers.IO) {
            try {
                val response = httpClient.prepareRequest {
                    this.method = method
                    url {
                        val pathSegments = urlPathSegments.map { it.toString() }
                        appendPathSegments(pathSegments)
                    }
                    body?.let { setBody(it) }
                    queryParams?.let { params ->
                        params.forEach { (key, value) ->
                            parameter(key, value)
                        }
                    }
                }.execute().body<R>()
                NetworkResult.Success(response)
            } catch (e: Exception) {
                val networkException = if (e is ResponseException) {
                    val errorBody = e.response.body<DefaultError>()
                    when (e.response.status) {
                        HttpStatusCode.Unauthorized -> NetworkException.UnauthorizedException(
                            errorBody.message,
                            e,
                        )

                        else -> NetworkException.NotFoundException("API Not Found", e)
                    }
                } else {
                    NetworkException.UnknownException(e.message ?: "Unknown Error", e)
                }
                NetworkResult.Error(null, networkException)
            }
        }
    }

    suspend inline fun <reified R> get(
        urlPathSegments: List<Any>,
        queryParams: Map<String, Any>? = null,
    ): NetworkResult<R> =
        executeRequest<Any, R>(
            method = HttpMethod.Get,
            urlPathSegments = urlPathSegments.toList(),
            queryParams = queryParams,
        )

    suspend inline fun <reified B, reified R> post(
        urlPathSegments: List<Any>,
        body: B? = null,
    ): NetworkResult<R> =
        executeRequest(
            method = HttpMethod.Post,
            urlPathSegments = urlPathSegments.toList(),
            body = body,
        )
}

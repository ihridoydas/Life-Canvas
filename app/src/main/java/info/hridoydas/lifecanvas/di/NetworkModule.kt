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
package info.hridoydas.lifecanvas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.hridoydas.lifecanvas.BuildConfig
import info.hridoydas.lifecanvas.DataStoreSessionHandler
import info.hridoydas.lifecanvas.common.DEFAULT_PORT
import info.hridoydas.lifecanvas.network.LifeCanvasHttpClientBuilder
import info.hridoydas.lifecanvas.network.RequestHandler
import info.hridoydas.lifecanvas.storage.SessionHandler
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideSessionHandler(sessionHandler: DataStoreSessionHandler): SessionHandler = sessionHandler

    @Provides
    fun provideHttpClient(sessionHandler: SessionHandler): HttpClient =
        LifeCanvasHttpClientBuilder(sessionHandler)
            .protocol(URLProtocol.HTTP)
            .host(BuildConfig.LifeCanvas_HOST)
            .port(DEFAULT_PORT)
            .build()

    @Provides
    fun provideRequestHandler(client: HttpClient): RequestHandler = RequestHandler(client)
}

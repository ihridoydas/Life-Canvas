package info.hridoydas.lifecanvas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.hridoydas.lifecanvas.BuildConfig
import info.hridoydas.lifecanvas.network.LifeCanvasHttpClientBuilder
import info.hridoydas.lifecanvas.network.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideHttpClient(): HttpClient =
        LifeCanvasHttpClientBuilder()
            .protocol(URLProtocol.HTTP)
            .host(BuildConfig.LifeCanvas_HOST)
            .port(8080)
            .build()

    @Provides
    fun provideRequestHandler(client: HttpClient): RequestHandler = RequestHandler(client)
}

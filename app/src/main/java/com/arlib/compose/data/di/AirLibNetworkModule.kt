package com.arlib.compose.data.di

import com.arlib.compose.data.remote.api.AirLibMedicineApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This is a Dagger Hilt module that provides dependencies related to networking for the application.
 * It contains methods that provide instances of `HttpLoggingInterceptor`, `OkHttpClient`, `Retrofit`,
 * and `AirLibMedicineApiService`. These instances are scoped as singletons and are provided to
 * various parts of the application where required.
 *
 * The networking layer uses Retrofit for making HTTP requests and OkHttp for handling network
 * communication, including logging.
 *
 * This module is installed in the `SingletonComponent`, meaning the dependencies will have
 * application-wide single instances.
 */
@Module
@InstallIn(SingletonComponent::class)
object AirLibNetworkModule {

    /**
     * Provides a singleton instance of `HttpLoggingInterceptor`.
     *
     * This interceptor is responsible for logging network request and response details.
     * The logging level is set to `BODY`, meaning it logs request and response lines and their respective
     * headers and bodies (if present).
     *
     * @return An instance of `HttpLoggingInterceptor` with logging level set to `BODY`.
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY) // You can set this to HEADERS or BASIC for less detail
        return logging
    }

    /**
     * Provides a singleton instance of `OkHttpClient`.
     *
     * This method builds an `OkHttpClient` and attaches the logging interceptor to it. The
     * `OkHttpClient` is responsible for managing network requests and caching, and adding the
     * logging interceptor allows logging of the network requests and responses for debugging purposes.
     *
     * @param loggingInterceptor The `HttpLoggingInterceptor` used to log network requests and responses.
     * @return An instance of `OkHttpClient` with the logging interceptor attached.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * Provides a singleton instance of `Retrofit`.
     *
     * This method builds a `Retrofit` instance, which is responsible for creating API service
     * interfaces for network requests. The base URL is set here, and the OkHttpClient with logging
     * is attached to handle network communication. A `GsonConverterFactory` is added to handle
     * serialization and deserialization of JSON data.
     *
     * @param okHttpClient The `OkHttpClient` that handles network requests and logging.
     * @return An instance of `Retrofit` with the base URL and JSON converter attached.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://mocki.io/v1/") // Replace with your actual API base URL
            .client(okHttpClient) // Attach OkHttpClient with logging
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON conversion
            .build()
    }

    /**
     * Provides a singleton instance of `AirLibMedicineApiService`.
     *
     * This method uses the `Retrofit` instance to create the implementation of the `AirLibMedicineApiService`
     * interface. This interface defines the API calls that can be made in the app related to medicine data.
     *
     * @param retrofit The `Retrofit` instance used to generate the API service.
     * @return An instance of `AirLibMedicineApiService` for making network requests.
     */
    @Provides
    @Singleton
    fun provideMedicineApiService(retrofit: Retrofit): AirLibMedicineApiService {
        return retrofit.create(AirLibMedicineApiService::class.java)
    }
}

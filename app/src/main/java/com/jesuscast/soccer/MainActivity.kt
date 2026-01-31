package com.jesuscast.soccer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.crossfade
import coil3.svg.SvgDecoder
import coil3.util.DebugLogger
import com.jesuscast.soccer.core.di.AppContainer
import com.jesuscast.soccer.features.soccerTeam.di.SoccerTeamModule
import com.jesuscast.soccer.features.soccerTeam.presentation.screens.SoccerTeamsScreen
import okio.Path.Companion.toOkioPath

class MainActivity : ComponentActivity() {

    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Crear AppContainer primero
        appContainer = AppContainer(this)

        // Configurar ImageLoader de Coil con el mismo OkHttpClient que usa Retrofit
        SingletonImageLoader.setSafe {
            ImageLoader.Builder(this)
                .components {
                    add(SvgDecoder.Factory())
                    add(OkHttpNetworkFetcherFactory(callFactory = appContainer.okHttpClient))
                }
                .memoryCache {
                    MemoryCache.Builder()
                        .maxSizePercent(this, 0.25)
                        .build()
                }
                .diskCache {
                    DiskCache.Builder()
                        .directory(cacheDir.resolve("image_cache").toOkioPath())
                        .maxSizeBytes(50 * 1024 * 1024) // 50 MB
                        .build()
                }
                .logger(DebugLogger())
                .crossfade(true)
                .build()
        }

        Log.d("MainActivity", "ImageLoader configurado correctamente con OkHttpClient compartido")

        val soccerTeamsModule = SoccerTeamModule(appContainer)

        setContent {
            SoccerTeamsScreen(
                factory = soccerTeamsModule.provideSoccerTeamViewModelFactory()
            )
        }
    }
}




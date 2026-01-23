package com.exemaple.meuprimeiroapp.weatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Classe de Configuração Global do Hilt.
 *
 * Responsabilidades:
 * 1. Inicializar a geração de código do Dagger/Hilt.
 * 2. Criar o 'SingletonComponent' que hospeda todas as dependências @Singleton
 * (como o WeatherRepository).
 * 3. Servir de container raiz para a injeção de dependência em todo o app.
 *
 * Nota: Deve ser registrada no AndroidManifest.xml no atributo 'android:name'.
 */
@HiltAndroidApp // <--- O MÁGICO ACONTECE AQUI
class WeatherApplication : Application()
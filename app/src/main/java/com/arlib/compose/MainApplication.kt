package com.arlib.compose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The [MainApplication] class serves as the application-level entry point for the app.
 * It initializes the Dagger Hilt dependency injection framework.
 *
 * This class is annotated with [HiltAndroidApp], which triggers Hilt's code generation
 * and creates a Hilt component for the entire application. This component will
 * be used to manage the application's dependencies and allow them to be injected
 * into various parts of the application.
 *
 * Make sure to specify this class in the AndroidManifest.xml as the application
 * class.
 */
@HiltAndroidApp
class MainApplication : Application()

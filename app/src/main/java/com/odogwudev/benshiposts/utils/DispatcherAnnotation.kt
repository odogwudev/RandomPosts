package com.odogwudev.benshiposts.utils

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher
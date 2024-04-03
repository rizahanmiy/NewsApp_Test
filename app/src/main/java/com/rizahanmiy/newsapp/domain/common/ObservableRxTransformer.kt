package com.rizahanmiy.newsapp.domain.common

import io.reactivex.ObservableTransformer

abstract class ObservableRxTransformer <T>: ObservableTransformer<T, T>
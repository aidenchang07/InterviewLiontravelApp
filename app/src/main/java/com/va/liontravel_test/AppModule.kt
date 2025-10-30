package com.va.liontravel_test

import com.va.liontravel_test.data.repo.TravelRepositoryImpl
import com.va.liontravel_test.domain.repo.TravelRepository
import com.va.liontravel_test.presentation.TravelViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by AidenChang on 2025/10/22
 */
val appModule = module {
    single<TravelRepository> { TravelRepositoryImpl(get()) }
    viewModel { TravelViewModel(get()) }
}
package com.jbavaji.kotlinmvvm.di

import com.jbavaji.kotlinmvvm.model.CountriesService
import com.jbavaji.kotlinmvvm.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)

    fun inject(viewModel: ListViewModel)

}
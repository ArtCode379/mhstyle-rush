package mhstore.apparel.mhstylerush.di

import mhstore.apparel.mhstylerush.ui.viewmodel.AppViewModel
import mhstore.apparel.mhstylerush.ui.viewmodel.CartViewModel
import mhstore.apparel.mhstylerush.ui.viewmodel.CheckoutViewModel
import mhstore.apparel.mhstylerush.ui.viewmodel.JNVNJOnboardingVM
import mhstore.apparel.mhstylerush.ui.viewmodel.OrderViewModel
import mhstore.apparel.mhstylerush.ui.viewmodel.ProductDetailsViewModel
import mhstore.apparel.mhstylerush.ui.viewmodel.ProductViewModel
import mhstore.apparel.mhstylerush.ui.viewmodel.JNVNJSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        JNVNJSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        JNVNJOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}
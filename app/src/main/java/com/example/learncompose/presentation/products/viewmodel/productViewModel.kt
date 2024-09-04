package com.example.learncompose.presentation.products.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learncompose.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ProductState())
    val state: State<ProductState> = _state

     fun onEvent(event: ProductEvent) {
         viewModelScope.launch {
             when (event) {
                 is ProductEvent.LoadCategories -> {
                     loadCategories()
                     println("After")
                 }

                 is ProductEvent.LoadProductsByCategory -> {
                     loadProductsByCategory(event.category)
                 }
             }
         }

    }

//    init {
//        viewModelScope.launch {
//            loadCategories()
//        }
//    }

    private suspend fun loadCategories() {
        println("After 111")

        _state.value = state.value.copy(
            isLoading = true
        )
        val res = productUseCase.invoke()


        _state.value = state.value.copy(
            isLoading = false,
            categoryResponse = res
        )

    }

    private suspend fun loadProductsByCategory(category: String) {
        println("After 222")

//        _state.value = state.value.copy(
//            isLoading = true
//        )
        val res = productUseCase.getProductsInCategory(category)


        _state.value = state.value.copy(
//            isLoading = false,
            productModel = res
        )

    }

}
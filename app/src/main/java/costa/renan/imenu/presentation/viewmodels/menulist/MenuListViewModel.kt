package costa.renan.imenu.presentation.viewmodels.menulist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import costa.renan.imenu.data.remote.RemoteDataState
import costa.renan.imenu.domain.menulist.models.MenuListItem
import costa.renan.imenu.domain.menulist.use_cases.GetMenuListUseCase
import costa.renan.imenu.domain.shoppingcart.GetAllCartItemsUseCase
import costa.renan.imenu.domain.shoppingcart.GetCartItemByIdUseCase
import costa.renan.imenu.domain.shoppingcart.UpsertCartItemsUseCase
import costa.renan.imenu.presentation.ui.view.menulist.MenuListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuListViewModel @Inject constructor(
    private val getMenuListUseCase: GetMenuListUseCase,
    private val upsertCartItemsUseCase: UpsertCartItemsUseCase,
    private val getCartItemByIdUseCase: GetCartItemByIdUseCase,
    private val getAllCartItemsUseCase: GetAllCartItemsUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<MenuListState> = MutableStateFlow(MenuListState())

    val uiState: StateFlow<MenuListState> = _stateFlow.asStateFlow()

    //TODO: Implement pagination
    fun getMenuList() = viewModelScope.launch {
        getMenuListUseCase.invoke().onEach { response ->
            when (response) {
                is RemoteDataState.Loading -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        isLoading = true,
                        items = emptyList()
                    )
                }

                is RemoteDataState.Success -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        isLoading = false,
                        items = response.data ?: emptyList()
                    )
                }

                is RemoteDataState.Error -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        isLoading = false,
                        items = null,
                        errorMessage = response.message
                    )
                }
            }
        }.launchIn(this)
    }

    fun updateCartSize(){
        viewModelScope.launch {
            _stateFlow.value = _stateFlow.value.copy(
                cartSize = getAllCartItemsUseCase.invoke().size
            )
        }
    }

    fun openSheet(item: MenuListItem) = viewModelScope.launch {
        val cartItem = getCartItemByIdUseCase.invoke(item.id)
        _stateFlow.value = _stateFlow.value.copy(
            openSheet = true,
            sheetItem = item,
            amount = cartItem?.second ?: 1
        )
    }
    //TODO: Add remove item from cart feature
    fun addToCart() = viewModelScope.launch {
        if (_stateFlow.value.sheetItem != null) {
            upsertCartItemsUseCase.invoke(
                Pair(
                    _stateFlow.value.sheetItem!!,
                    _stateFlow.value.amount
                )
            )
            updateCartSize()
        }
    }

    fun closeSheet() {
        _stateFlow.value = _stateFlow.value.copy(
            openSheet = false,
            sheetItem = null,
            amount = 1
        )
    }

    fun increaseAmount() {
        _stateFlow.value = _stateFlow.value.copy(
            amount = _stateFlow.value.amount + 1
        )
    }

    fun decreaseAmount() {
        if (_stateFlow.value.amount > 1) {
            _stateFlow.value = _stateFlow.value.copy(
                amount = _stateFlow.value.amount - 1
            )
        }
    }
}
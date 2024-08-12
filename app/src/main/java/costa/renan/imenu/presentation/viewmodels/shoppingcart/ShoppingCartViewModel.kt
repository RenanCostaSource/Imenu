package costa.renan.imenu.presentation.viewmodels.shoppingcart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import costa.renan.imenu.domain.menulist.models.MenuListItem
import costa.renan.imenu.domain.shoppingcart.DeleteAllCartItemsUseCase
import costa.renan.imenu.domain.shoppingcart.DeleteCartItemsUseCase
import costa.renan.imenu.domain.shoppingcart.GetAllCartItemsUseCase
import costa.renan.imenu.domain.shoppingcart.UpsertCartItemsUseCase
import costa.renan.imenu.presentation.ui.view.shoppingcart.shoppingcart.ShoppingCartState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val getAllCartItemsUseCase: GetAllCartItemsUseCase,
    private val upsertCartItemsUseCase: UpsertCartItemsUseCase,
    private val deleteCartItemsUseCase: DeleteCartItemsUseCase,
    private val deleteAllCartItemsUseCase: DeleteAllCartItemsUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ShoppingCartState> =
        MutableStateFlow(ShoppingCartState())

    val stateFlow: StateFlow<ShoppingCartState> = _stateFlow.asStateFlow()

    fun buy() = viewModelScope.launch {
        deleteAllCartItemsUseCase.invoke()
    }

    fun getCartItems() = viewModelScope.launch  {
        _stateFlow.value = _stateFlow.value.copy(
            items = getAllCartItemsUseCase.invoke()
        )
    }

    fun increase(item: Pair<MenuListItem, Int>) = viewModelScope.launch {
        upsertCartItemsUseCase.invoke(item.copy(first = item.first, second = item.second + 1))
        getCartItems()
    }

    fun decrease(item: Pair<MenuListItem, Int>) = viewModelScope.launch {
        upsertCartItemsUseCase.invoke(item.copy(first = item.first, second = item.second - 1))
        getCartItems()
    }

    fun delete(item: Pair<MenuListItem, Int>) = viewModelScope.launch {
        deleteCartItemsUseCase.invoke(item.first.id)
        getCartItems()
    }

}
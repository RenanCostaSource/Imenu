package costa.renan.imenu.presentation.viewmodels.shoppingcart

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import costa.renan.imenu.presentation.ui.view.shoppingcart.shoppingcart.ShoppingCartState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ShoppingCartState> =
        MutableStateFlow(ShoppingCartState())

    val stateFlow: StateFlow<ShoppingCartState> = _stateFlow.asStateFlow()


}
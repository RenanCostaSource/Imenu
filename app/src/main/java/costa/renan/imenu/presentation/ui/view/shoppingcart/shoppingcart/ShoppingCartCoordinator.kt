package costa.renan.imenu.presentation.ui.view.shoppingcart.shoppingcart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import costa.renan.imenu.presentation.viewmodels.shoppingcart.ShoppingCartViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ShoppingCartCoordinator(
    val viewModel: ShoppingCartViewModel
) {
    val screenStateFlow = viewModel.stateFlow

}

@Composable
fun rememberShoppingCartCoordinator(
    viewModel: ShoppingCartViewModel = hiltViewModel()
): ShoppingCartCoordinator {
    return remember(viewModel) {
        ShoppingCartCoordinator(
            viewModel = viewModel
        )
    }
}
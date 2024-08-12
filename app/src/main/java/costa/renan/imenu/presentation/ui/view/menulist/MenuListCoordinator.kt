package costa.renan.imenu.presentation.ui.view.menulist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import costa.renan.imenu.presentation.viewmodels.menulist.MenuListViewModel

class MenuListCoordinator(
    val viewModel: MenuListViewModel
) {
    val uiState = viewModel.uiState
}

@Composable
fun rememberMenuListCoordinator(
    viewModel: MenuListViewModel = hiltViewModel<MenuListViewModel>()
): MenuListCoordinator {
    return remember(viewModel) {
        MenuListCoordinator(
            viewModel = viewModel
        )
    }
}
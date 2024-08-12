package costa.renan.imenu.presentation.ui.view.menulist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun MenuListRoute(
    coordinator: MenuListCoordinator = rememberMenuListCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.uiState.collectAsState(MenuListState())

    // UI Actions
    val actions = rememberMenuListActions(coordinator)

    // UI Rendering
    MenuListScreen(uiState, actions)
}


@Composable
fun rememberMenuListActions(coordinator: MenuListCoordinator): MenuListActions {
    return remember(coordinator) {
        MenuListActions(
            onOpen = {
                coordinator.viewModel.getMenuList()
            },
            onSelect = { item -> coordinator.viewModel.openSheet(item) },
            onDismiss = { coordinator.viewModel.closeSheet() },
            onIncrease = { coordinator.viewModel.increaseAmount() },
            onDecrease = { coordinator.viewModel.decreaseAmount() },
            onAddToCart = {
                //TODO
            }
        )
    }
}
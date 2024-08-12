package costa.renan.imenu.presentation.ui.view.menulist

import costa.renan.imenu.domain.menulist.models.MenuListItem

data class MenuListState(
    val isLoading: Boolean = false,
    val items: List<MenuListItem>? = null,
    val errorMessage: String = "",
    var openSheet: Boolean = false,
    var sheetItem: MenuListItem? = null,
    var amount: Int = 1
)

data class MenuListActions(
    val onClick: () -> Unit = {},
    val onOpen: () -> Unit = {},
    val onSelect: (MenuListItem) -> Unit = {},
    val onDismiss: () -> Unit = {},
    val onIncrease: () -> Unit = {},
    val onDecrease: () -> Unit = {},
    val onAddToCart: () -> Unit = {},
    val onOpenShoppingCart: () -> Unit = {}
)



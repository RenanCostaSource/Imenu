package costa.renan.imenu.presentation.ui.view.shoppingcart.shoppingcart

import costa.renan.imenu.domain.menulist.models.MenuListItem

data class ShoppingCartState(
    val items: List<Pair<MenuListItem, Int>>? = null,
)

data class ShoppingCartActions(
    val onSave: () -> Unit = {},
    val onDismiss: () -> Unit = {},
    val onIncrease: (MenuListItem) -> Unit = {},
    val onDecrease: (MenuListItem) -> Unit = {},
    val onRemove: (MenuListItem) -> Unit = {},
)



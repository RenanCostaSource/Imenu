package costa.renan.imenu.presentation.ui.view.shoppingcart.shoppingcart

import costa.renan.imenu.domain.menulist.models.MenuListItem

data class ShoppingCartState(
    val items: List<Pair<MenuListItem, Int>>? = null,
)

data class ShoppingCartActions(
    val onOpen: () -> Unit = {},
    val onBuy: () -> Unit = {},
    val onDismiss: () -> Unit = {},
    val onIncrease: (Pair<MenuListItem, Int>) -> Unit = {},
    val onDecrease: (Pair<MenuListItem, Int>) -> Unit = {},
    val onRemove: (Pair<MenuListItem, Int>) -> Unit = {},
)



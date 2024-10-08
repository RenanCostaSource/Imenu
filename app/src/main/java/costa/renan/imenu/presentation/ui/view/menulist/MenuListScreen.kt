package costa.renan.imenu.presentation.ui.view.menulist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import costa.renan.imenu.presentation.ui.view.menulist.components.AddToCartSheet
import costa.renan.imenu.presentation.ui.view.menulist.components.MenuItem

@Composable
fun MenuListScreen(
    state: MenuListState,
    actions: MenuListActions,
) {
    LaunchedEffect(key1 = true) {
        actions.onOpen()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(64.dp)
                .background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(start = 24.dp),
                text = "IMenu",
                color = Color.White,
                fontSize = 24.sp
            )
            BadgedBox(modifier = Modifier.padding(end = 24.dp).clickable {
                actions.onOpenShoppingCart()
            }, badge = {
                Badge {
                    Text(
                        state.cartSize.toString(),
                        modifier =
                        Modifier.semantics {
                            contentDescription = "${state.cartSize} itens in cart"
                        }
                    )
                }
            }) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "Shopping Cart",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

        }
        when {
            state.isLoading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            !state.items.isNullOrEmpty() -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 48.dp)
                ) {
                    state.items.forEach { menuItem ->
                        item {
                            MenuItem(
                                modifier = Modifier,
                                menuListItem = menuItem,
                                onClick = actions.onSelect
                            )
                        }
                    }
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Failed to get menu list \n ${state.errorMessage}")
                }
            }
        }
        AddToCartSheet(
            item = state.sheetItem,
            amount = state.amount,
            showSheet = state.openSheet,
            onDismiss = actions.onDismiss,
            onIncrease = actions.onIncrease,
            onDecrease = actions.onDecrease,
            onAddToCart = actions.onAddToCart
        )
    }
}

@Composable
@Preview(name = "MenuList", showBackground = true)
private fun MenuListScreenPreview() {
    MenuListScreen(
        state = MenuListState(),
        actions = MenuListActions()
    )
}

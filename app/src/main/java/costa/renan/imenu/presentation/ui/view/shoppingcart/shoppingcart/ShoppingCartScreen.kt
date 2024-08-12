package costa.renan.imenu.presentation.ui.view.shoppingcart.shoppingcart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import costa.renan.imenu.domain.menulist.models.MenuListItem
import costa.renan.imenu.presentation.ui.view.shoppingcart.shoppingcart.components.CartItem

@Composable
fun ShoppingCartScreen(
    state: ShoppingCartState,
    actions: ShoppingCartActions,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Shopping Cart",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp
            )
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { actions.onDismiss() },
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Scaffold(
            modifier = Modifier.padding(bottom = 24.dp),
            bottomBar = {
                Column(Modifier.padding(horizontal = 24.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Total: ", fontSize = 20.sp)
                        Text(text = "R$ 0,00", fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(modifier = Modifier
                        .fillMaxWidth(), onClick = actions.onSave) {
                        Text(text = "Finish purchase", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    CartItem(
                        Modifier,
                        Pair(
                            MenuListItem(
                                imageURL = "https://goldbelly.imgix.net/uploads/showcase_media_asset/image/66752/carolina-bbq-oink-sampler.1340b5a10cedc238cb2280306dd1d5a5.jpg?ixlib=react-9.0.2&auto=format&ar=1%3A1",
                                name = "Joe's BBQ",
                                description = "really big description to test how it lookssssssssssss",
                                price = 20f,
                                rate = 3
                            ),
                            1
                        )
                    )
                }
            }
        }
    }
}

@Composable
@Preview(name = "ShoppingCart")
private fun ShoppingCartScreenPreview() {
    ShoppingCartScreen(
        state = ShoppingCartState(),
        actions = ShoppingCartActions()
    )
}

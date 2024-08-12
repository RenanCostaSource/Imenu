package costa.renan.imenu.presentation.ui.view.shoppingcart.shoppingcart.components

import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import costa.renan.imenu.domain.menulist.models.MenuListItem
import costa.renan.imenu.utils.extensions.formatCurrency

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    cartListItem: Pair<MenuListItem, Int>,
    onDelete: (MenuListItem) -> Unit = {},
    onIncrease: (MenuListItem) -> Unit = {},
    onDecrease: (MenuListItem) -> Unit = {}
) {
    val menuListItem = cartListItem.first
    val amount = cartListItem.second
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp, 8.dp),) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier=Modifier.weight(1f)) {
                Text(
                    text = menuListItem.name,
                    fontSize = 20.sp,
                    softWrap = true,
                    style = TextStyle.Default.copy(lineBreak = LineBreak.Simple)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = modifier
                        .border(
                            shape = RoundedCornerShape(4.dp),
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        .fillMaxWidth(0.3f)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(amount>1) {
                        Text(
                            text = "-",
                            modifier = Modifier
                                .clickable { onDecrease(menuListItem) }
                                .width(IntrinsicSize.Max),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp
                        )
                    } else {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.Delete),
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .clickable { onDelete(menuListItem) }
                                .size(16.dp),
                        )
                    }
                    Text(text = amount.toString(), fontSize = 16.sp)
                    Text(
                        text = "+",
                        modifier = Modifier
                            .clickable { onIncrease(menuListItem) }
                            .width(IntrinsicSize.Max),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp
                    )
                }
            }
            Text(modifier = Modifier.weight(0.27f), text = (menuListItem.price * amount).formatCurrency(), fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
    }
}

@Preview(name = "MenuItem", showBackground = true)
@Composable
private fun PreviewMenuItem() {
    CartItem(
        Modifier,
        Pair(
            MenuListItem(
                imageURL = "https://goldbelly.imgix.net/uploads/showcase_media_asset/image/66752/carolina-bbq-oink-sampler.1340b5a10cedc238cb2280306dd1d5a5.jpg?ixlib=react-9.0.2&auto=format&ar=1%3A1",
                name = "Joe's BBQ realyyyyyyy long fooooorrr realllllllll",
                description = "really big description to test how it lookssssssssssss",
                price = 20f,
                rate = 1
            ),
            1
        )
    )
}
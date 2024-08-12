package costa.renan.imenu.presentation.ui.view.menulist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import costa.renan.imenu.R
import costa.renan.imenu.domain.menulist.models.MenuListItem
import costa.renan.imenu.utils.extensions.formatCurrency

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    menuListItem: MenuListItem,
    onClick: (MenuListItem) -> Unit = {}
) {
    Column(modifier.fillMaxSize().clickable { onClick(menuListItem) }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.width(IntrinsicSize.Max).weight(1f)) {
                Text(
                    text = menuListItem.name,
                    fontSize = 18.sp,
                    softWrap = true,
                    style = TextStyle.Default.copy(lineBreak = LineBreak.Simple)
                )
                Text(
                    text = menuListItem.description,
                    color = Color.Gray,
                    softWrap = true,
                    style = TextStyle.Default.copy(lineBreak = LineBreak.Simple)
                )
                Text(text = menuListItem.price.formatCurrency())
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.Star,
                        contentDescription = "Item rating",
                        tint = Color.Gray
                    )
                    Text(text = menuListItem.rate.toString(), color = Color.Gray, fontSize = 16.sp)
                }
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(menuListItem.imageURL)
                    .crossfade(true)
                    .build(),
                contentDescription = "Image for ${menuListItem.name}",
                placeholder =  painterResource(id = R.drawable.ic_food_placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(64.dp).weight(0.30f),
            )
        }
        HorizontalDivider()
    }
}

@Preview(name = "MenuItem", showBackground = true)
@Composable
private fun PreviewMenuItem() {
    MenuItem(
        Modifier,
        MenuListItem(
            imageURL = "https://goldbelly.imgix.net/uploads/showcase_media_asset/image/66752/carolina-bbq-oink-sampler.1340b5a10cedc238cb2280306dd1d5a5.jpg?ixlib=react-9.0.2&auto=format&ar=1%3A1",
            name = "really big name to test how it looksssssssssss",
            description = "really big description to test how it lookssssssssssss",
            price = 20f,
            rate = 3
        )
    )
}
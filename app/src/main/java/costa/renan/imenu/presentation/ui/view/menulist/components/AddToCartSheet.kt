package costa.renan.imenu.presentation.ui.view.menulist.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import costa.renan.imenu.R
import costa.renan.imenu.domain.menulist.models.MenuListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToCartSheet(
    modifier: Modifier = Modifier,
    item: MenuListItem?,
    amount: Int,
    showSheet: Boolean,
    onDismiss: () -> Unit = {},
    onIncrease: () -> Unit = {},
    onDecrease: () -> Unit = {},
    onAddToCart: () -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState()
    if (showSheet) {
        ModalBottomSheet(onDismissRequest = onDismiss, sheetState = sheetState) {
            Column(
                modifier = modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = item?.name ?: "", fontSize = 24.sp)
                Text(text = item?.description ?: "", color = Color.Gray)
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item?.imageURL?:"")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image for ${item?.name ?: ""}",
                    placeholder =  painterResource(id = R.drawable.ic_food_placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(140.dp),
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = modifier
                        .border(
                            shape = RoundedCornerShape(4.dp),
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        .fillMaxWidth(0.4f)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "-",
                        modifier.clickable { onDecrease() }.width(IntrinsicSize.Max),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 24.sp
                    )
                    Text(text = amount.toString(), fontSize = 24.sp)
                    Text(
                        text = "+",
                        modifier.clickable { onIncrease() }.width(IntrinsicSize.Max),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 24.sp
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(modifier = Modifier.fillMaxWidth(), onClick = onAddToCart) {
                    Text(text = "Add to Cart", color = MaterialTheme.colorScheme.onPrimary)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(name = "AddToCartSheet")
@Composable
private fun PreviewAddToCartSheet() {
    AddToCartSheet(
        showSheet = true,
        amount = 1,
        item = MenuListItem(
            name = "Hamburguer",
            id = "1",
            price = 10f,
            rate = 5,
            description = "",
            imageURL = ""
        )
    )
}
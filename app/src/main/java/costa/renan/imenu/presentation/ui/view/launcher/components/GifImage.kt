package costa.renan.imenu.presentation.ui.view.launcher.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.request.onAnimationEnd
import coil.request.repeatCount
import coil.size.Size
import costa.renan.imenu.R

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
    gifId: Int,
    onEnd: () -> Unit = {}
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
                add(ImageDecoderDecoder.Factory())
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = gifId).apply(block = {
                size(Size.ORIGINAL)
            }).repeatCount(0).onAnimationEnd(onEnd).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier.fillMaxWidth(),
    )
}

@Preview(name = "GifImage", showBackground = true)
@Composable
private fun PreviewGifImage() {
    GifImage(gifId = R.drawable.gif_launcher)
}
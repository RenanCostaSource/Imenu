package costa.renan.imenu.presentation.ui.view.launcher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import costa.renan.imenu.R
import costa.renan.imenu.presentation.ui.view.launcher.components.GifImage

@Composable
fun Launcher(onEnd: () -> Unit = {}) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,) {
        //I rather use Lottie for this
        GifImage(gifId = R.drawable.gif_launcher, onEnd = onEnd)
    }
}

@Preview(name = "Launcher", showBackground = true)
@Composable
fun PreviewLauncher() {
    Launcher()
}
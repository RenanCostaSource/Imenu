package costa.renan.imenu.presentation.ui.theme

import androidx.compose.ui.graphics.Color

object LightPalette: PaletteAbstractColors() {
    override val primary: Color
        get() = Color(0xFFFF7F7F)
    override val secondary: Color
        get() = Color(0xFF5E3030)

    override val background: Color
        get() = Color.White

    override val surface: Color
        get() = Color.White

    override val error: Color
        get() = Color.Red

    override val tertiary: Color
        get() = Color.Blue
}
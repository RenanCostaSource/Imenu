package costa.renan.imenu.utils.extensions

import java.text.NumberFormat
import java.util.Locale

fun Number.formatCurrency(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formatter.format(this)

}
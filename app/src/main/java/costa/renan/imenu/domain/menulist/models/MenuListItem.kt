package costa.renan.imenu.domain.menulist.models

data class MenuListItem(
    val id: String = "",
    val imageURL: String,
    val name: String,
    val description: String,
    val price: Float,
    val rate: Int,
)

package costa.renan.imenu.data.remote.models

import com.google.gson.annotations.SerializedName
import costa.renan.imenu.domain.menulist.models.MenuListItem

data class MenuItemDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("dsc")
    val dsc: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("rate")
    val rate: Int,
    @SerializedName("country")
    val country: String,
) {
    fun toDomain() = MenuListItem(
        id = id,
        imageURL = img,
        name = name,
        price = price.toFloat(),
        rate = rate,
        description = dsc
    )
}

fun List<MenuItemDto>.toDomain() = map {
    it.toDomain()
}

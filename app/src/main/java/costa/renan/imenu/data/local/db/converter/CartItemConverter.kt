package costa.renan.imenu.data.local.db.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import costa.renan.imenu.data.local.db.entity.CartItemEntity

@ProvidedTypeConverter
class CartItemConverter {
    @TypeConverter
    fun fromCartItem(entity: CartItemEntity?): String? {
        return Gson().toJson(entity)
    }

    @TypeConverter
    fun toCartItem(json: String?): CartItemEntity? {
        return Gson().fromJson(json, CartItemEntity::class.java)

    }
}
package costa.renan.imenu.data.local.db.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItemEntity (
    @PrimaryKey(autoGenerate = false)
    val id: String = "",

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "price")
    val price: Float,

    @ColumnInfo(name = "rate")
    val rate: Int,

    @ColumnInfo(name = "image_url")
    val imageURL: String,

    @ColumnInfo(name = "amount")
    val amount: Int,
)
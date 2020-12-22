package com.example.sprinklerassignement.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.lang.reflect.Type

enum class FILTER_TYPE(val value: String) {
    BOOKMARK("BOOKMARK"),
    TRENDING("TRENDING"),
    MACHINE_LEARNING("MACHINE_LEARNING"),
    ED_TECH("ED_TECH"),
    MEDICAL_TECH("MEDICAL_TECH"),
    NONE("NONE");

    companion object {
        fun getFilterByValue(value:String) =  values().find { it.value == value } ?: NONE
    }

}

@Entity(tableName = "techProducts")
class TechProduct: JsonDeserializer<TechProduct> {

    @PrimaryKey
    @SerializedName("productId")
    var productId: Long? = null

    @SerializedName("title")
    var title: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("productImage")
    var productImage: String? = null
    @SerializedName("founderName")
    var founderName:String? = null
    @SerializedName("profileLink")
    var profileLink: String? = null
    @SerializedName("upvoteCount")
    var upvoteCount: Int = 0
    @SerializedName("type")
    var type: String? = null
    @SerializedName("isBookmarked")
    var isBookmarked: Boolean = false
    @SerializedName("isUpvoted")
    var isUpvoted: Boolean = false

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): TechProduct {
        val techProduct = TechProduct()

        val product  = JSONObject(json.toString())

        techProduct.productId = product.optLong("productId",0L)
        techProduct.title = product.optString("title", "")
        techProduct.description = product.optString("description","")
        techProduct.productImage =  product.optString("productImage","")
        techProduct.founderName = product.optString("founderName","")
        techProduct.profileLink =  product.optString("profileLink","")
        techProduct.upvoteCount =  product.optInt("upvoteCount",0)
        techProduct.type = product.optString("type","")
        techProduct.isBookmarked =  product.optBoolean("isBookmarked",false)
        techProduct.isUpvoted = product.optBoolean("isUpvoted",false)
        return techProduct
    }

}

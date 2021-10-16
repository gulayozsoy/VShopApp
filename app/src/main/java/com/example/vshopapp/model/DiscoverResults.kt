package com.example.vshopapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DiscoverResults  (

    @SerializedName("type")
    var type: String,

    @SerializedName("title")
    var title: String,

    @SerializedName(value="featured")
    var feature: ArrayList<Feature>,
    @SerializedName(value="products")
    var product: ArrayList<Product>,
    @SerializedName(value="categories")
    var category: ArrayList<Category>,
    @SerializedName(value="collections")
    var collection: ArrayList<Collectioon>,
    @SerializedName(value="shops")
    var editorshops: ArrayList<EditorShops>

):Serializable

// ------ Featured ------
data class Feature(
    @SerializedName("cover")
    var cover: FCover?,

    @SerializedName("title")
    var title: String?,

    @SerializedName("sub_title")
    var subtitle: String?
)

data class FCover(
    @SerializedName("url")
    var url: String?
)

// ------ Products------
data class Product(
    @SerializedName("title")
    var title: String,

    @SerializedName("shop")
    var pshop: PShop,

    @SerializedName("images")
    var pimages: ArrayList<PImages>
): Serializable

data class PShop(
    @SerializedName("name")
    var name: String
): Serializable

data class PImages(
    @SerializedName("medium")
    var medium: PMedium
): Serializable

data class PMedium(
    @SerializedName("url")
    var purl: String
): Serializable



// ------ Categories ------
data class Category(
    @SerializedName("name")
    var name: String,
    @SerializedName("logo")
    var clogo: CLogo
)

data class CLogo(
    @SerializedName("url")
    var curl: String
)


// ------ Collections ------

data class Collectioon(
    @SerializedName("title")
    var title: String,
    @SerializedName("definition")
    var definition: String,
    @SerializedName("cover")
    var cocover: CoCover
):Serializable

data class CoCover(
    @SerializedName("url")
    var courl: String
):Serializable


// ------ Editor Shops ------
data class EditorShops(
    @SerializedName("name")
    var name: String,
    @SerializedName("logo")
    var elogo: ELogo,
    @SerializedName("definition")
    var definition: String,
    @SerializedName("product_count")
    var nsproductcount: String,
    @SerializedName("popular_products")
    var popularproducts: ArrayList<PopularProducts>,

    @SerializedName("cover")
    var escover: ESCover
): Serializable

data class ELogo(
    @SerializedName("url")
    var url: String
):Serializable

data class ESCover(
    @SerializedName("url")
    var esurl: String?
):Serializable


data class PopularProducts(
    @SerializedName("images")
    var images: ArrayList<EImages>
):Serializable

data class EImages(
    @SerializedName("medium")
    var emedium: EMedium
):Serializable

data class EMedium(
    @SerializedName("url")
    var url: String
):Serializable

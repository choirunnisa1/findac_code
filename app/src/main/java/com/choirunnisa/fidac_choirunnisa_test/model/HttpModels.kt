package com.choirunnisa.fidac_choirunnisa_test.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductsData(
  val status : String,
  val message: String,
  val data : List<Products>
)

data class Products(
    val id_produk : Int,
    val product_name : String,
    val product_desc : String,
    val product_price : String,
    val product_image : String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class UsersData(
    val status: String,
    val message: String,
    val data: List<User>
)

data class User(
    val data_diri : String,
    val riwayat_pendidikan: String,
    val subtitle1 : List<Name>,
    val subtitle2: List<Pendidikan>
)

data class Name(
    val subtitle1: String,
    val value1: String
)

data class Pendidikan(
    val subtitle2: String,
    val value2: String
)

data class DetailProductsData(
    val status: String,
    val message: String,
    val data: List<DetailProduct>
)

data class DetailProduct(
    val id_produk: Int,
    val product_name : String,
    val product_desc: String,
    val product_price: String,
    val product_image: String,
    val variant: List<Variants>
)

data class Variants(
    val id_variant: Int,
    val id_produk: Int,
    val variant_type : String,
    val variant_color : String,
    val variant_image : String
)

data class ImageSlider(
    val imageSlide : String
)

data class reqProduct(
    val id : Int
)

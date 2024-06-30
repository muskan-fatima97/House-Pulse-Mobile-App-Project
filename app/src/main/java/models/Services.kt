package models

data class Service(
    val imageRes: Int,
    val serviceName: String,
    val reviews: Int,
    val price: String,
    val originalPrice: String,
    val providerName: String,
    val providerRole: String,
    val providerImage: Int
)


package com.example.bolis.data.models

import kotlinx.serialization.Serializable

@Serializable
data class BarModel(
    val invoiceNumber: String,
    val client: Client,
    val purchase: List<PurchaseItem>,
    val totalAmount: Double
)

@Serializable
data class Client(
    val name: String,
    val email: String,
    val address: String
)

@Serializable
data class PurchaseItem(
    val item: String,
    val quantity: Int,
    val price: Double
)
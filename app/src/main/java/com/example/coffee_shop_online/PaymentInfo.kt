package com.example.coffee_shop_online

data class PaymentInfo(val orderId: String, val cardNumber:String, val holderName:String,
                       val Amount: String, val cvv:String, val ExDate:String )

package com.lotto.lottoapp.model.response.register


 data class RegisterResponseItem(
     val `data`: RegisterData,
     val message: String,
     val success: Boolean
 ){
     var code = ""
 }

 data class RegisterData(
     val createdAt: String,
     val email: String,
     val emailIdentifier: String,
     val expiresAt: String,
     val type: String
 )
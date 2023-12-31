package me.kzv.mandalart.support.exception

class MandalartException(error: MandalartError = MandalartError.UNKNOWN) : RuntimeException(error.messageKey)

enum class MandalartError(
    val messageKey: String? = null
){
    UNKNOWN("error.unknown"),
}
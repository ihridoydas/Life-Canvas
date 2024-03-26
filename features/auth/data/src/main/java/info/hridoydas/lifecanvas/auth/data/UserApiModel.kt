package info.hridoydas.lifecanvas.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class UserApiModel(
    val authToken: String? = null,
    val avatar: String,
    val createAt: String,
    val email: String,
    val fullName: String,
    val id: Int,
)

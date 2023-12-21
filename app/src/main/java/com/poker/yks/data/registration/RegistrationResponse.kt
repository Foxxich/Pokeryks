package com.poker.yks.data.registration

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationResponse(
    @Json(name = "password")
    val password: String?,
    @Json(name = "last_login")
    val lastLogin: String?,
    @Json(name = "is_superuser")
    val isSuperuser: Boolean?,
    @Json(name = "username")
    val username: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "is_vip")
    val isVip: Boolean?,  // Nullable
    @Json(name = "is_staff")
    val isStaff: Boolean?,
    @Json(name = "is_active")
    val isActive: Boolean?,
    @Json(name = "groups")
    val groups: List<Any>?,
    @Json(name = "user_permissions")
    val userPermissions: List<Any>?
)

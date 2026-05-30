package com.justincontreras.androidbasicsclass.ui.login.model

data class UserCredentials(
    val username: String,
    val token: String
)

data class LoginUiState(
    val isLoading: Boolean = false,
    val usernameError: String? = null,
    val passwordError: String? = null,
    val loginError: String? = null,
    val isLoginSuccess: Boolean = false
)
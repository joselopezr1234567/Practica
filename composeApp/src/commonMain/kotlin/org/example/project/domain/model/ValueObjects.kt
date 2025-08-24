package org.example.project.domain.model

data class Email(val value: String) {
    init { require(value.contains("@")) { "Invalid email format" } }
}

data class Password(val value: String) {
    init { require(value.length >= 4) { "Password too short" } }
}

data class UserId(val value: String)

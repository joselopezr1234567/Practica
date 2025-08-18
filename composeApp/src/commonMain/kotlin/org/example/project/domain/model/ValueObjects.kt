package org.example.project.domain.model

@JvmInline
value class Email(val value: String) {
    init { require(value.contains("@")) { "Invalid email format" } }
}

@JvmInline
value class Password(val value: String) {
    init { require(value.length >= 4) { "Password too short" } }
}

@JvmInline
value class UserId(val value: String)

package com.example.firstapplication

class User {
    private var email: String
    private var password: String
    private var name: String
    private var lastName: String
    private var idPhoto: Int

    public constructor(email: String, password: String, name: String, lastName: String, idPhoto: Int) {
        this.email = email
        this.password = password
        this.name = name
        this.lastName = lastName
        this.idPhoto = idPhoto
    }

    public fun getEmail(): String {
        return email
    }

    public fun getPassword(): String {
        return password
    }

    public fun getName(): String {
        return name
    }

    public fun getLastName(): String {
        return lastName
    }

    public fun getIdPhoto(): Int {
        return idPhoto
    }
}
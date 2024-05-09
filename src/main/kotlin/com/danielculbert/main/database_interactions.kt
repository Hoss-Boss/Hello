package com.danielculbert.main

import java.sql.Connection
import java.sql.DriverManager
import java.security.MessageDigest;

fun getConnection(): Connection {
    val url = "jdbc:sqlite:wallets.db"
    return DriverManager.getConnection(url)
}


fun createTable(connection: Connection) {
    val sql = """
        CREATE TABLE IF NOT EXISTS Wallets (
            ID INTEGER PRIMARY KEY AUTOINCREMENT,
            Name TEXT NOT NULL,
            PrivateKey TEXT NOT NULL,
            PublicKey TEXT NOT NULL,
            Address TEXT NOT NULL,
            xAddress TEXT NOT NULL,
            isEncrypted INTEGER NOT NULL
        );
    """.trimIndent()

    connection.createStatement().use { stmt ->
        stmt.execute(sql)
    }
}

fun doingTheInsert() {
    val connection = getConnection()
    createTable(connection)
    connection.close()

}


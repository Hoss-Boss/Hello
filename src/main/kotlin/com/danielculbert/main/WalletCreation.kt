package com.danielculbert.main

import okhttp3.HttpUrl.Companion.toHttpUrl
import org.xrpl.xrpl4j.client.XrplClient
import org.xrpl.xrpl4j.codec.addresses.AddressCodec
import org.xrpl.xrpl4j.crypto.keys.KeyPair
import org.xrpl.xrpl4j.crypto.keys.Seed
import org.xrpl.xrpl4j.model.transactions.Address
import java.sql.Connection
import java.sql.SQLException


var created_address = "..."

fun create_wallet() {

    val db_connection = getConnection()

    val rippledUrl = "https://s2.ripple.com:51234/".toHttpUrl()
    println("Constructing an XrplClient connected to $rippledUrl")
    val xrplClient = XrplClient(rippledUrl)

    val randomKeyPair: KeyPair = Seed.ed25519Seed().deriveKeyPair()
    println("Generated KeyPair: $randomKeyPair")

    val classicAddress: Address = randomKeyPair.publicKey().deriveAddress()
    created_address = classicAddress.toString()

    println("Classic Address: " + classicAddress);

    val xAddress = AddressCodec.getInstance().classicAddressToXAddress(classicAddress, false)
    println("X Address: " + xAddress);

    val privateKey = randomKeyPair.privateKey().value()
    //val privateKeyHex = privateKey.bytes().joinToString("") { String.format("%02x", it) }
    println("Private Key: $privateKey")


    //insertWallet(db_connection, "Wallet",)

}



/**
 * Inserts a new wallet into the Wallets table.
 * @param connection The database connection.
 * @param name The name of the wallet.
 * @param privateKey The private key of the wallet.
 * @param publicKey The public key of the wallet.
 * @param address The address of the wallet.
 * @param xAddress The xAddress of the wallet.
 * @param isEncrypted Flag indicating whether the wallet is encrypted.
 */
fun insertWallet(
    connection: Connection,
    name: String,
    privateKey: String,
    publicKey: String,
    address: String,
    xAddress: String,
    isEncrypted: Boolean
) {
    val sql = """
        INSERT INTO Wallets (Name, PrivateKey, PublicKey, Address, xAddress, isEncrypted) VALUES (?, ?, ?, ?, ?, ?);
    """.trimIndent()

    try {
        connection.prepareStatement(sql).use { stmt ->
            stmt.setString(1, name)
            stmt.setString(2, privateKey)
            stmt.setString(3, publicKey)
            stmt.setString(4, address)
            stmt.setString(5, xAddress)
            stmt.setInt(6, if (isEncrypted) 1 else 0)
            stmt.executeUpdate()
        }
    } catch (e: SQLException) {
        println("Error inserting data into Wallets table: ${e.message}")
    }
}

package com.danielculbert.main

import okhttp3.HttpUrl.Companion.toHttpUrl
import org.xrpl.xrpl4j.client.XrplClient
import org.xrpl.xrpl4j.codec.addresses.AddressCodec
import org.xrpl.xrpl4j.crypto.keys.KeyPair
import org.xrpl.xrpl4j.crypto.keys.Seed
import org.xrpl.xrpl4j.model.transactions.Address


fun create_wallet() {


    val rippledUrl = "https://s2.ripple.com:51234/".toHttpUrl()
    println("Constructing an XrplClient connected to $rippledUrl")
    val xrplClient = XrplClient(rippledUrl)

    val randomKeyPair: KeyPair = Seed.ed25519Seed().deriveKeyPair()
    println("Generated KeyPair: $randomKeyPair")

    val classicAddress: Address = randomKeyPair.publicKey().deriveAddress()

    println("Classic Address: " + classicAddress);

    val xAddress = AddressCodec.getInstance().classicAddressToXAddress(classicAddress, true)
    println("X Address: " + xAddress);

}
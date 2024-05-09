package com.danielculbert.main

import okhttp3.HttpUrl.Companion.toHttpUrl
import org.xrpl.xrpl4j.client.XrplClient
import org.xrpl.xrpl4j.codec.addresses.AddressCodec
import org.xrpl.xrpl4j.crypto.keys.KeyPair
import org.xrpl.xrpl4j.crypto.keys.Seed
import org.xrpl.xrpl4j.model.transactions.Address
import java.awt.Color
import java.awt.Toolkit
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel


fun main() {

    setup_first_time_panel()
    initial_GUI_setup()
    setup_choose_between_import_or_creation_panel()
    setup_create_wallet_panel()
    //val myFile = create_existence_file("Existence.txt")
    //println("File created at: ${myFile.absolutePath}")
    redirect_to_new_panel(first_time_panel)
    check_if_db_file_exists()



}

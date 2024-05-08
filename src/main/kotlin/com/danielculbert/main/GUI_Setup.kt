package com.danielculbert.main

import java.awt.Color
import java.awt.Image
import java.awt.Toolkit
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import java.net.URL
import java.io.InputStream
import javax.swing.JButton


fun initial_GUI_setup() {

    val frame = JFrame("H.E.L.L.O.")
    frame.setSize(500, 500)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    val panel = JPanel()
    frame.contentPane.add(panel)

    val customColor = Color.decode("#CBF0B4")
    panel.background = customColor

    // Adding welcome image
    val welcomeImage = ImageIcon("Welcome1.png") // Ensure the path is correct
    val welcomeLabel = JLabel(welcomeImage)
    panel.add(welcomeLabel)

    val createWalletButton = JButton("Create/Import Wallet")
    val loadWalletButton = JButton("View Existing Wallets")
    panel.add(createWalletButton)
    panel.add(loadWalletButton)
    frame.add(panel)

    frame.isVisible = true

}//INITIAL_GUI_SETUP


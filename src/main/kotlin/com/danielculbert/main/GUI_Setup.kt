package com.danielculbert.main

import java.awt.Color
import java.awt.Container
import java.util.*
import javax.swing.*


private val panelStack = Stack<JPanel>()
//private lateinit var home_frame: JFrame
val home_frame = JFrame("H.E.L.L.O.")
val home_panel = JPanel()
val choose_panel = JPanel()
val create_wallet_panel = JPanel()

fun initial_GUI_setup() {

    //Sets up and displays the frame
    //sets up and sets the home panel on the frame

    home_frame.setSize(500, 500)
    home_frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    home_frame.contentPane.add(home_panel)

    val customColor = Color.decode("#CBF0B4")
    home_panel.background = customColor

    // Adding welcome image
    val welcomeImage = ImageIcon("Welcome1.png") // Ensure the path is correct
    val welcomeLabel = JLabel(welcomeImage)
    home_panel.add(welcomeLabel)

    val createWalletButton = JButton("Create/Import Wallet")
    val loadWalletButton = JButton("View Existing Wallets")
    home_panel.add(createWalletButton)
    home_panel.add(loadWalletButton)
    home_frame.add(home_panel)
    home_frame.setLocationRelativeTo(null)

    home_frame.isVisible = true
    //panelStack.push(home_panel)

    createWalletButton.addActionListener {
       redirect_to_new_panel(choose_panel)
    }






}//INITIAL_GUI_SETUP




fun setup_choose_between_import_or_creation_panel() {
    val customColor = Color.decode("#CBF0B4")
    choose_panel.background = customColor
    val backButton = JButton("Back")
    val createWalletButton = JButton("Create Wallet")
    val importWalletButton = JButton("Import Wallet")
    choose_panel.add(backButton)
    choose_panel.add(createWalletButton)
    choose_panel.add(importWalletButton)


    backButton.addActionListener {
        redirect_to_new_panel(home_panel)
    }

    createWalletButton.addActionListener {
        redirect_to_new_panel(create_wallet_panel)
    }

}//setup choose panel

fun setup_create_wallet_button() {
    val customColor = Color.decode("#CBF0B4")
    create_wallet_panel.background = customColor
    val yourAddressIsLabel = JLabel("Your address is: ")
    create_wallet_panel.add(yourAddressIsLabel)
    val addressLabel = JLabel("")
    create_wallet_panel.add(addressLabel)
    val generateButton = JButton("Generate Wallet")
    create_wallet_panel.add(generateButton)
    addressLabel.text = created_address

    generateButton.addActionListener {
        create_wallet()
        addressLabel.text = created_address
        home_frame.repaint()
    }


}



fun redirect_to_new_panel(redirect_to:JPanel) {

    home_frame.contentPane.removeAll()
    home_frame.contentPane.add(redirect_to)
    home_frame.revalidate()
    home_frame.repaint()

}


fun change_to_box() {
    val pane: Container = home_frame.getContentPane()
    pane.setLayout(BoxLayout(pane, BoxLayout.Y_AXIS))

}
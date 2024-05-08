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
import java.util.*
import javax.swing.JButton

private val panelStack = Stack<JPanel>()
//private lateinit var home_frame: JFrame
val home_frame = JFrame("H.E.L.L.O.")
val home_panel = JPanel()
val choose_panel = JPanel()

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




fun goto_screen_where_user_chooses_between_creation_or_importing() {

    //panelStack.push(choose_panel)



    val customColor = Color.decode("#CBF0B4")
    choose_panel.background = customColor


    val backButton = JButton("Back")


    home_frame.setContentPane(choose_panel)
    home_frame.revalidate()
    home_frame.repaint()

    home_frame.contentPane.add(choose_panel)



}//goto_choose



fun setup_choose_between_import_or_creation_panel() {
    val customColor = Color.decode("#CBF0B4")
    choose_panel.background = customColor
    val backButton = JButton("Back")
    choose_panel.add(backButton)

    backButton.addActionListener {
        redirect_to_new_panel(home_panel)
    }

}

fun redirect_to_new_panel(redirect_to:JPanel) {

    home_frame.contentPane.removeAll()
    home_frame.contentPane.add(redirect_to)
    home_frame.revalidate()
    home_frame.repaint()

}
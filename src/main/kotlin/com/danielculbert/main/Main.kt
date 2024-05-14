package com.danielculbert.main

fun main() {

    setup_first_time_panel()
    initial_GUI_setup()
    setup_choose_between_import_or_creation_panel()
    setup_create_wallet_panel()

    if (check_if_db_file_exists() == false) {
        create_file("wallets.db")
        redirect_to_new_panel(first_time_panel)
    }



}

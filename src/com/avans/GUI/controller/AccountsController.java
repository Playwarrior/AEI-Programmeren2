package com.avans.GUI.controller;

/*
    Created By Robin Egberts On 1/6/2019
    Copyrighted By OrbitMines ©2019
*/

import com.avans.NFS;

import com.avans.util.ScreenState;
import com.avans.handlers.user.Subscriber;
import com.avans.util.DataUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class AccountsController extends BasicController {

    public ListView<String> accounts;

    @FXML
    public void initialize() {
        super.initialize();

        this.addAccounts();
    }

    private void addAccounts() {
        List<Subscriber> s = DataUtil.getSubscribersByCount(1);

        List<String> items = new ArrayList<>();

        int i = 1;

        for (Subscriber subscriber : s) {
            items.add(String.format("%d. %s%s", i, subscriber.toString(), subscriber.getAdress() == null ? "" : (" " + subscriber.getAdress())));

            i++;
        }

        accounts.setItems(FXCollections.observableList(items));
    }
}

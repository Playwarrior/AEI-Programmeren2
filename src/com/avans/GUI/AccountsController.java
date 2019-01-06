package com.avans.GUI;

/*
    Created By Robin Egberts On 1/6/2019
    Copyrighted By OrbitMines ©2019
*/

import com.avans.NFS;

import com.avans.ScreenState;
import com.avans.handlers.user.Subscriber;
import com.avans.util.DataUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class AccountsController {

    public Button AWT;
    public Button ACWT;
    public Button MV;
    public Button LM;
    public Button A;
    public Button CM;

    public ListView<String> accounts;

    private ObservableList<String> items = FXCollections.emptyObservableList();

    private void changeState(ScreenState state) {
        NFS.setState(state);
    }

    @FXML
    public void initialize() {
        AWT.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changeState(ScreenState.WATCHTIME));
        ACWT.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changeState(ScreenState.S_WATCHTIME));
        MV.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changeState(ScreenState.M_WATCHED));
        LM.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changeState(ScreenState.LONGEST_MOVIE));
        A.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changeState(ScreenState.ACCOUNTS));
        CM.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changeState(ScreenState.COMPLETED_MOVIES));

        this.addAccounts();
    }

    private void addAccounts(){
        List<Subscriber> s = DataUtil.getSubscribersByCount(1);

        for(Subscriber subscriber : s){
            items.add(subscriber.toString());
        }

        accounts.setItems(items);
    }
}

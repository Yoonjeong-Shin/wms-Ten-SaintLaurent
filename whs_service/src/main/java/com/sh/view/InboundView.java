package com.sh.view;

import com.sh.controller.InboundController;
import com.sh.model.dto.InboundDto;

import java.util.Scanner;

public class InboundView {

    private InboundController inboundController = new InboundController();
    private Scanner sc = new Scanner(System.in);

    public void inboundMenu() {
        String choice = sc.next();
        switch (choice) {
//            case "1" : inboundController.approveInbound(); break;
//            case "2" : inboundController.checkInbound(); break;
//            case "3" : inboundController.showInboundResult(); break;
            case "0" : return;
            default:
                System.out.println("잘못 입력하셨습니다.");
        }
    }
}

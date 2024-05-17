package com.sh;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class whsApp {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8889, 3);
            System.out.println("=> 서버 실행중....");

            while (true) {
            try {
                Socket socket = serverSocket.accept();
                if (socket != null) {
                    // 새로운 클라이언트 연결을 처리하기 위해 별도의 스레드 생성 및 시작
                    new whsServerThread(socket).start();
                } else {
                    System.out.println("클라이언트 연결 실패");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    }
}

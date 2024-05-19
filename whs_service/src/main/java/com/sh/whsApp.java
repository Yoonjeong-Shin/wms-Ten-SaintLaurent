package com.sh;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class whsApp {
    public static final Object lock = new Object();
    public static void main(String[] args) {

        List<Socket> Sockets = new ArrayList<Socket>();
        try {
            ServerSocket serverSocket = new ServerSocket(8889, 3);
            System.out.println("=> 서버 실행중....");

            while (true) {
            try {
                Socket socket = serverSocket.accept();
                Sockets.add(socket);
                if (socket != null) {
                    // 새로운 클라이언트 연결을 처리하기 위해 별도의 스레드 생성 및 시작
                    synchronized (lock) {//한번에 한요청만 처리.
                        new whsServerThread(socket).start();
                    }
                } else {
                    System.out.println("클라이언트 연결 실패");
                }
            } catch (IOException e) {
                for(int i = 0;i<Sockets.size();i++) {
                    Socket socket = Sockets.get(i);
                    socket.close();
                }
                e.printStackTrace();
            }
        }
    }
        catch (IOException e) {
        e.printStackTrace();
    }
        
    }
}


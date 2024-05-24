package com.sh;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class sellApp {

    public static long whsPk = 0;
    public static String whsNM = "";
    private static final String USER_ID = "";
    private static final String PASSWORD = "";
    public static void main(String[] args) {

        List<Socket> Sockets = new ArrayList<Socket>();
        try {
            ServerSocket serverSocket = new ServerSocket(8891, 3);
            System.out.println("=> 셀러 서버 실행중....");
//            입고 클라이언트
//            InbClientTask clientTask = new InbClientTask( new Socket("localhost", 8889));
//            clientTask.run();
            //출고 클라이언트
            for(int i = 0; i < 3; ++i ) {
                OutbClientTask clientTask2 = new OutbClientTask(new Socket("localhost", 8889));
                clientTask2.run();
            }
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    Sockets.add(socket);
                    if (socket != null) {
                        // 새로운 클라이언트 연결을 처리하기 위해 별도의 스레드 생성 및 시작

                            new sellServerThread(socket).start();

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


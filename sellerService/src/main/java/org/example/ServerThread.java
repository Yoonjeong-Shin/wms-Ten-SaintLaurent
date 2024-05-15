package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ServerThread extends Thread {
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8889, 3);
            System.out.println("=> 서버 소켓 생성 완료!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("=> 클라이언트 연결 승인!");

                InputStream in0 = socket.getInputStream();
                OutputStream out0 = socket.getOutputStream();

                Scanner in = new Scanner(in0);
                PrintStream out = new PrintStream(out0);

                String str = in.nextLine(); // 클라이언트로부터 문자열을 한 줄 읽는다.
                out.println(str); // 클라이언트가 보낸 문자열을 그대로 돌려준다.
                System.out.println(str);

                in.close();
                in0.close();
                out.close();
                out0.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
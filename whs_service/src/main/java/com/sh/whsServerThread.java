package com.sh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

class whsServerThread extends Thread {
    private final Socket socket;
    public static int testInt;
    public static final Object lock = new Object();
    public whsServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        { // 동기화 블록 시작
            try {
                System.out.println("=> 클라이언트 연결 승인!");
                InputStream in0 = socket.getInputStream();
                OutputStream out0 = socket.getOutputStream();
                for(int i = 0; i < 5; ++i){
                    testInt++;
                }
                System.out.println(testInt);

                Scanner in = new Scanner(in0);
                PrintStream out = new PrintStream(out0);

                String str = in.nextLine(); // 클라이언트로부터 문자열을 한 줄 읽는다.
                out.println(str); // 클라이언트가 보낸 문자열을 그대로 돌려준다.
                System.out.println(str);

                in.close();
                in0.close();
                out.close();
                out0.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } // 동기화 블록 종료
    }
}

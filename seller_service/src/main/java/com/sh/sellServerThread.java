package com.sh;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class sellServerThread extends Thread {
    private final Socket socket;
    public static int testInt;
    public static final Object lock = new Object();
    public sellServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        synchronized (lock) { // 동기화 블록 시작
            try {
                System.out.println("=> 클라이언트 연결 승인!");
                InputStream in0 = socket.getInputStream();
                OutputStream out0 = socket.getOutputStream();
                for (int i = 0; i < 5; ++i) {
                    testInt++;
                }

                //처리로직 넣기.
                System.out.println(testInt);

                Scanner in = new Scanner(in0);
                PrintStream out = new PrintStream(out0);

                //응답 반환해주기.
                String line = "";
                String str = in.nextLine(); // 클라이언트로부터 문자열을 한 줄 읽는다.
                while (in.hasNextLine()) {
                    line += in.nextLine();
                    out.println("수신 완료");
                }
                String apiNm = line.split("#")[1];
                System.out.println(apiNm);


                if(apiNm.equals("true#")) {
                    System.out.println("response" + line);
                }
                else if(apiNm.equals("false#")) {
                    System.out.println("response" + line);
                }
//              System.out.println(line);
                // 클라이언트가 보낸 문자열을 그대로 돌려준다.
                in0.close();
                out.close();
                out0.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } // 동기화 블록 종료

}

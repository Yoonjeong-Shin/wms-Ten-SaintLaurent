package com.sh;

import com.sh.model.service.SupervisionService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class whsApp {
    public static final Object lock = new Object();
    public static long whsPk = 0;
    public static String whsNM = "";
    private static final String USER_ID = "";
    private static final String PASSWORD = "";
    public static void main(String[] args) {
        SupervisionService supervisionService = new SupervisionService();
        List<Socket> Sockets = new ArrayList<Socket>();
        try {
            ServerSocket serverSocket = new ServerSocket(8889, 3);
            while (true) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("로그인 하세요");

                // 사용자로부터 아이디 입력받기
                System.out.print("아이디: ");
                String inputId = scanner.nextLine();

                // 사용자로부터 비밀번호 입력받기
                System.out.print("비밀번호: ");
                String inputPassword = scanner.nextLine();

                // 입력된 아이디와 비밀번호가 미리 정의된 값과 일치하는지 확인
                if (supervisionService.getWhsPk(inputId) != null) {
                    System.out.println("로그인 성공!");
                    whsPk = supervisionService.getWhsPk(inputId);
                    whsNM = supervisionService.getWhsNm(whsPk);
                    break;
                } else {
                    System.out.println("로그인 실패. 아이디 또는 비밀번호가 올바르지 않습니다.");
                }

                scanner.close();
            }
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


package org.example;

public class whsServiceMain {
    public static void main(String[] args) throws Exception {
        System.out.println("whs 서버 실행 중...");
        // ServerSocket(port, backlog)
        // => port: 포트번호
        // => backlog: 대기열의 크기
        //리스팅 서버
        ServerThread serverThread = new ServerThread();
        serverThread.start();

    }
}
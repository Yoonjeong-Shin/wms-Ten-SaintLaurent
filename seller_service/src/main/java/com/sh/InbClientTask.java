package com.sh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sh.model.service.SelInboundService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class InbClientTask implements Runnable {
    private final Socket socket;

    public InbClientTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        SelInboundService selInboundService = new SelInboundService();
        try {
            System.out.println("=> 스레드 " + Thread.currentThread().getId() + ": 소켓 객체 생성 완료!");

            // 입출력 스트림 준비
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            // 스트림 객체에 입출력 보조 객체 연결
            Scanner scanner = new Scanner(in);
            PrintStream printer = new PrintStream(out);

            // 키보드 입력 및 서버 전송
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


                // JSON 문자열로 변환
            String message = "apiNm#selInbOrder#" + selInboundService.findAllInboundOrders();
            printer.println("\n"+message);
                printer.println("\n" + message);



            System.out.println("=> 스레드 " + Thread.currentThread().getId() + ": 서버에 메시지 전송 완료!");

            // 서버 응답 수신 및 출력
            String response = scanner.nextLine();
            System.out.println("=> 스레드 " + Thread.currentThread().getId() + ": 서버로부터 메시지 수신 완료!");
            System.out.println(response);

            // 스트림 및 소켓 닫기
            scanner.close();
            printer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
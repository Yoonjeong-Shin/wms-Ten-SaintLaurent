package com.sh;

import com.sh.model.service.FactoryService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sh.model.service.FactoryService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
class ClientTask implements Runnable {
    private final Socket socket;

    public ClientTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        FactoryService factoryService = new FactoryService();
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

            String message;
            try {
                // JSON 문자열로 변환
                String jsonString = objectMapper.writeValueAsString(factoryService.searchFactory());
                message = "apiNm#facOutbOrder#: " + jsonString;
                printer.println("\n" + message);
                System.out.println(jsonString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

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
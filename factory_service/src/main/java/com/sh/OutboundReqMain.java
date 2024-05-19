package com.sh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sh.model.dto.json.InbJsonDto;
import com.sh.model.service.FactoryService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
public class OutboundReqMain {
    static int id = 0;
    private static final int SERVER_PORT = 8889;
    public static final Object lock = new Object();
    public static void main(String[] args) throws Exception {
        // 키보드에서 데이터를 읽는 스캐너 객체 준비
// 1) 접속할 서버 주소 입력 받기
        FactoryService factoryService = new FactoryService();
        String serverAddress = "localhost";
        List<Socket> sockets= new ArrayList<Socket>();
        // 2) 각 클라이언트별 스레드 생성 및 실행
        for (int i = 0; i < 10; i++) { // 10개의 스레드 생성
            synchronized (lock){
            Thread thread = new Thread(() -> {
                try (Socket socket = new Socket(serverAddress, SERVER_PORT)) {
                    System.out.println("=> 스레드 " + Thread.currentThread().getId() + ": 소켓 객체 생성 완료!");
                    sockets.add(socket);
                    // 4) 입출력 스트림 준비
                    InputStream in = socket.getInputStream();
                    OutputStream out = socket.getOutputStream();

                    // 5) 스트림 객체에 입출력 보조 객체 연결
                    Scanner scanner = new Scanner(in);
                    PrintStream printer = new PrintStream(out);

                    // 6) 키보드 입력 및 서버 전송
                    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

                    String message;
                    try {
                        // JSON 문자열로 변환
                        String jsonString = objectMapper.writeValueAsString(factoryService.searchFactory());
                        message = "apiNm#facOutbOrder#: " + jsonString;
                        printer.println("\n"+message);
                        System.out.println(jsonString);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    System.out.println("=> 스레드 " + Thread.currentThread().getId() + ": 서버에 메시지 전송 완료!");

                    // 7) 서버 응답 수신 및 출력
                    String response = scanner.nextLine();
                    System.out.println("=> 스레드 " + Thread.currentThread().getId() + ": 서버로부터 메시지 수신 완료!");
                    System.out.println(response);
                    socket.close();
                    // 8) 스트림 및 소켓 닫기
                    scanner.close();
                    printer.close();
                } catch (IOException e) {
                    for (int u = 0; u < sockets.size(); ++u) {
                        try {
                            sockets.get(u).close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    e.printStackTrace();
                }
            });

            thread.start(); // 스레드 시작
            thread.join();
        }
        }

        for (int u = 0; u < sockets.size(); ++u) {
            try {
                sockets.get(u).close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    }
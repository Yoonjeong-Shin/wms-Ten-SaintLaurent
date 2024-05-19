package com.sh;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sh.model.dto.json.InbDetailJsonDto;
import com.sh.model.dto.json.InbJsonDto;
import com.sh.model.dto.json.SelInboundOrder;
import com.sh.model.dto.json.SelOutboundOrder;
import com.sh.view.InboundView;
import lombok.Synchronized;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

class whsServerThread extends Thread {
    private final Socket socket;
    public static int testInt;
    public static final Object lock = new Object();
    public InboundView inboundView = new InboundView();
    public whsServerThread(Socket socket) {
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
                    out.println(line);
                }
                String apiNm = line.split("#")[1];
                System.out.println(apiNm);
                if(apiNm.equals("facOutbOrder")) {
                    List<InbJsonDto> orders = parseFacOrders(line);//

                    System.out.println("facOutbOrder" + orders);
                }
                if(apiNm.equals("selOutbOrder")) {
                    List<SelOutboundOrder> orders = parseOutbOrders(line);//
                    System.out.println("selOutbOrder" + orders);
                }
                if(apiNm.equals("selInbOrder")) {
                    List<SelInboundOrder> orders = parseInbOrders(line);//
                    inboundView.inputInb(orders);
                    System.out.println("selInbOrder" + orders);
                }

//                System.out.println(line);

                // 클라이언트가 보낸 문자열을 그대로 돌려준다.


                in.close();
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
    public static List<SelOutboundOrder> parseOutbOrders(String input) {
        List<SelOutboundOrder> orders = new ArrayList<>();

        String[] parts = input.split("SelOutboundOrder\\{");
        for (int i = 1; i < parts.length; i++) {
            String orderString = parts[i].split("}")[0];
            SelOutboundOrder order = new SelOutboundOrder();

            order.setId(Long.parseLong(orderString.split("id=")[1].split(",")[0].trim()));
            order.setSellerName(orderString.split("sellerName='")[1].split("'")[0].trim());
            order.setCategory(orderString.split("category='")[1].split("'")[0].trim());
            order.setItemName(orderString.split("itemName='")[1].split("'")[0].trim());
            order.setVolume(Integer.parseInt(orderString.split("volume=")[1].split(",")[0].trim()));
            order.setExpirationDate(LocalDate.parse(orderString.split("expirationDate=")[1].split(",")[0].trim()));
            order.setPrice(Integer.parseInt(orderString.split("price=")[1].split(",")[0].trim()));
            order.setProductCount(Integer.parseInt(orderString.split("productCount=")[1].trim()));

            orders.add(order);
        }

        return orders;
    }
    public static List<SelInboundOrder> parseInbOrders(String input) {
        List<SelInboundOrder> orders = new ArrayList<>();

        String[] parts = input.split("SelInboundOrder\\{");
        for (int i = 1; i < parts.length; i++) {
            String orderString = parts[i].split("}")[0];
            SelInboundOrder order = new SelInboundOrder();

            order.setId(Long.parseLong(orderString.split("id=")[1].split(",")[0].trim()));
            order.setSellerName(orderString.split("sellerName='")[1].split("'")[0].trim());
            order.setCategory(orderString.split("category='")[1].split("'")[0].trim());
            order.setItemName(orderString.split("itemName='")[1].split("'")[0].trim());
            order.setVolume(Integer.parseInt(orderString.split("volume=")[1].split(",")[0].trim()));
            order.setExpirationDate(LocalDate.parse(orderString.split("expirationDate=")[1].split(",")[0].trim()));
            order.setPrice(Integer.parseInt(orderString.split("price=")[1].split(",")[0].trim()));
            order.setProductCount(Integer.parseInt(orderString.split("productCount=")[1].trim()));
            orders.add(order);
        }
        return orders;
    }
    public static List<InbJsonDto> parseFacOrders(String input) {
        List<InbJsonDto> orders = new ArrayList<>();

        String jsonString = input.split("#")[2];
        jsonString = jsonString.split(": ")[1];

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {
            // JSON 문자열을 List<InbJsonDto> 객체로 변환
            List<InbJsonDto> inbJsonDtoList = objectMapper.readValue(jsonString, new TypeReference<List<InbJsonDto>>() {});
            return inbJsonDtoList;
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }
}

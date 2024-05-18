package com.sh.model.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sh.model.dto.json.InbDetailJsonDto;
import com.sh.model.dto.json.InbJsonDto;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InboundRepository {
    private List<InbJsonDto> inbList;
    private List<InbDetailJsonDto> itemsDetailList;
    private static final String JSON_FILE_PATH = "whs_service/src/main/resources/json/inbItemDto.json";

    public InboundRepository() {
        inbList = readInbFromJson();

        if (inbList == null) {
            inbList = new ArrayList<>();
        }
    }

    private List<InbJsonDto> readInbFromJson() {
        try (BufferedReader reader = new BufferedReader(new FileReader(JSON_FILE_PATH))) {
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            // JSON 문자열을 Inbound 리스트로 변환
            return parseJsonToInbList(jsonString.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<InbJsonDto> parseJsonToInbList(String jsonData) {
        inbList = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray inbJsonArray = gson.fromJson(jsonData, JsonArray.class);

        for (int i = 0; i < inbJsonArray.size(); i++) {
            JsonObject inbJsonObject = inbJsonArray.get(i).getAsJsonObject();
            String sellerName = inbJsonObject.get("sellerName").getAsString();
            String sellerLoc = inbJsonObject.get("sellerLoc").getAsString();
            String factoryName = inbJsonObject.get("factoryName").getAsString();
            String factoryLoc = inbJsonObject.get("factoryLoc").getAsString();
            String cat = inbJsonObject.get("cat").getAsString();
            String itemName = inbJsonObject.get("itemName").getAsString();
            int vol = inbJsonObject.get("vol").getAsInt();
            int price = inbJsonObject.get("price").getAsInt();
            int itemCount = inbJsonObject.get("itemCount").getAsInt();
            LocalDate expirationDate = LocalDate.parse(inbJsonObject.get("expirationDate").getAsString());
            List<InbDetailJsonDto> itemsDetail = parseItemsDetail(inbJsonObject.getAsJsonArray("itemsDetail"));
            InbJsonDto inbJsonDto = new InbJsonDto(sellerName, sellerLoc, factoryName, factoryLoc, cat, itemName, vol, price, itemCount, expirationDate, itemsDetail);
            inbList.add(inbJsonDto);
        }

        return inbList;
    }

    private List<InbDetailJsonDto> parseItemsDetail(JsonArray itemsDetailArray) {
        itemsDetailList = new ArrayList<>();
        for (int i = 0; i < itemsDetailArray.size(); i++) {
            JsonObject itemsDetailObject = itemsDetailArray.get(i).getAsJsonObject();
            String itemSerialNum = itemsDetailObject.get("itemSerialNum").getAsString();
            int state = itemsDetailObject.get("state").getAsInt();
            InbDetailJsonDto itemsDetail = new InbDetailJsonDto(itemSerialNum, state);
            itemsDetailList.add(itemsDetail);
        }
        return itemsDetailList;
    }

    // inbItemDto json 데이터 모두 읽어오기
    public List<InbJsonDto> readInb() {
        return new ArrayList<>(inbList);
    }

    // inbItemDto json 데이터 중 itemsDetailList 읽어오기
    public List<InbDetailJsonDto> readInbDetail() {
        return new ArrayList<>(itemsDetailList);
    }
}

package com.example.petproject.service;

import com.example.petproject.bean.Shelter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShelterService {

    private static List<Shelter> shelterList = new ArrayList<>();

    static {
        readShelterFile();
    }

    private static void readShelterFile() {
        String content = null;
        try {
            File file = ResourceUtils.getFile("classpath:static/shelter.json");
            content = new String(Files.readAllBytes(file.toPath()));
            JSONArray jsonArray = new JSONArray(content);
            jsonArray.forEach(o -> {
                JSONObject jsonObject = (JSONObject)o;
                Shelter shelter = new Shelter(jsonObject.getInt("areaId")
                        , jsonObject.getInt("shelterId"), jsonObject.getString("shelterName"),
                        jsonObject.getString("position"));
                shelterList.add(shelter);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Shelter> getShelterList(String position) {
        return shelterList.stream()
                .filter(shelter -> position.equals(shelter.getPosition()))
                .collect(Collectors.toList());
    }
}

package com.example.petproject.service;

import com.example.petproject.util.AdoptMapper;
import com.example.petproject.vo.AdoptVo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdoptService {

    private static AdoptMapper adoptMapper = new AdoptMapper();

    private static final String DATA_URL = "https://data.coa.gov.tw/Service/OpenData/TransService.aspx?UnitId=QcbUEzN6E6DL";
    private static final List<AdoptVo> adoptList = new ArrayList<>();
    static {
        loadAdoptData();
    }

    /**
     * 由API取得領養資訊
     */
    private static void loadAdoptData() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> adoptData
                = restTemplate.getForEntity(DATA_URL, String.class);
        String body = adoptData.getBody();
        JSONArray jsonArray = new JSONArray(body);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            adoptList.add(adoptMapper.AdoptJsonToVo(jsonObject));
        }
    }

    public List<AdoptVo> getAdoptList(int currentIndex) {
        return adoptList.stream()
                .skip(currentIndex)
                .limit(10).collect(Collectors.toList());
    }
}

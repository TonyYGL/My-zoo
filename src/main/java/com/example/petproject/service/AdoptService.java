package com.example.petproject.service;

import com.example.petproject.util.AdoptMapper;
import com.example.petproject.util.RedisUtil;
import com.example.petproject.vo.AdoptVo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdoptService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdoptService.class);

    private static final AdoptMapper ADOPT_MAPPER = new AdoptMapper();

    private static final String DATA_URL = "https://data.coa.gov.tw/Service/OpenData/TransService.aspx?UnitId=QcbUEzN6E6DL";
    private static final String ADOPT_KEY = "ADOPT_ZSET";

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 由API取得領養資訊
     */
    @PostConstruct
    public void loadAPIData() {
//        new Thread(() -> {
//            try {
//                RestTemplate restTemplate = new RestTemplate();
//                ResponseEntity<String> adoptData
//                        = restTemplate.getForEntity(DATA_URL, String.class);
//                String body = adoptData.getBody();
//                JSONArray jsonArray = new JSONArray(body);
//                for (int index = 0; index < jsonArray.length(); index++) {
//                    JSONObject jsonObject = jsonArray.getJSONObject(index);
//                    redisUtil.addZSet(ADOPT_KEY, ADOPT_MAPPER.adoptJsonToVo(jsonObject), jsonObject.getLong("animal_id"));
//                }
//            } catch (Exception exception) {
//                exception.printStackTrace();
//                LOGGER.error(exception.getMessage());
//            }
//        }).start();
    }

    public List<AdoptVo> getAdoptList(int currentIndex) {
        Set<Object> adoptSet = redisUtil.rangeZSet(ADOPT_KEY, currentIndex, currentIndex + 19);
        return adoptSet.stream()
                .map(o -> (AdoptVo) o)
                .filter(adoptVo -> !"".equals(adoptVo.getAlbumFile()))
                .collect(Collectors.toList());
    }
}

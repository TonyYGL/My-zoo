package com.example.petproject.util;

import com.example.petproject.vo.AdoptVo;
import org.json.JSONObject;

public class AdoptMapper {

    public AdoptVo AdoptJsonToVo(JSONObject jsonObject) {
        AdoptVo adoptVo = new AdoptVo();
        adoptVo.setId(String.valueOf(jsonObject.getInt("animal_id")));
        adoptVo.setAge(transAge(jsonObject.getString("animal_age")));
        adoptVo.setAlbumFile(jsonObject.getString("album_file"));
        adoptVo.setBacterin(transBacterin(jsonObject.getString("animal_bacterin")));
        adoptVo.setBodyType(transBodType(jsonObject.getString("animal_bodytype")));
        adoptVo.setColor(jsonObject.getString("animal_colour"));
        adoptVo.setPlace(jsonObject.getString("animal_place"));
        adoptVo.setFoundPlace(jsonObject.getString("animal_foundplace"));
        adoptVo.setKind(jsonObject.getString("animal_kind"));
        adoptVo.setRemark(jsonObject.getString("animal_remark"));
        adoptVo.setSex(transSex(jsonObject.getString("animal_sex")));
        adoptVo.setShelterPhone(jsonObject.getString("shelter_tel"));
        adoptVo.setShelterAddress(jsonObject.getString("shelter_address"));
        adoptVo.setStatus(transStatus(jsonObject.getString("animal_status")));
        adoptVo.setSterilization(transSterilization(jsonObject.getString("animal_sterilization")));
        adoptVo.setOpenDate(jsonObject.getString("animal_opendate"));
        return adoptVo;
    }

    private String transAge(String age) {
        String result = "";
        if ("CHILD".equals(age)) {
            result = "幼年";
        } else if ("ADULT".equals(age)) {
            result = "成年";
        }
        return result;
    }

    private String transBacterin(String bacterin) {
        String result = "";
        if ("T".equals(bacterin)) {
            result = "是";
        } else if ("F".equals(bacterin)) {
            result = "否";
        } else if ("N".equals(bacterin)) {
            result = "未輸入";
        }
        return result;
    }

    private String transBodType(String bodyType) {
        String result = "";
        if ("SMALL".equals(bodyType)) {
            result = "小型";
        } else if ("MEDIUM".equals(bodyType)) {
            result = "中型";
        } else if ("BIG".equals(bodyType)) {
            result = "大型";
        }
        return result;
    }

    private String transSex(String sex) {
        String result = "";
        if ("M".equals(sex)) {
            result = "公";
        } else if ("F".equals(sex)) {
            result = "母";
        } else if ("N".equals(sex)) {
            result = "未輸入";
        }
        return result;
    }

    private String transStatus(String status) {
        String result = "";
        if ("NONE".equals(status)) {
            result = "未公告";
        } else if ("OPEN".equals(status)) {
            result = "開放認養";
        } else if ("ADOPTED".equals(status)) {
            result = "已認養";
        } else if ("OTHER".equals(status)) {
            result = "其他";
        } else if ("DEAD".equals(status)) {
            result = "死亡";
        }
        return result;
    }

    private String transSterilization(String sterilization) {
        String result = "";
        if ("T".equals(sterilization)) {
            result = "是";
        } else if ("F".equals(sterilization)) {
            result = "否";
        } else if ("N".equals(sterilization)) {
            result = "未輸入";
        }
        return result;
    }
}

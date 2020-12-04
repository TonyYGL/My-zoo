package com.example.petproject.bean;

import lombok.Data;

import java.util.Arrays;

@Data
public class Shelter {

    private String area;
    private int shelterId;
    private String name;
    private String position;

    public Shelter(int areaId, int shelterId, String name, String position) {
        this.area = Area.getArea(areaId).name;
        this.shelterId = shelterId;
        this.name = name;
        this.position = position;
    }

    enum Area {
        UNKNOWN(1, "未知"), TAIPEI(2, "臺北市"), NEW_TAIPEI(3, "新北市"), KEELUNG(4, "基隆市")
        , YILAN(5, "宜蘭縣"), TAOYUAN(6, "桃園縣"), HSINCHU_COUNTRY(7, "新竹縣")
        , HSINCHU_CITY(8, "新竹市"), MAIOLI(9, "苗栗縣"), TAICHUNG(10, "臺中市")
        , CHANGHUA(11, "彰化縣"), NANTOU(12, "南投縣"), YUNLIN(13, "雲林縣")
        , CHIAYI_COUNTRY(14, "嘉義縣"), CHIAYI_CITY(15, "嘉義市"), TAINAN(16, "臺南市")
        , KAOHSIUNG(17, "高雄市"), PINGTUNG(18, "屏東縣"), HUALIEN(19, "花蓮縣")
        , TAITUNG(20, "臺東縣"), PENGHU(21, "澎湖縣"), KINMEN(22, "金門縣")
        , LIENCHIANG(23, "連江縣");

        private int areaId;
        private String name;

        Area(int areaId, String name) {
            this.areaId = areaId;
            this.name = name;
        }

        public static Area getArea(int areaId) {
            return Arrays.stream(Area.values())
                    .filter(area -> area.areaId == areaId)
                    .findFirst()
                    .orElse(UNKNOWN);
        }
    }
}

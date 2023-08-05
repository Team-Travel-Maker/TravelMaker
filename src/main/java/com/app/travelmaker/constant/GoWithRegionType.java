package com.app.travelmaker.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GoWithRegionType {
    /**
     * SEOUL : 서울
     * GYEONGGI_DO : 경기도
     * INCHEON : 인천
     * GANG_WONDO : 강원도
     * SEJONG : 세종
     * CHUNGCHEONGNAM_DO : 충청남도
     * CHUNGCHEONG_BUKDO : 충청북도
     * DAEJEON : 대전
     * JEOLLANAM_DO : 전라남도
     * JEOLLABUK_DO : 전라북도
     * GWANGJU : 광주
     * GYEONGSANGNAM_DO : 경상남도
     * GYEONGSANGNBUK_DO : 경상북도
     * DAEGU : 대구
     * ULSAN : 울산
     * BUSAN : 부산
     * JEJU_ISLAND : 제주도
     * */
    SEOUL("SEOUL", "서울"),
    GYEONGGI_DO("GYEONGGI_DO", "경기도"),
    INCHEON("INCHEON", "인천"),
    GANG_WONDO("GANG_WONDO", "강원도"),
    SEJONG("SEJONG", "세종"),
    CHUNGCHEONGNAM_DO("CHUNGCHEONGNAM_DO", "충청남도"),
    CHUNGCHEONG_BUKDO("CHUNGCHEONG_BUKDO", "충청북도"),
    DAEJEON("DAEJEON", "대전"),
    JEOLLANAM_DO("JEOLLANAM_DO", "전라남도"),
    JEOLLABUK_DO("JEOLLABUK_DO", "전라북도"),
    GWANGJU("GWANGJU", "광주"),
    GYEONGSANGNAM_DO("GYEONGSANGNAM_DO", "경상남도"),
    GYEONGSANGNBUK_DO("GYEONGSANGNBUK_DO", "경상북도"),
    DAEGU("DAEGU", "대구"),
    ULSAN("ULSAN", "울산"),
    BUSAN("BUSAN", "부산"),
    JEJU_ISLAND("JEJU_ISLAND", "제주도");

    private final String code;
    private final String name;


}

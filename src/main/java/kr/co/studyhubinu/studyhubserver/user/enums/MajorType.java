package kr.co.studyhubinu.studyhubserver.user.enums;

import kr.co.studyhubinu.studyhubserver.common.enums.EnumModel;

public enum MajorType implements EnumModel {
    NONE("전공없음"),
    PERFORMING_ART("공연예술과"),
    IBE("IBE전공"),
    CIVIL_AND_ENVIRONMENTAL_ENGINEERING("건설환경공학"),
    ARCHITECTURAL_ENGINEERING("건축공학"),
    BUSINESS_ADMINISTRATION("경영학부"),
    ECONOMICS("경제학과"),
    KOREAN_LANGUAGE_EDUCATION("국어교육과"),
    KOREAN_LANGUAGE_LITERATURE("국어국문학과"),
    MECHANICAL_ENGINEERING("기계공학과"),
    BUSINESS_DATA_SCIENCE("데이터과학과"),
    ARCHITECTURE_AND_URBAN_DESIGN("도시건축학"),
    URBAN_ENGINEERING("도시공학과"),
    URBAN_POLICY_AND_ADMINISTRATION("도시행정학과"),
    GERMAN_LANGUAGE_LITERATURE("독어독문학과"),
    SCHOOL_OF_NORTHEAST_ASIAN("동북아통상전공"),
    DESIGN("디자인학부"),
    INTERNATIONAL_TRADE("무역학부"),
    LIBRARY_AND_INFORMATION("문헌정보학과"),
    PHYSICS("물리학과"),
    MASS_COMMUNICATION("미디어커뮤니케이션학과"),
    MECHANICAL_ENGINEERING_AND_ROBOTICS("바이오-로봇 시스템 공학과"),
    LAW("법학부"),
    FRENCH_LANGUAGE_LITERATURE("불어불문학과"),
    SOCIAL_WELFARE("사회복지학과"),
    INDUSTRIAL_AND_MANAGEMENT_ENGINEERING("산업경영공학과"),
    NANO_BIO_ENGINEERING("생명공학부(나노바이오공학전공)"),
    BIO_ENGINEERING("생명공학부(생명공학전공)"),
    MOLECULAR_MEDICAL_SCIENCE("생명과학부(분자의생명전공)"),
    BIOLOGICAL_SCIENCE("생명과학부(생명과학전공)"),
    WESTERN_PAINTING("서양화전공"),
    TAX_ACCOUNTING("세무회계학과"),
    CONSUMER_SCIENCE("소비자학과"),
    MATHEMATICS("수학과"),
    MATHEMATICS_EDUCATION("수학교육과"),
    SMART_LOGISTICS_ENGINEERING("스마트물류공학전공"),
    SPORT_SCIENCE("스포츠과학부"),
    MATERIALS_SCIENCE_ENGINEERING("신소재공학과"),
    SAFETY_ENGINEERING("안전공학과"),
    ENERGY_CHEMICAL_ENGINEERING("에너지화학공학"),
    HISTORY_EDUCATION("역사교육과"),
    ENGLISH_LANGUAGE_EDUCATION("영어교육학과"),
    ENGLISH_LANGUAGE_LITERATURE("영어영문학과"),
    HEALTH_KINESIOLOGY("운동건강학부"),
    EARLY_CHILDHOOD_EDUCATION("유아교육과"),
    ETHICS_EDUCATION("윤리교육과"),
    JAPANESE_LANGUAGE_LITERATURE("일본지역문화학과"),
    JAPANESE_LANGUAGE_EDUCATION("일어교육과"),
    EMBEDDED_SYSTEM_ENGINEERING("임베디드시스템공학과"),
    ELECTRICAL_ENGINEERING("전기공학과"),
    ELECTRONICS_ENGINEERING("전자공학과"),
    INFORMATION_TELECOMMUNICATION_ENGINEERING("정보통신학과"),
    POLITICAL_INTERNATIONAL("정치외교학과"),
    CHINESE_LANGUAGE_LITERATURE("중어중문학과"),
    CREATIVE_HUMAN_RESOURCE_DEVELOPMENT("창의인재개발학과"),
    PHYSICAL_EDUCATION("체육교육과"),
    COMPUTER_SCIENCE_ENGINEERING("컴퓨터공학부"),
    TECHNOLOGY_MANAGEMENT("테크노경영학과"),
    FASHION_INDUSTRY("패션산업학과"),
    KOREAN_PAINTING("한국화전공"),
    MARINE_SCIENCE("해양학과"),
    PUBLIC_ADMINISTRATION("행정학과"),
    CHEMISTRY("화학과"),
    ENVIRONMENTAL_ENGINEERING("환경공학");

    private String value;

    MajorType(String value) {
        this.value = value;
    }

    /**
     * enum key 리턴
     * @return 'Male' or 'FEMALE'
     */
    @Override
    public String getKey() {
        return name();
    }

    /**
     * enum value 리턴
     * @return '남자' or '여자'
     */
    @Override
    public String getValue() {
        return value;
    }

    public static MajorType of(String major) {
        for (MajorType majorType : MajorType.values()) {
            if (majorType.getValue().equals(major)) {
                return majorType;
            }
        }
        return NONE;
    }
}
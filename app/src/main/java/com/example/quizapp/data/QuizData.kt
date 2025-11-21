package com.example.quizapp.data

import com.example.quizapp.data.model.Quiz

val AllQuizzes = listOf(
    // ---------------- 과학 상식 ----------------
    Quiz(
        subject = "과학 상식",
        question = "지구의 대기에서 가장 많은 비중을 차지하는 기체는 무엇인가요?",
        options = listOf("산소", "질소", "이산화탄소", "수소"),
        answerIndex = 1
    ),
    Quiz(
        subject = "과학 상식",
        question = "태양계에서 가장 큰 행성은 무엇인가요?",
        options = listOf("화성", "토성", "목성", "천왕성"),
        answerIndex = 2
    ),
    Quiz(
        subject = "과학 상식",
        question = "물질의 상태가 고체에서 액체를 거치지 않고 바로 기체로 변하는 현상은 무엇인가요?",
        options = listOf("융해", "기화", "승화", "응결"),
        answerIndex = 2
    ),
    Quiz(
        subject = "과학 상식",
        question = "빛의 속도는 1초에 대략 몇 km인가요?",
        options = listOf("3만 km", "30만 km", "300만 km", "3천만 km"),
        answerIndex = 1
    ),
    Quiz(
        subject = "과학 상식",
        question = "원자의 핵을 구성하는 입자는 무엇인가요?",
        options = listOf("전자와 중성자", "양성자와 전자", "양성자와 중성자", "중성자와 핵자"),
        answerIndex = 2
    ),
    Quiz(
        subject = "과학 상식",
        question = "지구의 가장 안쪽에 있는 핵은 주로 어떤 물질로 이루어져 있나요?",
        options = listOf("규소", "산소", "철과 니켈", "마그네슘"),
        answerIndex = 2
    ),
    Quiz(
        subject = "과학 상식",
        question = "DNA의 이중 나선 구조를 발견한 과학자는 누구인가요?",
        options = listOf("아이슈타인", "뉴턴", "왓슨과 크릭", "멘델"),
        answerIndex = 2
    ),
    Quiz(
        subject = "과학 상식",
        question = "산성비를 유발하는 주요 원인 물질은 무엇인가요?",
        options = listOf("수증기", "메탄", "황산화물과 질소산화물", "오존"),
        answerIndex = 2
    ),
    Quiz(
        subject = "과학 상식",
        question = "전기를 잘 통하게 하는 물질을 무엇이라고 부르나요?",
        options = listOf("절연체", "반도체", "도체", "유전체"),
        answerIndex = 2
    ),
    Quiz(
        subject = "과학 상식",
        question = "인간의 몸에서 가장 큰 장기는 무엇인가요?",
        options = listOf("심장", "뇌", "간", "폐"),
        answerIndex = 2
    ),



    // ---------------- 스포츠 상식 ----------------
    Quiz(
        subject = "스포츠 상식",
        question = "축구 경기에서 한 팀의 선수는 골키퍼를 포함하여 총 몇 명인가요?",
        options = listOf("9명", "10명", "11명", "12명"),
        answerIndex = 2
    ),
    Quiz(
        subject = "스포츠 상식",
        question = "육상 경기에서 마라톤의 공식 거리는 약 몇 km인가요?",
        options = listOf("40.195 km", "42.195 km", "45.195 km", "48.195 km"),
        answerIndex = 1
    ),
    Quiz(
        subject = "스포츠 상식",
        question = "농구 경기에서 3점 라인 밖에서 슛을 성공했을 때 얻는 점수는 몇 점인가요?",
        options = listOf("1점", "2점", "3점", "4점"),
        answerIndex = 2
    ),
    Quiz(
        subject = "스포츠 상식",
        question = "올림픽 오륜기에서 5개의 고리가 상징하는 것은 무엇인가요?",
        options = listOf("5개 종목", "5대양", "5대륙", "5가지 정신"),
        answerIndex = 2
    ),
    Quiz(
        subject = "스포츠 상식",
        question = "테니스 경기에서 득점 순서는 '0점'에서 시작하여 다음 순서로 올라갑니다. 다음 순서는 무엇인가요?",
        options = listOf("15, 30, 40", "10, 20, 30", "15, 30, 50", "10, 30, 40"),
        answerIndex = 0
    ),
    Quiz(
        subject = "스포츠 상식",
        question = "야구에서 한 이닝에 아웃이 몇 개 발생하면 공수가 교대되나요?",
        options = listOf("1개", "2개", "3개", "4개"),
        answerIndex = 2
    ),
    Quiz(
        subject = "스포츠 상식",
        question = "김연아가 금메달을 획득했던 올림픽 종목은 무엇인가요?",
        options = listOf("쇼트트랙", "스피드 스케이팅", "피겨 스케이팅", "스키 점프"),
        answerIndex = 2
    ),
    Quiz(
        subject = "스포츠 상식",
        question = "골프에서 규정 타수보다 1타 적게 공을 홀에 넣는 것을 무엇이라고 하나요?",
        options = listOf("보기", "파", "버디", "이글"),
        answerIndex = 2
    ),
    Quiz(
        subject = "스포츠 상식",
        question = "배드민턴에서 셔틀콕을 치는 라켓이 닿는 부분을 무엇이라고 부르나요?",
        options = listOf("그립", "샤프트", "프레임", "헤드"),
        answerIndex = 3
    ),
    Quiz(
        subject = "스포츠 상식",
        question = "수영 경기에서 가장 빠른 영법은 무엇인가요?",
        options = listOf("배영", "평영", "접영", "자유형"),
        answerIndex = 3
    ),



// ---------------- 수도 상식 ----------------
    Quiz(
        subject = "수도 상식",
        question = "대한민국의 수도는 무엇인가요?",
        options = listOf("부산", "서울", "인천", "대전"),
        answerIndex = 1
    ),
    Quiz(
        subject = "수도 상식",
        question = "일본의 수도는 무엇인가요?",
        options = listOf("오사카", "삿포로", "도쿄", "교토"),
        answerIndex = 2
    ),
    Quiz(
        subject = "수도 상식",
        question = "중국의 수도는 무엇인가요?",
        options = listOf("상하이", "베이징", "광저우", "텐진"),
        answerIndex = 1
    ),
    Quiz(
        subject = "수도 상식",
        question = "프랑스의 수도는 무엇인가요?",
        options = listOf("니스", "파리", "리옹", "마르세유"),
        answerIndex = 1
    ),
    Quiz(
        subject = "수도 상식",
        question = "이탈리아의 수도는 무엇인가요?",
        options = listOf("밀라노", "베니스", "로마", "나폴리"),
        answerIndex = 2
    ),
    Quiz(
        subject = "수도 상식",
        question = "영국의 수도는 무엇인가요?",
        options = listOf("런던", "리버풀", "맨체스터", "버밍엄"),
        answerIndex = 0
    ),
    Quiz(
        subject = "수도 상식",
        question = "캐나다의 수도는 무엇인가요?",
        options = listOf("토론토", "오타와", "몬트리올", "밴쿠버"),
        answerIndex = 1
    ),
    Quiz(
        subject = "수도 상식",
        question = "호주의 수도는 무엇인가요?",
        options = listOf("시드니", "멜버른", "캔버라", "브리즈번"),
        answerIndex = 2
    ),
    Quiz(
        subject = "수도 상식",
        question = "독일의 수도는 무엇인가요?",
        options = listOf("베를린", "프랑크푸르트", "뮌헨", "함부르크"),
        answerIndex = 0
    ),
    Quiz(
        subject = "수도 상식",
        question = "러시아의 수도는 무엇인가요?",
        options = listOf("상트페테르부르크", "모스크바", "카잔", "블라디보스토크"),
        answerIndex = 1
    ),




    // ---------------- IT 상식 ----------------
    Quiz(
        subject = "IT 상식",
        question = "HTTP의 기본 포트 번호는 무엇인가요?",
        options = listOf("22", "80", "8080", "443"),
        answerIndex = 1
    ),
    Quiz(
        subject = "IT 상식",
        question = "운영체제(OS)의 역할이 아닌 것은 무엇인가요?",
        options = listOf("프로세스 관리", "메모리 관리", "UI 디자인", "파일 시스템 관리"),
        answerIndex = 2
    ),
    Quiz(
        subject = "IT 상식",
        question = "컴퓨터가 이해하는 정보의 최소 단위는 무엇인가요?",
        options = listOf("바이트", "비트", "워드", "니블"),
        answerIndex = 1
    ),
    Quiz(
        subject = "IT 상식",
        question = "다음 중 오픈소스 운영체제는 무엇인가요?",
        options = listOf("Windows", "macOS", "Linux", "iOS"),
        answerIndex = 2
    ),
    Quiz(
        subject = "IT 상식",
        question = "HTML에서 웹페이지의 구조를 담당하는 태그 언어는 무엇인가요?",
        options = listOf("CSS", "Python", "HTML", "JavaScript"),
        answerIndex = 2
    ),
    Quiz(
        subject = "IT 상식",
        question = "파이썬에서 리스트의 길이를 구하는 함수는 무엇인가요?",
        options = listOf("size()", "length()", "count()", "len()"),
        answerIndex = 3
    ),
    Quiz(
        subject = "IT 상식",
        question = "데이터베이스에서 데이터를 조회할 때 사용하는 명령어는?",
        options = listOf("INSERT", "UPDATE", "SELECT", "DELETE"),
        answerIndex = 2
    ),
    Quiz(
        subject = "IT 상식",
        question = "SNS 암호화 방식 중 계정 보안에 가장 널리 사용되는 방식은?",
        options = listOf("MD5", "SHA-256", "Base64", "ROT13"),
        answerIndex = 1
    ),
    Quiz(
        subject = "IT 상식",
        question = "다음 중 컴퓨터 네트워크 장비가 아닌 것은 무엇인가요?",
        options = listOf("라우터", "스위치", "허브", "프린터"),
        answerIndex = 3
    ),
    Quiz(
        subject = "IT 상식",
        question = "컴퓨터 부팅 시 가장 먼저 실행되는 프로그램은?",
        options = listOf("BIOS / UEFI", "OS", "드라이버", "브라우저"),
        answerIndex = 0
    ),
    )
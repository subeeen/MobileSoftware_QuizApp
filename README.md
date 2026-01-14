# 📱 Quiz App

Jetpack Compose 기반으로 개발한 안드로이드 퀴즈 애플리케이션입니다.  
다양한 카테고리의 퀴즈를 풀고, 점수 랭킹과 오답 노트를 통해 학습할 수 있도록 설계했습니다.

---

## 🧩 주요 기능

### 1️⃣ 메인 화면
<img width="180" alt="image" src="https://github.com/user-attachments/assets/318c19ed-4405-4467-aebf-25219f4e61d3" />
<br><br>

- **카테고리 선택**
  - 과학 / 스포츠 / 수도 / IT  
  - 버튼 형태로 제공하여 직관적인 퀴즈 선택 가능
- **하단 네비게이션**
  - 메인 / 랭킹 / 오답노트 탭 제공
  - 주요 기능 간 빠른 이동 지원
    

---

### 2️⃣ 퀴즈 풀이 화면
<img width="400" alt="image" src="https://github.com/user-attachments/assets/9a36db51-fbcc-4b13-98f6-2685dfb34a77" />
<br><br>

- **실시간 진행 상황 표시**
  - 상단에 현재 문제 번호 표시
- **무작위 문제 출제**
  - 총 20문제 중 랜덤으로 10문제 출제
  - 재시도 시에도 새로운 문제 경험 가능
- **즉각적인 피드백**
  - 정답: 초록색 표시 + 정답 사운드  
  - 오답: 빨간색 표시 + 오답 사운드
- **결과 요약 화면**
  - 맞힌 개수 및 최종 점수 표시
  - 메인 / 랭킹 / 오답노트 화면으로 이동 가능

---

### 3️⃣ 랭킹 화면
<img width="180" alt="image" src="https://github.com/user-attachments/assets/13736ba4-8efa-4785-9d98-a85c215a8e23" />
<br><br>

- **점수 기록 및 순위 표시**
  - 최고 점수를 저장하고 순위 및 날짜 함께 표시
- **랭킹 초기화 기능**
  - 테스트 및 관리를 위한 데이터 초기화 버튼 제공

---

### 4️⃣ 오답 노트 화면
<img width="180" alt="image" src="https://github.com/user-attachments/assets/88bbcd8b-f520-44b2-95a5-01330cb25f0c" />
<br><br>

- **문제 · 정답 · 오답 구분 표시**
  - 사용자가 틀린 문제를 명확하게 복습 가능
- **카테고리 표시**
  - 각 문제의 카테고리를 함께 표시하여 취약 분야 파악 가능

---

## 🎥 시연 영상

- https://www.youtube.com/watch?v=_Spcwpl-DD8

---

## 🛠 기술 스택

- **Language**: Kotlin  
- **UI**: Jetpack Compose  
- **Architecture**: MVVM 기반 구조
- **Data Storage**: Local Storage (점수 및 오답 기록)

---

## 🎯 프로젝트 목적

본 프로젝트는 Jetpack Compose를 활용한 UI 구성과 상태 관리 및 사용자 경험(UX)을 고려한 모바일 애플리케이션 설계를 목표로 진행되었습니다.  
퀴즈 풀이뿐 아니라 랭킹과 오답 노트 기능을 통해 학습 효과를 높이고자 하였습니다.

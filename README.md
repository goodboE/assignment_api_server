## 환경 및 의존성
* Java 17
* SpringBoot 3.2.5
* Spring Web
* Spring Data JPA
* Lombok
* Validation
* Jsoup
* MySQL
<br>

## 구현 결과
GroupController에 모든 API가 구현되어 있습니다.
<br>

### 1-1 REST API 호출 및 응답
* userId가 1인 User의 userId와 title을 출력합니다.
#### REQUEST
```json
GET "/api/1"
```
#### RESPONSE
```json
200 OK

{
    "userId": 1,
    "title": "delectus aut autem"
}

```
<br>

### 1-2 사용자 입력 검증
* 정규식을 사용해 이메일의 유효성을 검증합니다. <br>
Ex) developer@weaverloft.com -> valid <br>
developer.weaverloft.com -> invalid
#### REQUEST
```json
POST "/api/validate"
{
    "email" : "developer@weaverloft.com"
}
```
#### RESPONSE
```json
200 OK
Valid email
```
<br>

#### REQUEST
```json
POST "/api/validate"
{
    "email" : "developer.weaverloft.com"
}
```
#### RESPONSE
```json
400 Bad Request
invalid email
```
<br>

### 1-3 Json 파싱 및 객체 매핑
* 주어진 Json 데이터를 User 객체로 매핑해 서버에 저장하고 Name, Age 필드를 출력합니다.
#### REQUEST
```json
POST "/api/add"
{
    "name" : "Jhon",
    "age": 30,
    "city": "New York"
}
```
#### RESPONSE
```json
200 OK
Name: Jhon, Age: 30
```
<br>


### 2-1 멀티스레딩을 이용한 파일 다운로드
* 주어진 URL의 개수만큼 스레드풀에서 스레드 생성
* 각각의 스레드에서 파일 다운로드 로직 실행
#### REQUEST
```json
POST "/api/download"
{
    "urls" : [
    "https://cdn.pixabay.com/photo/2021/08/22/08/54/bird-6564593__340.jpg",
    "https://cdn.pixabay.com/photo/2021/08/22/08/54/bird-6564593__340.jpg",
    "https://cdn.pixabay.com/photo/2021/08/22/08/54/bird-6564593__340.jpg"
    ]
}
```
#### RESPONSE
```json
200 OK
{
    "sizes": [
        "스레드: pool-2-thread-1 파일 크기: 36Kb",
        "스레드: pool-2-thread-2 파일 크기: 36Kb",
        "스레드: pool-2-thread-3 파일 크기: 36Kb"
    ]
}
```
<br>

### 2-2 웹 페이지 스크래핑 및 데이터 추출
* 주어진 URL의 모든 링크 추출
#### REQUEST
```json
GET "/api/scraping"
{
    "url" : "https://naver.com"
}
```
#### RESPONSE
```json
200 OK
[
    "https://www.naver.com/#topAsideButton",
    "https://www.naver.com/#shortcutArea",
    "https://www.naver.com/#newsstand",
    "https://www.naver.com/#shopping",
    "https://www.naver.com/#feed",
    "https://www.naver.com/#account",
    "https://www.naver.com/#widgetboard",
    "https://www.naver.com/#viewSetting",
    "https://www.naver.com/#",
    "https://help.naver.com/alias/search/word/word_35.naver",
    "https://help.naver.com/alias/search/word/word_35.naver",
    "https://www.naver.com/#",
    "https://help.naver.com/alias/search/word/word_35.naver",
    "https://www.naver.com/#",
    "https://www.naver.com/#",
    "https://www.naver.com/#",
    "https://www.naver.com/#",
    "https://www.naver.com/#",
    "https://www.naver.com/#",
    "https://help.naver.com/alias/search/word/word_16.naver",
    "https://www.naver.com/#",
    "https://help.naver.com/alias/search/word/word_16.naver",
    "https://help.naver.com/support/alias/search/word/word_16.naver",
    "https://nid.naver.com/nidlogin.login",
    "https://www.naver.com/#",
    "https://www.naver.com/#",
    "https://help.naver.com/alias/search/word/word_17.naver",
    "https://help.naver.com/alias/search/word/word_18.naver",
    "https://www.naver.com/#"
]
```
<br>


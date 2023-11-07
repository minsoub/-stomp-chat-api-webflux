# chat-app-api (stomp with webflux)

Chat 2.0 APP API서버


## 스펙
* Spring Boot Framework 2.6.6
* java 11
* Mysql / Mybatis
* Gradle
* yaml
* lombok
* Swagger API DOC
* log4jdbc

<br/>
<br/>

## 계정정보
* application.yml 참조.
* 개인 Branch 사용할것, Master Branch를 메인으로 둔다.(main branch 사용X )
* Branch 생성시 반드시 Branch는 dev_사용자명 으로 고정한다. 해당 branch로 계속 사용.
* Ex) dev_jyson, 이후 아래처럼 git config 개인으로 설정 할것.
>* git config --global user.name “user_name ” : git 계정Name 변경하기
>* git config --global user.email “user_email” : git 계정Mail변경하기

<br/>
<br/>

## 환경설정
* Intellij 기준으로 framework 구성
* Intellij 에서 UTF-8 설정.
>  Intellij --> Help --> Edit Custom VM Option 에서 아래 2라인 추가 할것 <br>
> -Dfile.encoding=UTF-8 <br>
>-Dconsole.encoding=UTF-8
 
* STS IDE사용시 별도 환경설정 필요할수 있음


<br/>
<br/>

## Plugins
>필수 플러그인(Intellij 기준)

* .ignore
* GitToolBox
* MyBatisX
* Lombok

>properties 한글깨짐
* STS : simple properties Editor 1.0.5 (Plugins 설치필요).
* Intellij : Preferences > Editor > File Encodings, Transparent native-to-ascii conversion 를 체크, UTF-8 변경 후 Apply.

>Search .yml or properties
* Intellij : Spring Intilializr and Assistant (Plugins), 설치시 유용.

>머신러닝 AI, 효율적 개발 코드 검색..
* Intellij : Tabnine (Plugins)

   
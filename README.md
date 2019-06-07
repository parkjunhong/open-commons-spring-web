# open-commons-spring-web
Open Commons for Spring Web on Spring 5 & Spring Boot 

---
## open.commons.spring.web.config.CustomEnuRegister
사용자 정의 Enum 타입을 HTTP 요청 데이터로 사용하기 위해서 자동으로 변환기를 등록해주는 클래스. 


---
## HOWTO
### Eum 클래스 정보가 있는 패키지 정의 (spring-boot)
Sprig Boot Application 설정 파일에 아래 예시와 같이 항목에 대한 값으로 패키지 정보 설정.\

예) application.yml 인 경우
``` yml
...
open-commons:
  spring:
    web:
      factory:
        enum:
          packages:
            - package1
            - package2
```

버전 0.0.1 까지
``` yml
...
 bean.package.name=a.b.c
 ...
 ```


### 사용자 정의 Enum 작성법
- __@RequestValueSupported__: HTTP 요청/응답에 사용할 Enum 임을 선언
- __@RequestValueConverter__: 사용자 정의 Enum 객체를 제공하는 메소드 선언.

``` java
 import java.util.ArrayList;
 import java.util.List;
 
 import open.commons.spring5.annotation.RequestValueConverter;
 import open.commons.spring5.annotation.RequestValueSupported;
 
 @RequestValueSupported
 public enum Service {
     NORMAL("normal"), PREMIUM("premium"), PLATINUM("Platinum");
 
     private String service;
 
     private Service(String service) {
         this.service = service;
     }
 
     public String get() {
         return this.service;
     }
 
     public static Service get(String service) {
         return get(service, false);
     }
 
     @RequestValueConverter(hasIgnoreCase = true)
     public static Service get(String service, boolean ignoreCase) {
 
         if (service == null) {
             throw new IllegalArgumentException("'service' MUST NOT be null. input: " + service);
         }
 
         if (ignoreCase) {
             for (Service value : values()) {
                 if (value.service.equalsIgnoreCase(service)) {
                     return value;
                 }
             }
         } else {
             for (Service value : values()) {
                 if (value.service.equals(service)) {
                     return value;
                 }
             }
         }
 
         throw new IllegalArgumentException(
                 "Unexpected 'service' value of 'Service'. expected: " + values0() + " & Ignore case-sensitive: " + ignoreCase + ", input: " + service);
     }
 
     private static List values0() {
 
         List valuesStr = new ArrayList<>();
 
         for (Service value : values()) {
             valuesStr.add(value.get());
         }
 
         return valuesStr;
     }
 }
 ```
 
### 자동으로 등록하기 (spring-boot)

**1. @ComponentScan 사용하기**\
@ComponentScan 어노테이션을 사용하여 패키지를 등록한다.

``` java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import open.commons.spring5.config.CustomEnumRegister;

@ServletComponentScan
@ComponentScan(basePackages = { "{your-own-bean-packages}", "open.commons.spring.web.config" })
@SpringBootApplication
public class SpringExampleApplication {

    public static void main(String[] args) {
    
        SpringApplication app = new SpringApplication(SpringExampleApplication.class);
        app.run(args);
    }
}
```

**2. 직접 빈 생성하기**\
패키지 정보를 제공하는 Bean과 Converter 자동등록을 위한 Bean을 생성한다.

``` java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import open.commons.spring5.config.CustomEnumRegister;

@ServletComponentScan
@SpringBootApplication
public class SpringExampleApplication {
    /** 패키지 정보를 제공하는 Bean 생성 */
    @Bean
    public CustomEnumPackages getCustomEnumPackages() {
        return new CustomEnumPackages();
    }

    /** Converter 자동 등록을 위한 Bean 생성 */
    @Bean
    public CustomEnumRegister registerCustomEnumRegister() {
        return new CustomEnumRegister();
    }
 
    @Bean
    public CustomEnumRegister registerCustomEnumRegister() {
        return new CustomEnumRegister();
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringExampleApplication.class);
        app.run(args);
    }
}
```
 
--- 
## Repository
maven
``` xml

<repositories>
  <repository>
    <id>ymtech.kr</id>
    <name>YMTECH Maven Repository</name>
    <url>http://nexus3.ymtech.co.kr/repository/maven-public/</url>
    <layout>default</layout>
  </repository>
</repositories>

<dependency>
  <groupId>open.commons</groupId>
  <artifactId>open-commons-spring5</artifactId>
  <version>${open-commons-spring5.version}</version>
</dependency>
```
 




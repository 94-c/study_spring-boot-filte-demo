# [스터디] Spring Boot Filter 스터디

## 1. Filter 란?

자바 서블릿에서 제공하는 기능

스프링 프레임워크에서 필터로 인증 등 다양한 작업을 하는 데 사용하니 스프링 프레임워크에서의 필터에 대해 기록



스프링 프레임워크는 들어온 요청이 DispatcherServlet에 의해 컨트롤러에 매핑됩니다.

Filter는 요청이 DispatcherServlet에 의해 다뤄지기 전, 후에 동작

 

### 1-1. Filter를 어디에 사용하나?



#### 1) 필터 인터페이스의 3가지 메소드

- init() : 필터 가 생성될 때 수행되는 메소드
- dofilter() : Request, Response가 필터를 거칠 때 수행되는 메소드
- destory() : 필터가 소멸될 때 수행되는 메소드

#### 2) 필터의 용도 및 예시

- 공통된 보안 및 인증/인가 관련 작업
- 모든 요청에 대한 로깅 또는 감사
- 이미지/데이터 압축 및 문자열 인코딩
- Spring과 분리되어야 하는 기능



### 1-2. Filter 구현 로그

![21fcf170-e75b-4d29-8013-8fabd67b5fea](https://github.com/94-c/study_spring-boot-filte-demo/assets/79362952/f029e4bf-2e70-4d43-a537-e8b62023005b)


서버 실행 시, Filter를 먼저 생성 후에 서버가 실행이 된다.

![c9ed0b2b-1fc4-4d28-abe6-60ce214be6de](https://github.com/94-c/study_spring-boot-filte-demo/assets/79362952/26e8fdeb-ee9d-4df5-ad6c-c14719426e6a)

서버 구동 후, 해당 Controller 진입 전에 Filter 동작



### 1-3. 필터 체인 (Filter Chain)

- 여러 개의 필터가 모여서 하나의 체인을 형성 할 때 첫번째 필터가 변경하는 요청 정보는 클라이언트의 요청 정보가 되지만, 체인의 두번째 필터가 변경하는 요청 정보는 첫번째 필터를 통해서 변경된 요청 정보

- User가 보낸 Request(요청) 정보는 변경에 변경에 변경을 거듭하게 된다.

- Response 시, 필터의 적용 순서가 요청 때와는 반대로 돌아 온다.

> Request 요청 시 : 1 → 2 → 3 → 자원(Controller)

> Response 요청 시 : 자원(Controller) → 3 → 2-> 1 




#### FilterRegistrationBean 

- Spring Framework에서 제공하는 클래스 중 하나로, 서블릿 필터를 등록 하는데 사용
- 필터의 URL패턴, 필터 순서, 초기 매개변수 등을 설정 가능
``` java
@Bean
    public FilterRegistrationBean firstFilterRegister() {
        
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new FirstFilter());
        //Bean의 등록 순서를 설정 하는데 사용 -> 작은 숫자 일수록 우선순위가 높아진다.
        registrationBean.setOrder(1);
        
        return registrationBean;
    }
```


# 2. Interceptor란?

- Spring이 제공하는 기술, 디스패처 서블릿(Dispatcher Servlet)이 컨트롤러를 호출하기 전과 후에 요청과 응답을 참조하거나 가공할 수 있는 기능 제공



## 2-1. Interceptor는 왜 사용 하는가?

### 1) Interceptor 인터페이스 3가지 메소드

- preHandle() : 컨트롤러 이전에 처리해야 하는 전처리 작업이나 요청 정보를 가공하거나 추가하는 경우에 사용

- postHandle() : 컨트롤러를 호출된 후에 실행, 컨트롤러 이후에 처리해야 하는 후처리 작업이 있을 때 사용할 수 있다.

- afterCompletion() : 모든 View에서 최종 결과를 생성하는 일을 포함한 모든 작업이 완료된 후에 실행된다. 

### 2) Interceptor의 용도 & 예시

- 세부적인 보안 및 인증/인가 공통 작업

- API 호출에 대한 로깅 또는 감사

- Controller로 넘겨주는 정보(데이터)의 가공


2-2 Interceptor 구현 로그 

![5fc5351a-7cde-4941-af44-31909983ebda](https://github.com/94-c/study_spring-boot-filte-demo/assets/79362952/bbde901a-01ec-49d7-bc05-4dbd0e1d9760)

![295b6f21-1a3b-4be5-a53e-ae13815abcfc](https://github.com/94-c/study_spring-boot-filte-demo/assets/79362952/dc8554ac-3579-42e6-909e-1e3135d411db)


- 추가적으로 Intercepotor 생성 후, 실제 로그를 찍어봤을 때 서버 실행 후, Filter →  Dispatcher Servlet  -> Intercepotor -> 자원으로 넘어가는 로그를 확인 가능

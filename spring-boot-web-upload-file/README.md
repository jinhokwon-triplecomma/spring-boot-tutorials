
## 파일 업로드

http://localhost:8088/index.html 페이지에 접속하여 파일을 업로드 합니다.

## 파일 업로드 관련 설정 

```yaml
spring:
  servlet:
    multipart:
      enabled: true
      max-file-size: 1MB
      max-request-size: 2MB
      resolve-lazily: true
```

##### 주의사항
* multipart.enabled: 서버에서 Multipart Request 를 허용할지 여부
* multipart.max-file-size: 전송되는 개별 파일의 크기를 제한  
* multipart.max-request-size: 서버측으로 전송된 요청(request) 의 크기를 제한
* 때문에, max-request-size 는 max-file-size 보다 좀 더 크게 설정해야 합니다. 

파일 사이즈 초과시에는 다음과 같은 에러 핸들러가 필요합니다.
```java
@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {MaxUploadSizeExceededException.class})
    public ResponseEntity<Map<String, String>> maxUploadSizeExceededException(MaxUploadSizeExceededException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(
                Collections.singletonMap("response", e.getMessage())
        );
    }
}
```

만약, 별도의 에러 핸들러가 설정되어 있지 않을 경우, 아래와 같은 기본 에러화면이 표시됩니다.
```html
Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Tue Feb 27 21:06:22 KST 2024
There was an unexpected error (type=Payload Too Large, status=413).
```

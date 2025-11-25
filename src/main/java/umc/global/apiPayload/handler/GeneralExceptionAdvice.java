package umc.global.apiPayload.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.code.GeneralErrorCode;
import umc.global.apiPayload.code.PagingErrorCode;
import umc.global.apiPayload.exception.GeneralException;
import umc.global.apiPayload.exception.PagingException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // 애플리케이션에서 발생하는 커스텀 예외를 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            GeneralException ex
    ) {

        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                                ex.getCode(),
                                null
                        )
                );
    }


    //@Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        // 검사에 실패한 필드와 그에 대한 메시지를 저장하는 Map
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        GeneralErrorCode code = GeneralErrorCode.VALIDATION_ERROR;
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(code, errors);

        // 에러 코드, 메시지와 함께 errors를 반환
        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraint(ConstraintViolationException ex) {
        // 어떤 파라미터에서 위반이 났는지 보고 코드 분기
        boolean pageRelated = ex.getConstraintViolations().stream()
                .anyMatch(v -> {
                    String path = v.getPropertyPath().toString(); // 예: getMyReviews.page
                    return path.contains("page") || path.endsWith(".page");
                });

        BaseErrorCode code = pageRelated
                ? PagingErrorCode.INVALID_PAGE              // "page는 1 이상" 등
                : GeneralErrorCode.VALIDATION_ERROR;         // 그 외 검증 오류

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, null));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        // 타입 변환 실패: page=abc, size=abc 같은 케이스
        String name = ex.getName(); // 파라미터 이름
        BaseErrorCode code;

        if ("page".equals(name) || "size".equals(name)) {
            code = PagingErrorCode.INVALID_PAGE_FORMAT;     // 페이징 파라미터 형식 오류
        } else {
            code = GeneralErrorCode.VALIDATION_ERROR;       // 그 외 타입 오류는 일반 검증 실패로
        }

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, null));
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex
    ) {

        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                                code,
                                ex.getMessage()
                        )
                );
    }
}

package umc.domain.test.conveter;

import umc.domain.test.dto.TestResDTO;

public class TestConverter {

    public static TestResDTO.Testing toTestingDTO(String testing){
        return TestResDTO.Testing.builder()
                .testString(testing)
                .build();
    }

    public static TestResDTO.Exception toExceptionDTO(String testing){
        return TestResDTO.Exception.builder()
                .testString(testing)
                .build();
    }
}

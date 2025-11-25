package umc.domain.review.dto;

import java.util.List;

public class ReviewReqDto {

    public record ReviewCreate(
            String content,
            Double rating,
            List<String> imageUrls
    ){}
}

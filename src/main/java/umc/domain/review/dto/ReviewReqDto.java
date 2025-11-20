package umc.domain.review.dto;

import jakarta.persistence.*;
import umc.domain.member.entity.Member;
import umc.domain.review.entity.ReviewImage;
import umc.domain.review.entity.ReviewReply;
import umc.domain.store.entity.Store;

import java.util.ArrayList;
import java.util.List;

public class ReviewReqDto {

    public record Create(
            String content,
            Double rating,
            List<String> imageUrls
    ){}
}

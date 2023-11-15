package store.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddToCartRequestDto {
    @Positive
    private Long bookId;
    @Positive
    @Max(value = 100)
    private Integer quantity;
}

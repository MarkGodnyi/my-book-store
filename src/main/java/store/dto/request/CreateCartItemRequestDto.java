package store.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateCartItemRequestDto {
    @Positive
    private Long bookId;
    @Positive
    @Max(value = 10)
    private Integer quantity;
}

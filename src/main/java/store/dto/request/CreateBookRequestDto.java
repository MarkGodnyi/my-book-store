package store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @Pattern(regexp = "^\\d{10}|\\d{13}$", message = "must be either 10 or 13 digits")
    private String isbn;
    @Positive
    private BigDecimal price;
    @NotBlank
    private String description;
    @NotNull
    private String coverImage;
    @NotEmpty
    private Set<Long> categoryIds;
}

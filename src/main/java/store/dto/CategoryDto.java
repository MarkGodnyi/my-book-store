package store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
    @Null
    private Long id;
    @NotBlank
    private String name;
    @Size(min = 4, max = 255)
    private String description;
}

package store.dto.response;

import lombok.Data;

@Data
public class CartItemResponseDto {
    private Long id;
    private Long bookId;
    private String bookTitLe;
    private int quantity;
}

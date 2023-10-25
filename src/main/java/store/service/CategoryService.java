package store.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import store.dto.request.CategoryRequestDto;
import store.dto.response.CategoryResponseDto;

public interface CategoryService {
    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto);

    void deleteById(Long id);
}

package store.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.dto.request.CategoryRequestDto;
import store.dto.response.CategoryResponseDto;
import store.exception.EntityNotFoundException;
import store.mapper.CategoryDtoMapper;
import store.model.Category;
import store.repository.CategoryRepository;
import store.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll().stream()
                .map(categoryDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        String exAnswer = String.format("Category with id: %s doesn't found", id);
        return categoryRepository.findById(id)
                .map(categoryDtoMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(exAnswer));
    }

    @Override
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto) {
        Category category = categoryDtoMapper.toModel(categoryRequestDto);
        return categoryDtoMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto) {
        String exAnswer = String.format("Category with id: %s doesn't found", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(exAnswer));
        categoryDtoMapper.updateCategoryFromDto(categoryRequestDto, category);
        return categoryDtoMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}

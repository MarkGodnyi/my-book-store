package store.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.dto.CategoryDto;
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
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll().stream()
                .map(categoryDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getById(Long id) {
        String exAnswer = String.format("Category with id: %s doesn't found", id);
        return categoryRepository.findById(id)
                .map(categoryDtoMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(exAnswer));
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryDtoMapper.toModel(categoryDto);
        return categoryDtoMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        String exAnswer = String.format("Category with id: %s doesn't found", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(exAnswer));
        categoryDtoMapper.updateCategoryFromDto(categoryDto, category);
        return categoryDtoMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}

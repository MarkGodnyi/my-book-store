package store.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import store.config.MapperConfig;
import store.dto.request.CategoryRequestDto;
import store.dto.response.CategoryResponseDto;
import store.model.Category;

@Mapper(config = MapperConfig.class)
public interface CategoryDtoMapper {
    CategoryResponseDto toDto(Category category);

    Category toModel(CategoryRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromDto(CategoryRequestDto categoryDto, @MappingTarget Category category);
}

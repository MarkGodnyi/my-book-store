package store.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import store.config.MapperConfig;
import store.dto.CategoryDto;
import store.dto.request.CreateBookRequestDto;
import store.dto.response.BookDto;
import store.dto.response.BookDtoWithoutCategoryIds;
import store.model.Book;
import store.model.Category;
import store.service.CategoryService;

@Mapper(config = MapperConfig.class)
public abstract class BookDtoMapper {
    @Autowired
    protected CategoryService categoryService;
    @Autowired
    protected CategoryDtoMapper categoryDtoMapper;

    public abstract BookDto toDto(Book book);

    public abstract BookDtoWithoutCategoryIds toBookDtoWithoutCategoryIds(Book book);

    @Mapping(target = "categories",
            source = "categoryIds",
            qualifiedByName = "idsToCategories")
    public abstract Book toModel(CreateBookRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "categories",
            source = "categoryIds",
            qualifiedByName = "idsToCategories")
    public abstract void updateBookFromDto(CreateBookRequestDto requestDto,
                                           @MappingTarget Book book);

    @Named(value = "idsToCategories")
    public Set<Category> idsToCategories(Set<Long> ids) {
        return ids.stream()
                .map(id -> {
                    CategoryDto dto = categoryService.getById(id);
                    return categoryDtoMapper.toModel(dto); })
                .collect(Collectors.toSet());
    }

    @AfterMapping
    public void setCategoryIds(@MappingTarget BookDto dto, Book book) {
        Set<Long> categoryIds = book.getCategories().stream()
                .mapToLong(Category::getId)
                .boxed()
                .collect(Collectors.toSet());
        dto.setCategoryIds(categoryIds);
    }
}

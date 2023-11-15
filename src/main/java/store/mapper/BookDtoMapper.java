package store.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import store.config.MapperConfig;
import store.dto.request.CreateBookRequestDto;
import store.dto.response.BookDto;
import store.dto.response.BookDtoWithoutCategoryIds;
import store.model.Book;
import store.model.Category;

@Mapper(config = MapperConfig.class)
public interface BookDtoMapper {

    BookDto toDto(Book book);

    BookDtoWithoutCategoryIds toBookDtoWithoutCategoryIds(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookFromDto(CreateBookRequestDto requestDto, @MappingTarget Book book);

    @AfterMapping
    default void setCategories(@MappingTarget Book book, CreateBookRequestDto bookRequestDto) {
        book.setCategories(bookRequestDto.getCategoryIds().stream()
                .map(Category::new)
                .collect(Collectors.toSet()));
    }

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto dto, Book book) {
        Set<Long> categoryIds = book.getCategories().stream()
                .mapToLong(Category::getId)
                .boxed()
                .collect(Collectors.toSet());
        dto.setCategoryIds(categoryIds);
    }

    @Named("bookFromId")
    default Book bookFromId(Long id) {
        Book book = new Book();
        book.setId(id);
        return book;
    }
}

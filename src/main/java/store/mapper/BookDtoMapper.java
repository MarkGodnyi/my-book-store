package store.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import store.config.MapperConfig;
import store.dto.request.CreateBookRequestDto;
import store.dto.response.BookDto;
import store.model.Book;

@Mapper(config = MapperConfig.class)
public interface BookDtoMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookFromDto(CreateBookRequestDto requestDto, @MappingTarget Book book);
}

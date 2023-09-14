package store.mapper;

import org.mapstruct.Mapper;
import store.config.MapperConfig;
import store.dto.request.CreateBookRequestDto;
import store.dto.response.BookDto;
import store.model.Book;

@Mapper(config = MapperConfig.class)
public interface BookDtoMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}

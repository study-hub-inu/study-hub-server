package kr.co.studyhubinu.studyhubserver.common.dto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Converter {

    public static <T, U> Slice<U> toSlice(List<T> persistenceDto, Function<T, U> constructor, final Pageable pageable) {
        List<U> items = toApplicationDto(persistenceDto, constructor);

        if (items.size() > pageable.getPageSize()) {
            items.remove(items.size() - 1);
            return new SliceImpl<>(items, pageable, true);
        }
        return new SliceImpl<>(items, pageable, false);
    }

    public static <T, U> List<U> toApplicationDto(List<T> persistenceDto, Function<T, U> constructor) {
        return persistenceDto.stream()
                .map(constructor)
                .collect(Collectors.toList());
    }
}

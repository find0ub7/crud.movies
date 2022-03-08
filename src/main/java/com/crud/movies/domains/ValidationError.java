package com.crud.movies.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class ValidationError {
    private final MessageCode keyMessage;
    private final List<Object> params;
}

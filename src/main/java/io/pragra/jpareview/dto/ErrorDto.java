package io.pragra.jpareview.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class ErrorDto {
    private String errCode;
    private String errMessage;
    private String apiCode;
    private String apiMessage;
    private Instant timeStamp;
}

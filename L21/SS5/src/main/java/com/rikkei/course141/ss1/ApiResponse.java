package com.rikkei.course141.ss1;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private String status;
    private int code;
    private T data;
    private Meta meta;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Meta {
        private long total;
        private int page;
        private int size;
    }

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder().status("success").code(200).data(data).build();
    }

    public static <T> ApiResponse<T> success(T data, Meta meta) {
        return ApiResponse.<T>builder().status("success").code(200).data(data).meta(meta).build();
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return ApiResponse.<T>builder().status("error").code(code).data((T) message).build();
    }
}

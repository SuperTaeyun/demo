package com.example.backend.domain;

public record MemoResponse(
        Long id,
        String title,
        String content) {

    public static MemoResponse from(Memo memo) {
        return new MemoResponse(
                memo.getId(),
                memo.getTitle(),
                memo.getContent());
    }
}

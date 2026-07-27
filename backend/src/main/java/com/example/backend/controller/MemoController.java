package com.example.backend.controller;

import com.example.backend.domain.MemoCreateRequest;
import com.example.backend.domain.MemoResponse;
import com.example.backend.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memos")
@CrossOrigin(origins = "*")
public class MemoController {

    private final MemoService memoService;

    @PostMapping
    public MemoResponse createMemo(@RequestBody MemoCreateRequest req) {
        return memoService.createMemo(req);
    }

    @GetMapping("/{id}")
    public MemoResponse getMemo(@PathVariable Long id) {
        return memoService.getMemo(id);
    }

    @GetMapping
    public List<MemoResponse> getAllMemos() {
        return memoService.getAllMemos();
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
    }
}

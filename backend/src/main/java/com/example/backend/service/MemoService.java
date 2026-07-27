package com.example.backend.service;

import com.example.backend.domain.Memo;
import com.example.backend.domain.MemoCreateRequest;
import com.example.backend.domain.MemoResponse;
import com.example.backend.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponse createMemo(MemoCreateRequest request) {
        var memo = new Memo(request.title(), request.content());
        var savedMemo = memoRepository.save(memo);

        return MemoResponse.from(savedMemo);
    }

    @Cacheable(cacheNames = "memo", key = "#id")
    @Transactional(readOnly = true)
    public MemoResponse getMemo(Long id) {
        return MemoResponse.from(memoRepository.findById(id).orElseThrow());
    }

    @Transactional(readOnly = true)
    public List<MemoResponse> getAllMemos() {
        return memoRepository.findAll().stream().map(MemoResponse::from).toList();
    }


    @CacheEvict(cacheNames = "memo", key = "#id")
    @Transactional
    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }

}

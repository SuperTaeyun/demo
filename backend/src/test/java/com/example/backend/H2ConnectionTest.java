package com.example.backend;

import com.example.backend.domain.Memo;
import com.example.backend.repository.MemoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class H2ConnectionTest {

    @Autowired
    private MemoRepository repo;

    @Test
    @DisplayName("H2 DB 연결 및 저장 테스트")
    void connectAndSaveTest() {
        // given
        var memo = new Memo("test", "test");
        // when
        var savedMemo = repo.save(memo);
        // then
        assert savedMemo.getId() != null;
        assert savedMemo.getTitle().equals("test");
        assert savedMemo.getContent().equals("test");

        System.out.println("ID: " + savedMemo.getId() + ", Title: " + savedMemo.getTitle() + ", Content: " + savedMemo.getContent());
    }

}

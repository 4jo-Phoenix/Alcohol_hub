package com.teamphoenix.ahub.post.query.service;

import com.teamphoenix.ahub.post.query.dto.PostDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;


@SpringBootTest
class PostServiceImplTest {

    @Autowired
    private PostServiceImpl postServiceImpl;


    static Stream<Arguments> getSearchInfo() {

        return Stream.of(
                Arguments.of(new PostDTO(null, null)),
                Arguments.of(new PostDTO("2024", null)),
                Arguments.of(new PostDTO(null,"맥주")),
                Arguments.of(new PostDTO("맥주", "맥주"))
        );
    }
    @DisplayName("T1-게시글 선택 시 게시글 번호로 내용 불러오기 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 8})
    void findPostTest(int postId) {
        Assertions.assertDoesNotThrow(
                () -> postServiceImpl.getPost(postId)
        );
    }

    @DisplayName("T2-검색창에서 카테고리 조건에 맞는 게시글 검색 후 리스트 반환 테스트")
    @ParameterizedTest
    @MethodSource("getSearchInfo")
    void selectPostByConditionTest(PostDTO getInfo) {
        Assertions.assertDoesNotThrow(
                () -> postServiceImpl.findPostsByCondition(getInfo)
        );
    }

}
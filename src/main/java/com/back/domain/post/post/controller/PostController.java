package com.back.domain.post.post.controller;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    private String getWriteForHtml() {
        return getWriteForHtml("");
    }

    private String getWriteForHtml(String errorMessage) {
        return """
                <div style="color:red;">%s</div>
                
                <form action="doWrite" method="POST">
                    <input type="text" name="title" placeholder="제목" value="">
                    <br>
                    <textarea name="content" placeholder="내용"></textarea>
                    <br>
                    <input type="submit" value="작성">
                </form>
                """.formatted(errorMessage);
    }

    @GetMapping("/posts/write")
    @ResponseBody
    public String showWrite() {
        return  getWriteForHtml();
    }

    @PostMapping("/posts/doWrite")
    @ResponseBody
    @Transactional
    public String write(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content
    ) {
        if (title.isBlank()) return getWriteForHtml("제목을 입력해주세요.");
        if (content.isBlank()) return getWriteForHtml("내용을 입력해주세요.");

        Post post = postService.write(title, content);

        return "%d번 글이 생성되었습니다.".formatted(post.getId());
    }
}

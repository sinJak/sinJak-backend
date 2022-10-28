package net.sinzak.server.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.sinzak.server.config.auth.LoginUser;
import net.sinzak.server.config.auth.dto.SessionUser;
import net.sinzak.server.dto.WorkPostDto;
import net.sinzak.server.error.ErrorResponse;
import net.sinzak.server.service.WorkService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @ApiOperation(value = "외주 모집 글 생성")
    @PostMapping("/works/build")
    public JSONObject makeWorkPost(@LoginUser SessionUser user, @RequestBody WorkPostDto postDto) {
        return workService.makeWorkPost(user, postDto);
    }




    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleException1() {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, "존재하지 않는 유저");
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleException2() {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, "존재하지 않는 값을 조회중입니다.");
    }
}

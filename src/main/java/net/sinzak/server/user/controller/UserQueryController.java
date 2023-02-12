package net.sinzak.server.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.sinzak.server.common.dto.IdDto;
import net.sinzak.server.user.domain.User;
import net.sinzak.server.user.dto.respond.UserDto;
import net.sinzak.server.user.service.UserQueryService;
import org.json.simple.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = "유저-조회")
@RestController
@RequiredArgsConstructor
public class UserQueryController {
    private final UserQueryService userQueryService;

    @ApiOperation(value ="내 프로필 보기")
    @GetMapping(value ="/users/my-profile")
    public JSONObject getMyProfile(@AuthenticationPrincipal User user){
        return userQueryService.getMyProfile(user);
    }

    @ApiOperation(value ="유저 프로필 보기")
    @GetMapping(value ="/users/{userId}/profile")
    public JSONObject getUserProfile(@PathVariable Long userId, @AuthenticationPrincipal User user){
       return userQueryService.getUserProfile(userId, user);

    }
    @ApiOperation(value ="팔로워리스트")
    @GetMapping(value ="/users/{userId}/followers")
    public JSONObject getFollowerList(@PathVariable Long userId) {
        return userQueryService.getFollowerDtoList(userId);
    }

    @ApiOperation(value ="팔로잉리스트")
    @GetMapping(value ="/users/{userId}/followings")
    public JSONObject  getFollowingList(@PathVariable Long userId) {
        return userQueryService.getFollowingDtoList(userId);
    }

    @ApiOperation(value = "검색기록 출력", notes = "GetMapping에 유의 삭제는 Post로")
    @GetMapping(value = "/users/history")
    public JSONObject showHistory(@AuthenticationPrincipal User user) {
        return userQueryService.showSearchHistory(user);
    }

    @ApiOperation(value ="스크랩 목록 ")
    @GetMapping(value ="/users/{userId}/wish")
    public JSONObject showWish(@PathVariable Long userId){
        return userQueryService.getWishList(userId);
    }


}

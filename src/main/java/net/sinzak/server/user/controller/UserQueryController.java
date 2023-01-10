package net.sinzak.server.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.sinzak.server.config.auth.LoginUser;
import net.sinzak.server.config.auth.dto.SessionUser;
import net.sinzak.server.user.domain.User;
import net.sinzak.server.user.dto.request.UserIdDto;
import net.sinzak.server.user.dto.respond.GetFollowDto;
import net.sinzak.server.user.dto.respond.UserDto;
import net.sinzak.server.user.service.UserQueryService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
@Api(tags = "유저-조회")
@RestController
@RequiredArgsConstructor
public class UserQueryController {
    private final UserQueryService userQueryService;

    @ApiOperation(value ="내 프로필 보기")
    @GetMapping(value ="users/my-profile")
    public UserDto getMyProfile(@ApiIgnore @AuthenticationPrincipal User user){
        return userQueryService.getMyProfile(user);
    }

    @ApiOperation(value ="유저 프로필 보기")
    @GetMapping(value ="/users/profile")
    public UserDto getUserProfile(@RequestBody UserIdDto userIdDto, @ApiIgnore @AuthenticationPrincipal User user){
        UserDto userDto = userQueryService.getUserProfile(userIdDto, user);
        return userDto;
    }
    @ApiOperation(value ="팔로워리스트")
    @GetMapping(value ="/users/followers")
    public List<GetFollowDto> getFollowerList( @RequestBody UserIdDto userIdDto) {
        List<GetFollowDto> getFollowDtos =
               userQueryService.getFollowerDtoList(userIdDto);
        return getFollowDtos;
    }

    @ApiOperation(value ="팔로잉리스트")
    @GetMapping(value ="/users/followings")
    public List<GetFollowDto> getFollowingList(@RequestBody UserIdDto userIdDto) {
        List<GetFollowDto> getFollowDtos =
                userQueryService.getFollowingDtoList(userIdDto);
        return getFollowDtos;
    }
    //    @ApiOperation(value ="팔로워리스트")
//    @GetMapping(value ="/users/{userId}/followers")
//    public List<GetFollowDto> getFollowerList2(@PathVariable("userId") Long userId) {
//        return userQueryService.getFollowerDtoList(userId);
//    }



}

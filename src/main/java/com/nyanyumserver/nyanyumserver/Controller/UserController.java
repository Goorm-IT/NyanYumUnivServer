package com.nyanyumserver.nyanyumserver.Controller;

import com.nyanyumserver.nyanyumserver.Service.UserService;
import com.nyanyumserver.nyanyumserver.VO.UserInfo;
import com.nyanyumserver.nyanyumserver.VO.UserSearchInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;


    public String getSessionUid(HttpSession session){
        return (String) session.getAttribute("uid");
    }

    public void setUserSession(HttpSession session, List<UserInfo> getUserInfos){
        session.setAttribute("uid", getUserInfos.get(0).getUid());
        session.setAttribute("nickName", getUserInfos.get(0).getNickName());
        session.setAttribute("userLevel", getUserInfos.get(0).getUserLevel());
        session.setAttribute("postId", getUserInfos.get(0).getPostId());
        session.setAttribute("profileImg", getUserInfos.get(0).getProfileImg());
    }

    public void setUpdateNickName(HttpSession session, String nickName){
        session.removeAttribute("nickName");
        session.setAttribute("nickName", nickName);
    }


    @GetMapping("/login")
    @ApiOperation(value = "로그인")
    public Object login(
            @ApiParam(value = "uid (UserId)", required= true) @RequestParam(value = "uid", required =true) String uid ,
            @ApiIgnore HttpSession session){

        if (logger.isDebugEnabled()){
           logger.debug("START. Login");
        }
        try{

            UserSearchInfo userSearchInfo = new UserSearchInfo();

            userSearchInfo.setUid(uid);
            userService.getLogin(userSearchInfo);

            if (userSearchInfo.getUserInfos().get(0) == null){
                throw new Exception();
            }
            else{
                setUserSession(session, userSearchInfo.getUserInfos());

                logger.debug("END. Login");
                return new ResponseEntity<>("로그인", HttpStatus.OK);
            }
        }catch (Exception e){
            logger.debug("ERROR. NO USER");

            return new ResponseEntity<>("회원 정보가 없습니다.",HttpStatus.BAD_REQUEST);
        }

    }
    @ApiOperation(value = "회원가입")
    @PutMapping("/Register")
    public Object Register(
            @ApiParam(value = "uid (UserId)", required= true) @RequestParam(value = "uid", required =true) String uid,
            @ApiParam(value = "nickName (nickName)", required= true) @RequestParam("nickName") String nickName,
            @ApiIgnore HttpSession session, HttpServletResponse response) {
        UserSearchInfo userSearchInfo = new UserSearchInfo();

        userSearchInfo.setUid(uid);
        userSearchInfo.setNickName(nickName);

        System.out.println(userService.getUid(userSearchInfo));

        if (userService.getUid(userSearchInfo) != null) {
            return new ResponseEntity<>("이미 가입된 아이디 입니다.", HttpStatus.BAD_REQUEST);
        } else {
            try {
                userService.getRegister(userSearchInfo);
                return new ResponseEntity<>("회원가입 완료", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("중복된 닉네임 입니다.", HttpStatus.BAD_REQUEST);
            }

        }

    }

    @ApiOperation(value = "로그아웃")
    @GetMapping("/logout")
    public ResponseEntity<?> logOut(@ApiIgnore HttpSession session){
        session.invalidate();
        return new ResponseEntity<>("로그아웃", HttpStatus.OK);
    }

    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping("/secession")
    public Object secession(@ApiIgnore HttpSession session){

        userService.getSecession((String) session.getAttribute("uid"));
        session.invalidate();
        return new ResponseEntity<>("탈퇴 처리 되었습니다.", HttpStatus.OK);
    }

    @ApiOperation(value = "유저정보")
    @GetMapping("/info")
    public Object info(@ApiIgnore HttpSession session){
        UserSearchInfo userSearchInfo = new UserSearchInfo();
        UserInfo userInfo = new UserInfo();
        userInfo.setUid((String) session.getAttribute("uid"));
        userInfo.setNickName((String) session.getAttribute("nickName"));
        userInfo.setUserLevel((String) session.getAttribute("userLevel"));
        userInfo.setPostId((String) session.getAttribute("postId"));

        List<UserInfo> userInfos = new ArrayList<>();

        userInfos.add(userInfo);
        userSearchInfo.setUserInfos(userInfos);

        return userSearchInfo.getUserInfos();
    }

    @ApiOperation(value = "닉네임변경")
    @PostMapping("/updateNickName")
    public Object updateNickName(@ApiParam(value = "nickName (nickName)", required= true) @RequestParam("nickName") String nickName,
                                 @ApiIgnore HttpSession session){
        UserSearchInfo userSearchInfo = new UserSearchInfo();


        try{
            userSearchInfo.setUid((String) session.getAttribute("uid"));
            userSearchInfo.setNickName(nickName);

            if(userService.getUpdateNickName(userSearchInfo)){
                userService.getLogin(userSearchInfo);
                setUpdateNickName(session, userSearchInfo.getNickName());
            }else{
                throw new SQLException();
            }

            return new ResponseEntity<>("닉네임 변경 완료", HttpStatus.OK);
        } catch (Exception e){
            logger.error(e.getMessage(), e);

            return new ResponseEntity<>("중복된 닉네임 입니다.", HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value = "프로필 사진 등록")
    @PostMapping("/updateProfileImage")
    public Object updateProfileImage(@ApiParam(value="Image", required = true) @RequestParam MultipartFile file){
        UserSearchInfo userSearchInfo = new UserSearchInfo();

    }

}

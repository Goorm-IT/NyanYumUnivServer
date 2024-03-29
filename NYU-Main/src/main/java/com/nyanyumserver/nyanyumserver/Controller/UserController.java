package com.nyanyumserver.nyanyumserver.Controller;

import com.nyanyumserver.nyanyumserver.Service.UserService;
import com.nyanyumserver.nyanyumserver.Service.ImageService;
import com.nyanyumserver.nyanyumserver.VO.UserInfo;
import com.nyanyumserver.nyanyumserver.VO.UserSearchInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/nyu/user")
@RequiredArgsConstructor
@EnableSwagger2
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    private final ImageService imageService;

    public String getSessionUid(HttpSession session){
        return (String) session.getAttribute("uId");
    }

    public void setUserSession(HttpSession session, List<UserInfo> getUserInfos){
        session.setAttribute("uId", getUserInfos.get(0).getUid());
        session.setAttribute("userAlias", getUserInfos.get(0).getUserAlias());
        session.setAttribute("userLevel", getUserInfos.get(0).getUserLevel());
        session.setAttribute("registerDate", getUserInfos.get(0).getRegisterDate());
        session.setAttribute("imagePath", getUserInfos.get(0).getImagePath());
    }

    public void setUpdateUserAlias(HttpSession session, String userAlias){
        session.removeAttribute("userAlias");
        session.setAttribute("userAlias", userAlias);
    }


    public void setUpdatePath(HttpSession session, String path){
        session.removeAttribute("imagePath");
        session.setAttribute("imagePath", path);
    }


    @GetMapping("/session")
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
            logger.debug(userSearchInfo.getUid());
            userService.getLogin(userSearchInfo);
            logger.debug(userSearchInfo.getUserLevel());
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

    @ApiOperation(value = "로그아웃")
    @DeleteMapping("/session")
    public ResponseEntity<?> logOut(@ApiIgnore HttpSession session){

        if (logger.isDebugEnabled()){
            logger.debug("START. logout");
        }

        session.invalidate();
        return new ResponseEntity<>("로그아웃", HttpStatus.OK);
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("")
    public Object Register(
            @ApiParam(value = "uid", required= true) @RequestParam(value = "uid", required =true) String uid,
            @ApiParam(value = "userAlias", required= true) @RequestParam("userAlias") String userAlias,
            @ApiIgnore HttpSession session, HttpServletResponse response) {


        if (logger.isDebugEnabled()){
            logger.debug("START. register");
        }

        UserSearchInfo userSearchInfo = new UserSearchInfo();

        userSearchInfo.setUid(uid);
        userSearchInfo.setUserAlias(userAlias);
        userSearchInfo.setRegisterDate(LocalDate.now());

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

    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping("")
    public Object secession(@ApiIgnore HttpSession session){

        userService.getSecession((String) session.getAttribute("uId"));
        session.invalidate();
        return new ResponseEntity<>("탈퇴 처리 되었습니다.", HttpStatus.OK);
    }


    @ApiOperation(value = "유저정보")
    @GetMapping( "")
    public Object info(@ApiIgnore HttpSession session){

        if (logger.isDebugEnabled()){
            logger.debug("START. info");
        }

        UserSearchInfo userSearchInfo = new UserSearchInfo();


        UserInfo userInfo = new UserInfo();
        userInfo.setUid((String) session.getAttribute("uId"));
        userInfo.setUserAlias((String) session.getAttribute("userAlias"));
        userInfo.setUserLevel((String) session.getAttribute("userLevel"));
        userInfo.setRegisterDate((LocalDate) session.getAttribute("registerDate"));
        userInfo.setImagePath((String) session.getAttribute("imagePath"));

        List<UserInfo> userInfos = new ArrayList<>();

        userInfos.add(userInfo);
        userSearchInfo.setUserInfos(userInfos);

        return new ResponseEntity<>(userSearchInfo.getUserInfos(), HttpStatus.OK);
    }

    @ApiOperation(value = "닉네임변경")
    @PutMapping("/alias")
    public Object updateUserAlias(@ApiParam(value = "userAlias", required= true) @RequestParam("userAlias") String userAlias,
                                 @ApiIgnore HttpSession session){

        if (logger.isDebugEnabled()){
            logger.debug("START. updateUserAlias");
        }

        UserSearchInfo userSearchInfo = new UserSearchInfo();


        try{
            userSearchInfo.setUid((String) session.getAttribute("uId"));
            userSearchInfo.setUserAlias(userAlias);

            if(userService.getUpdateUserAlias(userSearchInfo)){
                userService.getLogin(userSearchInfo);
                setUpdateUserAlias(session, userSearchInfo.getUserAlias());
            }else{
                throw new SQLException();
            }

            return new ResponseEntity<>("닉네임 변경 완료", HttpStatus.OK);
        } catch (Exception e){
            logger.error(e.getMessage(), e);

            return new ResponseEntity<>("중복된 닉네임 입니다.", HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value = "프로필 사진 변경")
    @PostMapping("/image")

    public Object updateProfileImage(@ApiParam(value="Image", required = true) @RequestPart MultipartFile file,
                                     @ApiIgnore HttpSession session) throws IOException {

        if (logger.isDebugEnabled()){
            logger.debug("START. updateProfileImage");
        }

        UserSearchInfo userSearchInfo = new UserSearchInfo();

        try{
            Object uid = session.getAttribute("uId");
            userSearchInfo.setUid((String) uid);
            String imgPath = imageService.updateImage(file, (String) uid, "profile");
            userSearchInfo.setImagePath(imgPath);

            if(userService.getUpdatePath(userSearchInfo)){
                setUpdatePath(session, userSearchInfo.getImagePath());
            }else{
                throw new SQLException();
            }

            return new ResponseEntity<>("변경 완료", HttpStatus.OK);

        } catch (Exception e){

            return new ResponseEntity<>("변경 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "프로필 사진 삭제")
    @DeleteMapping("/image")

    public Object deleteProfileImage(@ApiIgnore HttpSession session) throws IOException {

        if (logger.isDebugEnabled()){
            logger.debug("START. deleteProfileImage");
        }

        UserSearchInfo userSearchInfo = new UserSearchInfo();

        try{
            Object uid = session.getAttribute("uId");
            userSearchInfo.setUid((String) uid);
            imageService.deleteImage((String) uid, "profile");
            userSearchInfo.setImagePath(null);

            if(userService.getUpdatePath(userSearchInfo)){
                setUpdatePath(session, userSearchInfo.getImagePath());
            }else{
                throw new SQLException();
            }

            return new ResponseEntity<>("삭제 완료", HttpStatus.OK);

        } catch (Exception e){

            return new ResponseEntity<>("삭제 실패", HttpStatus.BAD_REQUEST);
        }
    }
}

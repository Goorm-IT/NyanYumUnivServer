package com.nyanyumserver.nyanyumserver.Controller;

import com.nyanyumserver.nyanyumserver.Service.AdminService;
import com.nyanyumserver.nyanyumserver.VO.UserSearchInfo;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@RestController
@EnableSwagger2
@RequiredArgsConstructor
public class AdminController {


    private final AdminService adminService;

    @GetMapping("/nyu/admin/users")
    @ApiOperation(value = "전체 사용자 조회")
    public Object getAllUser(){

        UserSearchInfo userSearchInfo = new UserSearchInfo();

        try{
            adminService.getAllUser(userSearchInfo);
            return new ResponseEntity<>(userSearchInfo.getUserInfos(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("오류", HttpStatus.BAD_REQUEST);
        }

    }

}

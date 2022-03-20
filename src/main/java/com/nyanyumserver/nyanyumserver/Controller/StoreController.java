//package com.nyanyumserver.nyanyumserver.Controller;
//
//import com.nyanyumserver.nyanyumserver.Service.StoreService;
//import com.nyanyumserver.nyanyumserver.VO.PostSearchInfo;
//import com.nyanyumserver.nyanyumserver.VO.StoreSearchInfo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestParam;
//
//public class StoreController {
//
//    Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    StoreService storeService;
//
//    public Object getStoreList(){
//
//        StoreSearchInfo storeSearchInfo = new StoreSearchInfo();
//
//        try{
//            logger.debug("START.");
//
//            storeService.getStoreList(storeSearchInfo);
//
//        }catch (Exception e){
//
//        }
//    }
//}

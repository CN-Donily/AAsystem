package cn.com.liud.aa.controller;


import cn.com.liud.aa.entity.SubMsg;
import cn.com.liud.aa.pojo.BaseResult;
import cn.com.liud.aa.service.SubMsgService;
import cn.com.liud.aa.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Donily
 * @since 2019-09-23
 */
@Controller
@RequestMapping("/subMsg")
public class SubMsgController {
    @Autowired
    private UserService userService;
    @Autowired
    private SubMsgService subMsgService;

    /**
     * 保存提交的账单信息
     * @param costPerson
     * @param subMoney
     * @param aaPeopleStr
     * @return
     */
    @RequestMapping("/saveSubMsg")
    @ResponseBody
    public BaseResult saveSubMsg(String costPerson, String subMoney, String aaPeopleStr, String remark){
        SubMsg msg=new SubMsg();
        msg.setSubMoney(Double.valueOf(subMoney));
        msg.setSubUser(costPerson);
        msg.setAaPeople(aaPeopleStr);
        msg.setSubTime(new Date());
        msg.setRemark(remark);
        boolean save = subMsgService.save(msg);
        if(save){
            return BaseResult.success();
        }else{
            return BaseResult.error();
        }
    }

    /**
     * 显示AA制的计算结果
     * @return
     */
    @RequestMapping("/showAA")
    @ResponseBody
    public BaseResult<String> showAA(){
        //当周开始时间
        Calendar currentDate = new GregorianCalendar();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date startTime = currentDate.getTime();
        //当周结束时间
        currentDate = new GregorianCalendar();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date endTime = currentDate.getTime();
        //所有用户名
        List<String> userList=userService.getAllUserName();
        //存放结果的数组
        Double[] resultArr=new Double[userList.size()*userList.size()];
        for(int l=0;l<resultArr.length;l++){
            resultArr[l]=Double.valueOf(0);
        }
        //当周提交的所有aa付费信息
        List<SubMsg> msgList = subMsgService.getSubMsgFromStartToEndTime(startTime,endTime);
        //开始aa任务
        for(int i=0;i<msgList.size();i++){
            Double money=msgList.get(i).getSubMoney();
            String aaPeople=msgList.get(i).getAaPeople();
            String subUser=msgList.get(i).getSubUser();
            //subUser所在序号
            int m=0;
            for(int j=0;j<userList.size();j++){
                if(userList.get(j).equals(subUser)){
                    m=j;
                    break;
                }
            }
            String[] aaPeopleArr=aaPeople.split(",");
            for(String person:aaPeopleArr){
                //person所在序号
                int n=0;
                for(int k=0;k<userList.size();k++){
                    if(userList.get(k).equals(person)){
                        n=k;
                        break;
                    }
                }
                if(m==n){
                    resultArr[m*userList.size()+n]+=-money;
                }else{
                    resultArr[m*userList.size()+n]+=money/aaPeopleArr.length;
                    //m n对调表示对方应付金额
                    resultArr[n*userList.size()+m]+=-money/aaPeopleArr.length;
                }
            }
        }
        //拼接结果
        StringBuilder sb=new StringBuilder();
        int num=-1;
        for(int j=0;j<resultArr.length;j++){
            if(j%userList.size()==0){
                num++;
            }
            if(num==j%userList.size()){
                if(resultArr[j]==0){
                    sb.append(userList.get(num)+"自己花费"+r2(resultArr[j])+"元,\r\n");
                }else {
                    sb.append(userList.get(num) + "自己花费" + r2(-resultArr[j]) + "元,\r\n");
                }
            }else{
                sb.append(userList.get(num)+"应");
                if(resultArr[j]>Double.valueOf(0)){
                    sb.append("收");
                    sb.append(userList.get(j%userList.size())+r2(resultArr[j])+"元,\r\n");
                }else{
                    sb.append("给");
                    sb.append(userList.get(j%userList.size())+r2(-resultArr[j])+"元,\r\n");
                }

            }
        }
        return BaseResult.success(sb.toString());
    }


    public Double r2(Double f) {
        BigDecimal bg = new BigDecimal(f);
        /**
         * 参数：
         newScale - 要返回的 BigDecimal 值的标度。
         roundingMode - 要应用的舍入模式。
         返回：
         一个 BigDecimal，其标度为指定值，其非标度值可以通过此 BigDecimal 的非标度值乘以或除以十的适当次幂来确定。
         */
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    /**
     * 显示账单
     * @param model
     * @return
     */
    @RequestMapping("/getSubMsg")
    public String getSubMsg(Model model){
        //当周开始时间
        Calendar currentDate = new GregorianCalendar();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date startTime=currentDate.getTime();
        //当周结束时间
        currentDate = new GregorianCalendar();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date endTime=currentDate.getTime();

        //当周提交的所有aa付费信息
        List<SubMsg> msgList = subMsgService.getSubMsgFromStartToEndTime(startTime,endTime);
        model.addAttribute("msgList",msgList);
        return "detail";
    }

}


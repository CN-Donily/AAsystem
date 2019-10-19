package cn.com.liud.aa.service;

import cn.com.liud.aa.entity.SubMsg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Donily
 * @since 2019-09-23
 */
public interface SubMsgService extends IService<SubMsg> {

    List<SubMsg> getSubMsgFromStartToEndTime(Date startTime, Date endTime);
}

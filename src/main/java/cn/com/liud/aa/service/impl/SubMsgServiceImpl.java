package cn.com.liud.aa.service.impl;

import cn.com.liud.aa.entity.SubMsg;
import cn.com.liud.aa.mapper.SubMsgMapper;
import cn.com.liud.aa.service.SubMsgService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Donily
 * @since 2019-09-23
 */
@Service
public class SubMsgServiceImpl extends ServiceImpl<SubMsgMapper, SubMsg> implements SubMsgService {
    @Autowired
    private SubMsgMapper subMsgMapper;

    @Override
    public List<SubMsg> getSubMsgFromStartToEndTime(Date startTime, Date endTime) {
        QueryWrapper<SubMsg> wrapper = new QueryWrapper<>();
        wrapper.ge("sub_time",startTime).le("sub_time",endTime);
        return subMsgMapper.selectList(wrapper);
    }


}

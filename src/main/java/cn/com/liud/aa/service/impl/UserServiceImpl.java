package cn.com.liud.aa.service.impl;

import cn.com.liud.aa.entity.User;
import cn.com.liud.aa.mapper.UserMapper;
import cn.com.liud.aa.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<String> getAllUserName() {
        List<String> names = new ArrayList<>();
        List<User> users = userMapper.selectByMap(new HashMap<>());
        if (CollectionUtils.isNotEmpty(users)) {
            for (User user : users) {
                names.add(user.getUserName());
            }
        }
        return names;
    }
}

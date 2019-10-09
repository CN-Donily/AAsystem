package cn.com.liud.aa.service.impl;

import cn.com.liud.aa.entity.User;
import cn.com.liud.aa.mapper.UserMapper;
import cn.com.liud.aa.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

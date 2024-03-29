package cn.com.liud.aa.service;

import cn.com.liud.aa.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Donily
 * @since 2019-09-23
 */
public interface UserService extends IService<User> {

    List<String> getAllUserName();

}

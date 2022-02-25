package com.guojian.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guojian.server.config.security.JwtTokenUtil;
import com.guojian.server.mapper.AdminRoleMapper;
import com.guojian.server.mapper.RoleMapper;
import com.guojian.server.pojo.*;
import com.guojian.server.mapper.AdminMapper;
import com.guojian.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guojian.server.utils.AdminUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService
{
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 登陆之后返回Token
     * @param username 用户名
     * @param password 密码
     * @param code 校验码
     * @param request 登陆请求
     * @return 登陆Token
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request)
    {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if(StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code))
        {
            return RespBean.error("验证码输入错误");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(null==userDetails || !passwordEncoder.matches(password, userDetails.getPassword()))
        {
            return RespBean.error("用户名或者密码不正确");
        }
        if(!userDetails.isEnabled())
        {
            return RespBean.error("帐号被禁用，请联系管理员");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        return RespBean.success("登陆成功", tokenMap);
    }

    /**
     * 根据用户名获取用户类
     * @param username 用户名
     * @return 用户类
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username)
                .eq("enabled", true));
    }

    /**
     * 根据用户id获取角色列表
     * @param adminId 用户id
     * @return 角色列表
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    /**
     * 获取所有的操作员以及其角色
     * @return  操作员列表
     */
    @Override
    public List<Admin> getAllAdminsWithRole()
    {
        return adminMapper.getAllAdmins();
    }

    /**
     * 更新操作员的角色
     * @param adminId 操作员ID
     * @param roleIds 角色的ID数组
     * @return 更新结果
     */
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] roleIds)
    {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
        Integer result = adminRoleMapper.addRolesForAdmin(adminId, roleIds);
        if(roleIds.length == result)
        {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}

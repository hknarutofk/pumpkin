package xingkong.tool.core.web.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class AuthUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String wxOpenId;
    private Long uid;// 企业管理员用户ID
    private Long selfUid;// 用户ID 不区分企业
    private String mobile;
    private String nickName;
    private String password;
    private String name;
    private Integer sex;
    private String avatar;
    private Integer status;
    private String email;
    private Long personalId;// 用户账号id
    private Date lastLoginTime;
    private Date createTime;
    private Long enterpriseId;// 企业ID
    private String enterpriseName;// 企业名称
    private String profile;// 个人简介
    private String position;// 职位
    /**
     * 企业用户姓名
     */
    private String enterpriseUsername;
    private Set<GrantedAuthority> roles;// 用户角色
    private Set<GrantedAuthority> authorities;// 用户权限
    private boolean isOfEnterpriseUser = false;// 是否是企业用户
    private boolean hasEnterprise = false;// 是否关联企业
    private Boolean isEnterpriseLogin = false; // 是否是企业登录

    /**
     * 用户登陆系统类型 USER_TYPE_ADMIN 1 后台管理员 USER_TYPE_NORMAL 2 门户 USER_TYPE_ENTERPRISE 3 企业
     */
    private Integer userType;

    /**
     * 用户是否有原始密码
     */
    private Boolean isHasPassword;

    /**
     * 是否用户绑定微信
     */
    private Boolean isBindWechat;

    /**
     * 用户身份认证状态
     */
    private Integer identitificationStatus;

    /**
     * 真实的自身UID
     */
    private Long realSelfUid;

    public void setUid(Long uid) {
        this.uid = uid;
        this.id = uid;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = null;
        this.isBindWechat = !StringUtils.isEmpty(wxOpenId);
    }

    @Override
    public int hashCode() {
        // 自定义userInfo hashCode方法，session管理时会用做key
        return mobile.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AuthUser) {
            AuthUser userSessionVo = (AuthUser)obj;
            return new EqualsBuilder().append(userSessionVo.getId(), id).append(userSessionVo.getUid(), uid)
                .append(userSessionVo.getName(), name).isEquals();
        }
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return mobile;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

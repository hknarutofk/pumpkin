package xingkong.tool.core.web.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class AuthSession implements Serializable {
    private Long enterpriseId;// 企业ID
    Integer userType;
    private Long uid;// 企业管理员用户ID
    private Long selfUid;// 用户ID 不区分企业
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
    private String enterpriseName;// 企业名称
    /**
     * 企业用户姓名
     */
    private String enterpriseUsername;
    private String profile;// 个人简介
    private String position;// 职位
    /**
     * 真实的自身UID
     */
    private Long realSelfUid;
    private Boolean isEnterpriseLogin = false; // 是否是企业登录
    private boolean isOfEnterpriseUser = false;// 是否是企业用户
    private boolean hasEnterprise = false;// 是否关联企业

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
     * 区域
     */
    private String region;
}

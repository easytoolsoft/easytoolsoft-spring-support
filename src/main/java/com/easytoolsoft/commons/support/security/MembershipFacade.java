package com.easytoolsoft.commons.support.security;

import java.util.Set;

/**
 * 用户权限服务外观接口
 *
 * @param <User> 用户基本信息类
 * @author zhiwei.deng
 * @date 2017-03-25
 */
public interface MembershipFacade<User> {

    /**
     * @param account
     * @return
     */
    User getUser(String account);

    /**
     * @param roleIds
     * @return
     */
    String getRoleNames(String roleIds);

    /**
     * @param roleIds
     * @return
     */
    Set<String> getRoleSet(String roleIds);

    /**
     * @param roleIds
     * @return
     */
    Set<String> getPermissionSet(String roleIds);

    /**
     * @param roleIds
     * @param codes
     * @return
     */
    boolean hasPermission(String roleIds, String... codes);

    /**
     * @param roleIds
     * @return
     */
    boolean isAdministrator(String roleIds);
}

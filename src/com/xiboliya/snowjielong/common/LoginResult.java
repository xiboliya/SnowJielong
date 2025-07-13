/**
 * Copyright (C) 2025 冰原
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.xiboliya.snowjielong.common;

/**
 * 用于表示登录结果的枚举
 * 
 * @author 冰原
 * 
 */
public enum LoginResult {
  /**
   * 账号为空
   */
  USER_NAME_EMPTY,
  /**
   * 密码为空
   */
  PASSWORD_EMPTY,
  /**
   * 账号未注册
   */
  ACCOUNT_NOT_REGISTERED,
  /**
   * 密码错误
   */
  PASSWORD_WRONG,
  /**
   * 登录成功
   */
  SUCCESS;

  /**
   * 重写父类的方法
   */
  public String toString() {
    switch (this) {
    case USER_NAME_EMPTY:
      return "账号不能为空！";
    case PASSWORD_EMPTY:
      return "密码不能为空！";
    case ACCOUNT_NOT_REGISTERED:
      return "账号未注册！";
    case PASSWORD_WRONG:
      return "密码错误！";
    case SUCCESS:
      return "登录成功！";
    default:
      return "";
    }
  }

  /**
   * 是否登录成功
   * @return 是否登录成功，true表示登录成功，false反之
   */
  public boolean isSuccess() {
    switch (this) {
    case SUCCESS:
      return true;
    default:
      return false;
    }
  }
}

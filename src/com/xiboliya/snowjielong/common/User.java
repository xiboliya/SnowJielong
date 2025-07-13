/**
 * Copyright (C) 2024 冰原
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
 * 账号属性类
 * 
 * @author 冰原
 * 
 */
public class User {
  // 账号
  private String userName;
  // 密码
  private String password;
  // 找回密码问题
  private String retrievePasswordQuestion = "";
  // 找回密码答案
  private String retrievePasswordAnswer = "";
  // 进度存档
  public IdiomCache idiomCache = new IdiomCache();

  /**
   * 构造方法
   */
  public User() {
  }

  /**
   * 构造方法
   * 
   * @param userName 账号
   * @param password 密码
   */
  public User(String userName, String password) {
    this.setUserName(userName);
    this.setPassword(password);
  }

  /**
   * 构造方法
   * 
   * @param userName 账号
   * @param password 密码
   * @param retrievePasswordQuestion 找回密码问题
   * @param retrievePasswordAnswer 找回密码答案
   */
  public User(String userName, String password, String retrievePasswordQuestion, String retrievePasswordAnswer) {
    this.setUserName(userName);
    this.setPassword(password);
    this.setRetrievePasswordQuestion(retrievePasswordQuestion);
    this.setRetrievePasswordAnswer(retrievePasswordAnswer);
  }

  /**
   * 获取账号
   * 
   * @return 账号
   */
  public String getUserName() {
    return this.userName;
  }

  /**
   * 获取密码
   * 
   * @return 密码
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * 获取找回密码问题
   * 
   * @return 找回密码问题
   */
  public String getRetrievePasswordQuestion() {
    return this.retrievePasswordQuestion;
  }

  /**
   * 获取找回密码答案
   * 
   * @return 找回密码答案
   */
  public String getRetrievePasswordAnswer() {
    return this.retrievePasswordAnswer;
  }

  /**
   * 设置账号
   * 
   * @param userName 账号
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * 设置密码
   * 
   * @param password 密码
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * 设置找回密码问题
   * 
   * @param retrievePasswordQuestion 找回密码问题
   */
  public void setRetrievePasswordQuestion(String retrievePasswordQuestion) {
    this.retrievePasswordQuestion = retrievePasswordQuestion;
  }

  /**
   * 设置找回密码答案
   * 
   * @param retrievePasswordAnswer 找回密码答案
   */
  public void setRetrievePasswordAnswer(String retrievePasswordAnswer) {
    this.retrievePasswordAnswer = retrievePasswordAnswer;
  }
}

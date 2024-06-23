/**
 * Copyright (C) 2023 冰原
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

package com.xiboliya.snowjielong.setting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.xiboliya.snowjielong.common.BarrierOrder;
import com.xiboliya.snowjielong.common.User;
import com.xiboliya.snowjielong.util.Util;

/**
 * 用于解析和保存软件配置文件的工具类
 * 
 * @author 冰原
 * 
 */
public final class SettingAdapter {
  private Setting setting = null; // 软件参数配置类
  private URI uri = null; // 配置文件的URI
  private File file = null; // 配置文件

  /**
   * 带参数的构造方法
   * 
   * @param setting
   *          软件参数配置类
   */
  public SettingAdapter(Setting setting) {
    this.setSetting(setting);
    this.initSettingFile();
  }

  /**
   * 初始化配置文件
   */
  private void initSettingFile() {
    String dir = SettingAdapter.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    dir = new File(dir).getParent();
    dir = dir.replace(Util.FILE_SEPARATOR, "/"); // 将当前操作系统的文件分隔符统一替换为Unix/Linux风格，以避免在Windows系统下出现URI语法错误的问题。
    try {
      this.uri = new URI("file:///" + dir + "/" + Util.SETTING_FILE_NAME); // 使用URI来构建文件，避免出现由于路径中存在空格或中文所导致的错误
    } catch (URISyntaxException x) {
      // x.printStackTrace();
    }
    this.file = new File(this.uri);
  }

  /**
   * 设置软件参数配置类
   * 
   * @param setting
   *          软件参数配置类
   */
  public void setSetting(Setting setting) {
    this.setting = setting;
  }

  /**
   * 解析配置文件的方法
   */
  public void parse() {
    if (!this.file.exists()) {
      return;
    }
    String content = this.toOpenFile();
    if (!Util.isTextEmpty(content)) {
      this.parseSetting(content);
    }
  }

  /**
   * 获取配置文件文本
   * @return 配置文件文本
   */
  private String toOpenFile() {
    FileInputStream fileInputStream = null;
    InputStreamReader inputStreamReader = null;
    try {
      fileInputStream = new FileInputStream(this.file);
      StringBuilder stbTemp = new StringBuilder();
      inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
      char[] chrBuf = new char[Util.BUFFER_LENGTH];
      int len = 0;
      while ((len = inputStreamReader.read(chrBuf)) != -1) {
        stbTemp.append(chrBuf, 0, len);
      }
      String strTemp = stbTemp.toString();
      strTemp = strTemp.replaceAll("\r\n", "\n");
      strTemp = strTemp.replaceAll("\r", "\n");
      return strTemp;
    } catch (Exception x) {
      // x.printStackTrace();
    } finally {
      try {
        if (fileInputStream != null) {
          fileInputStream.close();
        }
        if (inputStreamReader != null) {
          inputStreamReader.close();
        }
      } catch (Exception x) {
        // x.printStackTrace();
      }
    }
    return "";
  }

  /**
   * 解析配置文件文本
   * @param content 配置文件文本
   */
  private void parseSetting(String content) {
    String[] arrUser = content.split(Util.SETTING_USER_SEPARATOR);
    for (String strUser : arrUser) {
      User user = this.parseUser(strUser);
      if (user != null) {
        this.setting.userList.add(user);
      }
    }
  }

  /**
   * 解析账号文本
   * @param strUser 账号文本
   * @return 账号
   */
  private User parseUser(String strUser) {
    String[] arrLine = strUser.split("\n");
    if (arrLine == null || arrLine.length <= 0) {
      return null;
    }
    User user = new User();
    for (String strLine : arrLine) {
      int index = strLine.indexOf("=");
      if (index <= 0) {
        continue;
      }
      String key = strLine.substring(0, index).trim();
      String value = strLine.substring(index + 1).trim();
      if (key.equals("userName") && !Util.isTextEmpty(value)) {
        user.setUserName(value);
      } else if (key.equals("password") && !Util.isTextEmpty(value)) {
        user.setPassword(value);
      } else if (key.equals("currentTopicLevel") && !Util.isTextEmpty(value)) {
        user.idiomCache.setCurrentTopicLevel(this.getNumber(value));
      } else if (key.equals("currentBarrierOrder") && !Util.isTextEmpty(value)) {
        user.idiomCache.setCurrentBarrierOrder(BarrierOrder.getItemByName(value));
      } else if (key.equals("currentBarrier") && !Util.isTextEmpty(value)) {
        user.idiomCache.setCurrentBarrier(this.getNumber(value));
      } else if (key.equals("currentBarrierFailTimes") && !Util.isTextEmpty(value)) {
        user.idiomCache.setCurrentBarrierFailTimes(this.getNumber(value));
      } else if (key.equals("isCurrentBarrierPassed") && !Util.isTextEmpty(value)) {
        user.idiomCache.setCurrentBarrierPassed(this.getBoolean(value));
      } else if (key.equals("totalScore") && !Util.isTextEmpty(value)) {
        user.idiomCache.setTotalScore(this.getNumber(value));
      } else if (key.equals("usedTime") && !Util.isTextEmpty(value)) {
        user.idiomCache.setUsedTime(this.getNumber(value));
      } else if (key.equals("passedBarrierCount") && !Util.isTextEmpty(value)) {
        user.idiomCache.setPassedBarrierCount(this.getNumber(value));
      } else if (key.equals("totalSubmitCount") && !Util.isTextEmpty(value)) {
        user.idiomCache.setTotalSubmitCount(this.getNumber(value));
      } else if (key.equals("totalRightCount") && !Util.isTextEmpty(value)) {
        user.idiomCache.setTotalRightCount(this.getNumber(value));
      } else if (key.equals("hintCount") && !Util.isTextEmpty(value)) {
        user.idiomCache.setHintCount(this.getNumber(value));
      } else if (key.equals("pauseCount") && !Util.isTextEmpty(value)) {
        user.idiomCache.setPauseCount(this.getNumber(value));
      } else if (key.equals("delayCount") && !Util.isTextEmpty(value)) {
        user.idiomCache.setDelayCount(this.getNumber(value));
      } else if (key.equals("energyCount") && !Util.isTextEmpty(value)) {
        user.idiomCache.setEnergyCount(this.getNumber(value));
      } else if (key.equals("energy") && !Util.isTextEmpty(value)) {
        user.idiomCache.setEnergy(this.getNumber(value));
      } else if (key.equals("startTimeMillis") && !Util.isTextEmpty(value)) {
        user.idiomCache.setStartTimeMillis(this.getLongNumber(value));
      } else if (key.equals("starMap") && !Util.isTextEmpty(value)) {
        user.idiomCache.setStarMap(this.getHashMap(value));
      }
    }
    return user;
  }

  /**
   * 将文本转换为数字
   * @param value 文本
   * @return 数字
   */
  private int getNumber(String value) {
    int number = 0;
    try {
      number = Integer.parseInt(value);
    } catch (Exception x) {
      // x.printStackTrace();
    }
    return number;
  }

  /**
   * 将文本转换为布尔值
   * @param value 文本
   * @return 布尔值
   */
  private boolean getBoolean(String value) {
    boolean logic = false;
    if (value.equalsIgnoreCase("true")) {
      logic = true;
    }
    return logic;
  }

  /**
   * 将文本转换为长整数
   * @param value 文本
   * @return 长整数
   */
  private long getLongNumber(String value) {
    long longNumber = 0L;
    try {
      longNumber = Long.parseLong(value);
    } catch (Exception x) {
      // x.printStackTrace();
    }
    return longNumber;
  }

  /**
   * 将文本转换为map
   * @param value 文本
   * @return map
   */
  private HashMap<String, Integer> getHashMap(String value) {
    String[] aarValue = value.replace(" ", "").split(",");
    if (aarValue == null || aarValue.length <= 0) {
      return null;
    }
    HashMap<String, Integer> starMap = new HashMap<String, Integer>();
    for (String item : aarValue) {
      int index = item.indexOf(":");
      String name = item.substring(0, index);
      int count = this.getNumber(item.substring(index + 1));
      starMap.put(name, count);
    }
    return starMap;
  }

  /**
   * 注册账号
   * @param userName 账号
   * @param password 密码
   * @return 是否注册成功，true表示注册成功，false反之
   */
  public boolean register(String userName, String password) {
    if (Util.isTextEmpty(userName) || Util.isTextEmpty(password)) {
      return false;
    }
    if (this.isUserExist(userName)) {
      return false;
    }
    User user = new User(userName, password);
    this.setting.userList.add(user);
    String content = this.getContents(this.setting.userList);
    this.toSaveFile(content);
    return true;
  }

  /**
   * 登录账号
   * @param userName 账号
   * @param password 密码
   * @return 是否登录成功，true表示登录成功，false反之
   */
  public boolean login(String userName, String password) {
    if (Util.isTextEmpty(userName) || Util.isTextEmpty(password)) {
      return false;
    }
    User user = this.getUser(userName);
    if (user == null) {
      return false;
    }
    String strPassword = user.getPassword();
    if (password.equals(strPassword)) {
      this.setting.user = user;
      return true;
    }
    return false;
  }

  /**
   * 账号是否已存在
   * @param userName 账号
   * @return 账号是否已存在，true表示账号已存在，false反之
   */
  private boolean isUserExist(String userName) {
    if (this.setting.userList.isEmpty()) {
      return false;
    }
    for (User user : this.setting.userList) {
      if (userName.equals(user.getUserName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * 将账号列表转换为文本
   * @param userList 账号列表
   * @return 账号列表的文本
   */
  private String getContents(ArrayList<User> userList) {
    StringBuilder stbResult = new StringBuilder();
    for (User user : userList) {
      stbResult.append(this.getContent(user));
    }
    return stbResult.toString();
  }

  /**
   * 将账号转换为文本
   * @param user 账号
   * @return 账号的文本
   */
  private String getContent(User user) {
    StringBuilder stbResult = new StringBuilder();
    stbResult.append("userName=" + user.getUserName() + "\n");
    stbResult.append("password=" + user.getPassword() + "\n");
    stbResult.append("currentTopicLevel=" + user.idiomCache.getCurrentTopicLevel() + "\n");
    stbResult.append("currentBarrierOrder=" + user.idiomCache.getCurrentBarrierOrder() + "\n");
    stbResult.append("currentBarrier=" + user.idiomCache.getCurrentBarrier() + "\n");
    stbResult.append("currentBarrierFailTimes=" + user.idiomCache.getCurrentBarrierFailTimes() + "\n");
    stbResult.append("isCurrentBarrierPassed=" + String.valueOf(user.idiomCache.isCurrentBarrierPassed()) + "\n");
    stbResult.append("totalScore=" + user.idiomCache.getTotalScore() + "\n");
    stbResult.append("usedTime=" + user.idiomCache.getUsedTime() + "\n");
    stbResult.append("passedBarrierCount=" + user.idiomCache.getPassedBarrierCount() + "\n");
    stbResult.append("totalSubmitCount=" + user.idiomCache.getTotalSubmitCount() + "\n");
    stbResult.append("totalRightCount=" + user.idiomCache.getTotalRightCount() + "\n");
    stbResult.append("hintCount=" + user.idiomCache.getHintCount() + "\n");
    stbResult.append("pauseCount=" + user.idiomCache.getPauseCount() + "\n");
    stbResult.append("delayCount=" + user.idiomCache.getDelayCount() + "\n");
    stbResult.append("energyCount=" + user.idiomCache.getEnergyCount() + "\n");
    stbResult.append("energy=" + user.idiomCache.getEnergy() + "\n");
    stbResult.append("startTimeMillis=" + String.valueOf(user.idiomCache.getStartTimeMillis()) + "\n");
    stbResult.append("starMap=" + this.getStarMapText(user.idiomCache.getStarMap()) + "\n");
    stbResult.append(Util.SETTING_USER_SEPARATOR);
    return stbResult.toString();
  }

  /**
   * 将群星图map转换为文本
   * @param starMap 群星图map
   * @return 群星图map的文本
   */
  private String getStarMapText(HashMap<String, Integer> starMap) {
    StringBuilder value = new StringBuilder();
    if (starMap != null && !starMap.isEmpty()) {
      for (String key : starMap.keySet()) {
        value.append(key).append(":").append(starMap.get(key)).append(",");
      }
      value.deleteCharAt(value.length() - 1);
    }
    return value.toString();
  }

  /**
   * 保存配置文件
   * @param content 配置文件文本
   */
  private void toSaveFile(String content) {
    FileOutputStream fileOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream(this.file);
      byte[] byteStr = content.getBytes("UTF-8");
      fileOutputStream.write(byteStr);
    } catch (Exception x) {
      // x.printStackTrace();
    } finally {
      try {
        fileOutputStream.flush();
        fileOutputStream.close();
      } catch (Exception x) {
        // x.printStackTrace();
      }
    }
  }

  /**
   * 获取账号
   * @param userName 用户名
   * @return 账号
   */
  private User getUser(String userName) {
    if (this.setting.userList.isEmpty()) {
      return null;
    }
    for (User user : this.setting.userList) {
      if (userName.equals(user.getUserName())) {
        return user;
      }
    }
    return null;
  }

  /**
   * 将软件设置保存到配置文件的方法
   */
  public void save() {
    String content = this.getContents(this.setting.userList);
    this.toSaveFile(content);
  }

}

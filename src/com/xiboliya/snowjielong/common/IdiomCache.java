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

package com.xiboliya.snowjielong.common;

/**
 * 成语接龙游戏的进度存档类
 * 
 * @author 冰原
 * 
 */
public class IdiomCache {
  // 当前难度等级
  private int currentTopicLevel;
  // 当前关卡
  private int currentBarrier;
  // 当前关卡闯关失败的次数
  private int currentBarrierFailTimes;
  // 当前关卡是否通过
  private boolean isCurrentBarrierPassed;
  // 累计得分
  private int totalScore;
  // 累计用时，单位：秒
  private int usedTime;
  // 累计通过关卡数
  private int passedBarrierCount;
  // 可用的提示次数，默认为3次
  private int hintCount = 3;

  /**
   * 获取当前难度等级
   * 
   * @return 当前难度等级
   */
  public int getCurrentTopicLevel() {
    return this.currentTopicLevel;
  }

  /**
   * 设置当前难度等级
   * 
   * @param currentTopicLevel 当前难度等级
   */
  public void setCurrentTopicLevel(int currentTopicLevel) {
    this.currentTopicLevel = currentTopicLevel;
  }

  /**
   * 获取当前关卡
   * 
   * @return 当前关卡
   */
  public int getCurrentBarrier() {
    return this.currentBarrier;
  }

  /**
   * 设置当前关卡
   * 
   * @param currentBarrier 当前关卡
   */
  public void setCurrentBarrier(int currentBarrier) {
    this.currentBarrier = currentBarrier;
  }

  /**
   * 获取当前关卡闯关失败的次数
   * 
   * @return 当前关卡闯关失败的次数
   */
  public int getCurrentBarrierFailTimes() {
    return this.currentBarrierFailTimes;
  }

  /**
   * 设置当前关卡闯关失败的次数
   * 
   * @param currentBarrierFailTimes 当前关卡闯关失败的次数
   */
  public void setCurrentBarrierFailTimes(int currentBarrierFailTimes) {
    this.currentBarrierFailTimes = currentBarrierFailTimes;
  }

  /**
   * 获取当前关卡是否通过
   * 
   * @return 当前关卡是否通过
   */
  public boolean isCurrentBarrierPassed() {
    return this.isCurrentBarrierPassed;
  }

  /**
   * 设置当前关卡是否通过
   * 
   * @param isCurrentBarrierPassed 当前关卡是否通过
   */
  public void setCurrentBarrierPassed(boolean isCurrentBarrierPassed) {
    this.isCurrentBarrierPassed = isCurrentBarrierPassed;
  }

  /**
   * 获取累计得分
   * 
   * @return 累计得分
   */
  public int getTotalScore() {
    return this.totalScore;
  }

  /**
   * 设置累计得分
   * 
   * @param totalScore 累计得分
   */
  public void setTotalScore(int totalScore) {
    this.totalScore = totalScore;
  }

  /**
   * 获取累计用时
   * 
   * @return 累计用时
   */
  public int getUsedTime() {
    return this.usedTime;
  }

  /**
   * 设置累计用时
   * 
   * @param usedTime 累计用时
   */
  public void setUsedTime(int usedTime) {
    this.usedTime = usedTime;
  }

  /**
   * 获取累计通过关卡数
   * 
   * @return 累计通过关卡数
   */
  public int getPassedBarrierCount() {
    return this.passedBarrierCount;
  }

  /**
   * 设置累计通过关卡数
   * 
   * @param passedBarrierCount 累计通过关卡数
   */
  public void setPassedBarrierCount(int passedBarrierCount) {
    this.passedBarrierCount = passedBarrierCount;
  }

  /**
   * 获取可用的提示次数
   * 
   * @return 可用的提示次数
   */
  public int getHintCount() {
    return this.hintCount;
  }

  /**
   * 设置可用的提示次数
   * 
   * @param hintCount 可用的提示次数
   */
  public void setHintCount(int hintCount) {
    this.hintCount = hintCount;
  }
}

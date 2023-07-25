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
  private int topicLevel;
  // 当前关卡
  private int barrier;
  // 当前关卡闯关失败的次数
  private int barrierFailTimes;
  // 当前关卡是否通过
  private boolean isBarrierPassed;
  // 累计得分
  private int score;
  // 累计用时，单位：秒
  private int time;
  // 累计通过关卡数
  private int passedBarrierCount;

  /**
   * 获取当前难度等级
   * 
   * @return 当前难度等级
   */
  public int getTopicLevel() {
    return this.topicLevel;
  }

  /**
   * 获取当前关卡
   * 
   * @return 当前关卡
   */
  public int getBarrier() {
    return this.barrier;
  }

  /**
   * 获取当前关卡闯关失败的次数
   * 
   * @return 当前关卡闯关失败的次数
   */
  public int getBarrierFailTimes() {
    return this.barrierFailTimes;
  }

  /**
   * 获取当前关卡是否通过
   * 
   * @return 当前关卡是否通过
   */
  public boolean isBarrierPassed() {
    return this.isBarrierPassed;
  }

  /**
   * 获取累计得分
   * 
   * @return 累计得分
   */
  public int getScore() {
    return this.score;
  }

  /**
   * 获取累计用时
   * 
   * @return 累计用时
   */
  public int getTime() {
    return this.time;
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
   * 设置当前难度等级
   * 
   * @param topicLevel 当前难度等级
   */
  public void setTopicLevel(int topicLevel) {
    this.topicLevel = topicLevel;
  }

  /**
   * 设置当前关卡
   * 
   * @param barrier 当前关卡
   */
  public void setBarrier(int barrier) {
    this.barrier = barrier;
  }

  /**
   * 设置当前关卡闯关失败的次数
   * 
   * @param barrier 当前关卡闯关失败的次数
   */
  public void setBarrierFailTimes(int barrierFailTimes) {
    this.barrierFailTimes = barrierFailTimes;
  }

  /**
   * 设置当前关卡是否通过
   * 
   * @param barrier 当前关卡是否通过
   */
  public void setBarrierPassed(boolean isBarrierPassed) {
    this.isBarrierPassed = isBarrierPassed;
  }

  /**
   * 设置累计得分
   * 
   * @param score 累计得分
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * 设置累计用时
   * 
   * @param time 累计用时
   */
  public void setTime(int time) {
    this.time = time;
  }

  /**
   * 设置累计通过关卡数
   * 
   * @param passedBarrierCount 累计通过关卡数
   */
  public void setPassedBarrierCount(int passedBarrierCount) {
    this.passedBarrierCount = passedBarrierCount;
  }
}

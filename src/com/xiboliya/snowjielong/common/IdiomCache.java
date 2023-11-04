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
  // 当前难度等级的关卡顺序
  private BarrierOrder currentBarrierOrder;
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
  // 累计备选答题次数
  private int totalSubmitCount;
  // 累计备选答对次数
  private int totalRightCount;
  // 可用的提示卡数量，默认为3张
  private int hintCount = 3;
  // 可用的暂停卡数量，默认为3张
  private int pauseCount = 3;
  // 可用的延时卡数量，默认为3张
  private int delayCount = 3;
  // 可用的体力卡数量，默认为3张
  private int energyCount = 3;
  // 可用的体力，默认为100
  private int energy = 100;
  // 一天内第一次闯关的时间戳
  private long startTimeMillis;

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
   * 获取当前难度等级的关卡顺序
   * 
   * @return 当前难度等级的关卡顺序
   */
  public BarrierOrder getCurrentBarrierOrder() {
    return this.currentBarrierOrder;
  }

  /**
   * 设置当前难度等级的关卡顺序
   * 
   * @param currentBarrierOrder 当前难度等级的关卡顺序
   */
  public void setCurrentBarrierOrder(BarrierOrder currentBarrierOrder) {
    this.currentBarrierOrder = currentBarrierOrder;
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
   * 获取累计备选答题次数
   * 
   * @return 累计备选答题次数
   */
  public int getTotalSubmitCount() {
    return this.totalSubmitCount;
  }

  /**
   * 设置累计备选答题次数
   * 
   * @param totalSubmitCount 累计备选答题次数
   */
  public void setTotalSubmitCount(int totalSubmitCount) {
    this.totalSubmitCount = totalSubmitCount;
  }

  /**
   * 获取累计备选答对次数
   * 
   * @return 累计备选答对次数
   */
  public int getTotalRightCount() {
    return this.totalRightCount;
  }

  /**
   * 设置累计备选答对次数
   * 
   * @param totalSubmitCount 累计备选答对次数
   */
  public void setTotalRightCount(int totalRightCount) {
    this.totalRightCount = totalRightCount;
  }

  /**
   * 获取可用的提示卡数量
   * 
   * @return 可用的提示卡数量
   */
  public int getHintCount() {
    return this.hintCount;
  }

  /**
   * 设置可用的提示卡数量
   * 
   * @param hintCount 可用的提示卡数量
   */
  public void setHintCount(int hintCount) {
    this.hintCount = hintCount;
  }

  /**
   * 获取可用的暂停卡数量
   * 
   * @return 可用的暂停卡数量
   */
  public int getPauseCount() {
    return this.pauseCount;
  }

  /**
   * 设置可用的暂停卡数量
   * 
   * @param pauseCount 可用的暂停卡数量
   */
  public void setPauseCount(int pauseCount) {
    this.pauseCount = pauseCount;
  }

  /**
   * 获取可用的延时卡数量
   * 
   * @return 可用的延时卡数量
   */
  public int getDelayCount() {
    return this.delayCount;
  }

  /**
   * 设置可用的延时卡数量
   * 
   * @param delayCount 可用的延时卡数量
   */
  public void setDelayCount(int delayCount) {
    this.delayCount = delayCount;
  }

  /**
   * 获取可用的体力卡数量
   * 
   * @return 可用的体力卡数量
   */
  public int getEnergyCount() {
    return this.energyCount;
  }

  /**
   * 设置可用的体力卡数量
   * 
   * @param energyCount 可用的体力卡数量
   */
  public void setEnergyCount(int energyCount) {
    this.energyCount = energyCount;
  }

  /**
   * 获取可用的体力
   * 
   * @return 可用的体力
   */
  public int getEnergy() {
    return this.energy;
  }

  /**
   * 设置可用的体力
   * 
   * @param energy 可用的体力
   */
  public void setEnergy(int energy) {
    this.energy = energy;
  }

  /**
   * 获取一天内第一次闯关的时间戳
   * 
   * @return 一天内第一次闯关的时间戳
   */
  public long getStartTimeMillis() {
    return this.startTimeMillis;
  }

  /**
   * 设置一天内第一次闯关的时间戳
   * 
   * @param startTimeMillis 一天内第一次闯关的时间戳
   */
  public void setStartTimeMillis(long startTimeMillis) {
    this.startTimeMillis = startTimeMillis;
  }
}

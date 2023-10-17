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

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.xiboliya.snowjielong.util.Util;

/**
 * 用于解析和保存软件配置文件的工具类
 * 
 * @author 冰原
 * 
 */
public final class SettingAdapter {
  private Setting setting = null; // 软件参数配置类
  private URI uri = null; // XML配置文件的URI
  private File file = null; // XML配置文件

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
   * 初始化XML配置文件
   */
  private void initSettingFile() {
    String dir = SettingAdapter.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    dir = new File(dir).getParent();
    dir = dir.replace(Util.FILE_SEPARATOR, "/"); // 将当前操作系统的文件分隔符统一替换为Unix/Linux风格，以避免在Windows系统下出现URI语法错误的问题。
    try {
      this.uri = new URI("file:///" + dir + "/" + Util.SETTING_XML); // 使用URI来构建文件，避免出现由于路径中存在空格或中文所导致的错误
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
   * 解析XML配置文件的方法
   */
  public void parse() {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      if (!this.file.exists()) {
        return;
      }
      Document document = builder.parse(this.file);
      Element root = document.getDocumentElement();
      NodeList nodeList = root.getElementsByTagName("Idiom");
      parseIdiom(nodeList);
    } catch (Exception x) {
      // x.printStackTrace();
    }
  }

  /**
   * Idiom节点的解析方法
   * 
   * @param nodeList
   *          节点列表
   */
  private void parseIdiom(NodeList nodeList) {
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node node = nodeList.item(i);
      if (node.hasChildNodes()) {
        NodeList list = node.getChildNodes();
        parseIdiom(list); // 递归调用
      } else if (node.getNodeType() == Node.TEXT_NODE) {
        String key = node.getParentNode().getNodeName();
        String value = node.getTextContent().trim();
        if (!Util.isTextEmpty(value)) {
          int number = 0;
          try {
            number = Integer.parseInt(value);
          } catch (NumberFormatException x) {
            // x.printStackTrace();
          }
          if (key.equalsIgnoreCase("currentTopicLevel")) {
            this.setting.idiomCache.setCurrentTopicLevel(number);
          } else if (key.equalsIgnoreCase("currentBarrier")) {
            this.setting.idiomCache.setCurrentBarrier(number);
          } else if (key.equalsIgnoreCase("currentBarrierFailTimes")) {
            this.setting.idiomCache.setCurrentBarrierFailTimes(number);
          } else if (key.equalsIgnoreCase("totalScore")) {
            this.setting.idiomCache.setTotalScore(number);
          } else if (key.equalsIgnoreCase("usedTime")) {
            this.setting.idiomCache.setUsedTime(number);
          } else if (key.equalsIgnoreCase("passedBarrierCount")) {
            this.setting.idiomCache.setPassedBarrierCount(number);
          } else if (key.equalsIgnoreCase("totalSubmitCount")) {
            this.setting.idiomCache.setTotalSubmitCount(number);
          } else if (key.equalsIgnoreCase("totalRightCount")) {
            this.setting.idiomCache.setTotalRightCount(number);
          } else if (key.equalsIgnoreCase("hintCount")) {
            this.setting.idiomCache.setHintCount(number);
          } else if (key.equalsIgnoreCase("pauseCount")) {
            this.setting.idiomCache.setPauseCount(number);
          } else if (key.equalsIgnoreCase("delayCount")) {
            this.setting.idiomCache.setDelayCount(number);
          } else if (key.equalsIgnoreCase("isCurrentBarrierPassed")) {
            boolean logic = false;
            if (value.equalsIgnoreCase("true")) {
              logic = true;
            }
            this.setting.idiomCache.setCurrentBarrierPassed(logic);
          }
        }
      }
    }
  }

  /**
   * 将软件设置保存到XML配置文件的方法
   */
  public void save() {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      URL url = ClassLoader.getSystemResource("res/" + Util.SETTING_XML);
      Document document = builder.parse(url.openStream());
      Element root = document.getDocumentElement();
      NodeList nodeList = root.getElementsByTagName("Idiom");
      saveIdiom(nodeList);
      // 以下操作最终将数据写入到硬盘文件中
      TransformerFactory tff = TransformerFactory.newInstance();
      Transformer tf = tff.newTransformer();
      DOMSource ds = new DOMSource(document);
      StreamResult sr = new StreamResult(this.file);
      tf.transform(ds, sr);
    } catch (Exception x) {
      // x.printStackTrace();
    }
  }

  /**
   * Idiom节点的保存方法
   * 
   * @param nodeList
   *          节点列表
   */
  private void saveIdiom(NodeList nodeList) {
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node node = nodeList.item(i);
      if (node.hasChildNodes()) {
        NodeList list = node.getChildNodes();
        saveIdiom(list); // 递归调用
      } else if (node.getNodeType() == Node.TEXT_NODE) {
        String key = node.getParentNode().getNodeName();
        if (key.equalsIgnoreCase("currentTopicLevel")) {
          node.setTextContent(String.valueOf(this.setting.idiomCache.getCurrentTopicLevel()));
        } else if (key.equalsIgnoreCase("currentBarrier")) {
          node.setTextContent(String.valueOf(this.setting.idiomCache.getCurrentBarrier()));
        } else if (key.equalsIgnoreCase("currentBarrierFailTimes")) {
          node.setTextContent(String.valueOf(this.setting.idiomCache.getCurrentBarrierFailTimes()));
        } else if (key.equalsIgnoreCase("totalScore")) {
          node.setTextContent(String.valueOf(this.setting.idiomCache.getTotalScore()));
        } else if (key.equalsIgnoreCase("usedTime")) {
          node.setTextContent(String.valueOf(this.setting.idiomCache.getUsedTime()));
        } else if (key.equalsIgnoreCase("passedBarrierCount")) {
          node.setTextContent(String.valueOf(this.setting.idiomCache.getPassedBarrierCount()));
        } else if (key.equalsIgnoreCase("totalSubmitCount")) {
          node.setTextContent(String.valueOf(this.setting.idiomCache.getTotalSubmitCount()));
        } else if (key.equalsIgnoreCase("totalRightCount")) {
          node.setTextContent(String.valueOf(this.setting.idiomCache.getTotalRightCount()));
        } else if (key.equalsIgnoreCase("hintCount")) {
          node.setTextContent(String.valueOf(this.setting.idiomCache.getHintCount()));
        } else if (key.equalsIgnoreCase("pauseCount")) {
          node.setTextContent(String.valueOf(this.setting.idiomCache.getPauseCount()));
        } else if (key.equalsIgnoreCase("delayCount")) {
          node.setTextContent(String.valueOf(this.setting.idiomCache.getDelayCount()));
        } else if (key.equalsIgnoreCase("isCurrentBarrierPassed")) {
          node.setTextContent(String.valueOf(this.setting.idiomCache.isCurrentBarrierPassed()));
        }
      }
    }
  }

  /**
   * 删除XML配置文件
   */
  public void deleteSettingFile() {
    if (this.file.exists()) {
      this.file.delete();
    }
  }
}

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

package com.xiboliya.snowjielong.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.xiboliya.snowjielong.base.BaseButton;
import com.xiboliya.snowjielong.base.BaseDialog;
import com.xiboliya.snowjielong.base.BaseKeyAdapter;

/**
 * "游戏规则"对话框
 * 
 * @author 冰原
 */
public class RulesDialog extends BaseDialog implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JPanel pnlMain = (JPanel) this.getContentPane();
  private JTextArea txaMain = new JTextArea();
  private BaseButton btnOk = new BaseButton("确定");
  private BaseKeyAdapter keyAdapter = new BaseKeyAdapter(this);
  private BaseKeyAdapter buttonKeyAdapter = new BaseKeyAdapter(this, false);

  /**
   * 构造方法
   * 
   * @param owner
   *          用于显示该对话框的父组件
   * @param modal
   *          是否为模式对话框
   */
  public RulesDialog(JFrame owner, boolean modal) {
    super(owner, modal);
    this.init();
    this.initView();
    this.addListeners();
    this.setSize(550, 450);
    this.setResizable(true);
    this.setVisible(true);
  }

  /**
   * 初始化界面
   */
  private void init() {
    this.setTitle("游戏规则");
    this.txaMain.setLineWrap(true);
    this.pnlMain.add(new JScrollPane(this.txaMain), BorderLayout.CENTER);
    this.pnlMain.add(this.btnOk, BorderLayout.SOUTH);
  }

  private void initView() {
    this.txaMain.setEditable(false);
    StringBuilder stbContent = new StringBuilder();
    stbContent.append("成语接龙游戏规则\n\n");
    stbContent.append("游戏共有5个难度等级：初级、中级、高级、特级、顶级，各等级都有200关。\n");
    stbContent.append("【初级】成语数为：2个，空格数为：1个，选项数为：4个，每轮答题次数为：2次，每轮答题时间为20秒，每关最高得分为：4分。\n");
    stbContent.append("【中级】成语数为：3个，空格数为：2个，选项数为：6个，每轮答题次数为：4次，每轮答题时间为30秒，每关最高得分为：6分。\n");
    stbContent.append("【高级】成语数为：4个，空格数为：3个，选项数为：8个，每轮答题次数为：6次，每轮答题时间为40秒，每关最高得分为：8分。\n");
    stbContent.append("【特级】成语数为：5个，空格数为：4个，选项数为：10个，每轮答题次数为：8次，每轮答题时间为50秒，每关最高得分为：10分。\n");
    stbContent.append("【顶级】成语数为：6个，空格数为：5个，选项数为：12个，每轮答题次数为：10次，每轮答题时间为60秒，每关最高得分为：12分。\n\n");
    stbContent.append("每关的每轮答题时间固定，超过时间未作答或超过答题次数的话，则本轮闯关失败。\n");
    stbContent.append("闯关失败的情况下，可以点击“重新闯关”按钮，开启下一轮闯关。\n");
    stbContent.append("每关非首轮闯关，即使答对也不得分。\n\n");
    stbContent.append("累计分数为每关闯关成功的得分之和，只增不减。\n\n");
    stbContent.append("累计用时为所有关卡的答题时间总数，只增不减。\n\n");
    stbContent.append("头衔为体现累计分数高低的表示方法，头衔名称灵感来自于中国古代科举考试的制度。由低到高共分为13个等级：\n");
    stbContent.append("童生(0分)、秀才(60分)、附生(180分)、增生(360分)、廪生(600分)、举人(900分)、解元(1260分)、");
    stbContent.append("贡士(1680分)、会元(2160分)、进士(2700分)、探花(3300分)、榜眼(3960分)、状元(4680分)。\n");
    this.txaMain.setText(stbContent.toString());
    this.txaMain.setCaretPosition(0);
  }

  /**
   * 添加事件监听器
   */
  private void addListeners() {
    this.txaMain.addKeyListener(this.keyAdapter);
    this.btnOk.addActionListener(this);
    this.btnOk.addKeyListener(this.buttonKeyAdapter);
  }

  /**
   * 为各组件添加事件的处理方法
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (this.btnOk.equals(source)) {
      this.onEnter();
    }
  }

  /**
   * 默认的"确定"操作方法
   */
  @Override
  public void onEnter() {
    this.dispose();
  }

  /**
   * 默认的"取消"操作方法
   */
  @Override
  public void onCancel() {
    this.dispose();
  }
}

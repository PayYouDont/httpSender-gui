package com.payudon.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.payudon.listener.MyFocusListener;
import com.payudon.listener.MyMouseListener;
import com.payudon.util.HttpClientUtil;

/** 
* @ClassName: HttpSendJFrame 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月20日 下午2:54:06 
*  
*/
@org.springframework.stereotype.Component
public class HttpSendJFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static void setJPanel(JPanel panel) {
		panel.setLayout(null);
		JLabel urlLabel = new JLabel("请求路径:");
		urlLabel.setBounds(10, 20, 80, 25);
		panel.add(urlLabel);
		//路径输入框
		JTextField urlText = new JTextField("http://localhost:8090/nodeAction/upload");
		urlText.setBorder(getBorder(getColor(51, 135, 255)));
		urlText.setBounds(100, 20, 300, 25);
		urlText.setName("urlText");
		panel.add(urlText);
		
		JLabel pathLabel = new JLabel("资源路径:");
		pathLabel.setBounds(10, 50, 80, 25);
		panel.add(pathLabel);
		//tar包路径框
		JTextField pathText = new JTextField();
		pathText.setBounds(100, 50, 300, 25);
		pathText.setName("pathText");
		panel.add(pathText);
		
		
		JLabel resultLabel = new JLabel("返回:");
		resultLabel.setBounds(10, 80, 80, 25);
		panel.add(resultLabel);
		//返回结果框
		JTextArea resultArea = new JTextArea();
		resultArea.setBounds(100, 80, 300, 150);
		resultArea.setBorder(getBorder(Color.gray));
		resultArea.setName("resultArea");
		panel.add(resultArea);
		
		JButton sendButton = new JButton("send");
		sendButton.setBounds(420, 20, 80, 25);
		sendButton.setFocusable(false);
		panel.add(sendButton);
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				send(panel);
			}
		});
		addFocusListener(panel);
		addMouseListener(panel);
	}
	
	public static void send(JPanel panel) {
		String url = ((JTextField)getCompentByName(panel, "urlText")).getText();
		String path = ((JTextField)getCompentByName(panel, "pathText")).getText();
		if(!"".equals(url)&&!"".equals(path)) {
			try {
				String result = HttpClientUtil.sendPostTar(url, path,"C:\\Users\\47111\\Desktop");
				((JTextArea)getCompentByName(panel, "resultArea")).setText(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static Component getCompentByName(JPanel panel,String name) {
		Component[] components= panel.getComponents();
		for (int i = 0; i < components.length; i++) {
			Component c = components[i];
			if(name.equals(c.getName())) {
				return c;
			}
		}
		return null;
	}
	public static void addFocusListener(JPanel panel){
		Component[] components= panel.getComponents();
		MyFocusListener fl = new MyFocusListener();
		for (int i = 0; i < components.length; i++) {
			Component c = components[i];
			c.addFocusListener(fl);
		}
	}
	public static void addMouseListener(JPanel panel){
		Component[] components= panel.getComponents();
		MyMouseListener fl = new MyMouseListener();
		for (int i = 0; i < components.length; i++) {
			Component c = components[i];
			c.addMouseListener(fl);
		}
	}
	public static Border getBorder(Color color) {
		Border border = BorderFactory.createLineBorder(color);
		return border;
	}
	public static Color getColor(int r,int g,int b) {
		Color color = new Color(r, g, b);
		return color;
	}
	
	public static void changeColor(MouseEvent e,JTextField urlText) {
		int index = e.getButton();
		if(index==MouseEvent.BUTTON1) {//鼠标左键按下
			urlText.setBorder(getBorder(getColor(51, 135, 255)));
		}
	}
	public void start() {
		JFrame frame = new JFrame("PostSender");
		frame.setSize(570, 300);
		//关闭窗体后终止程序
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.dispose();
		//创建面板，这个类似于 HTML 的 div 标签
		JPanel div = new JPanel();
		setJPanel(div);
		// 设置界面可见
		frame.add(div);
		frame.setVisible(true);
		// Frame在窗体居中
		frame.setLocationRelativeTo(null); 
	}
}

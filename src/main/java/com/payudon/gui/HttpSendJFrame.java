package com.payudon.gui;

import com.payudon.common.config.ApplicationContextRegister;
import com.payudon.common.config.ServerProperties;
import com.payudon.common.xml.base.EBD;
import com.payudon.common.xml.other.EBD_ConnectionCheck;
import com.payudon.common.xml.other.EBD_EBD;
import com.payudon.common.xml.other.EBD_EBMBrdLog;
import com.payudon.common.xml.other.EBD_Type;
import com.payudon.common.xml.request.EBD_OMDRequest;
import com.payudon.listener.MyFocusListener;
import com.payudon.listener.MyMouseListener;
import com.payudon.util.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Date;

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
	private static JPanel panel = new JPanel ();
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setJPanel(JPanel panel) {
		panel.setLayout(null);
		JLabel urlLabel = new JLabel("请求路径:");
		urlLabel.setBounds(10, 20, 80, 25);
		panel.add(urlLabel);
		//路径输入框
		JTextField urlText = new JTextField("http://localhost:8090/nodeAction/upload");
		urlText.setBorder(getBorder(new Color(51, 135, 255)));
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
		resultArea.setBorder(getBorder(new Color(0F, 0F, 0F, 0F)));
		resultArea.setName("resultArea");
		resultArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(resultArea);
		scrollPane.setBounds(100, 80, 300, 150);
		scrollPane.setBorder(getBorder(Color.gray));
		panel.add(scrollPane);
		
		JButton sendButton = new JButton("send");
		sendButton.setBounds(420, 20, 100, 25);
		sendButton.setFocusable(false);
		panel.add(sendButton);
		sendButton.addActionListener(e -> send());
        JComboBox<String> box = new JComboBox<> (EBD_Type.xmlNames);
        box.setBounds (420,50,100,25);
        box.setSelectedItem(EBD_Type.xmlNames[0]);
        box.setFocusable(false);
        box.addItemListener((e)->{
            if(e.getStateChange()==1) {
                int index = box.getSelectedIndex ();
                sendTest(index);
            }
        });
        panel.add(box);
        addFocusListener(panel);
		addMouseListener(panel);
	}
	public static void showResult(String result){
        ((JTextArea)getCompentByName(panel, "resultArea")).setText(result);
    }
	public static void sendTest(int index){
        if(index>0){
            String OMDType = EBD_Type.EBDS[index];
            String url = ((JTextField)getCompentByName(panel, "urlText")).getText();
            EBD ebd;
            if(OMDType==null){
                return;
            }else if(OMDType.equals ("ConnectionCheck")){
                EBD_ConnectionCheck connectionCheck = new EBD_ConnectionCheck ();
                connectionCheck.init ();
                ebd = connectionCheck;
            }else if(OMDType.equals ("EBM")){
                String ebmXmlModelPath = "D:\\tar\\test\\model\\EBM.xml";
                ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
                EBD_EBD ebd_ebd = (EBD_EBD)XMLUtil.readXMLToBean (new File (ebmXmlModelPath));
                ebd_ebd.getEBD ().setEBDID (EBDcodeUtil.getBaseEBDID());
                ebd_ebd.getEBD ().getSRC ().setEBRID (prop.getSRC_EBRID());
                ebd_ebd.getEBD ().getDEST ().setEBRID(prop.getDEST_EBRID());
                ebd_ebd.getEBD ().setEBDTime (DateUtils.getDateTime ());
                ebd_ebd.getEBD ().getEBM ().setEBMID (EBDcodeUtil.getEBMID ());
                ebd_ebd.getEBD ().getEBM ().getMsgBasicInfo ().setStartTime (DateUtils.formatDateTime (new Date(new Date().getTime ()+60*1000)));
                ebd_ebd.getEBD ().getEBM ().getMsgBasicInfo ().setEndTime (DateUtils.formatDateTime (new Date(new Date().getTime ()+10*60*1000)));
                ebd_ebd.getEBD ().getEBM ().getMsgContent ().setAreaCode (prop.getAreaCode ());
                ebd = ebd_ebd;
            }else{
                ebd = new EBD_OMDRequest (OMDType,"Full");
            }
            try {
                String tarPath = TarUtil.createXMLTarByBean (ebd,"D:\\tar\\test\\out",ebd.getEBD ().getEBDID ());
                String result = HttpClientUtil.sendPostFile(url, tarPath);
                showResult (result);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
	public static void send() {
		String url = ((JTextField)getCompentByName(panel, "urlText")).getText();
		String path = ((JTextField)getCompentByName(panel, "pathText")).getText();
		if(!"".equals(url)&&!"".equals(path)) {
			try {
				String result = HttpClientUtil.sendPostFile(url, path);
                showResult (result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static Component getCompentByName(JPanel panel,String name) {
		Component[] components= panel.getComponents();
		for (int i = 0; i < components.length; i++) {
			Component c = components[i];
			if(c instanceof JScrollPane) {
				JScrollPane pane = (JScrollPane)c;
				c = pane.getViewport().getView();
			}
			if(name.equals(c.getName())) {
				return c;
			}
		}
		return null;
	}
	public static void addFocusListener(JPanel panel){
		Component[] components= panel.getComponents();
		MyFocusListener focusListerner = new MyFocusListener();
		for (int i = 0; i < components.length; i++) {
			Component c = components[i];
			if(c instanceof JScrollPane) {
				JScrollPane pane = (JScrollPane)c;
				c = pane.getViewport().getView();
			}
			c.addFocusListener(focusListerner);
		}
	}
	public static void addMouseListener(JPanel panel){
		Component[] components= panel.getComponents();
		MyMouseListener fl = new MyMouseListener();
		for (int i = 0; i < components.length; i++) {
			Component c = components[i];
			if(c instanceof JScrollPane) {
				JScrollPane pane = (JScrollPane)c;
				c = pane.getViewport().getView();
			}
			c.addMouseListener(fl);
		}
	}
	public static Border getBorder(Color color) {
		Border border = BorderFactory.createLineBorder(color);
		return border;
	}
	
	public static void changeColor(MouseEvent e,JTextField urlText) {
		int index = e.getButton();
		if(index==MouseEvent.BUTTON1) {//鼠标左键按下
			urlText.setBorder(getBorder(new Color(51, 135, 255)));
		}
	}
	public void start() {
		JFrame frame = new JFrame("PostSender");
		frame.setSize(570, 300);
		//关闭窗体后终止程序
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.dispose();
		//创建面板，这个类似于 HTML 的 div 标签
		setJPanel(panel);
		frame.add(panel);
		// 设置界面可见
		frame.setVisible(true);
		// Frame在窗体居中
		frame.setLocationRelativeTo(null);
        SignatureUtil.start (0);
	}
}

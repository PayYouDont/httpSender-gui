
package com.payudon.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.text.JTextComponent;

import com.payudon.gui.FileDialog;

import lombok.Data;

/** 
* @ClassName: MyMouseListener 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月20日 下午4:10:17 
*  
*/
@Data
public class MyMouseListener implements MouseListener{
	/** 
	 * <p>Title: mouseClicked</p> 
	 * <p>Description: </p> 
	 * @param e 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月20日 下午4:10:29
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int index = e.getButton();
		if(index==MouseEvent.BUTTON1) {//鼠标左键按下
			Component c = e.getComponent();
			if(c instanceof JTextComponent) {
				//((JTextComponent)c).setBorder(HttpSendJFrame.getBorder(HttpSendJFrame.getColor(51, 135, 255)));
				JTextComponent jc = (JTextComponent)c;
				String textName = jc.getName();
				if("pathText".equals(textName)) {
					new FileDialog();
				}
			}
		}
	}

	/** 
	 * <p>Title: mousePressed</p> 
	 * <p>Description: </p> 
	 * @param e 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月20日 下午4:10:29
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * <p>Title: mouseReleased</p> 
	 * <p>Description: </p> 
	 * @param e 
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月20日 下午4:10:29
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * <p>Title: mouseEntered</p> 
	 * <p>Description: </p> 
	 * @param e 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月20日 下午4:10:29
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * <p>Title: mouseExited</p> 
	 * <p>Description: </p> 
	 * @param e 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月20日 下午4:10:29
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}

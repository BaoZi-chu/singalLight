//SingalPanel
package signal;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 * 显示信号灯的面板
* @author 8618534307787
 */
public class SignalPanel extends JPanel {
	/**
	 * 颜色数组
	 * 分别为 红、绿、黄
	 */
	private static Color cl[]= {Color.RED,Color.GREEN,Color.YELLOW};
	/**
	 * 访问颜色数组（cl）的下标
	 */
	private  int flag;
	/**
	 * 面板初始化
	 * @param flag 访问颜色数组的第几个
	 */
	SignalPanel(int flag){
		super();
		flag=this.flag;
	}
	/**
	 * 版本default
	 */
	private static final long serialVersionUID = 1L;
/**
 * 信号灯初始化
 */
	public void paint(Graphics g){
		super.paint(g);

		
		g.setColor(cl[flag]);
		g.drawOval(70, 30, 100, 100);
		g.fillOval(70, 30, 100, 100);
		}
	/**
	 * 设置信号灯初始颜色
	 * @param flag 颜色数组的下标
	 * 分别为 红、绿、黄
	 * 对应为 0 、 1、 2
	 */
	public void setFlag(int flag) {
		
		this.flag=flag;

	}
	/**
	 * 设置信号灯颜色
	 * @param g Graphics类对象，系统自动调用，无需给定参数
	 */
	public void repaint(Graphics g){
		super.paint(g);
		
		g.setColor(cl[flag]);
		g.drawOval(70,30, 100, 100);
		g.fillOval(70, 30, 100, 100);
		}

	}


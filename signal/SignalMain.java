//SignalMain
package signal;

/**
 * 
 * 主程序入口类
 * @author 8618534307787
 */
public class SignalMain {
	/**
	 * 主程序入口函数，调用第一个事件设置界面，进行信号灯初始化
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		new TimeSetFrame();//创建一个设置时间窗体，这里为初始值设置
	}
}

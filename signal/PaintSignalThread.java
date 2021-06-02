//PaintSignThread
package signal;

import java.util.Timer;
import java.util.TimerTask;
/**
 * 信号灯控制线程
* @author 8618534307787
 */
public class PaintSignalThread extends Thread {
	/**
	 *信号灯展示面板
	 */
	SignalPanel mp;
	/**
	 * 红色显示时间
	 */
	private int RedTime;
	/**
	 * 绿色显示时间
	 */
	private int GreenTime;
	/**
	 * 黄色显示时间
	 */
	private int YellowTime;
	/**
	 * 总时间
	 */
	private int TotalTime;
	/**
	 * 红色显示任务
	 */
	TimerTask redTimer;
	/**
	 * 绿色显示任务
	 */
	TimerTask GreenTimer;
	/**
	 * 黄色显示时间
	 */
	TimerTask YellowTimer;
	/**
	 * 定时器
	 */
	public Timer t;
	/**
	 * 总任务第  task 项
	 */
	private int task;
	/**
	 * 是否退出
	 */
	public volatile boolean exit = false;
/**
 * 构造函数
 * @param RedTime 红灯显示时间
 * @param GreenTime 绿灯显示时间
 * @param YellowTime 黄灯显示时间
 * @param mp 窗口中用于显示信号灯的面板
 */
	PaintSignalThread(int RedTime, int GreenTime, int YellowTime, SignalPanel mp) {
		this.RedTime = RedTime;
		this.GreenTime = GreenTime;
		this.YellowTime = YellowTime;
		this.TotalTime = RedTime + GreenTime + YellowTime;
		this.mp = mp;
		task=0;
		redTimer = new TimerTask() {
			@Override
			public void run() {
				try {
					// startTime=System.currentTimeMillis();
					mp.setFlag(0);
					mp.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		GreenTimer = new TimerTask() {
			@Override
			public void run() {
				try {
					// startTime=System.currentTimeMillis();
					mp.setFlag(1);
					mp.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		YellowTimer = new TimerTask() {
			@Override
			public void run() {
				try {
					// startTime=System.currentTimeMillis();
					mp.setFlag(2);
					mp.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

	}

	public void run() {

		t = new Timer();
		exit = false;
		switch (task) {
		case 0: {
			t.schedule(redTimer, 0, TotalTime);
			t.schedule(GreenTimer, RedTime, TotalTime);
			t.schedule(YellowTimer, RedTime + GreenTime, TotalTime);
			break;
		}
		case 1: {
			t.schedule(redTimer, GreenTime+YellowTime, TotalTime);
			t.schedule(GreenTimer, 0, TotalTime);
			t.schedule(YellowTimer,  GreenTime, TotalTime);
			break;
		}
		case 2:{
			t.schedule(redTimer, YellowTime, TotalTime);
			t.schedule(GreenTimer, YellowTime+RedTime, TotalTime);
			t.schedule(YellowTimer,  0, TotalTime);
			break;
		}
		default :
			break;

		}//任务选择，0代表红色先开始，1代表绿色先开始，20代表黄色先开始

		while (!exit) {
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//使线程持续执行，便于判断线程执行

	}

	public void endPaint() {
		exit = true;
		
		if (t != null) {
			t.cancel();//如果任务不为空的话，将任务取消
		}
		t = null;//定时任务清空，防止出现异常
	}
	/**
	 * 设置任务，0代表红色先开始，1代表绿色先开始，2代表黄色先开始
	 * @param t 任务标号
	 */
	public void setTask(int t) {
		task=t;
	}
}

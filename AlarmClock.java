import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.awt.event.*;

//����� ����� GridBagLayout ��� ������� ���������� � ��� ������

class AlarmClock{
	
	JFrame frame;
	Date date = new Date();
	JPanel mainPanel;
	
	public static void main (String[] args){
		AlarmClock clock = new AlarmClock();
		clock.go();
	}
	
	public void go(){
		frame = new JFrame();
		JPanel westPanel = new JPanel();//����� ������ � ������� ������������
		mainPanel = new JPanel(); //����������� ������ � ������
		JLabel time = new JLabel("132"); //����������� �������
		Box buttonBox = new Box(BoxLayout.Y_AXIS);//������ ������ � ��������
		JButton setAlarmClock;//������ ������� ���������
		JButton stopAlarmClock;//������ ���������� ������ ����������
		JTextField alarmClockSetter;//���� ��� �������� ������� ����������
		
		//����� ������
		Alarms alarm1 = new Alarms("14:45");
		Alarms alarm2 = new Alarms("12:49");
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.add (alarm1.getAlarm());
		westPanel.add (alarm2.getAlarm());
		
		//����������� ������
		time = this.setTimeLabel();
		
		//������ ������
		alarmClockSetter = new JTextField ();
		buttonBox.add(alarmClockSetter);
		
		setAlarmClock = new JButton ("��������");
		setAlarmClock.addActionListener(new mySetAlarClockListener());
		buttonBox.add(setAlarmClock);
		
		stopAlarmClock = new JButton ("����");
		stopAlarmClock.addActionListener(new myStopAlarmClockListener());
		buttonBox.add(stopAlarmClock);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel.add(time);
		
		frame.setSize(300, 400);
		frame.getContentPane().add(BorderLayout.WEST, westPanel);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.getContentPane().add(BorderLayout.EAST, buttonBox);
		frame.setVisible(true);
	}//����� ������ go
	public JLabel setTimeLabel(){
		JLabel label = new JLabel();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		label.setText(dateFormat.format(date).toString());
		Font timeFont = new Font ("TimesRoman", Font.BOLD, 34);
		label.setFont(timeFont);
		label.setForeground(Color.gray);
		
		javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed (ActionEvent e) {
				Date currentDate = new Date();
				label.setText(dateFormat.format(currentDate).toString());
			}
		});
		timer.start();
		
		return label;
	}//����� ������ setTimeLabel
	
	public class mySetAlarClockListener implements ActionListener{
		public void actionPerformed (ActionEvent a){
			System.out.println("mySetAlarClockListener");
		}
	}//����� ������
	
	public class myStopAlarmClockListener implements ActionListener{
		public void actionPerformed (ActionEvent a){
			System.out.println("myStopAlarmClockListener");
		}
	}//����� ������
}//����� ������

class Alarms extends JPanel{
	JPanel alarmsPanel = new JPanel();//������ � �������� ���������� � ������� �������
	
	CloseAlarm closeAlarm = new CloseAlarm(); //����������� ������� ���������
	JLabel alarmTime;
	Alarms (String time){
		alarmTime = new JLabel(time);
		Font timeFont = new Font ("TimesRoman", Font.BOLD, 25);
		alarmTime.setFont(timeFont);
		alarmsPanel.add(alarmTime);
		alarmsPanel.add(closeAlarm);
	}
	public JPanel getAlarm(){
		return alarmsPanel;
	}
}

class CloseAlarm extends JPanel{
	public void paintComponent (Graphics g){
		Image image = new ImageIcon("close.jpg").getImage();
		g.drawImage(image, 0, 0, 16, 16, this);
	}
}
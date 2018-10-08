import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.awt.event.*;

//СЛЕВА НУЖЕН GridBagLayout для времени будильника и его отмены

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
		JPanel westPanel = new JPanel();//Левая панель с текущии будильниками
		mainPanel = new JPanel(); //центральная панель с часами
		JLabel time = new JLabel("132"); //Отображение времени
		Box buttonBox = new Box(BoxLayout.Y_AXIS);//правая панель с кнопками
		JButton setAlarmClock;//кнопка завести будильник
		JButton stopAlarmClock;//кнопка остановить сигнал будильника
		JTextField alarmClockSetter;//поле для создания сигнала будильника
		
		//Левая панель
		Alarms alarm1 = new Alarms("14:45");
		Alarms alarm2 = new Alarms("12:49");
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.add (alarm1.getAlarm());
		westPanel.add (alarm2.getAlarm());
		
		//Центральная панель
		time = this.setTimeLabel();
		
		//Правая панель
		alarmClockSetter = new JTextField ();
		buttonBox.add(alarmClockSetter);
		
		setAlarmClock = new JButton ("Добавить");
		setAlarmClock.addActionListener(new mySetAlarClockListener());
		buttonBox.add(setAlarmClock);
		
		stopAlarmClock = new JButton ("Стоп");
		stopAlarmClock.addActionListener(new myStopAlarmClockListener());
		buttonBox.add(stopAlarmClock);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel.add(time);
		
		frame.setSize(300, 400);
		frame.getContentPane().add(BorderLayout.WEST, westPanel);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.getContentPane().add(BorderLayout.EAST, buttonBox);
		frame.setVisible(true);
	}//Конец метода go
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
	}//конец метода setTimeLabel
	
	public class mySetAlarClockListener implements ActionListener{
		public void actionPerformed (ActionEvent a){
			System.out.println("mySetAlarClockListener");
		}
	}//конец метода
	
	public class myStopAlarmClockListener implements ActionListener{
		public void actionPerformed (ActionEvent a){
			System.out.println("myStopAlarmClockListener");
		}
	}//конец метода
}//Конец класса

class Alarms extends JPanel{
	JPanel alarmsPanel = new JPanel();//панель с временем будильника и кнопкой закрыть
	
	CloseAlarm closeAlarm = new CloseAlarm(); //изображение закрыть будильник
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
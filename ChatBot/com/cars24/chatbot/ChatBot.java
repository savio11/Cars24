package com.cars24.chatbot;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ChatBot extends JFrame{
	Desktop d = Desktop.getDesktop();
	private JTextArea chatArea = new JTextArea();
	private JTextField chatBox = new JTextField();
	
	
	public ChatBot() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(600, 600);
		frame.setTitle("Cars24 Support");
		frame.add(chatArea);
		frame.add(chatBox);
//		JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		frame.setContentPane(pane);
//		SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                frame.setVisible(true);
//            }
//        });
		
		chatArea.setSize(600,400);
		chatArea.setLocation(2,2);
		
		chatBox.setSize(540,30);
		chatBox.setLocation(2,500);
		
		chatBox.addActionListener(new ActionListener() {
			String brand = "Not entered";
			String info = "Not entered";
			String need = "Not entered";
			String name = "Not entered";
			String old = "Not entered";
			String price = "Not entered";
			String km = "Not entered";
			Long value = 0L;
			boolean flag = false;
			boolean bname = false;
			boolean bold = false;
			boolean bprice = false;
			boolean bkm = false;
			boolean bfinal = false;
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO Auto-generated method stub
				String text = chatBox.getText().toLowerCase();
				chatArea.append("You : "+text+"\n");
				chatBox.setText("");
				if(text.contains("hi") || text.contains("help")) {
					bot("Hi, how may i help you");
					bot("Hi, Please tell whether you want to buy or sell a car");
				}
				else if(text.contains("buy") || text.contains("sell")) {
					if(text.contains("buy")) {
						need = "buy";
					}else {
						need = "sell";
					}
					bot("Please tell us which brand of car you want to "+need);
					bot("Below are the possible brands which we "+need);
					bot("1.Tata 2.Hyundai 3.Fiat 4.Ferrari 5.Honda 6.Mahindra");
					bot("7.Skoda 8.Ashok Leyland 9.Chevrolet 10.Volkswagen 11. Renault");

				}
//				else if(text.contains("car")) {
//					bot("Please tell whether you want to buy or sell a car");
//				}
				else if((text.contains("tata") ||text.contains("hyundai") || text.contains("fiat")
						|| text.contains("ferrari")||text.contains("honda")|| text.contains("mahindra")
						|| text.contains("skoda")|| text.contains("ashok leyland")||text.contains("chevrolet")
						|| text.contains("volkswagen")|| text.contains("renault"))&& text.contains("brand")) {
					flag = true;
					if(text.contains("tata")) {
						brand = "Tata";
					}else if(text.contains("hyundai")){
						brand = "Hyundai ";
					}else if(text.contains("ferrari")){
						brand = "Ferrari ";
					}else if(text.contains("honda")){
						brand = "Honda ";
					}else if(text.contains("mahindra")){
						brand = "Mahindra ";
					}else if(text.contains("skoda")){
						brand = "Skoda ";
					}else if(text.contains("fiat")){
						brand = "Fiat ";
					}else if(text.contains("ashok leyland")){
						brand = "Ashok Leyland ";
					}else if(text.contains("chevrolet")){
						brand = "Chevrolet ";
					}else if(text.contains("volkswagen")){
						brand = "Volkswagen ";
					}else if(text.contains("renault")){
						brand = "Renault ";
					}
					bot("Please tell which car of "+brand+" brand would you like to "+need);
				}
				else if(flag) {
					name = text;
					//bot("the car you would like to "+ need +" is "+name);
					bot("How much are you expecting for "+need+"ing this "+name+" car");
					bname = true;
					flag = false;
				}
				else if(bname) {
					price = text;
					//bot("How much are you expecting for "+need+"ing this car ");
					bot("Please select the year of the car which you are willing to "+need);
					bprice = true;
					
					bname = false;
				}
				else if(bprice) {
					value = Long.parseLong(text);
					if(need.equals("buy")) {
					    km = value+"0";
						bot("The "+name+" car of the year "+value+" on an average must have ran for "+value*10);
						bot("Please enter 'DONE'");
						bfinal = true;
					}
					else {
						
						bot("Please enter how many kilometers your car has run ");
						bkm = true;
					}
					bprice = false;
				}
				else if(bkm) {
					km = text;
					bot("your "+name+" has ran for "+km);
					bot("Please enter 'DONE'");
					bfinal = true;
					bkm = false;
				}
				
				else if(text.equals("done")) {
					bot("Brand ->"+brand);
					bot("Name ->"+name);
					bot("Price ->"+price);
					bot("Year ->"+value);
					bot("KM ->"+km);
				}
				else if(text.contains("yes")) {
					String link = "https://www.cars24.com/need-help/";
					bot(link);
					try {
						link = "https://www.cars24.com/need-help/";
						bot(link);
						d.browse(new URI(link));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(text.contains("no")) {
					bot("Please select brand of car from the above options");
				}
				else {
					bot("I am not able to understand you . Would you like to go the helpdesk of cars24");
					bot("Answer in Yes/No");
				}
			}
			
		});
		
		
		
	}
	private void bot(String string) {
		chatArea.append("Bot : "+string+"\n");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatBot();

	}

}

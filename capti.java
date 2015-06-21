import java.awt.*;//this is for awt look
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import java.net.*;//this is for connecting to oracle
import java.sql.*;
import java.util.LinkedList;
class capti extends JFrame implements ActionListener
{
	JButton ok;
	JTextField tuser;
	JPasswordField tpass;
	JLabel luser,lpass,lwelcome,lcopy;
	Font f=new Font("Algerian",Font.BOLD,20);
	Font f1=new Font("Harlow Solid Italic",Font.BOLD|Font.ITALIC,20);
	capti()
	{
		super("Login Window");//Name of Frame
		ok=new JButton("Start");//Initialize the Button and Gives Name
		ok.addActionListener(this);
		tpass=new JPasswordField(60);
		tuser=new JTextField(60);
		luser=new JLabel("Name:");
		lpass=new JLabel("Password");
		lwelcome=new JLabel("Welcome to C-Aptitude Test");
		lwelcome.setFont(f);
		lcopy=new JLabel("CopyWrites reserved to sarang");
		lcopy.setFont(f1);
		lcopy.setForeground(Color.BLUE);
		setLayout(null);
		lwelcome.setBounds(120,10,400,30);
		lwelcome.setForeground(Color.RED);
		luser.setBounds(130,90,60,20);
		tuser.setBounds(210,90,200,30);
		lpass.setBounds(130,150,60,20);
		tpass.setBounds(210,150,200,30);
		ok.setBounds(230,210,80,30);
		lcopy.setBounds(150,260,300,30);
		setForeground(Color.WHITE);
		add(lwelcome);add(luser);add(tuser);add(lpass);add(tpass);add(ok);add(lcopy);

		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(300,190);
		try 
		{
		      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		      SwingUtilities.updateComponentTreeUI(this);
		}
		catch (Exception ex1)
		{
		}
		setSize(600,350);
	}
	public void actionPerformed(ActionEvent e)
	{
		String s,s1;
		s=tuser.getText();
		s=s+"2014";
		s1=tpass.getText();
		if(s.equals(s1))
		{
			JOptionPane.showMessageDialog(null,"You are successfully Logged in!!!!!!!");
			setVisible(false);
			Information inf=new Information();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Incorrect password");
			tuser.setText(null);
			tpass.setText(null);
			tuser.requestFocus();
			
		}
		
	}
	public static void main(String [] args)
	{
		capti c=new capti();
		
		
	}
}  
class Information extends JFrame implements ActionListener
{
	JButton b1,b2;
	JPanel p1,p2;
	ImageIcon ii;
	Information()
	{
		super();
		b1=new JButton();
		ii=new ImageIcon("ins1.jpg");
		b1.setIcon(ii);
		b2=new JButton("Ok");
		setLayout(null);
		b2.addActionListener(this);
		b1.setBounds(10, 10, 800,600);
		b2.setBounds(440,630,80,30);
	   add(b1);
	   add(b2);
	    setVisible(true);
	    setSize(850,700);   
	}
	public void actionPerformed(ActionEvent e)
	{
		setVisible(false);
		main m=new main();
	}
}
class main extends JFrame implements ActionListener,ItemListener,Runnable
{
	JScrollPane p1;
	JTextArea ta1;
	ButtonGroup bg;
	int time=1200;
	int pm=2,nm=-1,total=0;
	JRadioButton a,b,c,d;
	JButton bsub,bpre,bnext,bgam,bfifty,bcorr,btime,bq;
	Thread th;
	PreparedStatement ps1,ps2,ps3,ps4;
	int []l1;
	int []l2;
	int che=0,flg1=0,flg2=0;
	Connection con;
	String s,ans,oracleans;
	int flg=0,i,j,k=0,rl=0;
	ImageIcon in,ip,ig,ic,ifif,bti;
	Font f=new Font("Algerian",Font.ROMAN_BASELINE,30);
	JLabel lscore,ltime,llife,la,lb,lc,ld,lq;
	ResultSet res;
	main()
	{
		super();
		 ImageIcon img = new ImageIcon("c.jpg");
			setIconImage(img.getImage());
		l1=new int[25];
		l2=new int[25];
		for(int u=0;u<25;u++)
		{
			l2[u]=456;
		}
		for(i=0;i<25;i++)
		{
			int a=(int)(Math.random()*40);
			if(a==0)
			{
				a=39;
			}
			l1[i]=a;
		}
		while(k<23)
		{
			for(i=0;i<25;i++)
			{
				for(j=0;j<25;j++)
				{
					if(i!=j)
					{
						if(l1[i]==l1[j]);
						{
							flg=1;
					
						}
					}	
				}
				if(flg==1)
				{
					int a=(int)(Math.random()*40);
					if(a==0)
						a=39;
					l1[i]=a;
				}
			}	
			k++;
		}
		for(int i=0;i<25;i++)
		{
			System.out.println("  "+l1[i]);
		}
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:","system","system");
		   // ps1=con.prepareStatement("select que from APTI where id=1");
		    ps2=con.prepareStatement("select que from APTI where id=?");
		    ps3=con.prepareStatement("select ans from APTI where id=?");		    
		}   
		catch(Exception v){
			System.out.print(""+v);
		}
		try
		{
		ps2.setInt(1, l1[che]);
		res=ps2.executeQuery();
		if(res.next())
		{
			s=res.getString(1);
		}
		}
	    catch(Exception f){}
		bg=new ButtonGroup();
			btime=new JButton();
			bq=new JButton("Quit");
			bq.setToolTipText("QUIT");
		ta1=new JTextArea(s,40,30);
		ta1.setEditable(false);
		ta1.setForeground(new Color(10,10,10));
		ta1.setBackground(new Color(255,255,255));
		ta1.setFont(new Font("Arial",Font.BOLD,16));
		in=new ImageIcon("next.jpg");
		ip=new ImageIcon("prev.jpg");
		ig=new ImageIcon("gamble.jpg");
		Window w=new Window(this);
		ic=new ImageIcon("bingo.jpg");
		ifif=new ImageIcon("50.jpg");
		bti=new ImageIcon("stopwatch.jpg");
		btime.setIcon(bti);
		p1=new JScrollPane(ta1);
		lscore=new JLabel("Score: 0");
		ltime=new JLabel("Time:1500 sec");
		llife=new JLabel("LifeLines:");
		llife.setFont(f);
		lscore.setFont(f);
		ltime.setFont(f);
		int g=che+1;
		lq=new JLabel("Que NO: "+g);
		ans="";
		la=new JLabel("a");
		lb=new JLabel("b");
		lc=new JLabel("c");
		ld=new JLabel("d");
		a=new JRadioButton("a");
		b=new JRadioButton("b");
		c=new JRadioButton("c");
		d=new JRadioButton("d");
		bg.add(a);bg.add(b);bg.add(c);bg.add(d);
		bsub=new JButton("submit");
		bsub.setToolTipText("Submit");
		bpre=new JButton();
		bpre.setToolTipText("Previous");
		bpre.setIcon(ip);
		bnext=new JButton();
		bnext.setToolTipText("Next");
		bnext.setIcon(in);
		bgam=new JButton();
		bgam.setToolTipText("Gamble::twice your marking scheme");
		bgam.setIcon(ig);
		bfifty=new JButton();
		bfifty.setToolTipText("Fifty-Fifty::Eliminate two incorrect option");
		bfifty.setIcon(ifif);
		bcorr=new JButton();
		bcorr.setToolTipText("Bingo::Gives the correct option directly");
		bcorr.setIcon(ic);
		th=new Thread(this);
		th.start();
		bcorr.addActionListener(this);
		bfifty.addActionListener(this);
		bgam.addActionListener(this);
		bnext.addActionListener(this);
		bpre.addActionListener(this);
		bsub.addActionListener(this);
		btime.addActionListener(this);
		a.addItemListener(this);
		b.addItemListener(this);
		c.addItemListener(this);
		d.addItemListener(this);
		setLayout(null);
		p1.setBounds(10,10,750,450);
		a.setBounds(10,470,20,20);
		la.setBounds(40,470,20,20);
		b.setBounds(80,470,20,20);
		lb.setBounds(110,470,20,20);
		c.setBounds(140,470,20,20);
		lc.setBounds(180,470,20,20);
		d.setBounds(210,470,20,20);
		ld.setBounds(240,470,20,20);
		llife.setBounds(40,540,170,40);
		bsub.setBounds(40,500,200,30);
		bpre.setBounds(290,500,115,30);
		bpre.setEnabled(false);
		bnext.setBounds(435,500,115,30);
		lq.setFont(f);
		lq.setBounds(600,480,155,30);
		bq.setBounds(1200,650,115,30);
		bq.addActionListener(this);
		bgam.setBounds(60,600,115,30);
		bcorr.setBounds(190,600,115,30);
		bfifty.setBounds(325,600,100,30);
		lscore.setBounds(900,40,200,40);
		ltime.setBounds(900,150,250,40);
		btime.setBounds(1000,220,283,380);
		add(p1);add(a);add(la);add(b);add(bgam);add(bcorr);add(bfifty);add(bq);add(lq);
		add(lb);add(c);add(lc);add(d);add(ld);add(llife);add(bsub);add(bnext);add(bpre);add(lscore);add(ltime);add(btime);
		setVisible(true);
		setSize(1350,730);
	}
	public void itemStateChanged(ItemEvent e)
	{
		if(a.isSelected())
		{
			ans="a";
		}
		if(b.isSelected())
		{
			ans="b";
		}
		if(c.isSelected())
		{
			ans="c";
		}
		if(d.isSelected())
		{
			ans="d";
		}
		
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b1=(JButton)e.getSource();
		if(b1==bsub)
		{
			if(a.isSelected()==false&&b.isSelected()==false&&c.isSelected()==false&&d.isSelected()==false)
			{
				JOptionPane.showMessageDialog(null, "Please select option");
				return;
			}
			try
			{
				ps3.setInt(1,l1[che]);
				res=ps3.executeQuery();
				if(res.next())
				{
					oracleans=res.getString(1);
					System.out.println("Oracleans:   "+oracleans+"   Ans :"+ans);
					if(oracleans.equals(ans))
					{
						if(flg1==1)
						{
							pm=4;
						}
						total+=pm;
					}
					else
					{
						if(flg1==1)
						{
							nm=-2;
						}
						  total+=nm;
					}
					
				}
				pm=2;
				nm=-1;
				flg1=0;
				l2[rl++]=che;
				int g=che+1;
				lq.setText("Que NO: "+g);
				bpre.setEnabled(true);
				if(che==24)
				{
					bnext.setEnabled(false);
					ta1.setEnabled(false);
					bsub.setEnabled(false);
					a.setEnabled(false);
					b.setEnabled(false);
					c.setEnabled(false);
					d.setEnabled(false);
					return;
				}
				if(che!=0)
				{
					bpre.setEnabled(true);
				}
				System.out.println("Total    "+total);
			}
			catch(Exception f){}
			 lscore.setText("Score :"+total);
			che++;
			for(int i=0;i<25;i++)
			{
				if(l2[i]==che)
				{
					flg2=1;
				}
			}
			if(flg2==1)
			{
				ta1.setEnabled(false);
				bsub.setEnabled(false);
				a.setEnabled(false);
				b.setEnabled(false);
				c.setEnabled(false);
				d.setEnabled(false);
				flg2=0;
			}
			else
			{
				bg.clearSelection();
				ta1.setEnabled(true);
				bsub.setEnabled(true);
				a.setEnabled(true);
				b.setEnabled(true);
				c.setEnabled(true);
				d.setEnabled(true);
				a.setSelected(false);
				b.setSelected(false);
				c.setSelected(false);
				d.setSelected(false);

			}
			a.setEnabled(true);
			b.setEnabled(true);
			c.setEnabled(true);
			bsub.setEnabled(true);
			d.setEnabled(true);
			bg.clearSelection();
			a.setSelected(false);
			b.setSelected(false);
			c.setSelected(false);
			d.setSelected(false);
			int g=che+1;
			lq.setText("Que NO: "+g);
			try
			{
				ps2.setInt(1,l1[che]);
				res=ps2.executeQuery();
				if(res.next())
				{
					s=""+res.getString(1);
					ta1.setText(s);
				}
			}
			catch(Exception l){}
		}
		if(b1==bnext)
		{
			bpre.setEnabled(true);
			bg.clearSelection();
			a.setSelected(false);
			b.setSelected(false);
			c.setSelected(false);
			d.setSelected(false);
			che++;
			for(int i=0;i<25;i++)
			{
				if(l2[i]==che)
				{
					flg2=1;
				}
			}
			if(flg2==1)
			{
				ta1.setEnabled(false);
				bsub.setEnabled(false);
				a.setEnabled(false);
				b.setEnabled(false);
				c.setEnabled(false);
				d.setEnabled(false);
				flg2=0;
			}
			else
			{
				ta1.setEnabled(true);
				bsub.setEnabled(true);
				a.setEnabled(true);
				b.setEnabled(true);
				c.setEnabled(true);
				d.setEnabled(true);
				bg.clearSelection();
				a.setSelected(false);
				b.setSelected(false);
				c.setSelected(false);
				d.setSelected(false);
			}
			int g=che+1;
			lq.setText("Que NO: "+g);
			if(che==24)
			{
				bnext.setEnabled(false);
			}
            try
            {
            	ps2.setInt(1, l1[che]);
            	res=ps2.executeQuery();
            	if(res.next())
            	{
            		s=""+res.getString(1);
            		ta1.setText(s);
            	}
            }
            catch(Exception v){}
		}
		if(b1==bq)
		{
			int opt=JOptionPane.showConfirmDialog(this,"Are You Sure To Quit ?");
			if(opt!=0) 
			{
				return;
			}
			else
			{
				JOptionPane.showMessageDialog(null,  "Your Socre is :"+total+" points");
				setVisible(false);
				dispose();
			}
		}
		if(b1==bpre)
		{
			bnext.setEnabled(true);
			bg.clearSelection();
			a.setSelected(false);
			b.setSelected(false);
			c.setSelected(false);
			d.setSelected(false);
			che--;
			flg2=0;
			for(int i=0;i<25;i++)
			{
				if(l2[i]==che)
				{
					flg2=1;
				}
			}
			if(flg2==1)
			{
				ta1.setEnabled(false);
				bsub.setEnabled(false);
				a.setEnabled(false);
				b.setEnabled(false);
				c.setEnabled(false);
				d.setEnabled(false);
				flg2=0;
			}
			else
			{
				ta1.setEnabled(true);
				bsub.setEnabled(true);
				a.setEnabled(true);
				b.setEnabled(true);
				c.setEnabled(true);
				d.setEnabled(true);
				bg.clearSelection();
				a.setSelected(false);
				b.setSelected(false);
				c.setSelected(false);
				d.setSelected(false);
			}
			int g=che+1;
			lq.setText("Que NO: "+g);
			if(che==0)
			{
				bpre.setEnabled(false);
			}
            try
            {
            	ps2.setInt(1,l1[che]);
            	res=ps2.executeQuery();
            	if(res.next())
            	{
            		s=""+res.getString(1);
            		ta1.setText(s);
            	}
            }
            catch(Exception v){}	
		}
		if(b1==bgam)
		{
			if(bsub.isEnabled()==false)
			{
				JOptionPane.showMessageDialog(null,"Sorry You can't Gamble");
				return;
			}
			int opt=JOptionPane.showConfirmDialog(this,"Are You Sure you want to use gamble ?");
			if(opt!=0) 
			{
				return;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Your marking scheam is doubled  correct:+4  Wrong:-2");
		        flg1=1;
			}
			bgam.setEnabled(false);
		}
		if(b1==bfifty)
		{
			if(bsub.isEnabled()==false)
			{
				JOptionPane.showMessageDialog(null,"Sorry You can't Fifty-Fifty");
				return;
			}
			int opt=JOptionPane.showConfirmDialog(this,"Are You Sure you want to use Fifty-Fifty ?");
			if(opt!=0)
			{
				return;
			}
			else
			{
			   try
			   {
				   ps3.setInt(1, l1[che]);
				   res=ps3.executeQuery();
				   if(res.next())
				   {
					   String str=res.getString(1);
					   if(str.equals("a"))
					   {
						   b.setEnabled(false);
						   d.setEnabled(false);
					   }
					   if(str.equals("b"))
					   {
						   a.setEnabled(false);
						   d.setEnabled(false);
					   }
					   if(str.equals("c"))
					   {
						   a.setEnabled(false);
						   b.setEnabled(false);
					   }
					   if(str.equals("d"))
					   {
						   a.setEnabled(false);
						   c.setEnabled(false);
					   }
				   }
	        	}
			   catch(Exception g){}
			   bfifty.setEnabled(false);
     		}
		}
		if(b1==bcorr)
		{
			if(bsub.isEnabled()==false)
			{
				JOptionPane.showMessageDialog(null,"Sorry You can't Bingo");
				return;
			}
			int opt=JOptionPane.showConfirmDialog(this,"Are You Sure you want to use Bingo ?");
			if(opt!=0)
			{
				return;
			}
			else
			{
				try
				   {
					   ps3.setInt(1, l1[che]);
					   res=ps3.executeQuery();
					   if(res.next())
					   {
						   String str=res.getString(1);
						   if(str.equals("a"))
						   {
							   a.setSelected(true);
						   }
						   if(str.equals("b"))
						   {
							   b.setSelected(true);
						   }
						   if(str.equals("c"))
						   {
							   c.setSelected(true);
						   }
						   if(str.equals("d"))
						   {
							   d.setSelected(true);
						   }
	        			}
				    }
				    catch(Exception e3){}
				   	}
			bcorr.setEnabled(false);
				}
			}
		public void run()
		{
			for(;;)
			{
			try                                                                                                                                                                                                                                                                                                                                                                                               
			{
		    th.sleep(1000);
		    time--;
		    ltime.setText("Time :"+time+" Sec");
		   
			}
			catch(Exception vf){}
			if(time==0)
			{
				JOptionPane.showMessageDialog(null, "TIME   OVER!!!!!\n Your Socre is :"+total+" points");
				setEnabled(false);
				break;
				
			}
			}
		}
}
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.event.ChangeEvent;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Component;
import javax.swing.JToolBar;
import java.awt.Choice;
import javax.swing.JTextPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;

public class mainScreen extends JFrame {

    class MyButton extends JButton {

        private Color hoverBackgroundColor;
        private Color pressedBackgroundColor;

        public MyButton() {
            this(null);
        }

        public MyButton(String text) {
            super(text);
            super.setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(pressedBackgroundColor);
            } else if (getModel().isRollover()) {
                g.setColor(hoverBackgroundColor);
            } else {
                g.setColor(getBackground());
            }
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }

        @Override
        public void setContentAreaFilled(boolean b) {
        }

        public Color getHoverBackgroundColor() {
            return hoverBackgroundColor;
        }

        public void setHoverBackgroundColor(Color hoverBackgroundColor) {
            this.hoverBackgroundColor = hoverBackgroundColor;
        }

        public Color getPressedBackgroundColor() {
            return pressedBackgroundColor;
        }

        public void setPressedBackgroundColor(Color pressedBackgroundColor) {
            this.pressedBackgroundColor = pressedBackgroundColor;
        }
    }
    
	private JPanel contentPane;
	private JPanel gamePanel;
	private JTextPane textPaneQuestion;
	private JButton btnAnswer1;
	private JButton btnAnswer2;
	private JButton btnAnswer3;
	private JButton btnAnswer4;
	
	class isSelected extends TimerTask {
		public void run() {
			if(btnAnswer1.getModel().isPressed()) {
				System.out.println("asdas");	
			}
		}
	}
	void isSelected() {
		if(btnAnswer1.getModel().isPressed()) {
			System.out.println("ASFSAF");
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScreen frame = new mainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainScreen() {
		setBounds(new Rectangle(0, 0, 1024, 768));
		setResizable(false);
		setTitle("Who Wants To Be A Millionaire?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 798);
		
		
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 1024, 768));
		contentPane.setSize(1024, 768);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		
		//this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//setContentPane(gamePane);
		//getContentPane().setLayout(null);
		
		
		MyButton btnNewGame = new MyButton("NEW GAME");
		btnNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Player newPlayer = null;
					String name = JOptionPane.showInputDialog("Please enter a user name.", "Unknown");
					if(name != null) {
						newPlayer = new Player();	// bunu direk oyun class'ýndan da alabiliriz.
						newPlayer.setName(name);
						contentPane.setVisible(false);
						gamePanel.setVisible(true);
						setContentPane(gamePanel);
						getContentPane().setLayout(null);	
					}
					Game newGame = new Game();
					newGame.setMyPlayer(newPlayer);
					Question[] gameQuestions = new Question[12];
					textPaneQuestion.setText("asdasd");	
					for(int i = 0; i < 12; i++) {
						gameQuestions[i] = new Question();
						gameQuestions[i].setQuestionLevel(i);
						gameQuestions[i].setLifelinesUsed(0);
						gameQuestions[i].setQuestionTime();
						gameQuestions[i].setQuestionText();
						gameQuestions[i].setCorrectAnswer();
						gameQuestions[i].setWrongAnswers();
					}
					newGame.setQuestions(gameQuestions);
					do {
						textPaneQuestion.setText(newGame.getQuestions()[0].getQuestionText());
						int ca, i = 0;
						ca = new Random().nextInt(4);
						if(ca == 0) {
							btnAnswer1.setText("A) "+ newGame.getQuestions()[i].getCorrectAnswer());
							btnAnswer2.setText("B) "+ newGame.getQuestions()[i].getWrongAnswers()[0]);
							btnAnswer3.setText("C) "+ newGame.getQuestions()[i].getWrongAnswers()[1]);
							btnAnswer4.setText("D) "+ newGame.getQuestions()[i].getWrongAnswers()[2]);
						}
						else if(ca == 1) {
							btnAnswer1.setText("A) "+ newGame.getQuestions()[i].getWrongAnswers()[0]);
							btnAnswer2.setText("B) "+ newGame.getQuestions()[i].getCorrectAnswer());
							btnAnswer3.setText("C) "+ newGame.getQuestions()[i].getWrongAnswers()[1]);
							btnAnswer4.setText("D) "+ newGame.getQuestions()[i].getWrongAnswers()[2]);
						}
						else if(ca == 2) {
							btnAnswer1.setText("A) "+ newGame.getQuestions()[i].getWrongAnswers()[0]);
							btnAnswer2.setText("B) "+ newGame.getQuestions()[i].getWrongAnswers()[1]);
							btnAnswer3.setText("C) "+ newGame.getQuestions()[i].getCorrectAnswer());
							btnAnswer4.setText("D) "+ newGame.getQuestions()[i].getWrongAnswers()[2]);
						}
						else {
							btnAnswer1.setText("A) "+ newGame.getQuestions()[i].getWrongAnswers()[0]);
							btnAnswer2.setText("B) "+ newGame.getQuestions()[i].getWrongAnswers()[1]);
							btnAnswer3.setText("C) "+ newGame.getQuestions()[i].getWrongAnswers()[2]);
							btnAnswer4.setText("D) "+ newGame.getQuestions()[i].getCorrectAnswer());
						}
						Timer t1 = new Timer(true);

						t1.schedule(new isSelected(), 0, 100);
						
					}while(false);				
				}catch(Exception e1) {
					System.out.println(e1.getMessage());
				}

			}
		});
		btnNewGame.setBounds(256, 0, 512, 192);
		btnNewGame.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		btnNewGame.setForeground(new Color(255, 228, 225));
		btnNewGame.setBackground(Color.blue.darker().darker().darker().darker());
		btnNewGame.setPressedBackgroundColor(Color.blue.darker().darker().darker());
		btnNewGame.setHoverBackgroundColor(Color.blue.darker().darker().darker().darker().darker().darker());
		contentPane.add(btnNewGame);
		
		MyButton btnBestPlayers = new MyButton("BEST PLAYERS");
		btnBestPlayers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BestDB bdb = new BestDB();
				Player[] myBests = new Player[3];
				myBests = bdb.getBests();
				String bests = "Best Scores\n"+myBests[0].getName()+" with score: "+myBests[0].getScore()+"\n"+myBests[1].getName()+" with score: "+myBests[1].getScore()+"\n"+myBests[2].getName()+" with score: "+myBests[2].getScore();
				JOptionPane.showMessageDialog(contentPane, bests, "Who Wants To Be A Millionaire?", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnBestPlayers.setBounds(256, 192, 512, 192);
		btnBestPlayers.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		btnBestPlayers.setForeground(new Color(255, 228, 225));
		btnBestPlayers.setBackground(Color.blue.darker().darker().darker().darker());
		btnBestPlayers.setPressedBackgroundColor(Color.blue.darker().darker().darker());
		btnBestPlayers.setHoverBackgroundColor(Color.blue.darker().darker().darker().darker().darker().darker());
		contentPane.add(btnBestPlayers);
		
		MyButton btnHowToPlay = new MyButton("HOW TO PLAY");
		btnHowToPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(contentPane, "Oyun þu þekilde oynanmaktadýr: \nÖncelikle oyunu oynayýnýz...", "Who Wants To Be A Millionaire?", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnHowToPlay.setBounds(256, 384, 512, 192);
		btnHowToPlay.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		btnHowToPlay.setForeground(new Color(255, 228, 225));
		btnHowToPlay.setBackground(Color.blue.darker().darker().darker().darker());
		btnHowToPlay.setPressedBackgroundColor(Color.blue.darker().darker().darker());
		btnHowToPlay.setHoverBackgroundColor(Color.blue.darker().darker().darker().darker().darker().darker());
		contentPane.add(btnHowToPlay);
		
		MyButton btnQuitTheGame = new MyButton("QUIT THE GAME");
		btnQuitTheGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(contentPane, "Are you sure?", "Who Wants To Be A Millionaire?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
				if(dialogResult == 0) {
					System.exit(1);
				}
			}
		});
		btnQuitTheGame.setBounds(256, 576, 512, 192);
		btnQuitTheGame.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		btnQuitTheGame.setForeground(new Color(255, 228, 225));
		btnQuitTheGame.setBackground(Color.blue.darker().darker().darker().darker());
		btnQuitTheGame.setPressedBackgroundColor(Color.blue.darker().darker().darker());
		btnQuitTheGame.setHoverBackgroundColor(Color.blue.darker().darker().darker().darker().darker().darker());
		contentPane.add(btnQuitTheGame);
		
		gamePanel = new JPanel();
		gamePanel.setAlignmentY(Component.TOP_ALIGNMENT);
		gamePanel.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		gamePanel.setBackground(UIManager.getColor("Panel.background"));
		gamePanel.setBounds(0, 0, 1024, 768);
		gamePanel.setVisible(false);
		gamePanel.setLayout(null);
		contentPane.add(gamePanel);

		
		JList moneyList = new JList();
		moneyList.setFont(new Font("Tahoma", Font.PLAIN, 23));
		moneyList.setModel(new AbstractListModel() {
			String[] values = new String[] {"\t1.000.000 TL ", "\t250.000 TL ", "\t125.000 TL ", "\t60.000 TL ", "\t30.000 TL ", "\t15.000 TL ", "\t7.500 TL ", "\t5.000 TL ", "\t3.000 TL ", "\t2.000 TL ", "\t1.000 TL ", "\t500 TL "};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		moneyList.setSelectedIndex(11);
		moneyList.setBackground(UIManager.getColor("OptionPane.background"));
		moneyList.setBounds(736, 36, 278, 396);
		gamePanel.add(moneyList);

		
		btnAnswer1 = new JButton("A)");
		btnAnswer1.setBounds(27, 613, 375, 50);
		gamePanel.add(btnAnswer1);
		
		btnAnswer2 = new JButton("B)");
		btnAnswer2.setBounds(452, 613, 375, 50);
		gamePanel.add(btnAnswer2);
		
		btnAnswer3 = new JButton("C)");
		btnAnswer3.setBounds(27, 681, 375, 50);
		gamePanel.add(btnAnswer3);
		
		btnAnswer4 = new JButton("D)");
		btnAnswer4.setBounds(452, 681, 375, 50);
		gamePanel.add(btnAnswer4);
		


		
		textPaneQuestion = new JTextPane();
		textPaneQuestion.setForeground(new Color(248, 248, 255));
		textPaneQuestion.setBackground(new Color(0, 0, 128));
		textPaneQuestion.setText("Fenerbahce en son ne zaman sampiyon olmustur?");
		textPaneQuestion.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textPaneQuestion.setBounds(27, 445, 800, 150);
		StyledDocument doc = textPaneQuestion.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		gamePanel.add(textPaneQuestion);
		
		JButton btnCall = new JButton("CALL");
		btnCall.setBackground(Color.GREEN);
		btnCall.setBounds(875, 445, 101, 50);
		gamePanel.add(btnCall);
		
		JButton btnAudience = new JButton("AUDIENCE");
		btnAudience.setBackground(Color.GREEN);
		btnAudience.setBounds(875, 527, 101, 50);
		gamePanel.add(btnAudience);
		
		JButton btnEliminate = new JButton("ELIMINATE");
		btnEliminate.setBackground(Color.GREEN);
		btnEliminate.setBounds(875, 609, 101, 50);
		gamePanel.add(btnEliminate);
		
		JButton btnTwoAnswer = new JButton("TWO ANSWER");
		btnTwoAnswer.setBackground(Color.GREEN);
		btnTwoAnswer.setBounds(875, 681, 101, 50);
		gamePanel.add(btnTwoAnswer);
		
		JButton btnWalkAway = new JButton("WALK AWAY");
		btnWalkAway.setBounds(27, 36, 200, 85);
		gamePanel.add(btnWalkAway);
		
		JLabel lblTime = new JLabel("15");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblTime.setBounds(588, 36, 100, 85);
		gamePanel.add(lblTime);
		
		JLabel lblMain = new JLabel("WHO WANTS TO BE A MILLIONAIRE?");
		lblMain.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblMain.setBounds(12, 107, 700, 300);
		gamePanel.add(lblMain);
		
		JLabel lblTimeLeft = new JLabel("TIME LEFT:");
		lblTimeLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeLeft.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTimeLeft.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblTimeLeft.setBounds(436, 36, 177, 85);
		gamePanel.add(lblTimeLeft);
		

	}
}

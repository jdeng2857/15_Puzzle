import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class SliderGame extends JFrame implements ActionListener{

	private JButton[] buttons = new JButton[16];  //Make room for 16 button objects
    	private JButton start = new JButton("New Game");
    	private JLabel label1;
	private int emptyIndex; //Variable will track the empty spot
    	private JLabel moves = new JLabel("Moves");
    	private int movesMade;
    	    
	public SliderGame()
	{	
		JFrame myFrame = new JFrame("Slider Game");
       		myFrame.setSize(1200,650);    
        	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	myFrame.setBackground(new Color(204,229,255));
		Font f = new Font("Times New Roman", Font.BOLD, 26);
		
		Container gameboardPanel = new Container();
        	gameboardPanel.setEnabled(false);
        	gameboardPanel.setSize(400,600);
		gameboardPanel.setBackground(Color.blue); //Allows empty space to be black
		int xpos = 0;
        	int ypos = 0;
        
        	start.setBackground(Color.white);
        	start.setForeground(Color.blue);
        	start.setFont(f);
        	start.addActionListener(this);
        	gameboardPanel.add(start);
        	start.setBounds(625,525,200,50);
        
        	moves.setFont(f);
        	moves.setText("Moves made: "+movesMade);
        	moves.setBounds(850, 525, 200, 50);
        	gameboardPanel.add(moves);
        
		ImageIcon image1 = new ImageIcon(this.getClass().getResource("full_image.jpg"));
		label1 = new JLabel(image1);
		label1.setBounds(625,0,500,500);
		gameboardPanel.add(label1);
		
		for (int i = 0; i < buttons.length; i++)  //From i is 0 to 15
		{   
		    buttons[i] = new JButton(""+(i+1));  //Constructor sets text on new buttons
		    buttons[i].addActionListener(this);   //Set up ActionListener on each button            
		    buttons[i].setBounds(xpos,ypos,150,150);
		    xpos+=150;
		    if(i%4 == 3){
			xpos = 0;
			ypos += 150;
		    }
		}                
		
		for(int i = 0; i<9; i++) {
			buttons[i].setIcon(new ImageIcon(this.getClass().getResource("image_part_00"+(i+1)+".jpg")));
			gameboardPanel.add(buttons[i]);       //Add buttons to the grid layout
			validate();
		}
		for(int i = 0; i<7; i++) {
			buttons[i+9].setIcon(new ImageIcon(this.getClass().getResource("image_part_01"+i+".jpg")));
			gameboardPanel.add(buttons[i+9]);       //Add buttons to the grid layout
			validate();
		}
		buttons[15].setVisible(false);  
		emptyIndex=15;
		randomize();
		movesMade = 0;
		moves.setText("Moves made:"+movesMade);

		gameboardPanel.setEnabled(true);               
		myFrame.setContentPane(gameboardPanel); //Add gameboardPanel to JFrame
		myFrame.setVisible(true); //Turn on JFrame
	}
	
	public void actionPerformed(ActionEvent e) {
              
		for(in	t i = 0; i < buttons.length; i++){
		    if(e.getSource() == buttons[i]){
			swap(i);                   
			if(checkWin()) {
			    JOptionPane.showMessageDialog(null,"You have won");
			}	
		    }
		}

		if(e.getSource() == start){
		    randomize();
		    movesMade = 0;
		    moves.setText("Moves made:"+movesMade);
		} 
	}   

    public boolean checkWin(){
	for(int j = 0; j < buttons.length-1; j++){
	    try{
		//int num = Integer.parseInt(buttons[j].getText());
		if(Integer.parseInt(buttons[j].getText()) != j+1)
		    return false;
	    }catch(Exception e){
		System.out.println("Invalid Text on Button");
	    }
	}
	return true;

    }
    
    public void up() {
    	if(emptyIndex<12)
    		swap(emptyIndex+4);
    }
    
    public void down() {
    	if(emptyIndex>3)
    		swap(emptyIndex-4);
    }
    
    public void left() {
    	if(emptyIndex%4!=3)
    		swap(emptyIndex+1);
    }
    
    public void right() {
    	if(emptyIndex%4!=0)
    		swap(emptyIndex-1);
    		
    }
        
	private void swap(int btnIndex)   //Send along button that was pushed
	{            
        if(Math.abs(emptyIndex-btnIndex) == 4 || Math.abs(emptyIndex-btnIndex) == 1 && !(btnIndex%4==0 && emptyIndex==btnIndex-1) && !(btnIndex%4 ==3 && emptyIndex==btnIndex+1)){
            buttons[emptyIndex].setIcon(buttons[btnIndex].getIcon());  //Move over text
			buttons[emptyIndex].setText(buttons[btnIndex].getText());
            buttons[emptyIndex].setVisible(true);														   //to blank button
			buttons[btnIndex].setVisible(false);
			emptyIndex = btnIndex;		     //Update the emptyIndex to the button that was							     	
            movesMade++;
            moves.setText("Moves made:"+movesMade);
        }		
	}	
        
    private void randomize(){
    	for(int i = 0; i<1000; i++) {
    		int n = (int)(4*Math.random())+1;
    		switch(n) {
    			case 1:
    				up();
    				break;
    			case 2:
    				down();
    				break;
    			case 3:
    				left();
    				break;
    			case 4:
    				right();   
    				break;
    		}
    	}        
    }
	
}




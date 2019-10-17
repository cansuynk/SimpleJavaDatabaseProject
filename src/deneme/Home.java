package deneme;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane; 

import net.proteanit.sql.DbUtils;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*
	public ArrayList<Movie> movieList(){
		
		ArrayList<Movie> moviesList = new ArrayList<>();
		try {
			conn=DriverManager.getConnection(  
					"jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","cns96");
			
			String query1="SELECT * FROM MOVIES";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query1);
			Movie movie;
			
			while(rs.next()){
				movie = new Movie(rs.getInt(arg0));
			}
			
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	*/
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	private JTable table;
	private JTextField title;
	private JTextField actor_lastName;
	private JTextField popularity;
	private JTextField year;
	private JTextField length;
	private JTextField actor_name;
	private JTextField actress_lastName;
	private JTextField actress_name;
	private JTextField director_lastName;
	private JTextField director_name;
	JComboBox award;
	JComboBox subject;
	JScrollPane jTable_Display_User;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setResizable(false);
		try {
			
			conn=DriverManager.getConnection(  
					"jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","cns96");
			
			/*
			conn=DriverManager.getConnection(  
					"jdbc:oracle:thin:@192.168.56.3:1521:orcl12c","CANSU","cansu");
			*/
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1148, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("WELCOME TO OUR MOVIE ARCHIVE!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Yu Gothic Medium", Font.BOLD, 20));
		lblWelcome.setBounds(341, 26, 399, 33);
		contentPane.add(lblWelcome);
		
		JButton btnNewButton = new JButton("Load Movie Archive");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					stmt=conn.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT MOVIE_YEAR, MOVIE_LENGTH, MOVIE_TITLE, MOVIE_SUBJECT, MOVIE_ACTOR, MOVIE_ACTRESS, MOVIE_DIRECTOR,"
							+ "MOVIE_POPULARITY, MOVIE_AWARDS FROM MOVIES");
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		btnNewButton.setBounds(466, 89, 417, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//database connection
					conn=DriverManager.getConnection(  
							"jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","cns96");
					
					//insert query
					String query1="INSERT INTO MOVIES(MOVIE_YEAR, MOVIE_LENGTH, MOVIE_TITLE, MOVIE_SUBJECT, " +
							"MOVIE_ACTOR, MOVIE_ACTRESS, MOVIE_DIRECTOR, MOVIE_POPULARITY, MOVIE_AWARDS)VALUES(?,?,?,?,?,?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query1);		//prepare the query
					
					//get the inputs from interface into the relevant column names
					pst.setString(1, year.getText()); 
					pst.setString(2, length.getText());
					pst.setString(3, title.getText());	
					
					String subjects;
					subjects = subject.getSelectedItem().toString();
					pst.setString(4, subjects);		//get the subject input from interface into the forth column name
					
					pst.setString(5, actor_lastName.getText()+','+actor_name.getText());
					pst.setString(6, actress_lastName.getText()+','+actress_name.getText());
					pst.setString(7, director_lastName.getText()+','+director_name.getText());
					pst.setString(8, popularity.getText());
					
					String awards;
					awards = award.getSelectedItem().toString();
					pst.setString(9, awards);
		
					pst.executeUpdate();	//execute query
					
					//Message dialog will appear
					JOptionPane.showMessageDialog(null, "Inserted Successfuly!");
					
					//Displays current movies
					stmt=conn.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT MOVIE_YEAR, MOVIE_LENGTH, MOVIE_TITLE, MOVIE_SUBJECT, MOVIE_ACTOR, MOVIE_ACTRESS, MOVIE_DIRECTOR,"
							+ "MOVIE_POPULARITY, MOVIE_AWARDS FROM MOVIES");
					table.setModel(DbUtils.resultSetToTableModel(rs));
							
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		btnNewButton_1.setBounds(20, 556, 98, 25);
		contentPane.add(btnNewButton_1);
		
		jTable_Display_User = new JScrollPane();
		jTable_Display_User.setBounds(245, 151, 877, 464);
		contentPane.add(jTable_Display_User);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				if(row == -1)
				{}
				else
				{
					String value0 = (table.getModel().getValueAt(row, 0).toString());
					String value1 = (table.getModel().getValueAt(row, 1).toString());
					String value2 = (table.getModel().getValueAt(row, 2).toString());
					
					String value3 = (table.getModel().getValueAt(row, 3).toString());
					String value4 = (table.getModel().getValueAt(row, 4).toString());
					String value5 = (table.getModel().getValueAt(row, 5).toString());
					String value6 = (table.getModel().getValueAt(row, 6).toString());
					
					String value7 = (table.getModel().getValueAt(row, 7).toString());
					
					String value8 = (table.getModel().getValueAt(row, 8).toString());
					
					year.setText(value0);
					length.setText(value1);
					title.setText(value2);
					subject.setSelectedItem(value3);
					String[] namesList;
					if(value4!=null){
						namesList = value4.split(",");
						actor_lastName.setText(namesList [0]);
						actor_name.setText(namesList [1]);
					}
					else{
						actor_lastName.setText("");
						actor_name.setText("");
					}
					
					if(value5!=null){
						namesList = value5.split(",");
						actress_lastName.setText(namesList [0]);
						actress_name.setText(namesList [1]);
					}
					else{
						actress_lastName.setText("");
						actress_name.setText("");
					}
					
					if(value5!=null){
						namesList = value6.split(",");
						director_lastName.setText(namesList [0]);
						director_name.setText(namesList [1]);
					}
					else{
						director_lastName.setText("");
						director_name.setText("");
					}
					
					popularity.setText(value7);
					award.setSelectedItem(value8);
				}
			}
		});
		jTable_Display_User.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Add a new movie");
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		lblNewLabel.setBounds(61, 93, 146, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setHorizontalAlignment(SwingConstants.LEFT);
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYear.setBounds(20, 201, 63, 17);
		contentPane.add(lblYear);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 152, 63, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblLength = new JLabel("Length");
		lblLength.setHorizontalAlignment(SwingConstants.LEFT);
		lblLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLength.setBounds(20, 243, 63, 17);
		contentPane.add(lblLength);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubject.setBounds(20, 283, 63, 17);
		contentPane.add(lblSubject);
		
		JLabel lblActor = new JLabel("Actor");
		lblActor.setHorizontalAlignment(SwingConstants.LEFT);
		lblActor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActor.setBounds(20, 332, 63, 17);
		contentPane.add(lblActor);
		
		JLabel lblActress = new JLabel("Actress");
		lblActress.setHorizontalAlignment(SwingConstants.LEFT);
		lblActress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActress.setBounds(20, 377, 63, 17);
		contentPane.add(lblActress);
		
		JLabel lblDirector = new JLabel("Director");
		lblDirector.setHorizontalAlignment(SwingConstants.LEFT);
		lblDirector.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDirector.setBounds(20, 422, 63, 17);
		contentPane.add(lblDirector);
		
		JLabel lblPopularity = new JLabel("Popularity");
		lblPopularity.setHorizontalAlignment(SwingConstants.LEFT);
		lblPopularity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPopularity.setBounds(20, 472, 63, 17);
		contentPane.add(lblPopularity);
		
		JLabel lblAward = new JLabel("Award");
		lblAward.setHorizontalAlignment(SwingConstants.LEFT);
		lblAward.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAward.setBounds(20, 517, 63, 17);
		contentPane.add(lblAward);
		
		title = new JTextField();
		title.setBounds(89, 149, 146, 20);
		contentPane.add(title);
		title.setColumns(10);
		
		actor_lastName = new JTextField();
		actor_lastName.setToolTipText("LastName");
		actor_lastName.setForeground(new Color(0, 0, 0));
		actor_lastName.setColumns(10);
		actor_lastName.setBounds(89, 332, 69, 20);
		contentPane.add(actor_lastName);
		
		popularity = new JTextField();
		popularity.setColumns(10);
		popularity.setBounds(89, 472, 146, 20);
		contentPane.add(popularity);
		
		String[] messageStrings ={"Yes","No"};
		award = new JComboBox(messageStrings);
		award.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
		award.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		award.setBounds(89, 517, 146, 20);
		contentPane.add(award);
		
		year = new JTextField();
		year.setColumns(10);
		year.setBounds(89, 201, 146, 20);
		contentPane.add(year);
		
		length = new JTextField();
		length.setColumns(10);
		length.setBounds(89, 243, 146, 20);
		contentPane.add(length);
		
		String[] messageStrings_2 ={"Action","Adventure","Comedy","Crime","Drama","Horror","Music","Mystery","Science Fiction","Short","War","Western","Westerns"};
		subject = new JComboBox(messageStrings_2);
		subject.setBounds(89, 283, 146, 20);
		contentPane.add(subject);
		
		actor_name = new JTextField();
		actor_name.setForeground(new Color(0, 0, 0));
		actor_name.setColumns(10);
		actor_name.setBounds(168, 332, 67, 20);
		contentPane.add(actor_name);
		
		JLabel lblNewLabel_3 = new JLabel(",");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(162, 333, 17, 14);
		contentPane.add(lblNewLabel_3);
		
		actress_lastName = new JTextField();
		actress_lastName.setColumns(10);
		actress_lastName.setBounds(89, 377, 69, 20);
		contentPane.add(actress_lastName);
		
		JLabel label = new JLabel(",");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(162, 380, 17, 14);
		contentPane.add(label);
		
		actress_name = new JTextField();
		actress_name.setColumns(10);
		actress_name.setBounds(168, 377, 67, 20);
		contentPane.add(actress_name);
		
		director_lastName = new JTextField();
		director_lastName.setColumns(10);
		director_lastName.setBounds(89, 422, 69, 20);
		contentPane.add(director_lastName);
		
		JLabel label_1 = new JLabel(",");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(162, 425, 17, 14);
		contentPane.add(label_1);
		
		director_name = new JTextField();
		director_name.setColumns(10);
		director_name.setBounds(168, 422, 67, 20);
		contentPane.add(director_name);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				year.setText("");
				length.setText("");
				title.setText("");
				actor_lastName.setText("");
				actor_name.setText("");
				actress_lastName.setText("");
				actress_name.setText("");
				director_lastName.setText("");
				director_name.setText("");
				popularity.setText("");
				subject.setSelectedIndex(0);
				award.setSelectedIndex(0);
			}
		});
		btnReset.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		btnReset.setBounds(128, 556, 98, 25);
		contentPane.add(btnReset);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					conn=DriverManager.getConnection(  
							"jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","cns96");
					
					int row = table.getSelectedRow();
					String value = (table.getModel().getValueAt(row, 2).toString());
					//String query2 = "DELETE FROM MOVIES WHERE MOVIE_ID ="+(row+1);
					String query2 = "DELETE FROM MOVIES WHERE MOVIE_TITLE ="+"\'"+value+"\'";
					
					PreparedStatement pst = conn.prepareStatement(query2);
					pst.executeUpdate();
					
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(2);
					stmt=conn.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT MOVIE_YEAR, MOVIE_LENGTH, MOVIE_TITLE, MOVIE_SUBJECT, MOVIE_ACTOR, MOVIE_ACTRESS, MOVIE_DIRECTOR,"
							+ "MOVIE_POPULARITY, MOVIE_AWARDS FROM MOVIES");
					table.setModel(DbUtils.resultSetToTableModel(rs));
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		btnDelete.setBounds(20, 592, 98, 23);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					conn=DriverManager.getConnection(  
							"jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","cns96");
					
					int row = table.getSelectedRow();
					String value = (table.getModel().getValueAt(row, 2).toString());
					//String query2 = "DELETE FROM MOVIES WHERE MOVIE_ID ="+(row+1);
					String query2 = "DELETE FROM MOVIES WHERE MOVIE_TITLE ="+"\'"+value+"\'";
					
					PreparedStatement pst = conn.prepareStatement(query2);
					pst.executeUpdate();
					
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(2);
					
					String query1="INSERT INTO MOVIES(MOVIE_YEAR, MOVIE_LENGTH, MOVIE_TITLE, MOVIE_SUBJECT, MOVIE_ACTOR, MOVIE_ACTRESS, MOVIE_DIRECTOR, MOVIE_POPULARITY, MOVIE_AWARDS)VALUES(?,?,?,?,?,?,?,?,?)";
					pst = conn.prepareStatement(query1);
					pst.setString(1, year.getText());
					pst.setString(2, length.getText());
					pst.setString(3, title.getText());
					
					String subjects;
					subjects = subject.getSelectedItem().toString();
					pst.setString(4, subjects);
					
					
					pst.setString(5, actor_lastName.getText()+','+actor_name.getText());
					pst.setString(6, actress_lastName.getText()+','+actress_name.getText());
					pst.setString(7, director_lastName.getText()+','+director_name.getText());
					pst.setString(8, popularity.getText());
					
					
					String awards;
					awards = award.getSelectedItem().toString();
					pst.setString(9, awards);
		
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Updated Successfully!");
					
					stmt=conn.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT MOVIE_YEAR, MOVIE_LENGTH, MOVIE_TITLE, MOVIE_SUBJECT, MOVIE_ACTOR, MOVIE_ACTRESS, MOVIE_DIRECTOR,"
							+ "MOVIE_POPULARITY, MOVIE_AWARDS FROM MOVIES");
					table.setModel(DbUtils.resultSetToTableModel(rs));
	
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnUpdate.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		btnUpdate.setBounds(128, 590, 98, 25);
		contentPane.add(btnUpdate);
	}
}

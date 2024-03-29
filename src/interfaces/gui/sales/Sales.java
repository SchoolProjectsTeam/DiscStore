package interfaces.gui.sales;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import main.Application;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JTable;

import logic.business.abstractions.Disc;
import logic.business.auxiliars.CDManager;
import logic.business.auxiliars.DVDManager;
import logic.business.auxiliars.SCManager;
import logic.business.controllers.SalesController;
import logic.business.core.CD;
import logic.business.core.DVD;
import logic.business.core.Song;
import logic.business.core.Store;
import logic.business.core.Video;
import javax.swing.JScrollPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



@SuppressWarnings("serial")
public class Sales extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelCD;
	private JPanel panelDVD;
	private JButton btnSearchCD;
	private JButton btnSearchDVD;
	private JLabel lblIntroduzcaSuCriterioCD;
	private JLabel lblIntroduzcaSuCriterioDVD;
	private JButton buttonAddCD;
	private JButton buttonAddDVD;
	private JTextField textFieldSearchDVD;
	private JTextField textFieldSearchCD;
	private JButton btnCleanListCD;
	private JButton btnCleanListDVD;
	private JButton buttonMoveSC;
	private JButton buttonDel;
	private JLabel lblWarning;
	private	JButton btnBack;

	private JScrollPane scrollPaneDVD;
	private JScrollPane scrollPaneCD;
	private JTable tableCD;	
	private JTable tableDVD;

	private JButton buttonInfo;
	private JScrollPane scrollPaneCont;
	private JTable tableCont;
	private ArrayList<Song>auxSong;
	private ArrayList<Video>auxVideo;
	private ArrayList<Song>auxSCSong;
	private ArrayList<Video>auxSCVideo;

	//test tabla

	String columnas[]={ "Titulo","Album","Artista","ID",""};
	boolean columnasEditables[]={false, false, false, false, true};
	@SuppressWarnings("rawtypes")
	Class data[]={java.lang.Object.class,java.lang.Object.class , java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class };
	DefaultTableModel modelSong = new DefaultTableModel(){
		public boolean isCellEditable(int row, int col){
			return columnasEditables[col];
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int index){
			return data[index];
		}
	};
	DefaultTableModel modelContSong = new DefaultTableModel(){
		public boolean isCellEditable(int row, int col){
			return columnasEditables[col];
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int index){
			return data[index];
		}
	};


	String columnasVideo[]={ "T�tulo","G�nero","Int�rprete","ID",""};
	boolean columnasEditablesVideo[]={false, false, false, false, true};
	@SuppressWarnings("rawtypes")
	Class dataVideo[]={java.lang.Object.class,java.lang.Object.class , java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class };
	DefaultTableModel modelVideo = new DefaultTableModel(){
		public boolean isCellEditable(int row, int col){
			return columnasEditablesVideo[col];
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int index){
			return dataVideo[index];
		}
	};

	DefaultTableModel modelContVideo = new DefaultTableModel(){
		public boolean isCellEditable(int row, int col){
			return columnasEditablesVideo[col];
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int index){
			return dataVideo[index];
		}
	};

	
	String columnasPrefab[]={"Nombre","ID","Tipo","Costo",""};
	boolean columnasEditablesPrefab[]={false,false,false,false,true};
	@SuppressWarnings("rawtypes")
	Class dataPrefab[]={java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Boolean.class};

	DefaultTableModel modelPrefab = new DefaultTableModel(){
		public boolean isCellEditable(int row, int col){
			return columnasEditablesPrefab[col];
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int index){
			return dataPrefab[index];
		}
	};

	private SalesController controller;
	private CDManager cdManager;
	private DVDManager dvdManager;
	private SCManager scManager;
	private JTabbedPane tabbedPane_1;
	private JPanel panelConfirmation;
	private JButton btnShowSC;
	private JButton btnNewButton;
	private JPanel panelPrefab;
	private JButton btnShowSCPrefab;
	private JButton buttonMoveSCPrefab;
	private JScrollPane scrollPane;
	private JTable tablePrefab;
	private JButton btnRefresh;



	/**
	 * Create the frame.
	 */
	public Sales(Store store) {		
		drawWindow();
		this.controller = store.getSalesController();
		this.scManager = controller.getSCManager();
		this.cdManager = controller.getCDManager();
		this.dvdManager = controller.getDVDManager();
		auxSong = new ArrayList<Song>();
		auxVideo = new ArrayList<Video>();
		auxSCSong = new ArrayList<Song>();
		auxSCVideo = new ArrayList<Video>();
	}
	private void drawWindow(){
		setTitle("Ventana De Venta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(new MigLayout("", "[82px][387px][40px][449px]", "[23px][591px]"));
		JLabel lblNewLabel = new JLabel("Gesti\u00F3n de Venta");
		contentPane.add(lblNewLabel, "cell 0 0,alignx left,aligny center");

		btnBack = new JButton("Volver");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goMain();
			}
		});

		lblWarning = new JLabel("Warning");
		contentPane.add(lblWarning, "cell 2 0,alignx right,aligny center");

		btnNewButton = new JButton("Ver historial de ventas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showSellReports();
			}
		});
		contentPane.add(btnNewButton, "flowx,cell 3 0,alignx right");
		contentPane.add(btnBack, "cell 3 0,alignx right,aligny top");
		lblWarning.setVisible(false);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "cell 0 1 3 1,grow");
		tabbedPane.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e){
				resetModelCont();
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}
		});

		panelCD = new JPanel();
		tabbedPane.addTab("Venta CD", null, panelCD, null);
		panelCD.setLayout(new MigLayout("", "[7.00][109.00,grow][251.00][grow][]", "[30.00][36.00][9.00][418.00,grow][19.00]"));
		lblIntroduzcaSuCriterioCD = new JLabel("Introduzca su criterio de busqueda");
		panelCD.add(lblIntroduzcaSuCriterioCD, "cell 1 0");


		textFieldSearchCD = new JTextField();
		textFieldSearchCD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					addToSearchListSong(modelSong);
				}
			}
		});
		panelCD.add(textFieldSearchCD, "cell 1 1 2 1,growx");
		textFieldSearchCD.setColumns(10);

		btnSearchCD = new JButton("Buscar");
		btnSearchCD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addToSearchListSong(modelSong);
			}
		});
		panelCD.add(btnSearchCD, "cell 3 1");

		scrollPaneCD = new JScrollPane();
		panelCD.add(scrollPaneCD, "cell 1 3 3 1,grow");

		tableCD = new JTable();
		modelSong.setColumnIdentifiers(columnas);
		tableCD.setModel(modelSong);
		tableCD.getColumnModel().getColumn(0).setPreferredWidth(160);
		tableCD.getColumnModel().getColumn(1).setPreferredWidth(160);
		tableCD.getColumnModel().getColumn(2).setPreferredWidth(160);
		tableCD.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableCD.getColumnModel().getColumn(4).setPreferredWidth(35);
		tableCD.getColumnModel().getColumn(0).setResizable(false);
		tableCD.getColumnModel().getColumn(1).setResizable(false);
		tableCD.getColumnModel().getColumn(2).setResizable(false);
		tableCD.getColumnModel().getColumn(3).setResizable(false);
		tableCD.getColumnModel().getColumn(3).setResizable(false);



		scrollPaneCD.setViewportView(tableCD);


		btnCleanListCD = new JButton("Limpiar Lista");
		btnCleanListCD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableCD.getRowCount()>0){
					cleanTableSearch(modelSong);
				}
				else{
					JOptionPane.showMessageDialog(null, "La tabla ya se encuentra vacia");
				}
			}
		});
		panelCD.add(btnCleanListCD, "cell 1 4");

		buttonAddCD = new JButton("A\u00F1adir");
		buttonAddCD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane_1.setSelectedIndex(0);
				moveToVerifyListSong(tableCD, modelContSong, 12, auxSong);
			}
		});
		panelCD.add(buttonAddCD, "cell 2 4 2 1,alignx right");


		//////////////////////////////////////////////////////////////////


		panelDVD = new JPanel();
		tabbedPane.addTab("Venta DVD", null, panelDVD, null);
		panelDVD.setLayout(new MigLayout("", "[7.00][109.00,grow][251.00][grow][]", "[30.00][36.00][9.00][418.00,grow][19.00]"));

		lblIntroduzcaSuCriterioDVD = new JLabel("Introduzca su criterio de busqueda");
		panelDVD.add(lblIntroduzcaSuCriterioDVD, "cell 1 0");

		textFieldSearchDVD = new JTextField();
		textFieldSearchDVD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					addToSearchListVideo(modelVideo);
				}
			}
		});
		panelDVD.add(textFieldSearchDVD, "cell 1 1 2 1,growx");
		textFieldSearchDVD.setColumns(10);

		btnSearchDVD = new JButton("Buscar");
		btnSearchDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addToSearchListVideo(modelVideo);
			}
		});
		panelDVD.add(btnSearchDVD, "cell 3 1");

		scrollPaneDVD = new JScrollPane();
		panelDVD.add(scrollPaneDVD, "cell 1 3 3 1,grow");

		tableDVD = new JTable();
		scrollPaneDVD.setViewportView(tableDVD);
		modelVideo.setColumnIdentifiers(columnasVideo);
		tableDVD.setModel(modelVideo);
		tableDVD.getColumnModel().getColumn(0).setPreferredWidth(160);
		tableDVD.getColumnModel().getColumn(1).setPreferredWidth(160);
		tableDVD.getColumnModel().getColumn(2).setPreferredWidth(160);
		tableDVD.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableDVD.getColumnModel().getColumn(4).setPreferredWidth(35);
		tableDVD.getColumnModel().getColumn(0).setResizable(false);
		tableDVD.getColumnModel().getColumn(1).setResizable(false);
		tableDVD.getColumnModel().getColumn(2).setResizable(false);
		tableDVD.getColumnModel().getColumn(3).setResizable(false);
		tableDVD.getColumnModel().getColumn(3).setResizable(false);

		btnCleanListDVD = new JButton("Limpiar Lista");
		btnCleanListDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cleanTableSearch(modelVideo);
			}
		});
		panelDVD.add(btnCleanListDVD, "cell 1 4");

		buttonAddDVD = new JButton("A\u00F1adir");
		buttonAddDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane_1.setSelectedIndex(0);
				moveToVerifyListVideo(tableDVD, modelContVideo, 7, auxVideo);
			}
		});
		panelDVD.add(buttonAddDVD, "cell 2 4 2 1,alignx right");
		modelContSong.setColumnIdentifiers(columnas);

		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane_1, "cell 3 1,grow");
		tabbedPane_1.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e){
				refreshPrefab();
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}
		});

		panelConfirmation = new JPanel();
		tabbedPane_1.addTab("Confirmaci�n a disco", null, panelConfirmation, null);
		panelConfirmation.setLayout(new MigLayout("", "[150px:n:150px][109.00][100px:n:130px]", "[30.00][49.00][505.00][52.00]"));

		JLabel lblProducts = new JLabel("Productos agregados a contenedor");
		panelConfirmation.add(lblProducts, "cell 0 1");

		buttonInfo = new JButton("?");
		panelConfirmation.add(buttonInfo, "cell 1 1 2 1,alignx right");
		buttonInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				msgInfo();
			}
		});

		scrollPaneCont = new JScrollPane();
		panelConfirmation.add(scrollPaneCont, "cell 0 2 3 1,growy");


		tableCont = new JTable();
		scrollPaneCont.setViewportView(tableCont);


		buttonDel = new JButton("Eliminar");
		panelConfirmation.add(buttonDel, "cell 0 3,alignx right");
		buttonDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableCont.getModel().equals(modelContSong)){
					delVerifySong(tableCont, modelContSong);
				}
				else{
					delVerifyVideo(tableCont, modelContVideo);
				}
			}
		});


		buttonMoveSC = new JButton("Enviar Al Carrito");
		panelConfirmation.add(buttonMoveSC, "flowx,cell 1 3,alignx center");
		buttonMoveSC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableCont.getRowCount()>0){
					if(tableCont.getModel().equals(modelContSong)){
						moveToShoppingcar(addSongsToDisc());
					}
					else{
						moveToShoppingcar(addVideosToDisc());
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Debe agregar al menos un elemento para enviar al carrito");	
				}
			}
		});



		btnShowSC = new JButton("Ver Carrito");
		btnShowSC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeShoppingcar();
			}
		});
		panelConfirmation.add(btnShowSC, "cell 2 3,alignx center");
		
		panelPrefab = new JPanel();
		tabbedPane_1.addTab("Discos Prefabricados", null, panelPrefab, null);
		panelPrefab.setLayout(new MigLayout("", "[150px:n:150px,grow][109.00][198.00px:n:130px]", "[29.00][49.00][49,grow][505][52px]"));
		
		btnShowSCPrefab = new JButton("Ver Carrito");
		btnShowSCPrefab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeShoppingcar();
			}
		});
		
		scrollPane = new JScrollPane();
		panelPrefab.add(scrollPane, "cell 0 1 3 3,grow");
		
		tablePrefab = new JTable();
		tablePrefab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					if(tablePrefab.getRowCount()>0){
					int a = tablePrefab.getSelectedRow();	
					JOptionPane.showMessageDialog(null, controller.getHistory().get(a).getStringContent());
					}
				}
			}
		});
		scrollPane.setViewportView(tablePrefab);
		modelPrefab.setColumnIdentifiers(columnasPrefab);
		tablePrefab.setModel(modelPrefab);
		tablePrefab.getColumnModel().getColumn(0).setPreferredWidth(160);
		tablePrefab.getColumnModel().getColumn(1).setPreferredWidth(50);
		tablePrefab.getColumnModel().getColumn(2).setPreferredWidth(50);
		tablePrefab.getColumnModel().getColumn(3).setPreferredWidth(80);
		tablePrefab.getColumnModel().getColumn(4).setPreferredWidth(35);
		tablePrefab.getColumnModel().getColumn(0).setResizable(false);
		tablePrefab.getColumnModel().getColumn(1).setResizable(false);
		tablePrefab.getColumnModel().getColumn(2).setResizable(false);
		tablePrefab.getColumnModel().getColumn(3).setResizable(false);
		tablePrefab.getColumnModel().getColumn(3).setResizable(false);
		
		btnRefresh = new JButton("Refrescar");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshPrefab();
			}
		});
		panelPrefab.add(btnRefresh, "cell 0 4");
		
		buttonMoveSCPrefab = new JButton("Enviar Al Carrito");
		buttonMoveSCPrefab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prefabToSC();
				tablePrefab.clearSelection();
				for(int i = 0; i<modelPrefab.getRowCount();i++)
				tablePrefab.setValueAt(false, i, 4);
			}
		});
		panelPrefab.add(buttonMoveSCPrefab, "cell 1 4,alignx center");
		panelPrefab.add(btnShowSCPrefab, "cell 2 4,alignx center");


		textFieldSearchCD.requestFocus();
	}
	//methods
	public void resetModelCont(){
		if(tabbedPane.getSelectedIndex()==0){
			resetModelContSong();
		}
		else{
			resetModelContVideo();
		}
	}

	public void resetModelContSong(){
		modelContSong.setColumnIdentifiers(columnas);
		tableCont.setModel(modelContSong);
		tableCont.getColumnModel().getColumn(0).setPreferredWidth(160);
		tableCont.getColumnModel().getColumn(1).setPreferredWidth(160);
		tableCont.getColumnModel().getColumn(2).setPreferredWidth(160);
		tableCont.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableCont.getColumnModel().getColumn(4).setPreferredWidth(35);
		tableCont.getColumnModel().getColumn(0).setResizable(false);
		tableCont.getColumnModel().getColumn(1).setResizable(false);
		tableCont.getColumnModel().getColumn(2).setResizable(false);
		tableCont.getColumnModel().getColumn(3).setResizable(false);
		tableCont.getColumnModel().getColumn(3).setResizable(false);
	}
	public void resetModelContVideo(){
		modelContVideo.setColumnIdentifiers(columnasVideo);
		tableCont.setModel(modelContVideo);
		tableCont.getColumnModel().getColumn(0).setPreferredWidth(160);
		tableCont.getColumnModel().getColumn(1).setPreferredWidth(160);
		tableCont.getColumnModel().getColumn(2).setPreferredWidth(160);
		tableCont.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableCont.getColumnModel().getColumn(4).setPreferredWidth(35);
		tableCont.getColumnModel().getColumn(0).setResizable(false);
		tableCont.getColumnModel().getColumn(1).setResizable(false);
		tableCont.getColumnModel().getColumn(2).setResizable(false);
		tableCont.getColumnModel().getColumn(3).setResizable(false);
		tableCont.getColumnModel().getColumn(3).setResizable(false);
	}

	public void goMain(){
		Application.changeWindow(this, Application.WindowType.main);
	}
	public void goShopingcar(){
		Application.changeWindow(this, Application.WindowType.shoppingcar);
	}
	public void changeShoppingcar() {
		Application.openChildWindow(this, Application.WindowType.shoppingcar);
	}

	public void msgInfo(){
		JOptionPane.showMessageDialog(null,"Los costos base son:\nCD  : 12.50$\nDVD : 15.50$\nY el precio de cada"
				+ " producto es: 2.50$\n\nEsta lista contiene los elementos que conformar�n vuestro CD o DVD, al enviar"
				+ " al carrito enviar� los \nproductos en formato de disco, quedando registrado el mismo con un id "
				+ "asignado por el sistema.");
	}

	public void cleanTableSearch(DefaultTableModel modelOrigen){
		int centinel = modelOrigen.getRowCount();
		for(int i=0; i<centinel; i++){
			modelOrigen.removeRow(0);
		}
	}

	public int asignId(){
		return controller.getReportId();
	}

	public void showSellReports(){
		String text = "Reportes: \n";
		for(int i=0;i<controller.getSellReports().size();i++){
			text += "Reporte #"+ controller.getSellReports().get(i).getId()+"\n"+"Disco ID: "
					+controller.getSellReports().get(i).getDisc().getID() +"  Tipo :   "
					+controller.getSellReports().get(i).getDisc().getType()+"  - "
					+"  Precio:  " +controller.getSellReports().get(i).getCost()+"$"+ "   Por:  " 
					+ controller.getSellReports().get(i).getWorkerName() + "\n" + controller.getSellReports().get(i).getContent() +"\n";
		}
		JOptionPane.showMessageDialog(null , text);
	}
	
	public void refreshPrefab(){
		cleanTableSearch(modelPrefab);
		for(Disc disc : controller.getHistory()){
			Object rows[]={disc.getName(),disc.getID(),disc.getType(),disc.calculateCost(), false};
			modelPrefab.addRow(rows);
		}
	}
	
	
	

	//Metodos para canciones
	public ArrayList<Song> searchSongs(){
		return cdManager.search(textFieldSearchCD.getText());
	}	

	public void addToSearchListSong(DefaultTableModel modelOrigen){
		auxSong.clear();
		cleanTableSearch(modelOrigen);
		if(!textFieldSearchCD.getText().equals("")){
			ArrayList<Song> auxiliar = searchSongs();
			for (Song song : auxiliar) {
				Object rowns[] = {song.getTitle(), song.getAlbum(), song.getAuthor(),song.getID(), false};
				modelOrigen.addRow(rowns);		
			}
			lblWarning.setVisible(false);
			auxSong=searchSongs();
		}	
		else{
			lblWarning.setText("Debe introducir su criterio de b�squeda en la caja de texto");
			lblWarning.setVisible(true);
		}
	}


	public void moveToVerifyListSong(JTable tableOrigen, DefaultTableModel modelSC, int max, ArrayList<Song> auxSearch){
		ArrayList<Song>auxiliar = searchSelectedSong(tableOrigen, auxSearch);
		if(!(auxiliar.size() > (max-modelSC.getRowCount()))){

			modelContSong.setColumnIdentifiers(columnas);
			resetModelCont();
			for (Song song : auxiliar) {
				if(!auxSCSong.contains(song)){
					Object rowns[] = {song.getTitle(), song.getAlbum(), song.getAuthor(),song.getID(), false};
					modelSC.addRow(rowns);	
					auxSCSong.add(song);
				}
				else{
					JOptionPane.showMessageDialog(null, "Ya existe la cancion: "+song.getTitle());
				}
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "El limite maximo de productos a agregar es " + max);
		}
	}

	public ArrayList<Song> searchSelectedSong(JTable table, ArrayList<Song> auxSong){
		ArrayList<Song>search = new ArrayList<Song>();
		boolean confirm = false;
		boolean secure = false;
		for(int i = 0; i<table.getRowCount(); i++){
			if((boolean) table.getValueAt(i, 4)){
				int id = (int) table.getValueAt(i, 3);				
				for(int j = 0; j<auxSong.size() && !secure;j++){
					if(auxSong.get(j).getID() == id){
						search.add(auxSong.get(j));
						confirm = true;
						secure = true;
					}
				}
				secure = false;
			}
		}
		if(!confirm){	
			JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un elemento para agregar");	
		}
		return search;
	}

	public void delVerifySong(JTable tableCont, DefaultTableModel modelCont){
		boolean confirm = false;
		for(int i = 0; i<tableCont.getRowCount(); i++){
			if((boolean) tableCont.getValueAt(i, 4)){			
				modelCont.removeRow(i);
				auxSCSong.remove(i);
				confirm = true;
				i--;
			}		
		}		
		if(!confirm){	
			JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un elemento para eliminar");	
		}
	}

	public Disc addSongsToDisc(){
		CD cd = new CD();
		for (Song song : auxSCSong) {
			cd.addItem(song);	
		}
		return cd;
	}

	public String assignCDName(){
		String name = auxSCSong.get(0).getAuthor();
		boolean more = false;
		for(int i = 1; i<auxSCSong.size() && !more;i++){
			if(!auxSCSong.get(i).getAuthor().equals(name)){
				more = true;
			}
		}
		if(more){
			name += " y otros";
		}
		return name;
	}

	public void moveToShoppingcar(Disc disc){
		boolean centinel = false;
		int auxiliarID = 0;
		for(Disc product : controller.getHistory()){
			if(disc.getProducts().equals(product.getProducts())){
				centinel = true;
				auxiliarID = product.getID();
			}
		}
		if(centinel){
			disc.setID(auxiliarID);
			if(disc instanceof CD){		
				disc.setType("CD");
				disc.setName(assignCDName());
				auxSCSong.clear();
				cleanTableSearch(modelContSong);
			}
			else{
				disc.setType("DVD");
				disc.setName(assignDVDName());
				auxSCVideo.clear();
				cleanTableSearch(modelContVideo);
			}
		}
		else{
			disc.setID(asignId());
			if(disc instanceof CD){		
				disc.setType("CD");
				disc.setName(assignCDName());
				auxSCSong.clear();
				cleanTableSearch(modelContSong);
			}else{
				disc.setType("DVD");
				disc.setName(assignDVDName());
				auxSCVideo.clear();
				cleanTableSearch(modelContVideo);
			}
			controller.addHistory(disc);
		}
		scManager.addItem(disc);
	}



	//Metodos para Videos
	public ArrayList<Video> searchVideos(){
		return dvdManager.search(textFieldSearchDVD.getText());
	}	

	public void addToSearchListVideo(DefaultTableModel modelOrigen){
		auxVideo.clear();
		cleanTableSearch(modelOrigen);
		if(!textFieldSearchDVD.getText().equals("")){
			ArrayList<Video> auxiliar = searchVideos();
			for (Video video : auxiliar) {
				Object rowns[] = {video.getTitle(),video.getGenre(), video.getInterpreter(),video.getID(), false};
				modelOrigen.addRow(rowns);		
			}
			lblWarning.setVisible(false);
			auxVideo=searchVideos();
		}	
		else{
			lblWarning.setText("Debe introducir su criterio de b�squeda en la caja de texto");
			lblWarning.setVisible(true);
		}
	}


	public void moveToVerifyListVideo(JTable tableOrigen, DefaultTableModel modelSC, int max, ArrayList<Video> auxSearch){
		ArrayList<Video>auxiliar = searchSelectedVideo(tableOrigen, auxSearch);
		if(!(auxiliar.size() > (max-modelSC.getRowCount()))){
			modelSC.setColumnIdentifiers(columnasVideo);
			resetModelCont();
			for (Video video : auxiliar) {
				if(!auxSCVideo.contains(video)){
					Object rowns[] = {video.getTitle(),video.getGenre() ,video.getInterpreter(),video.getID(), false};
					modelContVideo.addRow(rowns);	
					auxSCVideo.add(video);					
				}
				else{
					JOptionPane.showMessageDialog(null, "Ya existe el video: "+video.getTitle());
				}
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "El limite maximo de productos a agregar es " + max);
		}
	}

	public ArrayList<Video> searchSelectedVideo(JTable table, ArrayList<Video> auxSearch){
		ArrayList<Video>search = new ArrayList<Video>();
		boolean confirm = false;
		boolean secure = false;
		for(int i = 0; i<table.getRowCount(); i++){
			if((boolean) table.getValueAt(i, 4)){
				int id = (int) table.getValueAt(i, 3);				
				for(int j = 0; j<auxSearch.size() && !secure;j++){
					if(auxSearch.get(j).getID() == id){
						search.add(auxSearch.get(j));
						confirm = true;
						secure = true;
					}
				}
				secure = false;
			}
		}
		if(!confirm){	
			JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un elemento para agregar");	
		}
		return search;
	}

	public void delVerifyVideo(JTable tableCont, DefaultTableModel modelCont){
		boolean confirm = false;
		for(int i = 0; i<tableCont.getRowCount(); i++){
			if((boolean) tableCont.getValueAt(i, 4)){
				modelCont.removeRow(i);
				auxSCVideo.remove(i);
				confirm = true;
				i--;
			}
		}
		if(!confirm){	
			JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un elemento para eliminar");	
		}
	}


	public Disc addVideosToDisc(){
		DVD dvd = new DVD();
		for (Video video : auxSCVideo) {
			dvd.addItem(video);	
		}
		return dvd;
	}

	public String assignDVDName(){
		String name = auxSCVideo.get(0).getInterpreter();
		boolean more = false;
		for(int i = 1; i<auxSCVideo.size() && !more;i++){
			if(!auxSCVideo.get(i).getInterpreter().equals(name)){
				more = true;
			}
		}
		if(more){
			name += " y otros";
		}
		return name;
	}


	////Prefabs
	
	public void prefabToSC(){
		boolean centinel = false;
		for(int i = 0; i<tablePrefab.getRowCount() ; i++){
			if((boolean)modelPrefab.getValueAt(i, 4)){
				scManager.addItem(controller.getHistory().get(i));
				centinel = true;
			}
		}
		if(!centinel){
			JOptionPane.showMessageDialog(null,"Seleccione al menos un elemento para a�adir al carrito");
		}
	}

	

}

package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.servicios.UsuarioBeanRemote;

import enums.Perfil;
import objetos.Usuario;

import java.awt.event.KeyAdapter;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class FrameListarUsuarios extends JFrame {

	private JFrame frmBuscarUsuarios;
	private JTextField txtBusqueda;
	private JTable tablaUsuarios;

	@SuppressWarnings("rawtypes")
	TableRowSorter filtro;
	DefaultTableModel model;
	TableRowSorter<TableModel> ordenarTabla;

	@SuppressWarnings("rawtypes")
	private JComboBox comboFiltro;

	public Window getBuscarUsuarios() {
		return frmBuscarUsuarios;
	}

	/**
	 * Create the application.
	 */
	public FrameListarUsuarios() {
		initialize();

		frmBuscarUsuarios.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frmBuscarUsuarios = new JFrame();
		frmBuscarUsuarios.setResizable(false);
		frmBuscarUsuarios.setTitle("Eliminar Usuario");
		frmBuscarUsuarios.setBounds(100, 100, 490, 405);
		frmBuscarUsuarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBuscarUsuarios.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 51, 484, 268);
		frmBuscarUsuarios.getContentPane().add(scrollPane);

		tablaUsuarios = new JTable();
		tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaUsuarios.setForeground(Color.DARK_GRAY);
		tablaUsuarios.setFont(new Font("Tahoma", Font.BOLD, 13));
		tablaUsuarios.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablaUsuarios);
		try {
			cargarTabla();
		} catch (NamingException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}

		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setForeground(Color.DARK_GRAY);
		lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFiltro.setBounds(20, 20, 50, 20);
		frmBuscarUsuarios.getContentPane().add(lblFiltro);

		comboFiltro = new JComboBox();
		comboFiltro.setForeground(Color.DARK_GRAY);
		comboFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboFiltro.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Usuario", "Apellido"}));
		comboFiltro.setBounds(65, 18, 100, 25);
		frmBuscarUsuarios.getContentPane().add(comboFiltro);

		JLabel lblBusqueda = new JLabel("Apellido/Usuario");
		lblBusqueda.setForeground(Color.DARK_GRAY);
		lblBusqueda.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBusqueda.setBounds(205, 20, 120, 20);
		frmBuscarUsuarios.getContentPane().add(lblBusqueda);

		txtBusqueda = new JTextField();
		txtBusqueda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(comboFiltro.getSelectedItem().equals("Usuario")) {
					filtro.setRowFilter(RowFilter.regexFilter(txtBusqueda.getText(), 1));
				}else if(comboFiltro.getSelectedItem().equals("Apellido")) {
					filtro.setRowFilter(RowFilter.regexFilter("(?i)"+txtBusqueda.getText(), 3));
				}
			}
		});
		filtro = new TableRowSorter(model);
		tablaUsuarios.setRowSorter(filtro);

		txtBusqueda.setForeground(Color.DARK_GRAY);
		txtBusqueda.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtBusqueda.setBounds(329, 18, 145, 23);
		frmBuscarUsuarios.getContentPane().add(txtBusqueda);
		txtBusqueda.setColumns(10);


		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBuscarUsuarios.dispose();
			}
		});
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setBounds(155, 330, 100, 35);
		frmBuscarUsuarios.getContentPane().add(btnCancelar);


		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(tablaUsuarios.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "No hay registros seleccionados");
				}else {
					int row = tablaUsuarios.getSelectedRow();
					String nombre = tablaUsuarios.getModel().getValueAt(row, 2).toString();
					String apellido = tablaUsuarios.getModel().getValueAt(row, 3).toString();
					String usuario = tablaUsuarios.getModel().getValueAt(row, 1).toString();
					String perfil = tablaUsuarios.getModel().getValueAt(row, 4).toString();

					Usuario usu = new Usuario();
					usu.setNombre(nombre);
					usu.setApellido(apellido);
					usu.setUsuario(usuario);
					usu.setPerfil(Perfil.valueOf(perfil));

					FrameModificarUsuario frmModificar = new FrameModificarUsuario(usu);
					frmModificar.setVisible(true);

					frmModificar.addWindowListener(new java.awt.event.WindowAdapter() {
						@Override
						public void windowClosing(java.awt.event.WindowEvent windowEvent) {
							try {
								recargarPanel();
							} catch (NamingException e) {
								JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
							}
						}
					});
				}
			}
		});
		btnModificar.setForeground(Color.DARK_GRAY);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnModificar.setBounds(374, 330, 100, 35);
		frmBuscarUsuarios.getContentPane().add(btnModificar);


		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(tablaUsuarios.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "No hay registros seleccionados");
				}else {
					int row = tablaUsuarios.getSelectedRow();
					Long idUsuario = (Long)tablaUsuarios.getModel().getValueAt(row, 0);
					Usuario usu = new Usuario(idUsuario);

					FrameBajaUsuario frmBajaUsuario = new FrameBajaUsuario(usu);
					frmBajaUsuario.setVisible(true);

					frmBajaUsuario.addWindowListener(new java.awt.event.WindowAdapter() {
						@Override
						public void windowClosing(java.awt.event.WindowEvent windowEvent) {
							model.removeRow(tablaUsuarios.getSelectedRow());
						}
					});
				}
			}
		});
		btnEliminar.setForeground(Color.DARK_GRAY);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEliminar.setBounds(265, 330, 100, 35);
		frmBuscarUsuarios.getContentPane().add(btnEliminar);
	}

	public void cargarTabla() throws NamingException {

		UsuarioBeanRemote altausuariobean = (UsuarioBeanRemote) InitialContext
				.doLookup("ProyectoEJB/UsuarioBean!com.servicios.UsuarioBeanRemote");

		LinkedList<Usuario> usuarios = altausuariobean.obtenerTodosUsuarios();

		String[] columnas = new String[] {"ID", "Usuario", "Nombre", "Apelido", "Perfil"};

		Object[][] datos = new Object[usuarios.size()][5];

		int fila = 0;

		for(Usuario u : usuarios){
			datos[fila][0] = u.getIdUsuario();
			datos[fila][1] = u.getUsuario();
			datos[fila][2] = u.getNombre();
			datos[fila][3] = u.getApellido();
			datos[fila][4] = u.getPerfil();
			fila++;
		}

		model = new DefaultTableModel(datos, columnas) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(5);
				tablaUsuarios.getColumnModel().getColumn(0).setMinWidth(5);
				tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(100);
				tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(75);
				tablaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(75);
				tablaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(60);
				return String.class;
			}
		};
		tablaUsuarios.setModel(model);

		ordenarTabla = new TableRowSorter<TableModel>(model);
		tablaUsuarios.setRowSorter(ordenarTabla);
	}

	public void recargarPanel() throws NamingException{
		cargarTabla();
		this.revalidate();
		this.repaint();
	}
}
package interfaz;

import java.awt.Window;

import javax.swing.JFrame;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.servicios.EventosClinicosBeanRemote;

import objetos.EventoClinico;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class FrameEventosClinicos {

	private JFrame frmEventosClinicos;
	private JTable tablaEventos;

	DefaultTableModel model;
	TableRowSorter<TableModel> ordenarTabla;

	public Window getEventosClinicos() {
		return frmEventosClinicos;
	}

	/**
	 * Create the application.
	 */
	public FrameEventosClinicos() {
		initialize();

		frmEventosClinicos.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEventosClinicos = new JFrame();
		frmEventosClinicos.setResizable(false);
		frmEventosClinicos.setTitle("Eventos Clinicos");
		frmEventosClinicos.setBounds(100, 100, 700, 450);
		frmEventosClinicos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEventosClinicos.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 694, 392);
		frmEventosClinicos.getContentPane().add(scrollPane);

		tablaEventos = new JTable();
		tablaEventos.setRowSelectionAllowed(false);
		tablaEventos.setForeground(Color.DARK_GRAY);
		tablaEventos.setFont(new Font("Tahoma", Font.BOLD, 13));
		tablaEventos.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablaEventos);

		JButton btnMostrarEventosClinicos = new JButton("Mostrar Eventos Clinicos");
		btnMostrarEventosClinicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cargarTabla();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		});
		btnMostrarEventosClinicos.setForeground(Color.DARK_GRAY);
		btnMostrarEventosClinicos.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMostrarEventosClinicos.setBounds(0, 391, 694, 30);
		frmEventosClinicos.getContentPane().add(btnMostrarEventosClinicos);
	}

	public long edadTernera(Date fechaNacimiento, Date fechaEnfermedad) {

		long dias = ((fechaEnfermedad.getTime() - fechaNacimiento.getTime())/86400000);

		return dias;
	}

	@SuppressWarnings("serial")
	public void cargarTabla() throws NamingException {
		EventosClinicosBeanRemote eventosclinicosbean = (EventosClinicosBeanRemote) InitialContext
				.doLookup("ProyectoEJB/EventosClinicosBean!com.servicios.EventosClinicosBeanRemote");

		LinkedList<EventoClinico> eventos = eventosclinicosbean.obtenerTodosEventos();

		String[] columnas = new String[] {"Ternera", "Enfermedad", "Fecha Nacimiento", "Fecha Inicio", "Fecha Fin", "Edad (dias)"};

		Object[][] datos = new Object[eventos.size()][6];

		int fila = 0;

		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

		for(EventoClinico e : eventos){
			datos[fila][0] = String.valueOf(e.getTernera().getNumCaravana());
			datos[fila][1] = String.valueOf(e.getEnfermedad().getNombre());
			datos[fila][2] = String.valueOf(formatoFecha.format(e.getTernera().getFechaNacimiento()));
			datos[fila][3] = String.valueOf(formatoFecha.format(e.getFechaInicio()));
			datos[fila][4] = String.valueOf(formatoFecha.format(e.getFechaFin()));
			datos[fila][5] = String.valueOf(edadTernera(e.getTernera().getFechaNacimiento(), e.getFechaInicio()));
			fila++;
		}

		DefaultTableModel model = new DefaultTableModel(datos, columnas) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}
		};
		tablaEventos.setModel(model);

		ordenarTabla = new TableRowSorter<TableModel>(model);
		tablaEventos.setRowSorter(ordenarTabla);
	}
}
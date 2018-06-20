/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Pelicula;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame {

    public JLabel lblNombre, lblClasificacion, lblDirector, lblPais, lblAño;

    public JTextField nombre, director, pais, año;
    public JComboBox clasificacion;

    ButtonGroup año = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Cinepolix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblNombre);
        container.add(lblClasificacion);
        container.add(lblDirector);
        container.add(lblPais);
        container.add(lblAño);
        container.add(nombre);
        container.add(clasificacion);
        container.add(director);
        container.add(pais);
        container.add(año);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600, 600);
        eventos();

    }

    private void agregarLabels() {
        lblNombre = new JLabel("Nombre");
        lblClasificacion = new JLabel("Clasificacion");
        lblDirector = new JLabel("Director");
        lblPais = new JLabel("Pais");
        lblAño = new JLabel("Año");
        lblClasificacion.setBounds(10, 10, ANCHOC, ALTOC);
        lblDirector.setBounds(10, 60, ANCHOC, ALTOC);
        lblPais.setBounds(10, 100, ANCHOC, ALTOC);
        lblAño.setBounds(10, 140, ANCHOC, ALTOC);
    }

    private void formulario() {
        nombre = new JTextField();
        director = new JTextField();
        clasificacion = new JComboBox();
        pais = new JTextField();
        año = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();

        clasificacion.addItem("G (toto espectador)");
        clasificacion.addItem("PG (menores acompañados con sus padres)");
        clasificacion.addItem("14A(menores acompañados por adultos)");
        clasificacion.addItem("18A (menores de 18 acompañados por adultos)");
        clasificacion.addItem("R (restringido,ningun menor)");
        clasificacion.addItem("A (mayores de 18 años)");

        año = new ButtonGroup();
        año.add(si);
        año.add(no);
        //-------------------------------------------
        nombre.setBounds(140, 5, ANCHOC, ALTOC);
        director.setBounds(140, 10, ANCHOC, ALTOC);
        clasificacion.setBounds(140, 60, ANCHOC, ALTOC);
        pais.setBounds(140, 100, ANCHOC, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140, 50, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);

        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));

    }

    private void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Nombre");
        tm.addColumn("Director");
        tm.addColumn("Pais");
        tm.addColumn("Clasificacion");
        tm.addColumn("Año");
        tm.addColumn("En proyeccion");

        FiltroDao fd = new FiltroDao();
        ArrayList<Pelicula> filtros = fd.readAll();

        for (Pelicula fi : filtros) {
            tm.addRow(new Object[]{fi.getDirector(), fi.getClasificacion(), fi.getPais(), fi.getAño()});
        }

        resultados.setModel(tm);

    }

    private void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Pelicula f = new Pelicula(director.getText(), clasificacion.getSelectedItem().toString(), Integer.parseInt(año.getText()), true);

                if (no.isSelected()) {
                    f.setAño(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Pelicula registrada con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema con la creación de esa pelicula.");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Pelicula f = new Pelicula(director.getText(), clasificacion.getSelectedItem().toString(), Integer.parseInt(año.getText()), true);

                if (no.isSelected()) {
                    f.setAño(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro modificado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de creación de este filtro.");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Pelicula f = new Pelicula(director.getText(), clasificacion.getSelectedItem().toString(), Integer.parseInt(stock.getText()), true);
                if (fd.delete(director.getText())) {
                    JOptionPane.showMessageDialog(null, "Pelicula eliminado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar esta pelicula.");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Pelicula f = fd.read(director.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "El Filtro buscado no ha sido encontrado");
                } else {

                    director.setText(f.getDirector());
                    clasificacion.setSelectedItem(f.getClasificacion());
                    pais.setText((f.getPais()));
                    año.setText(Integer.toString(f.getAño()));

                    if (f.getAño()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    public void limpiarCampos() {
        director.setText("");
        clasificacion.setSelectedItem("FRAM");
        pais.setText("");

    }

}

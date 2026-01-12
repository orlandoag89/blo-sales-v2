package com.blo.sales.v2.view.windows.commons;

import com.blo.sales.v2.utils.BloSalesV2Exception;
import static com.blo.sales.v2.utils.BloSalesV2Utils.validateRule;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.function.Consumer;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
* Minúsculas:
* á -> \u00e1
* é -> \u00e9
* í -> \u00ed
* ó -> \u00f3
* ú -> \u00fa
* ñ -> \u00f1
* ü -> \u00fc
* 
* Mayúsculas:
* Á -> \u00c1
* É -> \u00c9
* Í -> \u00cd
* Ó -> \u00d3
* Ú -> \u00da
* Ñ -> \u00d1
* 
* Símbolos:
* ¿ -> \u00bf
* ¡ -> \u00a1
 */

public final class GUICommons {
    
    private static final String INVALID_TEXT = "Texto no v\u00e1lido";
    
    private GUICommons() { }
    
    /**
     * abre un nuevo panel, limpia el contenedor para librerar memoria
     * @param content - contenedor donde se dibujara el siguiente panel
     * @param p - contenido
     */
    public static void showPanel(JPanel content, JPanel p) {
        p.setSize(1000, 600); // Ajusta al tamaño de tu contenedor
        p.setLocation(0, 0);
        content.removeAll(); // Limpia el panel principal
        content.add(p, BorderLayout.CENTER); // Agrega el nuevo formulario
        content.revalidate(); // Refresca el diseño
        content.repaint();    // Redibuja la interfaz
    }
    
    /**
     * Agrega un evento de doble clic a cualquier JList
     * @param <T> El tipo de dato del JList
     * @param list El componente JList
     * @param action Lo que quieres hacer con el item seleccionado (callback)
     */
    public static <T> void addDoubleClickOnListEvt(JList<T> lst, Consumer<T> action) {
        lst.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = lst.locationToIndex(evt.getPoint());
                    if (index >= 0) {
                        T item = lst.getModel().getElementAt(index);
                        action.accept(item); // Ejecuta la lógica personalizada
                    }
                }
            }
        });
    }
    
     /**
     * Recupera el texto de un text field
     * @param field
     * @return 
     */
    public static String getTextFromJText(JTextField field) throws BloSalesV2Exception {
        final var text = field.getText().trim();
        validateRule(text.isBlank(), INVALID_TEXT);
        return text;
    }
    
    public static BigDecimal getNumberFromJText(JTextField field) throws BloSalesV2Exception {
        final var txt = field.getText().trim();
        validateRule(txt.isBlank(), INVALID_TEXT);
        return new BigDecimal(txt);
    }
    
    /**
     * Recupera el valor de una password
     * @param field
     * @return
     * @throws BloSalesV2Exception 
     */
    public static String getPasswordFromJText(JPasswordField field) throws BloSalesV2Exception {
        final var passGet = field.getPassword();
        final var pass = new String(passGet);
        validateRule(pass.isBlank(), INVALID_TEXT);
        return pass;
    }
    
    
    public static void setTextToField(JTextField field, String txt) {
        field.setText(txt);
    }
    
    public static void setTextToLabel(JLabel lbl, String txt) {
        lbl.setText(txt);
    }
    
    public static String getTextFromLabel(JLabel lbl) {
        return lbl.getText();
    }
    
    /**
     * recupera el valor seleccionado de un combobox
     * @param comboBox
     * @return 
     */
    public static String getValueFromComboBox(JComboBox comboBox) {
        return comboBox.getSelectedItem().toString();
    }
    
    /**
     * recupera el status de un checkbox
     * @param checkbox
     * @return 
     */
    public static boolean isCheckedCkeckBox(JCheckBox checkbox) {
        return checkbox.isSelected();
    }
    
    /**
     * carga los titulos a las columnas de una tabla
     * @param table
     * @param titles 
     */
    public static void loadTitleOnTable(JTable table, String[] titles) {
        final var titleModel = new DefaultTableModel(titles, 0);
        table.setModel(titleModel);
    }
    
    /**
     * Inicializa el filtro de una tabla
     * @param table
     * @param sorter 
     */
    public static void filterTable(JTable table, TableRowSorter<DefaultTableModel> sorter) {
        final var model = (DefaultTableModel) table.getModel();
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
    }
}

package com.blo.sales.v2.plugins.writter;

import com.blo.sales.v2.plugins.writter.enums.ExtensionEnum;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public final class BloSalesV2WritterFile {
    
    private BloSalesV2WritterFile() { }
    
    public static void saveFile(String str, ExtensionEnum extension) throws IOException {
        final var fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            var archivo = fileChooser.getSelectedFile();
            // Asegurar la extensión .txt
            final var ruta = archivo.getAbsolutePath();
            if (!ruta.endsWith(extension.getExtension())) {
                archivo = new File(ruta + extension.getExtension());
            }

            final var bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(str);
            bw.flush();
            bw.close();
            JOptionPane.showMessageDialog(null, "Archivo guardado exitosamente.");
        }
    }
    
}

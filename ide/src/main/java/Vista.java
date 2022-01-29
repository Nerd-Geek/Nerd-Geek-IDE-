import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;


public class Vista extends JFrame {

    private JPanel panelPrincipal;
    private JPanel panelSuperior;
    private JPanel panelSalida;
    private JPanel panelBotones;
    private JPanel panelBotonesHor;
    private JTree navegador;
    private JLabel infoSalida;
    private JTextArea terminal;
    private JTextArea editor;
    private JScrollPane scrollEditor;
    private JScrollPane scrollNavegador;
    private JScrollPane scrollTerminal;
    private JMenuBar menu;
    private JMenu file;
    private JMenu view;
    private JMenu edit;
    private JMenu run;
    private JMenu compiler;
    private JMenu help;
    private JMenuItem incrementSize;
    private JMenuItem decrementSize;
    private JMenuItem runI;
    private JMenuItem compilerI;
    private JMenuItem nuevo;
    private JMenuItem saveAll;
    private JMenuItem save;
    private JMenuItem open;
    private JMenuItem imprimir;
    private JMenuItem exit;
    private JMenuItem corta;
    private JMenuItem copy;
    private JMenuItem paste;
    private JMenuItem deshacerM;
    private JMenuItem rehacerM;
    private JMenuItem sobre;
    private JMenuItem webPage;
    private JButton btnPlay;
    private JButton btnCompiler;
    private JButton btnPlayPrinci;
    private JButton btnCompilerPrinci;
    private String path;

    private int tamaño = 20;
    UndoManager manager;

    public Vista() {
        super(String.valueOf(new GridBagLayout()));
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("src/main/resources/images/linux.png").getImage());
        this.setTitle("Nerd-Geek IDE");
        JTreeFile jTreeFiles = new JTreeFile();
        jTreeFiles.setJTree(navegador);
        jTreeFiles.init();
    }

    private void initComponents() {


        panelPrincipal = new JPanel();
        panelSuperior = new JPanel();
        panelSalida = new JPanel();
        panelBotones = new JPanel();
        panelBotonesHor = new JPanel();
        infoSalida = new JLabel("Run:" + "  Unnamed");
        terminal = new JTextArea("Hello World!");
        terminal.setEditable(false);
        scrollTerminal = new JScrollPane(terminal, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        navegador = new JTree();
        editor = new JTextArea();
        scrollEditor = new JScrollPane(editor, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollNavegador = new JScrollPane(navegador, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        menu = new JMenuBar();
        file = new JMenu();
        view = new JMenu();
        edit = new JMenu();
        run = new JMenu();
        compiler = new JMenu();
        help = new JMenu("Help");
        runI = new JMenuItem("Run", new ImageIcon("src/main/resources/images/play.png"));
        compilerI = new JMenuItem("Compiler", new ImageIcon("src/main/resources/images/hammer.png"));
        nuevo = new JMenuItem("New", new ImageIcon("src/main/resources/images/agregar-archivo.png"));
        saveAll = new JMenuItem("Save all", new ImageIcon("src/main/resources/images/guardar-el-archivo.png"));
        save = new JMenuItem("Save", new ImageIcon("src/main/resources/images/guardar-el-archivo.png"));
        open = new JMenuItem("Open", new ImageIcon("src/main/resources/images/abrir-archivo.png"));
        imprimir = new JMenuItem("Print", new ImageIcon("src/main/resources/images/imprimir.png"));
        exit = new JMenuItem("Exit", new ImageIcon("src/main/resources/images/salir.png"));
        incrementSize = new JMenuItem("Size", new ImageIcon("src/main/resources/images/aumentar.png"));
        decrementSize = new JMenuItem("Size", new ImageIcon("src/main/resources/images/disminuir.png"));
        corta = new JMenuItem("Cut", new ImageIcon("src/main/resources/images/cortar.png"));
        copy = new JMenuItem("Copy", new ImageIcon("src/main/resources/images/copia.png"));
        paste = new JMenuItem("Paste", new ImageIcon("src/main/resources/images/pegar.png"));
        deshacerM = new JMenuItem("Undo", new ImageIcon("src/main/resources/images/deshacer.png"));
        rehacerM = new JMenuItem("Redo", new ImageIcon("src/main/resources/images/rehacer.png"));
        sobre = new JMenuItem("About the App", new ImageIcon("src/main/resources/images/informacion.png"));
        webPage = new JMenuItem("Web Page", new ImageIcon("src/main/resources/images/sitio-web.png"));
        btnPlay = new JButton(new ImageIcon("src/main/resources/images/play.png"));
        btnCompiler = new JButton(new ImageIcon("src/main/resources/images/hammer.png"));
        btnPlayPrinci = new JButton(new ImageIcon("src/main/resources/images/play.png"));
        btnCompilerPrinci = new JButton(new ImageIcon("src/main/resources/images/hammer.png"));

        manager = new UndoManager();
        manager.setLimit(500);

        file.setText("File");
        view.setText("View");
        edit.setText("Edit");
        run.setText("Run");
        compiler.setText("Compiler");

        menu.add(file);
        menu.add(view);
        menu.add(edit);
        menu.add(run);
        menu.add(compiler);
        menu.add(help);

        file.add(nuevo);
        file.add(saveAll);
        file.add(save);
        file.add(open);
        file.add(imprimir);
        file.add(exit);

        view.add(incrementSize);
        view.add(decrementSize);

        edit.add(corta);
        edit.add(copy);
        edit.add(paste);
        edit.add(deshacerM);
        edit.add(rehacerM);


        run.add(runI);

        compiler.add(compilerI);

        help.add(sobre);
        help.add(webPage);

        panelPrincipal.setLayout(new BorderLayout(1, 2));
        panelSuperior.setLayout(new BorderLayout(1, 2));
        panelSalida.setLayout(new BorderLayout(1, 2));
        panelBotones.setLayout(new BorderLayout(1, 2));
        panelBotonesHor.setLayout(new BoxLayout(panelBotonesHor, BoxLayout.Y_AXIS));

        JSplitPane sprit1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollNavegador, scrollEditor);
        JSplitPane sprit2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sprit1, panelSalida);
        panelPrincipal.add(sprit2);

        sprit2.setOneTouchExpandable(true);
        sprit2.setResizeWeight(0.6);
        panelPrincipal.add(sprit2, BorderLayout.CENTER);
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelSalida.add(infoSalida, BorderLayout.NORTH);
        panelSalida.add(scrollTerminal, BorderLayout.CENTER);
        panelSalida.add(panelBotonesHor, BorderLayout.WEST);
        panelSuperior.add(menu, BorderLayout.NORTH);
        panelBotones.add(btnPlayPrinci, BorderLayout.WEST);
        panelBotones.add(btnCompilerPrinci, BorderLayout.EAST);
        panelBotonesHor.add(btnPlay);
        panelBotonesHor.add(btnCompiler);
        panelSuperior.add(panelBotones, BorderLayout.EAST);


        editor.setFont(new Font("monospaced", Font.PLAIN, tamaño));
        terminal.setFont(new Font("monospaced", Font.PLAIN, 20));
        this.add(panelPrincipal);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setMinimumSize(new Dimension(200, 200));

        pack();

        JPanel P = new JPanel();
        JLabel a = new JLabel();

        // Action Listener

        // Para salir del la aplicación
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exit.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        incrementSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incremetarTamaño();
            }
        });
        incrementSize.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_ADD, ActionEvent.CTRL_MASK));

        decrementSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decrementarTamaño();
            }
        });
        decrementSize.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_SUBTRACT, ActionEvent.CTRL_MASK));

        // Para mostrar información de la aplicación
        sobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Aplicación realizada por Nerd-Geek", "Mensaje informativo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        sobre.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));

        // Para salvar el archivo utilizando JFileChooser
        saveAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarArchivoComo();
            }
        });
        saveAll.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_G, ActionEvent.CTRL_MASK));

        // Para abrir el archivo
        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArchivo();
            }
        });
        open.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        // Para guardar el archivo directamente
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
        save.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        // Para compilar
        btnCompiler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    compiler();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Para ejecutar
        btnPlay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    run();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnPlayPrinci.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    run();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnCompilerPrinci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    compiler();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        runI.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    run();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        runI.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.CTRL_MASK));

        compilerI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    compiler();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        compilerI.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        // Para deshacer el contenido
        deshacerM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manager.canUndo()) {
                    manager.undo();
                }
            }
        });

        deshacerM.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));

        // Para rehacer el contenido
        rehacerM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manager.canRedo()) {
                    manager.redo();
                }
            }
        });

        rehacerM.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_W, ActionEvent.CTRL_MASK));

        editor.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                manager.addEdit(e.getEdit());
            }
        });

        // Para copiar contenido
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiar();
            }
        });

        copy.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        // Para pegar el contenido
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pegar();
            }
        });

        paste.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        // Para cortar el contenido
        corta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cortar();
            }
        });

        corta.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        // Para crear un archivo nuevo
        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoArchivo();
            }
        });
        nuevo.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        // Para imprimir el archivo
        imprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    imprimir();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        });
        imprimir.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));

        // Redirige a una página web
        webPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page();
            }
        });
        webPage.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_B, ActionEvent.CTRL_MASK));

        // Para disminuir el tabulador por cuatro espacios
        editor.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    e.consume();
                    tabEvent();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    //Métodos funcionales
    private void guardarArchivoComo() {
        JFileChooser selector = new JFileChooser();
        selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selector.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Class (.java)", "java");
        selector.setFileFilter(filter);

        int opcion = selector.showSaveDialog(this);
        File archivo = selector.getSelectedFile();

        if (opcion == JFileChooser.APPROVE_OPTION) {
            if (!archivo.getName().endsWith(".java")) {
                archivo = new File(archivo.getPath() + ".java");
            }
        }

        try (FileWriter escritor = new FileWriter(archivo)) {
            if (archivo.exists()) {
                editor.write(escritor);
                JOptionPane.showMessageDialog(null, "Archivo guardado", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showConfirmDialog(null, "¿Está seguro de guardar el archivo?", "ConfirmDialog",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void abrirArchivo() {
        try {
            JFileChooser fc = new JFileChooser();
            File archivo = null;

            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int selectVal = fc.showOpenDialog(this);

            if (selectVal == JFileChooser.APPROVE_OPTION) {
                archivo = fc.getSelectedFile();
                editor.setText("");
            }

            String textoString;
            BufferedReader br = new BufferedReader(new FileReader(archivo));

            while ((textoString = br.readLine()) != null) {
                editor.setText(editor.getText() + textoString + "\n");
            }

            path = archivo.getAbsolutePath();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardar() {
        File archivo = new File(path);

        try (FileWriter escritor = new FileWriter(archivo)) {
            if (archivo.exists()) {
                editor.write(escritor);
                JOptionPane.showMessageDialog(null, "Archivo guardado", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showConfirmDialog(null, "¿Está seguro de guardar el archivo?", "ConfirmDialog",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void compiler() throws IOException {
        Process process = Runtime.getRuntime().exec("cmd.exe /C javac " + "" + path);
    }

    private void run() throws IOException {
        Process process = Runtime.getRuntime().exec("cmd.exe /C java " + "" + path);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String linea;

        terminal.setText("");
        while ((linea = br.readLine()) != null) {
            terminal.setText(terminal.getText() + linea + "\n");
        }
    }

    int i = 2;

    public void incremetarTamaño() {
        if (tamaño < 30) {
            editor.setFont(new Font("monospaced", Font.PLAIN, (tamaño += i)));
            i += 2;
        }
    }

    public void decrementarTamaño() {
        if (tamaño > 20) {
            editor.setFont(new Font("monospaced", Font.PLAIN, (tamaño -= i)));
            i -= 2;
        }
    }

    private Clipboard portaPapeles;

    public void copiar() {
        portaPapeles = Toolkit.getDefaultToolkit().getSystemClipboard();

        if (editor.getSelectedText() != null) {
            StringSelection seleccion = new StringSelection("" + editor.getSelectedText());
            portaPapeles.setContents(seleccion, seleccion);
        }
    }

    public void pegar() {
        Transferable datos = portaPapeles.getContents(null);
        try {
            if (datos != null && datos.isDataFlavorSupported(DataFlavor.stringFlavor))
                editor.replaceSelection("" + datos.getTransferData(DataFlavor.stringFlavor));
        } catch (UnsupportedFlavorException | IOException ex) {
            System.err.println(ex);
        }
    }

    public void cortar() {
        portaPapeles = Toolkit.getDefaultToolkit().getSystemClipboard();

        if (editor.getSelectedText() != null) {
            StringSelection seleccion = new StringSelection("" + editor.getSelectedText());
            editor.cut();
            portaPapeles.setContents(seleccion, seleccion);
        }
    }

    public void imprimir() throws PrinterException {
        editor.print();
    }

    private int opcion;

    public void nuevoArchivo() {
        try {
            opcion = JOptionPane.showConfirmDialog(null, "¿Deseas guardar el archivo?",
                    "Guardar", JOptionPane.YES_NO_OPTION);
            if (opcion == 0) {
                guardar();
                editor.setText("");
                guardarArchivoComo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void page() {
        try {
            Desktop d = null;
            d = Desktop.getDesktop();
            d.browse(new URI("https://github.com/Nerd-Geek"));
        } catch (URISyntaxException ex) {
            System.err.println("Error la lanzar URI");
        } catch (IOException ex) {
            System.err.println("Error la lanzar Proceso Desktop");
        }
    }

    public void tabEvent() {
        try {
            String espacios = "    ";
            editor.getDocument().insertString(editor.getCaretPosition(), espacios, null);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
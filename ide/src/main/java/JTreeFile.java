
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class JTreeFile extends Component implements TreeExpansionListener {

    private JTree jTree1;
    private DefaultTreeModel modelo;

    public DefaultTreeModel getModelo() {
        return modelo;
    }

    public JTreeFile() {
    }

    public JTreeFile(JTree jTree1) {
        this.jTree1 = jTree1;
    }

    public void setJTree(JTree jTree1) {
        this.jTree1 = jTree1;
    }

    public void init() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Directorio");
        modelo = new DefaultTreeModel(top);
        jTree1.setModel(modelo);
        jTree1.addTreeExpansionListener(this);
        for (File f : File.listRoots()) {
            DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(f);

            top.add(raiz);
            actualizaNodo(raiz, f, 2);
        }
    }

    private boolean actualizaNodo(DefaultMutableTreeNode nodo, File f) {
        nodo.removeAllChildren();
        return actualizaNodo(nodo, f, 2);
    }

    private boolean actualizaNodo(DefaultMutableTreeNode nodo, File f, int profundidad) {
        File[] files = f.listFiles();
        if (files != null && profundidad > 0) {
            for (File file : files) {
                DefaultMutableTreeNode nuevo = new DefaultMutableTreeNode(file);
                //vuelve a mandar en caso que sea directorio
                actualizaNodo(nuevo, file, profundidad - 1);
                nodo.add(nuevo); //crea el nodo
            }
        }
        return true;
    }

    @Override
    public void treeExpanded(TreeExpansionEvent event) {
        TreePath path = event.getPath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();

        if (node == null || !(node.getUserObject() instanceof File)) return;
        File f = (File) node.getUserObject();
        actualizaNodo(node, f);
        JTree tree = (JTree) event.getSource();
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.nodeStructureChanged(node);
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {
    }

}

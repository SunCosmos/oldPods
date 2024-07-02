package bsh.util;

import bsh.BshClassManager;
import bsh.StringUtil;
import bsh.classpath.BshClassPath;
import bsh.classpath.ClassManagerImpl;
import bsh.classpath.ClassPathListener;
import bsh.org.objectweb.asm.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/* loaded from: classes.dex */
public class ClassBrowser extends JSplitPane implements ListSelectionListener, ClassPathListener {
    private static final Color LIGHT_BLUE = new Color(245, 245, 255);
    BshClassManager classManager;
    BshClassPath classPath;
    String[] classesList;
    JList classlist;
    Constructor[] consList;
    JList conslist;
    Field[] fieldList;
    JList fieldlist;
    JFrame frame;
    JInternalFrame iframe;
    JTextArea methodLine;
    Method[] methodList;
    JList mlist;
    String[] packagesList;
    PackageTree ptree;
    Class selectedClass;
    String selectedPackage;
    JTree tree;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class PackageTree extends JTree {
        Map nodeForPackage = new HashMap();
        TreeNode root;
        DefaultTreeModel treeModel;

        PackageTree(Collection collection) {
            setPackages(collection);
            setRootVisible(false);
            setShowsRootHandles(true);
            setExpandsSelectedPaths(true);
        }

        void addNodeMap(TreeNode treeNode) {
            StringBuffer stringBuffer = new StringBuffer();
            for (TreeNode treeNode2 = treeNode; treeNode2 != this.root; treeNode2 = treeNode2.getParent()) {
                stringBuffer.insert(0, treeNode2.toString());
                if (treeNode2.getParent() != this.root) {
                    stringBuffer.insert(0, ".");
                }
            }
            this.nodeForPackage.put(stringBuffer.toString(), treeNode);
        }

        MutableTreeNode makeNode(Map map, String str) {
            DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(str);
            for (String str2 : map.keySet()) {
                Map map2 = (Map) map.get(str2);
                if (map2.size() == 0) {
                    defaultMutableTreeNode.add(new DefaultMutableTreeNode(str2));
                } else {
                    defaultMutableTreeNode.add(makeNode(map2, str2));
                }
            }
            return defaultMutableTreeNode;
        }

        DefaultTreeModel makeTreeModel(Collection collection) {
            HashMap hashMap = new HashMap();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                Map map = hashMap;
                for (String str : StringUtil.split((String) it.next(), ".")) {
                    Map map2 = (Map) map.get(str);
                    if (map2 == null) {
                        map2 = new HashMap();
                        map.put(str, map2);
                    }
                    map = map2;
                }
            }
            MutableTreeNode makeNode = makeNode(hashMap, "root");
            this.root = makeNode;
            mapNodes(makeNode);
            return new DefaultTreeModel(this.root);
        }

        void mapNodes(TreeNode treeNode) {
            addNodeMap(treeNode);
            Enumeration children = treeNode.children();
            while (children.hasMoreElements()) {
                mapNodes((TreeNode) children.nextElement());
            }
        }

        public void setPackages(Collection collection) {
            DefaultTreeModel makeTreeModel = makeTreeModel(collection);
            this.treeModel = makeTreeModel;
            setModel(makeTreeModel);
        }

        void setSelectedPackage(String str) {
            DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) this.nodeForPackage.get(str);
            if (defaultMutableTreeNode == null) {
                return;
            }
            TreePath treePath = new TreePath(this.treeModel.getPathToRoot(defaultMutableTreeNode));
            setSelectionPath(treePath);
            ClassBrowser.this.setClist(str);
            scrollPathToVisible(treePath);
        }
    }

    public ClassBrowser() {
        this(BshClassManager.createClassManager(null));
    }

    public ClassBrowser(BshClassManager bshClassManager) {
        super(0, true);
        this.classManager = bshClassManager;
        setBorder(null);
        BasicSplitPaneUI ui = getUI();
        if (ui instanceof BasicSplitPaneUI) {
            ui.getDivider().setBorder((Border) null);
        }
    }

    public static void main(String[] strArr) {
        ClassBrowser classBrowser = new ClassBrowser();
        classBrowser.init();
        JFrame jFrame = new JFrame("BeanShell Class Browser v1.0");
        jFrame.getContentPane().add("Center", classBrowser);
        classBrowser.setFrame(jFrame);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private JSplitPane splitPane(int i2, boolean z, JComponent jComponent, JComponent jComponent2) {
        JSplitPane jSplitPane = new JSplitPane(i2, z, jComponent, jComponent2);
        jSplitPane.setBorder((Border) null);
        BasicSplitPaneUI ui = jSplitPane.getUI();
        if (ui instanceof BasicSplitPaneUI) {
            ui.getDivider().setBorder((Border) null);
        }
        return jSplitPane;
    }

    @Override // bsh.classpath.ClassPathListener
    public void classPathChanged() {
        this.ptree.setPackages(this.classPath.getPackagesSet());
        setClist(null);
    }

    public void driveToClass(String str) {
        String[] splitClassname = BshClassPath.splitClassname(str);
        int i2 = 0;
        String str2 = splitClassname[0];
        String str3 = splitClassname[1];
        if (this.classPath.getClassesForPackage(str2).size() == 0) {
            return;
        }
        this.ptree.setSelectedPackage(str2);
        while (true) {
            String[] strArr = this.classesList;
            if (i2 >= strArr.length) {
                return;
            }
            if (strArr[i2].equals(str3)) {
                this.classlist.setSelectedIndex(i2);
                this.classlist.ensureIndexIsVisible(i2);
                return;
            }
            i2++;
        }
    }

    Constructor[] getPublicConstructors(Constructor[] constructorArr) {
        Vector vector = new Vector();
        for (int i2 = 0; i2 < constructorArr.length; i2++) {
            if (Modifier.isPublic(constructorArr[i2].getModifiers())) {
                vector.addElement(constructorArr[i2]);
            }
        }
        Constructor[] constructorArr2 = new Constructor[vector.size()];
        vector.copyInto(constructorArr2);
        return constructorArr2;
    }

    Field[] getPublicFields(Field[] fieldArr) {
        Vector vector = new Vector();
        for (int i2 = 0; i2 < fieldArr.length; i2++) {
            if (Modifier.isPublic(fieldArr[i2].getModifiers())) {
                vector.addElement(fieldArr[i2]);
            }
        }
        Field[] fieldArr2 = new Field[vector.size()];
        vector.copyInto(fieldArr2);
        return fieldArr2;
    }

    Method[] getPublicMethods(Method[] methodArr) {
        Vector vector = new Vector();
        for (int i2 = 0; i2 < methodArr.length; i2++) {
            if (Modifier.isPublic(methodArr[i2].getModifiers())) {
                vector.addElement(methodArr[i2]);
            }
        }
        Method[] methodArr2 = new Method[vector.size()];
        vector.copyInto(methodArr2);
        return methodArr2;
    }

    public void init() {
        BshClassPath classPath = ((ClassManagerImpl) this.classManager).getClassPath();
        this.classPath = classPath;
        classPath.addListener(this);
        PackageTree packageTree = new PackageTree(this.classPath.getPackagesSet());
        this.ptree = packageTree;
        packageTree.addTreeSelectionListener(new TreeSelectionListener() { // from class: bsh.util.ClassBrowser.1
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                Object[] path = treeSelectionEvent.getPath().getPath();
                StringBuffer stringBuffer = new StringBuffer();
                int i2 = 1;
                while (i2 < path.length) {
                    stringBuffer.append(path[i2].toString());
                    i2++;
                    if (i2 < path.length) {
                        stringBuffer.append(".");
                    }
                }
                ClassBrowser.this.setClist(stringBuffer.toString());
            }
        });
        JList jList = new JList();
        this.classlist = jList;
        Color color = LIGHT_BLUE;
        jList.setBackground(color);
        this.classlist.addListSelectionListener(this);
        JList jList2 = new JList();
        this.conslist = jList2;
        jList2.addListSelectionListener(this);
        JList jList3 = new JList();
        this.mlist = jList3;
        jList3.setBackground(color);
        this.mlist.addListSelectionListener(this);
        JList jList4 = new JList();
        this.fieldlist = jList4;
        jList4.addListSelectionListener(this);
        JSplitPane splitPane = splitPane(1, true, labeledPane(new JScrollPane(this.ptree), "Packages"), splitPane(1, true, labeledPane(new JScrollPane(this.classlist), "Classes"), splitPane(0, true, splitPane(0, true, labeledPane(new JScrollPane(this.conslist), "Constructors"), labeledPane(new JScrollPane(this.mlist), "Methods")), labeledPane(new JScrollPane(this.fieldlist), "Fields"))));
        JPanel jPanel = new JPanel(new BorderLayout());
        JTextArea jTextArea = new JTextArea(1, 60);
        this.methodLine = jTextArea;
        jTextArea.setBackground(color);
        this.methodLine.setEditable(false);
        this.methodLine.setLineWrap(true);
        this.methodLine.setWrapStyleWord(true);
        this.methodLine.setFont(new Font("Monospaced", 1, 14));
        this.methodLine.setMargin(new Insets(5, 5, 5, 5));
        this.methodLine.setBorder(new MatteBorder(1, 0, 1, 0, color.darker().darker()));
        jPanel.add("North", this.methodLine);
        JPanel jPanel2 = new JPanel(new BorderLayout());
        JTree jTree = new JTree();
        this.tree = jTree;
        jTree.addTreeSelectionListener(new TreeSelectionListener() { // from class: bsh.util.ClassBrowser.2
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                ClassBrowser.this.driveToClass(treeSelectionEvent.getPath().getLastPathComponent().toString());
            }
        });
        this.tree.setBorder(BorderFactory.createRaisedBevelBorder());
        setClassTree(null);
        jPanel2.add("Center", this.tree);
        jPanel.add("Center", jPanel2);
        jPanel.setPreferredSize(new Dimension(Constants.FCMPG, Constants.FCMPG));
        setTopComponent(splitPane);
        setBottomComponent(jPanel);
    }

    JPanel labeledPane(JComponent jComponent, String str) {
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add("Center", jComponent);
        jPanel.add("North", new JLabel(str, 0));
        return jPanel;
    }

    String[] parseConstructors(Constructor[] constructorArr) {
        int length = constructorArr.length;
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            Constructor constructor = constructorArr[i2];
            strArr[i2] = StringUtil.methodString(constructor.getName(), constructor.getParameterTypes());
        }
        return strArr;
    }

    String[] parseFields(Field[] fieldArr) {
        int length = fieldArr.length;
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            strArr[i2] = fieldArr[i2].getName();
        }
        return strArr;
    }

    String[] parseMethods(Method[] methodArr) {
        int length = methodArr.length;
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            strArr[i2] = StringUtil.methodString(methodArr[i2].getName(), methodArr[i2].getParameterTypes());
        }
        return strArr;
    }

    void setClassTree(Class cls) {
        MutableTreeNode defaultMutableTreeNode;
        MutableTreeNode mutableTreeNode = null;
        if (cls == null) {
            this.tree.setModel((TreeModel) null);
            return;
        }
        MutableTreeNode mutableTreeNode2 = null;
        while (true) {
            defaultMutableTreeNode = new DefaultMutableTreeNode(cls.toString());
            if (mutableTreeNode != null) {
                defaultMutableTreeNode.add(mutableTreeNode);
            } else {
                mutableTreeNode2 = defaultMutableTreeNode;
            }
            cls = cls.getSuperclass();
            if (cls == null) {
                break;
            } else {
                mutableTreeNode = defaultMutableTreeNode;
            }
        }
        this.tree.setModel(new DefaultTreeModel(defaultMutableTreeNode));
        TreeNode parent = mutableTreeNode2.getParent();
        if (parent != null) {
            this.tree.expandPath(new TreePath(this.tree.getModel().getPathToRoot(parent)));
        }
    }

    void setClist(String str) {
        this.selectedPackage = str;
        Set<String> classesForPackage = this.classPath.getClassesForPackage(str);
        if (classesForPackage == null) {
            classesForPackage = new HashSet();
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : classesForPackage) {
            if (str2.indexOf("$") == -1) {
                arrayList.add(BshClassPath.splitClassname(str2)[1]);
            }
        }
        String[] sortedStrings = toSortedStrings(arrayList);
        this.classesList = sortedStrings;
        this.classlist.setListData(sortedStrings);
    }

    void setConslist(Class cls) {
        if (cls == null) {
            this.conslist.setListData(new Object[0]);
            return;
        }
        Constructor[] publicConstructors = getPublicConstructors(cls.getDeclaredConstructors());
        this.consList = publicConstructors;
        this.conslist.setListData(parseConstructors(publicConstructors));
    }

    void setFieldList(Class cls) {
        if (cls == null) {
            this.fieldlist.setListData(new Object[0]);
            return;
        }
        Field[] publicFields = getPublicFields(cls.getDeclaredFields());
        this.fieldList = publicFields;
        this.fieldlist.setListData(parseFields(publicFields));
    }

    public void setFrame(JFrame jFrame) {
        this.frame = jFrame;
    }

    public void setFrame(JInternalFrame jInternalFrame) {
        this.iframe = jInternalFrame;
    }

    void setMethodLine(Object obj) {
        this.methodLine.setText(obj == null ? "" : obj.toString());
    }

    void setMlist(String str) {
        Class classForName;
        if (str == null) {
            this.mlist.setListData(new Object[0]);
            setConslist(null);
            setClassTree(null);
            return;
        }
        try {
            if (this.selectedPackage.equals("<unpackaged>")) {
                classForName = this.classManager.classForName(str);
            } else {
                BshClassManager bshClassManager = this.classManager;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(this.selectedPackage);
                stringBuffer.append(".");
                stringBuffer.append(str);
                classForName = bshClassManager.classForName(stringBuffer.toString());
            }
            this.selectedClass = classForName;
            Class cls = this.selectedClass;
            if (cls == null) {
                PrintStream printStream = System.err;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("class not found: ");
                stringBuffer2.append(str);
                printStream.println(stringBuffer2.toString());
                return;
            }
            Method[] publicMethods = getPublicMethods(cls.getDeclaredMethods());
            this.methodList = publicMethods;
            this.mlist.setListData(parseMethods(publicMethods));
            setClassTree(this.selectedClass);
            setConslist(this.selectedClass);
            setFieldList(this.selectedClass);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void toFront() {
        JFrame jFrame = this.frame;
        if (jFrame != null) {
            jFrame.toFront();
            return;
        }
        JInternalFrame jInternalFrame = this.iframe;
        if (jInternalFrame != null) {
            jInternalFrame.toFront();
        }
    }

    String[] toSortedStrings(Collection collection) {
        return StringUtil.bubbleSort((String[]) new ArrayList(collection).toArray(new String[0]));
    }

    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        Object obj;
        Object source = listSelectionEvent.getSource();
        JList jList = this.classlist;
        if (source != jList) {
            Object source2 = listSelectionEvent.getSource();
            JList jList2 = this.mlist;
            if (source2 == jList2) {
                int selectedIndex = jList2.getSelectedIndex();
                if (selectedIndex != -1) {
                    obj = this.methodList[selectedIndex];
                }
                setMethodLine(null);
                return;
            }
            Object source3 = listSelectionEvent.getSource();
            JList jList3 = this.conslist;
            if (source3 == jList3) {
                int selectedIndex2 = jList3.getSelectedIndex();
                if (selectedIndex2 != -1) {
                    obj = this.consList[selectedIndex2];
                }
                setMethodLine(null);
                return;
            }
            Object source4 = listSelectionEvent.getSource();
            JList jList4 = this.fieldlist;
            if (source4 == jList4) {
                int selectedIndex3 = jList4.getSelectedIndex();
                if (selectedIndex3 != -1) {
                    obj = this.fieldList[selectedIndex3];
                }
                setMethodLine(null);
                return;
            }
            return;
        }
        String str = (String) jList.getSelectedValue();
        setMlist(str);
        if (str == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Package: ");
            stringBuffer.append(this.selectedPackage);
            obj = stringBuffer.toString();
        } else {
            if (!this.selectedPackage.equals("<unpackaged>")) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(this.selectedPackage);
                stringBuffer2.append(".");
                stringBuffer2.append(str);
                str = stringBuffer2.toString();
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(str);
            stringBuffer3.append(" (from ");
            stringBuffer3.append(this.classPath.getClassSource(str));
            stringBuffer3.append(")");
            obj = stringBuffer3.toString();
        }
        setMethodLine(obj);
    }
}

/*
 * CodePawView.java
 */

package codepaw;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class CodePawView extends FrameView {

    public CodePawView(SingleFrameApplication app){
        super(app);
        
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }


    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = CodePawApp.getApplication().getMainFrame();
            aboutBox = new CodePawAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        CodePawApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        GenerateBTN = new javax.swing.JButton();
        Description = new javax.swing.JScrollPane();
        DescriptionText = new javax.swing.JTextArea();
        MenyCode = new javax.swing.JScrollPane();
        MenyCodeText = new javax.swing.JTextArea();
        CopyBTN = new javax.swing.JButton();
        PasteBTN = new javax.swing.JButton();
        ChoiseCBox = new javax.swing.JComboBox();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        CopyM = new javax.swing.JMenuItem();
        PasteM = new javax.swing.JMenuItem();
        GenerateM = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        License = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        InfoLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(codepaw.CodePawApp.class).getContext().getActionMap(CodePawView.class, this);
        GenerateBTN.setAction(actionMap.get("generate")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(codepaw.CodePawApp.class).getContext().getResourceMap(CodePawView.class);
        GenerateBTN.setText(resourceMap.getString("GenerateBTN.text")); // NOI18N
        GenerateBTN.setToolTipText(resourceMap.getString("GenerateBTN.toolTipText")); // NOI18N
        GenerateBTN.setName("GenerateBTN"); // NOI18N

        Description.setName("Description"); // NOI18N

        DescriptionText.setColumns(20);
        DescriptionText.setLineWrap(true);
        DescriptionText.setRows(5);
        DescriptionText.setText(resourceMap.getString("DescriptionText.text")); // NOI18N
        DescriptionText.setWrapStyleWord(true);
        DescriptionText.setFocusable(false);
        DescriptionText.setName("DescriptionText"); // NOI18N
        DescriptionText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clean(evt);
            }
        });
        Description.setViewportView(DescriptionText);

        MenyCode.setName("MenyCode"); // NOI18N

        MenyCodeText.setColumns(20);
        MenyCodeText.setEditable(false);
        MenyCodeText.setRows(5);
        MenyCodeText.setText(resourceMap.getString("MenyCodeText.text")); // NOI18N
        MenyCodeText.setEnabled(false);
        MenyCodeText.setName("MenyCodeText"); // NOI18N
        MenyCode.setViewportView(MenyCodeText);

        CopyBTN.setAction(actionMap.get("copy")); // NOI18N
        CopyBTN.setText(resourceMap.getString("CopyBTN.text")); // NOI18N
        CopyBTN.setToolTipText(resourceMap.getString("CopyBTN.toolTipText")); // NOI18N
        CopyBTN.setName("CopyBTN"); // NOI18N

        PasteBTN.setAction(actionMap.get("paste")); // NOI18N
        PasteBTN.setText(resourceMap.getString("PasteBTN.text")); // NOI18N
        PasteBTN.setToolTipText(resourceMap.getString("PasteBTN.toolTipText")); // NOI18N
        PasteBTN.setName("PasteBTN"); // NOI18N
        PasteBTN.setPreferredSize(new java.awt.Dimension(62, 30));

        ChoiseCBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "in same class", "is separate class" }));
        ChoiseCBox.setToolTipText(resourceMap.getString("ChoiseCBox.toolTipText")); // NOI18N
        ChoiseCBox.setName("ChoiseCBox"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MenyCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(Description, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                        .addComponent(PasteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 67, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GenerateBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CopyBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChoiseCBox, 0, 162, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(Description, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GenerateBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CopyBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChoiseCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenyCode, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
        );

        GenerateBTN.getAccessibleContext().setAccessibleName(resourceMap.getString("GenerateBTN.AccessibleContext.accessibleName")); // NOI18N
        CopyBTN.getAccessibleContext().setAccessibleDescription(resourceMap.getString("CopyBTN.AccessibleContext.accessibleDescription")); // NOI18N
        PasteBTN.getAccessibleContext().setAccessibleDescription(resourceMap.getString("PasteBTN.AccessibleContext.accessibleDescription")); // NOI18N

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        CopyM.setAction(actionMap.get("copy")); // NOI18N
        CopyM.setText(resourceMap.getString("CopyM.text")); // NOI18N
        CopyM.setName("CopyM"); // NOI18N
        fileMenu.add(CopyM);

        PasteM.setAction(actionMap.get("paste")); // NOI18N
        PasteM.setText(resourceMap.getString("PasteM.text")); // NOI18N
        PasteM.setName("PasteM"); // NOI18N
        fileMenu.add(PasteM);

        GenerateM.setAction(actionMap.get("generate")); // NOI18N
        GenerateM.setText(resourceMap.getString("GenerateM.text")); // NOI18N
        GenerateM.setName("GenerateM"); // NOI18N
        fileMenu.add(GenerateM);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        License.setAction(actionMap.get("ShowLicense")); // NOI18N
        License.setText(resourceMap.getString("License.text")); // NOI18N
        License.setName("License"); // NOI18N
        helpMenu.add(License);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(418, 27));

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        InfoLabel.setFont(resourceMap.getFont("InfoLabel.font")); // NOI18N
        InfoLabel.setText(resourceMap.getString("InfoLabel.text")); // NOI18N
        InfoLabel.setName("InfoLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusMessageLabel)
                            .addComponent(InfoLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                        .addComponent(statusAnimationLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InfoLabel)
                    .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusMessageLabel)
                        .addComponent(statusAnimationLabel)))
                .addGap(1, 1, 1))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void clean(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clean
        // TODO add your handling code here:
        DescriptionText.setText("");
    }//GEN-LAST:event_clean

    @Action
    public void generate() {
        MenyCodeText.setEnabled(true);
        String text=new String();
        String klasa=new String();
        String retKlasa=new String();
        String potpis=new String();
        String metoda=new String();
        boolean first=true;
        Scanner tekst=new Scanner(DescriptionText.getText());

        //zapishuvanje na pochetnite raboti
        while(tekst.hasNext()){
            String iter= tekst.nextLine();
            if(iter.compareTo("Class Name:")==0){
                klasa=tekst.nextLine().substring(2);
            }else if(iter.compareTo("Method signature:")==0){
                potpis=tekst.nextLine().substring(2);
                metoda=potpis.split(" ")[2];
                metoda=metoda.substring(0,new StringBuffer(metoda).indexOf("("));
                retKlasa=potpis.split(" ")[1];
            }
        }
        if(klasa.equals("")||retKlasa.equals("")||metoda.equals("")||potpis.equals("")){
            MenyCodeText.setText("<invalid problem description>");
            this.InfoLabel.setText("invalid description");
            MenyCodeText.setEnabled(false);
            return;
        }
        if(ChoiseCBox.getSelectedIndex()==1){
                    text+="public class Main {\n\tpublic static void main(String [] args)throws Exception{\n";
                    text+="\t// powered by CodePaw\n";
                    text+="\t// subject of LGP License, do not remove\n";
                    this.InfoLabel.setText("code for separate class generated");

        }else if(ChoiseCBox.getSelectedIndex()==0){
                    text+="public class "+klasa+"{\n" +"\t"+potpis+"{\n\t\t/*\n\t\t * your code goes here!!\n\t\t */\n";
                    text+="\t\treturn ";
                    if("String".equals(retKlasa))
                        text+="\"\";";
                    else if(retKlasa.toLowerCase().equals(retKlasa))
                        text+="0;";
                    else
                        text+="new "+retKlasa+"(\"\")";
                    text+="\n\t}\n";
                    text+="\tpublic static void main(String [] args)throws Exception{\n";
                    text+="\t// powered by CodePaw\n";
                    text+="\t// subject of LGP License, do not remove\n";
                    this.InfoLabel.setText("code for the same class generated");
        }
        text+="\t\tclass RunMethod extends Thread{\n\t\t\t"+retKlasa+" value;\n\t\t\tlong start;\n\t\t\tlong end=0;\n";

        potpis=ZvoX(potpis);
        //zemanje na tipovi na parametri od baraniot potpis na metodata
        String [] params=(potpis.substring(new StringBuffer(potpis).indexOf("(")+1, new StringBuffer(potpis).indexOf(")"))).split(",");
        potpis=XvoZ(potpis);
        for(int i=0;i<params.length;i++){
            params[i]=XvoZ(params[i]);
        }

        for(String tmp: params){
            text+="\t\t\t"+tmp.trim()+";\n";
        }
        text+="\t\t\t"+klasa+" Obj=new "+klasa+"();\n";
        text+="\t\t\tRunMethod("+potpis.substring(new StringBuffer(potpis).indexOf("(")+1, new StringBuffer(potpis).indexOf(")"))+"){\n";
        for(String tmp: params){
            String []tmp2=tmp.trim().split(" ");
            text+="\t\t\t\tthis."+tmp2[1].trim()+"="+tmp2[1].trim()+";\n";
        }
        text+="\t\t\t}\n\t\t\tpublic void run(){\n\t\t\t\tstart=System.currentTimeMillis();\n\t\t\t\tvalue=Obj.";
        text+=metoda+"(";
        for(String tmp: params){
            if(tmp.compareTo(params[0])!=0)
                text+=", ";
            String []tmp2=tmp.trim().split(" ");
            text+=tmp2[tmp2.length-1].trim();
        }
        text+=");\n\t\t\t\tend=System.currentTimeMillis()-start;\n\t\t\t}\n\t\t}\n";


        text+="\t\tlong end;\n\t\tRunMethod exe;\n\t\t"+retKlasa+" value;\n";
        text+="\t\tSystem.out.println(\"\\tret:\\texp:\\ttime:\\tcase:\");\n";

        tekst=new Scanner(DescriptionText.getText());
        while(tekst.hasNext()){
            String iter= tekst.nextLine();
            if(iter.matches("Test Case [1-9][0-9]:") || iter.matches("Test Case [1-9]:")){
                String []sluchaj=tekst.nextLine().split(" = ");
                text+="\n\n\t\t//==="+sluchaj[0].substring(2+metoda.length(),sluchaj[0].length())+"==============================================\n";
                //zemanje na tipovi od potpis
                
                potpis=ZvoX(potpis);
                String [] tipovi=(potpis.substring(new StringBuffer(potpis).indexOf("(")+1, new StringBuffer(potpis).indexOf(")"))).split(",");
                potpis=XvoZ(potpis);
                for(int i=0;i<tipovi.length;i++){
                    tipovi[i]=XvoZ(tipovi[i]);
                }

                char [] args=ZvoX(sluchaj[0]).substring(3+metoda.length(),sluchaj[0].length()).trim().toCharArray();
                int i=0;
                for(String tmp: tipovi){
                    if(first)
                        text+="\t\t"+tmp.trim()+"=";
                    else{
                        String []tmp2=tmp.trim().split(" ");
                        text+="\t\t"+tmp2[tmp2.length-1].trim()+"=";
                    }

                    if(tmp.trim().contains("[]")){
                        text+="new ";
                        String [] tip=tmp.trim().split(" ");
                        for(int k=0;k<tip.length-1;k++)
                            text+=tip[k];
                    }else if(Character.isUpperCase(tmp.trim().charAt(0))){
                        text+="new ";
                        String [] tip=tmp.trim().split(" ");
                        for(int k=0;k<tip.length-1;k++)
                            text+=tip[k];
                        text+="(";
                    }
                    int pass=0;
                    if(args[i]==' ')
                        i++;
                    while((args[i]!=','|| pass>0) && i<args.length-1){
                        text+=args[i];
                        if(args[i]=='(' || args[i]=='{'/* || args[i]=='"'*/)
                            pass++;
                        if(args[i]==')' || args[i]=='}'/* || args[i]=='"'*/)
                            pass--;
                        i++;
                    }
                    if(i<args.length)
                        if(args[i]==',')
                            i++;
                    if(Character.isUpperCase(tmp.trim().charAt(0)) && !tmp.trim().contains("[]"))
                        text+=")";
                    text+=";\n";
                }
                first=false;
                text+="\t\texe=new RunMethod(";
                for(String tmp: params){
                    if(tmp.compareTo(params[0])!=0)
                        text+=", ";
                    String []tmp2=tmp.trim().split(" ");
                    text+=tmp2[1].trim();
                }
                text+=");\n";
                text+="\t\texe.start();\n";
                text+="\t\texe.join(10000);\n";
           //   text+="\n\t\tfor(int i=0;i<100;i++){\n\t\t\tif(!exe.isAlive())\n\t\t\t\tbreak;\n\t\t\telse\n\t\t\t\tThread.sleep(100);\n\t\t}\n";
                text+="\t\tif(exe.isAlive()){\n";
                text+="\t\t\tSystem.out.println(\"fail\\tnull\\t"+sluchaj[1].replace('"', ' ').trim()+"\\t>10\\t"+sluchaj[0].substring(3+metoda.length(),sluchaj[0].length()-1).replace('"', ' ').trim()+"\");\n";
                text+="\t\t}else{\n";
                text+="\t\t\tvalue=exe.value;\n\t\t\tend=exe.end;\n";
                if(Character.isLowerCase(retKlasa.charAt(0))){
                  //  text+="\t\tvalue=Obj."+sluchaj[0].substring(2,sluchaj[0].length())+";\n";
                    text+="\t\t\tif(value=="+sluchaj[1]+" && end<=5000){\n";
                    text+="\t\t\t\tSystem.out.print(\"success\");\n\t\t\t}else{\n\t\t\t\tSystem.out.print(\"fail\");\n\t\t\t}\n";
                    text+="\t\t\tSystem.out.println(\"\\t\"+value+\"\\t"+sluchaj[1].replace('"', ' ').trim()+"\\t\"+end+\"\\t"+sluchaj[0].substring(3+metoda.length(),sluchaj[0].length()-1).replace('"', ' ').trim()+"\");\n";
                }else{
                  //  text+="\t\tvalue=Obj."+sluchaj[0].substring(2,sluchaj[0].length())+";\n";
                    text+="\t\t\tif(value.equals("+sluchaj[1]+") && end<=5000){\n";
                    text+="\t\t\t\tSystem.out.print(\"success\");\n\t\t\t}else{\n\t\t\t\tSystem.out.print(\"fail\");\n\t\t\t}\n";
                    text+="\t\t\tSystem.out.println(\"\\t\"+value.toString()+\"\\t"+sluchaj[1].replace('"', ' ').trim()+"\\t\"+end+\"\\t"+sluchaj[0].substring(3+metoda.length(),sluchaj[0].length()-1).replace('"', ' ').trim()+"\");\n";
                }
                text+="\t\t}\n";
                
            }
        }
        text+="\t}\n}";
        text=XvoZ(text);
        MenyCodeText.setText(text);
     }

    public static String ZvoX(String x){
        StringBuilder tmp=new  StringBuilder();
        boolean in=false;
        for(int i=0;i<x.length();i++){
            char a=x.charAt(i);
            if(in){//ako e vlezeno vo ""
                if(a=='"'){
                    in=false;
                }
                if(a==',')
                    tmp.append('Ø');
                else
                    tmp.append(a);
            }else{//ako ne e
                if(a=='"'){
                    in=true;
                }
                tmp.append(a);
            }
        }
        return tmp.toString();
    }

    public static String XvoZ(String x){
        StringBuilder tmp=new StringBuilder();
        for(char a:x.toCharArray())
            if(a=='Ø')
                tmp.append(',');
            else
                tmp.append(a);
        return tmp.toString();
    }
    @Action
    public void copy()throws Exception {
        if(this.MenyCodeText.isEnabled()==true){
            MenyCodeText.selectAll();
            MenyCodeText.copy();
            this.InfoLabel.setText("code copied");
        }else
            this.InfoLabel.setText("invalid description");
    }

    @Action
    public void paste() {
        DescriptionText.setText("");
        DescriptionText.paste();
        this.InfoLabel.setText("code pasted");
    }

    @Action
    public void ShowLicense() {
        if (LicenseBox == null) {
            JFrame mainFrame = CodePawApp.getApplication().getMainFrame();
            LicenseBox = new LicenseFrame();
            LicenseBox.setLocationRelativeTo(mainFrame);
        }
        CodePawApp.getApplication().show(LicenseBox);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ChoiseCBox;
    private javax.swing.JButton CopyBTN;
    private javax.swing.JMenuItem CopyM;
    private javax.swing.JScrollPane Description;
    private javax.swing.JTextArea DescriptionText;
    private javax.swing.JButton GenerateBTN;
    private javax.swing.JMenuItem GenerateM;
    private javax.swing.JLabel InfoLabel;
    private javax.swing.JMenuItem License;
    private javax.swing.JScrollPane MenyCode;
    private javax.swing.JTextArea MenyCodeText;
    private javax.swing.JButton PasteBTN;
    private javax.swing.JMenuItem PasteM;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    private LicenseFrame LicenseBox;
}

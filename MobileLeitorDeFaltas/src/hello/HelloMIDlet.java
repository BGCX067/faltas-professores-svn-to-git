/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Autor: João Ricardo Schet
 * Matricula: 1290371113019
 * ADS 5º NOTURNO
 */
public class HelloMIDlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command faltasCommand;
    private Command backCommand;
    private Command detalhesCommand;
    private Command backToListCommand;
    private Command detalhesCommand1;
    private Command backCommand1;
    private Form form;
    private StringItem stringItem;
    private ImageItem imageItem;
    private StringItem stringItem3;
    private StringItem stringItem2;
    private StringItem stringItem1;
    private List list;
    private Image logoimage;
    private Image buttonImage;
    private Font font;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The HelloMIDlet constructor.
     */
    public HelloMIDlet() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|
    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|19-preAction
            if (command == exitCommand) {//GEN-END:|7-commandAction|1|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|19-postAction
                // write post-action user code here
            } else if (command == faltasCommand) {//GEN-LINE:|7-commandAction|3|23-preAction
                // write pre-action user code here
                switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|4|23-postAction
                String url = "http://www.fatecpg.com.br/fatec/_layouts/listfeed.aspx?List="
                        + "{4B72BB8A-B784-4388-8BD5-13DDDAAAEE1F}"; //URL para consumo RSS da lista de falta de professores

                try { //Tenta se conectar a url e exibir o conteúdo do feed rss, caso contrário exibe mensagem de falha
                    getList().deleteAll();
                    StreamConnection connection = (StreamConnection) Connector.open(url, Connector.READ_WRITE);
                    SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
                    saxParser.parse(connection.openInputStream(), new DefaultHandler() {

                        private String currentName = "";

                        public void startElement(String uri, String lName, String name, Attributes att) throws SAXException {

                            currentName = name;
                        }

                        public void characters(char[] ch, int start, int length) throws SAXException {
                            String chars = new String(ch, start, length).trim();
                            String textoFormatado = tiraHTML(chars); // utiliza a função que remove tags html
                            if (chars.length() > 0) {

                                if (currentName.equals("description")) {
                                    //exibe a conteúdo da tag description do CDATA do código fonte (falta dos professores)
                                    getList().append(textoFormatado, getButtonImage()); //exibe a falta sem as tags html
                                }
                            }
                        }
                    });
                } catch (Exception error) { // em casa de falha exibe a mensagem "falha"
                    Alert mensagem = new Alert("Falha");
                    mensagem.setString(error.getMessage());
                    mensagem.setTimeout(3000);
                    getDisplay().setCurrent(mensagem);
                }
            }//GEN-BEGIN:|7-commandAction|5|42-preAction
        } else if (displayable == list) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|5|42-preAction
                // write pre-action user code here
                listAction();//GEN-LINE:|7-commandAction|6|42-postAction
                // write post-action user code here
            } else if (command == backCommand1) {//GEN-LINE:|7-commandAction|7|45-preAction
                // write pre-action user code here
                switchDisplayable(null, getForm());//GEN-LINE:|7-commandAction|8|45-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|7-postCommandAction
        }//GEN-END:|7-commandAction|9|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|10|
    //</editor-fold>//GEN-END:|7-commandAction|10|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of form component.
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            form = new Form("Leitor de faltas Fatec Praia Grande", new Item[] { getStringItem(), getStringItem1(), getStringItem2(), getStringItem3(), getImageItem() });//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.addCommand(getFaltasCommand());
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return form;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|16-getter|0|16-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
            stringItem = new StringItem("", ".:. Faltas de Professores .:.");//GEN-BEGIN:|16-getter|1|16-postInit
            stringItem.setFont(getFont());//GEN-END:|16-getter|1|16-postInit
            // write post-init user code here
        }//GEN-BEGIN:|16-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|16-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: faltasCommand ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of faltasCommand component.
     * @return the initialized component instance
     */
    public Command getFaltasCommand() {
        if (faltasCommand == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            faltasCommand = new Command("Visualizar", Command.ITEM, 0);//GEN-LINE:|22-getter|1|22-postInit
            // write post-init user code here
        }//GEN-BEGIN:|22-getter|2|
        return faltasCommand;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            backCommand = new Command("Voltar", Command.BACK, 0);//GEN-LINE:|25-getter|1|25-postInit
            // write post-init user code here
        }//GEN-BEGIN:|25-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|25-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: detalhesCommand ">//GEN-BEGIN:|31-getter|0|31-preInit
    /**
     * Returns an initiliazed instance of detalhesCommand component.
     * @return the initialized component instance
     */
    public Command getDetalhesCommand() {
        if (detalhesCommand == null) {//GEN-END:|31-getter|0|31-preInit
            // write pre-init user code here
            detalhesCommand = new Command("Item", Command.ITEM, 0);//GEN-LINE:|31-getter|1|31-postInit
            // write post-init user code here
        }//GEN-BEGIN:|31-getter|2|
        return detalhesCommand;
    }
    //</editor-fold>//GEN-END:|31-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backToListCommand ">//GEN-BEGIN:|33-getter|0|33-preInit
    /**
     * Returns an initiliazed instance of backToListCommand component.
     * @return the initialized component instance
     */
    public Command getBackToListCommand() {
        if (backToListCommand == null) {//GEN-END:|33-getter|0|33-preInit
            // write pre-init user code here
            backToListCommand = new Command("Voltar", Command.BACK, 0);//GEN-LINE:|33-getter|1|33-postInit
            // write post-init user code here
        }//GEN-BEGIN:|33-getter|2|
        return backToListCommand;
    }
    //</editor-fold>//GEN-END:|33-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|44-getter|0|44-preInit
    /**
     * Returns an initiliazed instance of backCommand1 component.
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {//GEN-END:|44-getter|0|44-preInit
            // write pre-init user code here
            backCommand1 = new Command("Voltar", Command.BACK, 0);//GEN-LINE:|44-getter|1|44-postInit
            // write post-init user code here
        }//GEN-BEGIN:|44-getter|2|
        return backCommand1;
    }
    //</editor-fold>//GEN-END:|44-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: detalhesCommand1 ">//GEN-BEGIN:|47-getter|0|47-preInit
    /**
     * Returns an initiliazed instance of detalhesCommand1 component.
     * @return the initialized component instance
     */
    public Command getDetalhesCommand1() {
        if (detalhesCommand1 == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            detalhesCommand1 = new Command("Mostrar", Command.ITEM, 0);//GEN-LINE:|47-getter|1|47-postInit
            // write post-init user code here
        }//GEN-BEGIN:|47-getter|2|
        return detalhesCommand1;
    }
    //</editor-fold>//GEN-END:|47-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: imageItem ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initiliazed instance of imageItem component.
     * @return the initialized component instance
     */
    public ImageItem getImageItem() {
        if (imageItem == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            imageItem = new ImageItem("", getLogoimage(), ImageItem.LAYOUT_DEFAULT, "<Imagem perdida>");//GEN-BEGIN:|38-getter|1|38-postInit
            imageItem.setPreferredSize(999, -1);//GEN-END:|38-getter|1|38-postInit
            // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return imageItem;
    }
    //</editor-fold>//GEN-END:|38-getter|2|

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: list ">//GEN-BEGIN:|40-getter|0|40-preInit
    /**
     * Returns an initiliazed instance of list component.
     * @return the initialized component instance
     */
    public List getList() {
        if (list == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
            list = new List("Faltas", Choice.IMPLICIT);//GEN-BEGIN:|40-getter|1|40-postInit
            list.addCommand(getBackCommand1());
            list.setCommandListener(this);//GEN-END:|40-getter|1|40-postInit
            // write post-init user code here
        }//GEN-BEGIN:|40-getter|2|
        return list;
    }
    //</editor-fold>//GEN-END:|40-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listAction ">//GEN-BEGIN:|40-action|0|40-preAction
    /**
     * Performs an action assigned to the selected list element in the list component.
     */
    public void listAction() {//GEN-END:|40-action|0|40-preAction
        // enter pre-action user code here
        String __selectedString = getList().getString(getList().getSelectedIndex());//GEN-LINE:|40-action|1|40-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|40-action|2|
    //</editor-fold>//GEN-END:|40-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: logoimage ">//GEN-BEGIN:|39-getter|0|39-preInit
    /**
     * Returns an initiliazed instance of logoimage component.
     * @return the initialized component instance
     */
    public Image getLogoimage() {
        if (logoimage == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|39-getter|1|39-@java.io.IOException
                logoimage = Image.createImage("/imagens/cps.png");
            } catch (java.io.IOException e) {//GEN-END:|39-getter|1|39-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|39-getter|2|39-postInit
            // write post-init user code here
        }//GEN-BEGIN:|39-getter|3|
        return logoimage;
    }
    //</editor-fold>//GEN-END:|39-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: buttonImage ">//GEN-BEGIN:|53-getter|0|53-preInit
    /**
     * Returns an initiliazed instance of buttonImage component.
     * @return the initialized component instance
     */
    public Image getButtonImage() {
        if (buttonImage == null) {//GEN-END:|53-getter|0|53-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|53-getter|1|53-@java.io.IOException
                buttonImage = Image.createImage("/imagens/check.png");
            } catch (java.io.IOException e) {//GEN-END:|53-getter|1|53-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|53-getter|2|53-postInit
            // write post-init user code here
        }//GEN-BEGIN:|53-getter|3|
        return buttonImage;
    }
    //</editor-fold>//GEN-END:|53-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: font ">//GEN-BEGIN:|56-getter|0|56-preInit
    /**
     * Returns an initiliazed instance of font component.
     * @return the initialized component instance
     */
    public Font getFont() {
        if (font == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
            font = Font.getDefaultFont();//GEN-LINE:|56-getter|1|56-postInit
            // write post-init user code here
        }//GEN-BEGIN:|56-getter|2|
        return font;
    }
    //</editor-fold>//GEN-END:|56-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem1 ">//GEN-BEGIN:|57-getter|0|57-preInit
    /**
     * Returns an initiliazed instance of stringItem1 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem1() {
        if (stringItem1 == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
            stringItem1 = new StringItem("", ".:.  Fatec Praia Grande  .:.");//GEN-BEGIN:|57-getter|1|57-postInit
            stringItem1.setFont(getFont());//GEN-END:|57-getter|1|57-postInit
            // write post-init user code here
        }//GEN-BEGIN:|57-getter|2|
        return stringItem1;
    }
    //</editor-fold>//GEN-END:|57-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem2 ">//GEN-BEGIN:|58-getter|0|58-preInit
    /**
     * Returns an initiliazed instance of stringItem2 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem2() {
        if (stringItem2 == null) {//GEN-END:|58-getter|0|58-preInit
            // write pre-init user code here
            stringItem2 = new StringItem("          ", "              ");//GEN-LINE:|58-getter|1|58-postInit
            // write post-init user code here
        }//GEN-BEGIN:|58-getter|2|
        return stringItem2;
    }
    //</editor-fold>//GEN-END:|58-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem3 ">//GEN-BEGIN:|59-getter|0|59-preInit
    /**
     * Returns an initiliazed instance of stringItem3 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem3() {
        if (stringItem3 == null) {//GEN-END:|59-getter|0|59-preInit
            // write pre-init user code here
            stringItem3 = new StringItem("", "                                        ");//GEN-LINE:|59-getter|1|59-postInit
            // write post-init user code here
        }//GEN-BEGIN:|59-getter|2|
        return stringItem3;
    }
    //</editor-fold>//GEN-END:|59-getter|2|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

    private String tiraHTML(String textoComHTML) { // funcão que remove as tags html
        boolean eparasubstituir = false;
        StringBuffer sb = new StringBuffer();
        char[] chararray = textoComHTML.toCharArray();
        for (int i = 0; i < textoComHTML.length(); i++) {
            if (chararray[i] == '<') {
                eparasubstituir = true;
            }

            if (chararray[i] == '>') {
                chararray[i] = ' ';
                eparasubstituir = false;
            }
            if (eparasubstituir == false) {
                sb.append(chararray[i]);
            }
        }
        return sb.toString();
    }
}

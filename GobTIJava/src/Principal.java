import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class Principal extends MIDlet implements CommandListener {
  
	
	private Form AlEstra, FrmEstadisticas, PantallaInicial;
	private List listAlEstra;
    private List listEntValor;
    private List listAdmRiesgos;
    private List listAdmRecursos;
    private List listMedDesemp;
    private StringItem lblresAdmRies, lblresAdmRec, lblresAlEstr, lblresEntVal, lblresMedDes;
    private Display display;
    private RecordStore rsadmrec1 = null;    
    static String bdadmrec1 = "registrorec";
    private RecordStore rsadmries1 = null;    
    static String bdadmries1 = "registroriesg";
    static String bdalestr = "registroalestr";
    private RecordStore rsalestr = null; 
    static String bdentval = "registroentval";
    private RecordStore rsentval = null; 
    static String bdmeddes = "registromeddes";
    private RecordStore rsmeddes = null; 
   
    private static final Command AlEstracomm= new Command("Alineación Estrátegica", Command.SCREEN, 1);
    private static final Command EntValorcom = new Command("Entrega de Valor", Command.SCREEN, 1);
    private static final Command AdmRiesgoscom = new Command("Administración de Riesgos", Command.SCREEN, 1);
    private static final Command AdmRecursoscom= new Command("Administración de Recursos", Command.SCREEN, 1);
    private static final Command MedDesempcom= new Command("Medición de Desempeño", Command.SCREEN, 1);
    private static final Command Salir= new Command("Salir", Command.EXIT, 1);
    private static final Command grabar=new Command ("Grabar", Command.OK, 1);
    private static final Command principal= new Command("Principal", Command.SCREEN, 1);
    private static final Command estadisticas=new Command ("Estadisticas", Command.SCREEN, 1);;
 
   
    public Principal() {
      
    	display=Display.getDisplay(this);
    	//initAlEstraPant();
        initlistEntValorPant();
        initlistAdmRiesgosPant();
        initlistAdmRecursosPant();
        initlistMedDesempPant();
        initListAlEstra();
        initPantallainicial();
        initFrmEstadisticas();
        
    }
 
    private void initPantallainicial() {
		 PantallaInicial = new Form("Gobierno TI");
         StringItem item = new StringItem("Instrucciones", "Esta aplicación se desarrolló para facilitar la implementación del Gobierno TI. " +
         		"Se deben ir alimentando las listas de chequeo diseñadas para cada una de las instancias del Gobierno TI, una vez seleccionados los checkbox, se debe dar click en el botón grabar." +
         		"Para ver las estadísticas de lo que se ha implementado, se debe hacer desde la pantalla principal, seleccionando el botón Estadísticas");
         PantallaInicial.append(item);
         PantallaInicial.addCommand(AlEstracomm);
         PantallaInicial.addCommand(EntValorcom);
         PantallaInicial.addCommand(AdmRecursoscom);
         PantallaInicial.addCommand(AdmRiesgoscom);
         PantallaInicial.addCommand(MedDesempcom);
         PantallaInicial.addCommand(estadisticas);
         PantallaInicial.addCommand(Salir);
         PantallaInicial.setCommandListener(this);
		
	}

    private void initFrmEstadisticas() {
    	  FrmEstadisticas=new Form("Estadisticas de Gobierno TI");
    	  FrmEstadisticas.addCommand(principal);
    	  FrmEstadisticas.addCommand(AlEstracomm);
    	  FrmEstadisticas.addCommand(EntValorcom);
    	  FrmEstadisticas.addCommand(AdmRecursoscom);
    	  FrmEstadisticas.addCommand(AdmRiesgoscom);
    	  FrmEstadisticas.addCommand(MedDesempcom);
    	  FrmEstadisticas.addCommand(Salir);
    	  FrmEstadisticas.setCommandListener(this);
    
    }
    
    private void initListAlEstra() {
		listAlEstra=new List("Alineación Estrategica", List.MULTIPLE);
		listAlEstra.append("Al Estratégica 1",null);
        listAlEstra.append("Al Estratégica 2",null);
        listAlEstra.append("Al Estratégica 3",null);
        listAlEstra.append("Al Estratégica 4",null);
        listAlEstra.append("Al Estratégica 5",null);
        listAlEstra.addCommand(principal);
        listAlEstra.addCommand(AlEstracomm);
        listAlEstra.addCommand(EntValorcom);
        listAlEstra.addCommand(AdmRecursoscom);
        listAlEstra.addCommand(AdmRiesgoscom);
        listAlEstra.addCommand(MedDesempcom);
        listAlEstra.addCommand(grabar);
        listAlEstra.addCommand(Salir);
        listAlEstra.setCommandListener(this);
      		
	}
	


 
    private void initlistEntValorPant() {
    	listEntValor=new List("Entrega de Valor", List.MULTIPLE);
    	listEntValor.append("Ent Valor 1",null);
        listEntValor.append("Ent Valor 2",null);
        listEntValor.append("Ent Valor 3",null);
        listEntValor.append("Ent Valor 4",null);
        listEntValor.append("Ent Valor 5",null);
        listEntValor.addCommand(principal);
        listEntValor.addCommand(AlEstracomm);
        listEntValor.addCommand(EntValorcom);
        listEntValor.addCommand(AdmRecursoscom);
        listEntValor.addCommand(AdmRiesgoscom);
        listEntValor.addCommand(MedDesempcom);
        listEntValor.addCommand(grabar);
        listEntValor.addCommand(Salir);
        listEntValor.setCommandListener(this);
    }
 
    private void initlistAdmRiesgosPant() {
    	listAdmRiesgos=new List("Admon de Riesgos", List.MULTIPLE);
    	listAdmRiesgos.append("Adm Riesgos 1",null);
    	listAdmRiesgos.append("Adm Riesgos 2",null); 
    	listAdmRiesgos.append("Adm Riesgos 3",null);
    	listAdmRiesgos.append("Adm Riesgos 4",null);
    	listAdmRiesgos.append("Adm Riesgos 5",null);
    	listAdmRiesgos.addCommand(principal);
        listAdmRiesgos.addCommand(AlEstracomm);
        listAdmRiesgos.addCommand(EntValorcom);
        listAdmRiesgos.addCommand(AdmRecursoscom);
        listAdmRiesgos.addCommand(AdmRiesgoscom);
        listAdmRiesgos.addCommand(MedDesempcom);
        listAdmRiesgos.addCommand(grabar);
        listAdmRiesgos.addCommand(Salir);
        listAdmRiesgos.setCommandListener(this);
    }
    
    private void initlistAdmRecursosPant() {
    	listAdmRecursos=new List("Admon de Recursos", List.MULTIPLE);
    	listAdmRecursos.append("Adm Recursos 1",null);
    	listAdmRecursos.append("Adm Recursos 2",null);
    	listAdmRecursos.append("Adm Recursos 3",null);
    	listAdmRecursos.append("Adm Recursos 4",null);
    	listAdmRecursos.append("Adm Recursos 5",null);
    	listAdmRecursos.addCommand(principal);
    	listAdmRecursos.addCommand(AlEstracomm);
        listAdmRecursos.addCommand(EntValorcom);
        listAdmRecursos.addCommand(AdmRecursoscom);
        listAdmRecursos.addCommand(AdmRiesgoscom);
        listAdmRecursos.addCommand(MedDesempcom);
        listAdmRecursos.addCommand(Salir);
        listAdmRecursos.addCommand(grabar);
        listAdmRecursos.setCommandListener(this);
        
    }
    

 
    private void initlistMedDesempPant() {
    	
    	listMedDesemp=new List("Medición de Desempeño", List.MULTIPLE);
    	listMedDesemp.append("Med Desempeño 1",null);
    	listMedDesemp.append("Med Desempeño 2",null);
    	listMedDesemp.append("Med Desempeño 3",null);
    	listMedDesemp.append("Med Desempeño 4",null);
    	listMedDesemp.append("Med Desempeño 5",null);
    	listMedDesemp.addCommand(principal);
        listMedDesemp.addCommand(AlEstracomm);
        listMedDesemp.addCommand(EntValorcom);
        listMedDesemp.addCommand(AdmRecursoscom);
        listMedDesemp.addCommand(AdmRiesgoscom);
        listMedDesemp.addCommand(MedDesempcom);
        listMedDesemp.addCommand(grabar);
        listMedDesemp.addCommand(Salir);
        listMedDesemp.setCommandListener(this);
        
       
        }  
    
    private int getSelectedItems( List lbx ) {
        boolean[] arrSel = new boolean[lbx.size()];
        int selected = lbx.getSelectedFlags( arrSel );
        return selected;
}
    
    public void startApp() {
  
    	 
    
       	display.setCurrent(PantallaInicial);
    }
 
   
    public void pauseApp() {
        
    }
 
    
   
    public void destroyApp(boolean unconditional) {
      
    }
 
   
    public void commandAction(Command command, Displayable displayable) {
        if (command == Salir) 
              {
              notifyDestroyed();
              
              } 
        else 
        	if (command == AlEstracomm) 
        	  {
        	  display.setCurrent(listAlEstra);
              }
        if (command == principal) 
  	        {
  	         display.setCurrent(PantallaInicial);
            }
        else 
        	if(command==EntValorcom)
              {
        	  	display.setCurrent(listEntValor);
              }
        else 
        	if (command == AdmRiesgoscom) 
        	  {
            	display.setCurrent(listAdmRiesgos);
              }
        else 
            if (command==AdmRecursoscom)	
              {
            	display.setCurrent(listAdmRecursos);
               }
        else 
        	if (command==MedDesempcom)
            	{
            		display.setCurrent(listMedDesemp);
            	
            	}
        	else
        		 if (command==grabar && displayable==listAlEstra)
                 {
                 	//listAdmRecursos.setTitle("Selected:" + getSelectedItems(listAdmRecursos) );
                	try {
    				byte[] arrbytealestr=null;
    				ByteArrayOutputStream salidaalestr=new ByteArrayOutputStream();
    				DataOutputStream regsalidaalestr= new DataOutputStream(salidaalestr);
    				String resulalestr=Integer.toString(getSelectedItems(listAlEstra));
    				regsalidaalestr.writeUTF(resulalestr);
    				rsalestr=RecordStore.openRecordStore(bdalestr, true);
    				arrbytealestr=salidaalestr.toByteArray();
    			    rsalestr.addRecord(arrbytealestr, 0, arrbytealestr.length);
    			    rsalestr.closeRecordStore();
    			    Alert msg=new Alert("Registro guardado");
    				msg.setTimeout(2000);
    				display.setCurrent(msg);
    			
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				System.out.println(e.getMessage());
    			}    
                	} 
                	else
                	
                		 if (command==grabar && displayable==listEntValor)
                            {
                            	//listAdmRecursos.setTitle("Selected:" + getSelectedItems(listAdmRecursos) );
                           	try {
               				byte[] arrbyteentval=null;
               				ByteArrayOutputStream salidaentval=new ByteArrayOutputStream();
               				DataOutputStream regsalidaentval= new DataOutputStream(salidaentval);
               				String resulentval=Integer.toString(getSelectedItems(listEntValor));
               				regsalidaentval.writeUTF(resulentval);
               				rsentval=RecordStore.openRecordStore(bdentval, true);
               				arrbyteentval=salidaentval.toByteArray();
               			    rsentval.addRecord(arrbyteentval, 0, arrbyteentval.length);
               			    rsentval.closeRecordStore();
               			    Alert msg=new Alert("Registro guardado");
               				msg.setTimeout(2000);
               				display.setCurrent(msg);
               			
               			} catch (Exception e) {
               				// TODO Auto-generated catch block
               				System.out.println(e.getMessage());
               			} 
                            } 	
                  	else
                  		
            if (command==grabar && displayable==listAdmRecursos)
             {
             	//listAdmRecursos.setTitle("Selected:" + getSelectedItems(listAdmRecursos) );
            	try {
				byte[] arrbyteadmrec=null;
				ByteArrayOutputStream salidaadmrec=new ByteArrayOutputStream();
				DataOutputStream regsalidaadmrec= new DataOutputStream(salidaadmrec);
				String resuladmrec=Integer.toString(getSelectedItems(listAdmRecursos));
				regsalidaadmrec.writeUTF(resuladmrec);
				rsadmrec1=RecordStore.openRecordStore(bdadmrec1, true);
				arrbyteadmrec=salidaadmrec.toByteArray();
			    rsadmrec1.addRecord(arrbyteadmrec, 0, arrbyteadmrec.length);
			    rsadmrec1.closeRecordStore();
			    Alert msg=new Alert("Registro guardado");
				msg.setTimeout(2000);
				display.setCurrent(msg);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}    
            	} 
        
         		else
               		 if (command==grabar && displayable==listAlEstra)
                        {
                        	//listAdmRecursos.setTitle("Selected:" + getSelectedItems(listAdmRecursos) );
                       	try {
           				byte[] arrbytealestr=null;
           				ByteArrayOutputStream salidaalestr=new ByteArrayOutputStream();
           				DataOutputStream regsalidaalestr= new DataOutputStream(salidaalestr);
           				String resulalestr=Integer.toString(getSelectedItems(listAlEstra));
           				regsalidaalestr.writeUTF(resulalestr);
           				rsalestr=RecordStore.openRecordStore(bdalestr, true);
           				arrbytealestr=salidaalestr.toByteArray();
           			    rsalestr.addRecord(arrbytealestr, 0, arrbytealestr.length);
           			    rsalestr.closeRecordStore();
           			    Alert msg=new Alert("Registro guardado");
           				msg.setTimeout(2000);
           				display.setCurrent(msg);
           			
           			} catch (Exception e) {
           				// TODO Auto-generated catch block
           				System.out.println(e.getMessage());
           			}    
                       	}     	
               		 else
               			 
        if (command==grabar && displayable==listMedDesemp)
        {
        	
       	try {
			byte[] arrbytemeddes=null;
			ByteArrayOutputStream salidameddes=new ByteArrayOutputStream();
			DataOutputStream regsalidameddes= new DataOutputStream(salidameddes);
			String resulmeddesp=Integer.toString(getSelectedItems(listMedDesemp));
			regsalidameddes.writeUTF(resulmeddesp);
			rsmeddes=RecordStore.openRecordStore(bdmeddes, true);
			arrbytemeddes=salidameddes.toByteArray();
			rsmeddes.addRecord(arrbytemeddes, 0, arrbytemeddes.length);
			rsmeddes.closeRecordStore();
		    Alert msg=new Alert("Registro guardado");
			msg.setTimeout(2000);
			display.setCurrent(msg);
			//listAdmRiesgos.setTitle("Selected:" + getSelectedItems(listAdmRiesgos) );
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}    
       	} 
       	else
       	
          if (command==estadisticas && displayable==PantallaInicial)
		{
        	  
			try {
				FrmEstadisticas.deleteAll();
				rsadmrec1=RecordStore.openRecordStore(bdadmrec1, true);
				rsadmries1=RecordStore.openRecordStore(bdadmries1, true);
				rsalestr=RecordStore.openRecordStore(bdalestr, true);
				rsentval=RecordStore.openRecordStore(bdentval, true);
				rsmeddes=RecordStore.openRecordStore(bdmeddes, true);
				RecordEnumeration regisadmriesgos= rsadmries1.enumerateRecords(null, null, false);
				RecordEnumeration regisadmrecursos= rsadmrec1.enumerateRecords(null, null, false);
				RecordEnumeration regisalestr= rsalestr.enumerateRecords(null, null, false);
				RecordEnumeration regisentval= rsentval.enumerateRecords(null, null, false);
				RecordEnumeration regismeddes= rsmeddes.enumerateRecords(null, null, false);
				int idrec=regisadmrecursos.numRecords();
				int	idries=regisadmriesgos.numRecords();
				int	idestr=regisalestr.numRecords();
				int	identval=regisentval.numRecords();
				int	idmeddes=regismeddes.numRecords();
				byte[] datosrec;
				byte[] datosries;
				byte[] datosestr;
				byte[] datosentval;
				byte[] datosmeddes;
				
				idrec=regisadmrecursos.nextRecordId();
					datosrec=rsadmrec1.getRecord(idrec);
					ByteArrayInputStream entradaadmrec= new ByteArrayInputStream(datosrec);
					DataInputStream regentradaadmrec = new DataInputStream(entradaadmrec);
					lblresAdmRec=new StringItem("Recursos", regentradaadmrec.readUTF());
					
					identval=regisentval.numRecords();
					datosentval=rsentval.getRecord(identval);
					ByteArrayInputStream entradaentval= new ByteArrayInputStream(datosentval);
					DataInputStream regentradaentval = new DataInputStream(entradaentval);
					lblresEntVal=new StringItem("Entrega de Valor", regentradaentval.readUTF());
				
					idries=regisadmriesgos.numRecords();
					datosries=rsadmries1.getRecord(idries);
					ByteArrayInputStream entradaadmriesg= new ByteArrayInputStream(datosries);
					DataInputStream regentradaadmriesg = new DataInputStream(entradaadmriesg);
				    lblresAdmRies=new StringItem("Riesgos", regentradaadmriesg.readUTF());
				    
			       
				    idestr=regisalestr.numRecords();
					datosestr=rsalestr.getRecord(idestr);
					ByteArrayInputStream entradaalestr= new ByteArrayInputStream(datosestr);
					DataInputStream regentradaalestr = new DataInputStream(entradaalestr);
				    lblresAlEstr=new StringItem("Alineacion", regentradaalestr.readUTF());
				    
				    idmeddes=regismeddes.numRecords();
					datosmeddes=rsmeddes.getRecord(idmeddes);
					ByteArrayInputStream entradameddes= new ByteArrayInputStream(datosmeddes);
					DataInputStream regentradameddes = new DataInputStream(entradameddes);
				    lblresMedDes=new StringItem("Desempeño", regentradameddes.readUTF());
				    
				    
				    FrmEstadisticas.append(lblresAlEstr);
				    FrmEstadisticas.append(lblresEntVal); 
				    FrmEstadisticas.append(lblresAdmRec);
				    FrmEstadisticas.append(lblresAdmRies);
				    FrmEstadisticas.append(lblresMedDes);
				    
				        
			 display.setCurrent(FrmEstadisticas);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	
	}
     
}}
    
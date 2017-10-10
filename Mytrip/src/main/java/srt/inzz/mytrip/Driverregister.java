package srt.inzz.mytrip;

import srt.inz.connnectors.OnRegisterTaskCompleted;
import srt.inz.presenters.RegisterApiTask;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Driverregister extends Activity{
	
	Button brg;
	
	EditText etn,etun,etpas,etcpas,etph,etmail,etli,etvid;
	String sn,sun,spas,scpas,sphn,smail,slicense,svehid;
	
	Spinner sp_vtyp;
	
	ArrayAdapter<String> s1;
	String svtyp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		
		etn=(EditText)findViewById(R.id.etname);
		etun=(EditText)findViewById(R.id.etuname);
		etpas=(EditText)findViewById(R.id.etpass);
		etcpas=(EditText)findViewById(R.id.etcps);
		etph=(EditText)findViewById(R.id.etphn);
		etmail=(EditText)findViewById(R.id.etemail);
		etli=(EditText)findViewById(R.id.etlicense);
		etvid=(EditText)findViewById(R.id.etvehid);
		
		sp_vtyp=(Spinner)findViewById(R.id.spinner_vehtype);
		String[] cat = getResources().getStringArray(R.array.vehicle_type);
		
		s1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cat);
	    s1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_vtyp.setAdapter(s1);
        
        sp_vtyp.setOnItemSelectedListener(new OnItemSelectedListener()
        {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				svtyp=arg0.getItemAtPosition(arg2).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
	}
	
	public void regist(View v)
	{
		sn=etn.getText().toString();
		sun=etun.getText().toString();
		spas=etpas.getText().toString();
		scpas=etcpas.getText().toString();
		sphn=etph.getText().toString();
		smail=etmail.getText().toString();
		slicense=etli.getText().toString();
		svehid=etvid.getText().toString();


              RegisterApiTask task=new RegisterApiTask(getApplicationContext(),smail,sn,spas,sphn,sun,slicense,svehid,svtyp,
            		  new OnRegisterTaskCompleted() {
				
				@Override
				public void OnTaskCompleted(String result) {
					// TODO Auto-generated method stub
					
					if(result!=null)
                      {

                          if(result.contains("User Exists"))
                          {
                              Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_SHORT).show();
                          }
                          else{
                            
                              Intent intent =new Intent(getApplicationContext(),MainPage.class);
                              startActivity(intent);
                        	  Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_SHORT).show();
                          }
                      }
					
				}
			});
              
              task.execute();
	}

}

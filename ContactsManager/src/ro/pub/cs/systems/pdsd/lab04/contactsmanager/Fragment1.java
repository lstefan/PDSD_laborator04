package ro.pub.cs.systems.pdsd.lab04.contactsmanager;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment1 extends Fragment {
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup layout, Bundle state) {
		return inflater.inflate(R.layout.frame1, layout, false);
	}
	
	public void onActivityCreated (Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Button additionalDetailsButton = (Button) getActivity().findViewById(R.id.additional);
		Button saveButton = (Button) getActivity().findViewById(R.id.button_save);
		Button cancelButton = (Button) getActivity().findViewById(R.id.button_cancel);
		
		saveButton.setOnClickListener(buttonOnClickListener);
		cancelButton.setOnClickListener(buttonOnClickListener);
		additionalDetailsButton.setOnClickListener(buttonOnClickListener);
	}
	
	
	private ButtonOnClickListener buttonOnClickListener = new ButtonOnClickListener();
	
	private class ButtonOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()) {
			case R.id.additional:
				FragmentManager fm = getActivity().getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				Fragment f2 = (Fragment2)fm.findFragmentById(R.id.frameLayout2);
				if (f2 != null) {
					ft.remove(f2);
					((Button)v).setText(getActivity().getResources().getString(R.string.hide_additional));
				} else {
					ft.add(R.id.frameLayout2, new Fragment2());
					((Button)v).setText(getActivity().getResources().getString(R.string.additional));
				}
				ft.commit();
				break;
			
			case R.id.button_save:
				EditText nameEditText = (EditText)getActivity().findViewById(R.id.name);
				EditText phoneEditText = (EditText)getActivity().findViewById(R.id.phone_number);
				EditText emailEditText = (EditText)getActivity().findViewById(R.id.email);
				EditText addressEditText = (EditText)getActivity().findViewById(R.id.address);		
				
				EditText editText1 = (EditText)getActivity().findViewById(R.id.editText1);
				
				String name = nameEditText.getText().toString();
				String phone = phoneEditText.getText().toString();
				String email = emailEditText.getText().toString();
				String address = addressEditText.getText().toString();
				String jobTitle = editText1.getText().toString();
				
				Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
				intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
				if (name != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
				}
				if (phone != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
				}
				if (email != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
				}
				if (address != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
				}
				if (jobTitle != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
				}
				getActivity().startActivity(intent);
				
				break;
					
			case R.id.button_cancel:
				getActivity().finish();
				break;
				
			
			
		}
		
	}
	}

}

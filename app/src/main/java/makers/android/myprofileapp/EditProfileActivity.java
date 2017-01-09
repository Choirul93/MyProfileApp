package makers.android.myprofileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static makers.android.myprofileapp.MainActivity.REQUEST_PROFILE_DETAILS;

public class EditProfileActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText occupoationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        nameEditText=(EditText)findViewById(R.id.full_name_et);
        occupoationEditText=(EditText)findViewById(R.id.occupation_et);
    }

    public void saveProfile(View view) {
        nameEditText =(EditText)findViewById(R.id.full_name_et);
        occupoationEditText=(EditText)findViewById(R.id.occupation_et);
        Intent intent = new Intent();
        intent.putExtra("name",nameEditText.getText().toString());
        intent.putExtra("occupation",occupoationEditText.getText().toString());
        setResult(REQUEST_PROFILE_DETAILS, intent);
        finish();
    }
}

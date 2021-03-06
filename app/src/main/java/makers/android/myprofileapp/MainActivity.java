package makers.android.myprofileapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_PROFILE_DETAILS = 11;
    ImageView profileImageView;
    TextView nameTextView;
    TextView occupationTextView;
    String name;
    String occupation;
    SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profileImageView = (ImageView)findViewById(R.id.profile_iv);
        nameTextView=(TextView)findViewById(R.id.name_tv);
        occupationTextView=(TextView)findViewById(R.id.occupation_tv);

        name=mSharedPreferences.getString(name,"");
        occupation=mSharedPreferences.getString(occupation,"");
        nameTextView.setText(name);
        occupationTextView.setText(occupation);

    }

    public void takePicture(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // intent to camera
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) { // check if camera is available
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE); // running the intent
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) { // check if the request code is from camera and if the result is ok
            Bundle extras = data.getExtras(); // getting data from the camera
            Bitmap imageBitmap = (Bitmap) extras.get("data"); // assign the data, which is a picture to a variable
            profileImageView.setImageBitmap(imageBitmap); // applying the picture into an ImageView
        }

        if(requestCode==REQUEST_PROFILE_DETAILS){
            name=data.getStringExtra(name);
            occupation=data.getStringExtra(occupation);
            nameTextView.setText(name);
            occupationTextView.setText(occupation);

        }
    }

    public void editProfile(View view) {
        Intent intent = new Intent(MainActivity.this,EditProfileActivity.class);
        startActivityForResult(intent,REQUEST_PROFILE_DETAILS);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("name",nameTextView.getText().toString());
        mEditor.putString("occupation",occupationTextView.getText().toString());
        mEditor.apply();
    }
}

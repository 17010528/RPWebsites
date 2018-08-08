package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Spinner spnCat, spnSCat;
    Button btnGo;
    ArrayList<String> alChoice;
    ArrayAdapter<String> aaChoice;

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref  = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preEdit = sharedPref.edit();
        int pos = spnCat.getSelectedItemPosition();
        int pos1 = spnSCat.getSelectedItemPosition();
        preEdit.putInt("spinnerSelection", pos);
        preEdit.putInt("spinnerSelection2", pos1);
        preEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preEdit = sharedPref.edit();
        spnCat.setSelection(sharedPref.getInt("spinnerSelection", 0));
        spnSCat.setSelection(sharedPref.getInt("spinnerSelection2", 0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnCat = findViewById(R.id.spinnerCategory);
        spnSCat = findViewById(R.id.spinnerSub_Category);
        btnGo = findViewById(R.id.buttonGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = spnCat.getSelectedItemPosition();
                int pos1 = spnSCat.getSelectedItemPosition();
                String [][] sites ={
                        {"https://www.rp.edu.sg/",
                                "https://www.rp.edu.sg/student-life",
                        },
                        {"https://www.rp.edu.sg/soi/full-time-diplomas/details/r47/" ,
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12/"},

                } ;
                String URL = sites[pos][pos1];
                Intent intent = new Intent(getBaseContext(),MainActivityWebpage.class);
                intent.putExtra("URL", URL+"");
                startActivity(intent);
            }

        });
        alChoice = new ArrayList<>();
        aaChoice = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alChoice);

        String[] strcategory = getResources().getStringArray(R.array.SubCategory_RP);
        alChoice.addAll(Arrays.asList(strcategory));
        spnSCat.setAdapter(aaChoice);

        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        alChoice.clear();
                        String[] strcategory = getResources().getStringArray(R.array.SubCategory_RP);
                        alChoice.addAll(Arrays.asList(strcategory));
                        aaChoice.notifyDataSetChanged();
                        break;
                    case 1:
                        alChoice.clear();
                        String[] strcategory1 = getResources().getStringArray(R.array.SubCategory_SOI);
                        alChoice.addAll(Arrays.asList(strcategory1));
                        aaChoice.notifyDataSetChanged();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}



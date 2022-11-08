package com.example.shplesson;





import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private Button addButton;
    private Button  deleteB ;
    private Button  minusB ;
    private TextView tvResult;
    private int counter = 0;
    private final static String KEY = "key";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        pref = getSharedPreferences("TABLE", MODE_PRIVATE);//создание таблицы
        counter = pref.getInt(KEY,0);//запись данных в переменную counter сохраннёных в методе saveData(КЛЮЧ,значение по умолчанию ,если ничего не записано =0)
        tvResult.setText(String.valueOf(counter));

        minusB = findViewById(R.id.buttonMinus);
        minusB.setOnClickListener(view -> {
            counter--;
            tvResult.setText(String.valueOf(counter));
            saveData(counter);
        });


        deleteB = findViewById(R.id.deleteButton);
        deleteB.setOnClickListener(view ->{
           // deleteAll();
            deleteKey(KEY);//очистка по ключу
            tvResult.setText(String.valueOf("0"));//для зануления TextView
                });

        addButton = findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(view -> {
            counter++;
            tvResult.setText(String.valueOf(counter));
            saveData(counter);
        });

    }
    private void saveData(int dataToSave){
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(KEY,dataToSave);
        //editor.putString(KEY_STRING,"SaveString"); для сохранения стрингов
        editor.apply();
    }
    private void deleteAll(){
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();//очистить всю память в pref
        editor.apply();
    }
    private void deleteKey(String key){
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);//удаление по ключю ,или (KEY)
        editor.apply();
    }
}
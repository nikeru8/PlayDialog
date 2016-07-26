package com.example.nikeru8.playdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private TextView m_tv_message;
    private int mChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    //OptionMenu，ActionBar上面三個點點
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 2, Menu.NONE, " 首頁");
        menu.add(0, 3, Menu.NONE, "哈囉");
        return super.onCreateOptionsMenu(menu);
    }

    //OptionMenu按下之後的反應
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 2:
                Toast.makeText(MainActivity.this, "首頁", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    //Button1
    private void init() {
        m_tv_message = (TextView) findViewById(R.id.message);
    }

    //Button1
    public void clickAlertDialog(View view) {
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("I Know!", this)
                .setNegativeButton("hewe", this)
                .show();


    }

    //Button1 implments DialogInterface.OnClickListener 必須要override的方法
    @Override
    public void onClick(DialogInterface dialog, int which) {
        m_tv_message.setText("我知道");
    }


    //Button2 clickAlertDialogYesNo
    //因為MainActivity介面的DialogInterface.OnClickListener在Button1已經使用過了
    //所以在Button2我們在下面自己寫了一個方法
    //並寫入clcikAlertDialogYesNo
    public void clickAlertDialogYesNo(View view) {
        AlertDialogYesNoListener listener = new AlertDialogYesNoListener();
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("謝謝", listener)
                .setNegativeButton("sfdsfa", listener)
                .show();
    }


    //Button2
    private class AlertDialogYesNoListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    m_tv_message.setText("Thnks");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    m_tv_message.setText("狗腿");
                    break;
            }

        }
    }


    //Button3使用內部類別，直接把DialogInterface.OnClickListener這方法寫在內部
    //new DialogInterface.OnClickListener就是我們在Button2的時候寫在外面的方法
    public void Button3(View view) {
        new AlertDialog.Builder(this)
                .setMessage("選擇一二三")
                .setPositiveButton("hloOne", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                m_tv_message.setText("one");
                            }
                        }
                )
                .setNegativeButton("22222", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("two");
                    }
                })
                .setNeutralButton("threee", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("three");
                    }
                })
                .show();

    }

    //按下button4
    public void Button4(View view) {
        //建造陣列=把strings.xml內的response的string-array內的東西丟入
        final String[] response = getResources().getStringArray(R.array.response);
        new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar)
                //這邊必須用setTitle，不能像上面三個Button一樣使用setMessage
                .setTitle("你好帥喔")
                //setItems的用法
                .setItems(response, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText(response[which]);
                    }
                })
                .show();
    }


    //Button5
    public void Button5(View view){
        final String[] response=getResources().getStringArray(R.array.response);
        final boolean[] selected=new boolean[response.length];//記錄每個選項的勾選狀態
        new AlertDialog.Builder(this)
                .setTitle("你好")
                .setMultiChoiceItems(response,selected,new DialogInterface.OnMultiChoiceClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
selected[which]=isChecked;
                    }
                })
                .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder result=new StringBuilder();
                        for(int i=0;i<selected.length;i++){

                            if(selected[i]){
                                result.append(response[i]).append("\n");

                            }

                        }
                        m_tv_message.setText(result);
                    }
                })
                .setNegativeButton("CANCEL",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("無言");
                    }
                })
                .show();


    }


    public void Button6(View view){
final String[] response=getResources().getStringArray(R.array.response);
        new AlertDialog.Builder(this)
                .setTitle("你好帥")
                .setSingleChoiceItems(response,0,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       mChoice=which;
                    }
                })
                .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText(response[mChoice]);
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
m_tv_message.setText("hellono world" );
                    }
    })
                .show();

    }

}
























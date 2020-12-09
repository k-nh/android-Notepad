package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    private ListView listview ;
    private ArrayAdapter adapter ;
    LinearLayout ll;
    int count;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview) ;
        ll = findViewById(R.id.ll);
        final InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);


        //lottie
        final LottieAnimationView animationView = (LottieAnimationView)findViewById(R.id.loading);
        animationView.addAnimatorListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationView.setVisibility(View.GONE);
                ll.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        final ListViewAdapter adapter = new ListViewAdapter();

        listview.setAdapter(adapter) ;


        //text 추가 버튼

        Button buttonAdd = (Button) findViewById(R.id.buttonAdd) ;
        buttonAdd.setEnabled(false) ; // 초기 버튼 상태 비활성 상태로 지정.
        buttonAdd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNew = (EditText) findViewById(R.id.editTextNew) ;
                String strNew = (String) editTextNew.getText().toString();
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                if (strNew.length() > 0) {
                    // 리스트에 문자열 추가.
                    // 에디트텍스트 내용 초기화.
                    editTextNew.setText("") ;
                    adapter.addItem(strNew);
                }
            }
        });

        //delete 버튼
        Button deleteButton = (Button)findViewById(R.id.delete) ;
        deleteButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                adapter.removeItem();
                SparseBooleanArray check = listview.getCheckedItemPositions();
                for(int a=0; a<check.size(); a++) {
                    check = listview.getCheckedItemPositions(); // 갱신된 리스트뷰를 토대로 재설정
                }
            }
        }) ;

        //selectall 버튼
        Button selectAllButton = (Button)findViewById(R.id.deleteAll) ;
        selectAllButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                adapter.clearItem();
            }
        }) ;



        EditText editTextNew = (EditText) findViewById(R.id.editTextNew) ;
        editTextNew.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                Button buttonAdd = (Button) findViewById(R.id.buttonAdd) ;
                if (edit.toString().length() > 0) {
                    // 버튼 상태 활성화.
                    buttonAdd.setEnabled(true) ;
                } else {
                    // 버튼 상태 비활성화.
                    buttonAdd.setEnabled(false) ;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        }) ;

    }

}
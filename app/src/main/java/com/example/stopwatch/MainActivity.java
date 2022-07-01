package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //для доступа к элементу TextView нужно создать для него переменную
    private TextView textViewTimer;
    private TextView textViewMilSecs;
/*
При запуске runTimer мы создаем объект типа Handler, у этого объекта вызываем
метод post,в котором говорим "выполни мгновенно следующий метод run".
 */




//для отслеживания состояния секундомера создаем две переменные
    private int seconds = 0; //хранит кол-во прошедших секунд
    private int milliseconds = 0;
    private boolean isRunning = false; //запущен секундомер или нет
    private boolean isRunningMillSecs = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTimer = findViewById(R.id.textViewTimer);//присваиваем значение переменной textViewTimer значение поля из  R.id.textViewTimer
    runTimer();
        textViewMilSecs = findViewById(R.id.textViewMilSecs);
        runTimerMillSecs();
    }

    private void runTimerMillSecs() {
        isRunningMillSecs = true;
    }


    public void onClickStartTimer(View view) {
        isRunning = true;//когда юзер тыкает Пуск нужно isRunning присвоить true
    isRunningMillSecs=true;
    }

    public void onClickStopTimer(View view) {
        isRunning = false;//а когда тыкает Стоп - присвоить ей false
   isRunningMillSecs = false;
    }

    public void onClickResetTimer(View view) {
        isRunning = false;//когда тыкает Cброс - тоже присвоить ей false
        isRunningMillSecs = false;
        seconds = 0;
        milliseconds = 0;
        //milliseconds = 0;//нужно обнулить счетчик Таймера при Сбросе
    }
    private void runTimer() {//для обновления показаний секундомера используем метод runTimer
        final Handler handler = new Handler();//создаем объект handler
        handler.post(new Runnable() //выхываем метод post у handler и говорим "вызови сразу следующий код"
        {
            @Override
            public void run() {
                int hours = seconds / 3600;//получем часы из секунд
                int minutes = ((seconds) % 3600) / 60;//получаем минуты из остатка от часов
                int secs = seconds % 60;//оставшиеся секнды получаем из остатка от минут
                // int milsecs = milliseconds%1000;

                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, secs);
                textViewTimer.setText(time);//устанавливаем в переменную textViewTimer строку time
                if (isRunning) {//если таймер запущен, т.е. isRunning принимает значение true

                    seconds++;//секунды увеличиваются
                }
                handler.postDelayed(this::run, 1000);
            }
        });
    }

//          private  void runTimerMillSecs() {
//                final Handler handler1 = new Handler();
//                handler1.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        int millisecs = milliseconds % 1000;
//                        String timeMillSecs = String.format(Locale.getDefault(), "%03d", millisecs);
//                        textViewMilSecs.setText(timeMillSecs);
//                        if (isRunning) {
//                            milliseconds++;
//                        }
//                        handler1.postDelayed(this::run, 1);
//                    }
//                }











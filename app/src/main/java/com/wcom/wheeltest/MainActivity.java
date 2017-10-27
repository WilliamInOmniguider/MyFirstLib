package com.wcom.wheeltest;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.william.verticaltablib.LocationDistanceCalculator;
import com.william.verticaltablib.LocationPoint;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] strings = new String[]{"aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg"};
    List<String> data = Arrays.asList(strings);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWheelPicker();
        ((TextView) findViewById(R.id.activity_main_tv_distance)).setText(getDistance() + " km");
    }

    private void setWheelPicker() {
        WheelPicker picker = (WheelPicker) findViewById(R.id.wheel);
        picker.setData(data);
        picker.setItemTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                50,
                getResources().getDisplayMetrics()));

//        picker.setItemSpace((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                500,
//                getResources().getDisplayMetrics()));
        picker.setVisibleItemCount(3);
        picker.setSelectedItemTextColor(getResources().getColor(R.color.selected));
        picker.setItemTextColor(getResources().getColor(R.color.non_selected));

//        picker.setCurtain(true);
//        picker.setCurtainColor(Color.parseColor("#3c29b1"));
        picker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                Log.e("@W@", "selected : " + String.valueOf(data));
            }
        });
    }

    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        final int count = numberPicker.getChildCount();
        for(int i = 0; i < count; i++){
            View child = numberPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Log.e("@W@", "text : " + ((EditText) child).getText());
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
//                    ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText)child).setTextColor(color);
                    numberPicker.invalidate();
                    return true;
                } catch(NoSuchFieldException e){
                    Log.e("@W@", e.getMessage());
                }
//                catch(IllegalAccessException e){
//                    Log.w("@W@", e.getMessage());
//                }
                catch(IllegalArgumentException e){
                    Log.w("@W@", e.getMessage());
                }
            }
        }
        return false;
    }

    public float getDistance() {
        LocationPoint buenosAiresObeliscoPoint =
                new LocationPoint((float) -34.6037389, (float) -58.3815704);

        LocationPoint nycStatueOfLibertyPoint =
                new LocationPoint((float) 40.6892494, (float) -74.0445004);

        float distanceBetweenPoints = LocationDistanceCalculator.calculateDistance(
                buenosAiresObeliscoPoint,
                nycStatueOfLibertyPoint);

        return distanceBetweenPoints;
    }
}
